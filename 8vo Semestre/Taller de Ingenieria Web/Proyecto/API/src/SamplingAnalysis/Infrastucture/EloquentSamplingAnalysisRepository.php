<?php

namespace Src\SamplingAnalysis\Infrastucture;

use App\Models\SamplingAnalysis;
use Src\SamplingAnalysis\Application\SamplingAnalysisDTO;
use Src\SamplingAnalysis\Application\SamplingAnalysisRepository;
use Src\SamplingAnalysis\Domain\SamplingAnalysisEntity;

class EloquentSamplingAnalysisRepository implements SamplingAnalysisRepository
{
    public function create(SamplingAnalysisEntity $samplingAnalysisEntity): SamplingAnalysisDTO
    {
        $model = SamplingAnalysisMapper::toEloquent($samplingAnalysisEntity);
        $model->save();
        return SamplingAnalysisMapper::toDTO($model);
    }

    public function update(SamplingAnalysisEntity $samplingAnalysisEntity): SamplingAnalysisDTO
    {
        $model = SamplingAnalysis::find($samplingAnalysisEntity->getId());
        $model->update($samplingAnalysisEntity->toArray());
        $model->load('user');
        $model->load('water_body');
        $model->load('sampling_analysis_images');
        $model->load('field_trip');
        return SamplingAnalysisMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = SamplingAnalysis::find($id);
        return $model->delete();
    }

    public function getById(int $id): SamplingAnalysisDTO
    {
        $model = SamplingAnalysis::with(['user', 'water_body', 'sampling_analysis_images', 'field_trip'])->find($id);
        return SamplingAnalysisMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = SamplingAnalysis::with(['user', 'water_body', 'sampling_analysis_images', 'field_trip'])->get();
        $samplings = [];
        foreach ($models as $model) {
            $samplings[] = SamplingAnalysisMapper::toDTO($model);
        }
        return $samplings;
    }

    public function getByFieldTripIdAndUserId(int $fieldTripId, int $userId): array
    {
        $models = SamplingAnalysis::where('field_trip_id', $fieldTripId)
            ->where('user_id', $userId)
            ->with(['user', 'water_body', 'sampling_analysis_images', 'field_trip'])
            ->get();
        $samplings = [];
        foreach ($models as $model) {
            $samplings[] = SamplingAnalysisMapper::toDTO($model);
        }
        return $samplings;
    }
}
