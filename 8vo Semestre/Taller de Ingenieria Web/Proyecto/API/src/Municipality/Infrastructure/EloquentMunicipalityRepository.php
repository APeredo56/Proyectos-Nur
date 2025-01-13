<?php

namespace Src\Municipality\Infrastructure;

use App\Models\Municipality;
use Src\Municipality\Application\MunicipalityDTO;
use Src\Municipality\Application\MunicipalityRepository;
use Src\Municipality\Domain\MunicipalityEntity;

class EloquentMunicipalityRepository implements MunicipalityRepository
{

    public function create(MunicipalityEntity $municipalityEntity): MunicipalityDTO
    {
        $model = MunicipalityMapper::toEloquent($municipalityEntity);
        $model->save();
        return MunicipalityMapper::toDTO($model);
    }

    public function update(MunicipalityEntity $municipalityEntity): MunicipalityDTO
    {
        $model = Municipality::find($municipalityEntity->getId());
        $model->update($municipalityEntity->toArray());
        $model->load('department');
        $model->load('communities');
        return MunicipalityMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = Municipality::find($id);
        return $model->delete();
    }

    public function getById(int $id): MunicipalityDTO
    {
        $model = Municipality::with(['department', 'communities'])->find($id);
        return MunicipalityMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = Municipality::with(['department', 'communities'])->get();
        $municipalities = [];
        foreach ($models as $model) {
            $municipalities[] = MunicipalityMapper::toDTO($model);
        }
        return $municipalities;
    }
}
