<?php

namespace Src\FieldTrip\Infrastucture;

use App\Models\FieldTrip;
use Src\FieldTrip\Application\FieldTripDTO;
use Src\FieldTrip\Application\FieldTripRepository;
use Src\FieldTrip\Domain\FieldTripEntity;

class EloquentFieldTripRepository implements FieldTripRepository
{
    public function create(FieldTripEntity $fieldTripEntity): FieldTripDTO
    {
        $model = FieldTripMapper::toEloquent($fieldTripEntity);
        $model->save();
        return FieldTripMapper::toDTO($model);
    }

    public function update(FieldTripEntity $fieldTripEntity): FieldTripDTO
    {
        $model = FieldTrip::find($fieldTripEntity->getId());
        $model->update($fieldTripEntity->toArray());
        $model->load('water_bodies');
        return FieldTripMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = FieldTrip::find($id);
        return $model->delete();
    }

    public function getById(int $id): FieldTripDTO
    {
        $model = FieldTrip::with(['water_bodies'])->find($id);
        return FieldTripMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = FieldTrip::with(['water_bodies'])->get();
        $fieldTrips = [];
        foreach ($models as $model) {
            $fieldTrips[] = FieldTripMapper::toDTO($model);
        }
        return $fieldTrips;
    }

    public function getByUserId(int $userId): array
    {
        $models = FieldTrip::whereHas('users', function ($query) use ($userId) {
            $query->where('user_id', $userId);
        })->with(['water_bodies'])->get();
        $fieldTrips = [];
        foreach ($models as $model) {
            $fieldTrips[] = FieldTripMapper::toDTO($model);
        }
        return $fieldTrips;
    }
}
