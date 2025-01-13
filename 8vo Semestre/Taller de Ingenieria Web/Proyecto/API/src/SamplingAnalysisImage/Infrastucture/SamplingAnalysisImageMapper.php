<?php

namespace Src\SamplingAnalysisImage\Infrastucture;

use App\Models\SamplingAnalysisImage;
use Src\SamplingAnalysis\Infrastucture\SamplingAnalysisMapper;
use Src\SamplingAnalysisImage\Application\SamplingAnalysisImageDTO;
use Src\SamplingAnalysisImage\Domain\SamplingAnalysisImageEntity;

class SamplingAnalysisImageMapper
{
    public static function toEloquent(SamplingAnalysisImageEntity $samplingAnalysisImage): SamplingAnalysisImage
    {
        $eloquentSamplingAnalysisImage = new SamplingAnalysisImage($samplingAnalysisImage->toArray());
        $eloquentSamplingAnalysisImage->id = $samplingAnalysisImage->getId();
        return $eloquentSamplingAnalysisImage;
    }

    public static function toDTO(SamplingAnalysisImage $eloquentSamplingAnalysisImage, $depth = 1)
    {
        $sampligAnalysisDTO = new SamplingAnalysisImageDTO(
            $eloquentSamplingAnalysisImage->id,
            $eloquentSamplingAnalysisImage->image_url,
            $eloquentSamplingAnalysisImage->sampling_analysis_id
        );
        if ($eloquentSamplingAnalysisImage->sampling_analysis && $depth > 0) {
            $sampligAnalysisDTO->setSamplinganalysis(SamplingAnalysisMapper::toDTO($eloquentSamplingAnalysisImage->sampling_analysis, $depth - 1));
        }

        return $sampligAnalysisDTO;
    }
}
