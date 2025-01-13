<?php

namespace Src\Medal\Infrastructure;

use App\Models\Medal;
use Src\Medal\Application\MedalDTO;
use Src\Medal\Application\MedalRepository;
use Src\Medal\Domain\MedalEntity;

class EloquentMedalRepository implements MedalRepository
{
    /**
     * Create a new Medal and return its DTO.
     *
     * @param MedalEntity $medalEntity
     * @return MedalDTO
     */
    public function create(MedalEntity $medalEntity): MedalDTO
    {
        $model = MedalMapper::toEloquent($medalEntity);
        $model->save();
        return MedalMapper::toDTO($model);
    }

    /**
     * Update an existing Medal and return its DTO.
     *
     * @param MedalEntity $medalEntity
     * @return MedalDTO
     */
    public function update(MedalEntity $medalEntity): MedalDTO
    {
        $model = Medal::find($medalEntity->getId());
        $model->update($medalEntity->toArray());
        return MedalMapper::toDTO($model);
    }

    /**
     * Delete a Medal by its ID.
     *
     * @param int $id
     * @return bool
     */
    public function delete(int $id): bool
    {
        $model = Medal::find($id);
        return $model->delete();
    }

    /**
     * Get a Medal by its ID.
     *
     * @param int $id
     * @return MedalDTO
     */
    public function getById(int $id): MedalDTO
    {
        $model = Medal::find($id);
        return MedalMapper::toDTO($model);
    }

    /**
     * Get all Medals.
     *
     * @return array
     */
    public function getAll(): array
    {
        $models = Medal::all();
        $medals = [];
        foreach ($models as $model) {
            $medals[] = MedalMapper::toDTO($model);
        }
        return $medals;
    }
}
