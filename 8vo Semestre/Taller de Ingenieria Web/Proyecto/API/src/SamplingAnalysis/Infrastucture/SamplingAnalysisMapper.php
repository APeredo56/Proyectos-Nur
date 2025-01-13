<?php

namespace Src\SamplingAnalysis\Infrastucture;

use App\Models\SamplingAnalysis;
use Src\FieldTrip\Infrastucture\FieldTripMapper;
use Src\SamplingAnalysis\Application\SamplingAnalysisDTO;
use Src\SamplingAnalysis\Domain\SamplingAnalysisEntity;
use Src\SamplingAnalysisImage\Infrastucture\SamplingAnalysisImageMapper;
use Src\User\Infrastructure\UserMapper;
use Src\WaterBody\Infrastucture\WaterBodyMapper;

class SamplingAnalysisMapper
{
    public static function toEloquent(SamplingAnalysisEntity $samplingAnalysisEntity): SamplingAnalysis
    {
        $eloquentSamplingAnalysis = new SamplingAnalysis($samplingAnalysisEntity->toArray());
        $eloquentSamplingAnalysis->id = $samplingAnalysisEntity->getId();
        return $eloquentSamplingAnalysis;
    }

    public static function toDTO(SamplingAnalysis $eloquentSamplingAnalysis, $depth = 1)
    {
        $samplingAnalysisDTO = new SamplingAnalysisDTO(
            $eloquentSamplingAnalysis->id,
            $eloquentSamplingAnalysis->turbidity,
            $eloquentSamplingAnalysis->water_velocity,
            $eloquentSamplingAnalysis->width,
            $eloquentSamplingAnalysis->average_depth,
            $eloquentSamplingAnalysis->flow_rate,
            $eloquentSamplingAnalysis->water_body_id,
            $eloquentSamplingAnalysis->user_id,
            $eloquentSamplingAnalysis->field_trip_id
        );
        if ($eloquentSamplingAnalysis->water_body && $depth > 0) {
            $samplingAnalysisDTO->setWaterBody(WaterBodyMapper::toDTO($eloquentSamplingAnalysis->water_body, $depth - 1));
        }
        if ($eloquentSamplingAnalysis->user && $depth > 0) {
            $samplingAnalysisDTO->setUser(UserMapper::toDTO($eloquentSamplingAnalysis->user, $depth - 1));
        }
        if ($eloquentSamplingAnalysis->field_trip && $depth > 0) {
            $samplingAnalysisDTO->setFieldTrip(FieldTripMapper::toDTO($eloquentSamplingAnalysis->field_trip, $depth));
        }
        if ($eloquentSamplingAnalysis->sampling_analysis_images && $depth > 0) {
            $samplingImages = [];
            foreach ($eloquentSamplingAnalysis->sampling_analysis_images as $image) {
                $samplingImages[] = SamplingAnalysisImageMapper::toDTO($image, $depth - 1);
            }
            $samplingAnalysisDTO->setSamplingAnalysisImages($samplingImages);
        }

        return $samplingAnalysisDTO;
    }
}
