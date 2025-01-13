<?php

namespace Src\SamplingAnalysisImage\Infrastucture;

use App\Models\SamplingAnalysisImage;
use Src\SamplingAnalysisImage\Application\SamplingAnalysisImageDTO;
use Src\SamplingAnalysisImage\Application\SamplingAnalysisImageRepository;
use Src\SamplingAnalysisImage\Domain\SamplingAnalysisImageEntity;

class EloquentSamplingAnalysisImageRepository implements SamplingAnalysisImageRepository
{
    public function create(SamplingAnalysisImageEntity $samplingAnalysisImageEntity): SamplingAnalysisImageDTO
    {
        $model = SamplingAnalysisImageMapper::toEloquent($samplingAnalysisImageEntity);
        $model->save();
        $model->load('sampling_analysis');
        return SamplingAnalysisImageMapper::toDTO($model);
    }

    public function update(SamplingAnalysisImageEntity $samplingAnalysisImageEntity): SamplingAnalysisImageDTO
    {
        $model = SamplingAnalysisImage::find($samplingAnalysisImageEntity->getId());
        $model->update($samplingAnalysisImageEntity->toArray());
        $model->load('sampling_analysis');
        return SamplingAnalysisImageMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = SamplingAnalysisImage::find($id);
        return $model->delete();
    }

    public function getById(int $id): SamplingAnalysisImageDTO
    {
        $model = SamplingAnalysisImage::find($id);
        $model->load('sampling_analysis');
        return SamplingAnalysisImageMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = SamplingAnalysisImage::all();
        $samplingAnalysisImages = [];
        foreach ($models as $model) {
            $samplingAnalysisImages[] = SamplingAnalysisImageMapper::toDTO($model);
        }
        return $samplingAnalysisImages;
    }
}
