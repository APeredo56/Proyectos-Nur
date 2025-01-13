<?php

namespace Src\Point\Infrastucture;

use App\Models\Point;
use Src\Point\Application\PointDTO;
use Src\Point\Application\PointRepository;
use Src\Point\Domain\PointEntity;

class EloquentPointRepository implements PointRepository
{
    public function create(PointEntity $pointEntity): PointDTO
    {
        $model = PointMapper::toEloquent($pointEntity);
        $model->save();
        return PointMapper::toDTO($model);
    }

    public function update(PointEntity $pointEntity): PointDTO
    {
        $model = Point::find($pointEntity->getId());
        $model->update($pointEntity->toArray());
        $model->load('user');
        return PointMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = Point::find($id);
        return $model->delete();
    }

    public function getById(int $id): PointDTO
    {
        $model = Point::with(['user'])->find($id);
        return PointMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = Point::with(['user'])->get();
        $points = [];
        foreach ($models as $model) {
            $points[] = PointMapper::toDTO($model);
        }
        return $points;
    }

    public function addPoints(int $user_id, int $points): void
    {
        $model = Point::where('user_id', $user_id)->first();
        $model->update(['total_points' => $model->total_points + $points]);
    }

    public function subtractPoints(int $user_id, int $points): void
    {
        $model = Point::where('user_id', $user_id)->first();
        $model->update(['total_points' => $model->total_points - $points]);
    }

    public function getByUserId(int $userId): PointDTO
    {
        $model = Point::where('user_id', $userId)->first();
        return PointMapper::toDTO($model);
    }

    public function getRanking(): array
    {
        $models = Point::orderBy('total_points', 'desc')->get();
        $points = [];
        foreach ($models as $model) {
            $points[] = PointMapper::toDTO($model)->toArray();
        }
        return $points;
    }
}
