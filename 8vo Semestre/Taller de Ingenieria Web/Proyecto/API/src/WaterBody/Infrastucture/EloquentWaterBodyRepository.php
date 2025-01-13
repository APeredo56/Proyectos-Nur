<?php

namespace Src\WaterBody\Infrastucture;

use App\Models\WaterBody;
use Src\WaterBody\Application\WaterBodyDTO;
use Src\WaterBody\Application\WaterBodyRepository;
use Src\WaterBody\Domain\WaterBodyEntity;

class EloquentWaterBodyRepository implements WaterBodyRepository
{
    public function create(WaterBodyEntity $waterBodyEntity): WaterBodyDTO
    {
        $model = WaterBodyMapper::toEloquent($waterBodyEntity);
        $model->save();
        return WaterBodyMapper::toDTO($model);
    }

    public function update(WaterBodyEntity $waterBodyEntity): WaterBodyDTO
    {
        $model = WaterBody::find($waterBodyEntity->getId());
        $model->update($waterBodyEntity->toArray());
        $model->load('community');
        $model->load('water_body_images');
        return WaterBodyMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = WaterBody::find($id);
        return $model->delete();
    }

    public function getById(int $id): WaterBodyDTO
    {
        $model = WaterBody::with(['community', 'water_body_images'])->find($id);
        return WaterBodyMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = WaterBody::with(['community', 'water_body_images'])->get();
        $waterBodies = [];
        foreach ($models as $model) {
            $waterBodies[] = WaterBodyMapper::toDTO($model);
        }
        return $waterBodies;
    }
}
