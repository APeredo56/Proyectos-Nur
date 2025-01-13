<?php

namespace Src\FieldTrip\Application;

use Src\FieldTrip\Domain\FieldTripEntity;

class FieldTripService
{
    private FieldTripRepository $repository;

    public function __construct(FieldTripRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(FieldTripEntity $fieldtrip)
    {
        return $this->repository->create($fieldtrip);
    }

    public function update(FieldTripEntity $fieldtrip)
    {
        return $this->repository->update($fieldtrip);
    }

    public function delete(int $id)
    {
        return $this->repository->delete($id);
    }

    public function getById(int $id)
    {
        return $this->repository->getById($id);
    }

    public function getAll()
    {
        return $this->repository->getAll();
    }

    public function getByUserId(int $userId)
    {
        return $this->repository->getByUserId($userId);
    }
}
