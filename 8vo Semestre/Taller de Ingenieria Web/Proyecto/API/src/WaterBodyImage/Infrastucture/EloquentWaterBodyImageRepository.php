<?php

namespace Src\WaterBodyImage\Infrastucture;

use App\Models\WaterBodyImage;
use Src\WaterBodyImage\Application\WaterBodyImageDTO;
use Src\WaterBodyImage\Application\WaterBodyImageRepository;
use Src\WaterBodyImage\Domain\WaterBodyImageEntity;

class EloquentWaterBodyImageRepository implements WaterBodyImageRepository
{
    public function create(WaterBodyImageEntity $waterBodyImageEntity): WaterBodyImageDTO
    {
        $model = WaterBodyImageMapper::toEloquent($waterBodyImageEntity);
        $model->save();
        return WaterBodyImageMapper::toDTO($model);
    }

    public function update(WaterBodyImageEntity $waterBodyImageEntity): WaterBodyImageDTO
    {
        $model = WaterBodyImage::find($waterBodyImageEntity->getId());
        $model->update($waterBodyImageEntity->toArray());
        $model->load('water_body');
        return WaterBodyImageMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = WaterBodyImage::find($id);
        return $model->delete();
    }

    public function getById(int $id): WaterBodyImageDTO
    {
        $model = WaterBodyImage::with(['water_body'])->find($id);
        return WaterBodyImageMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = WaterBodyImage::with(['water_body'])->get();
        $waterBodyImages = [];
        foreach ($models as $model) {
            $waterBodyImages[] = WaterBodyImageMapper::toDTO($model);
        }
        return $waterBodyImages;
    }
}
