<?php

namespace Src\Medal\Application;

use Src\Medal\Domain\MedalEntity;

class MedalService
{
    private MedalRepository $repository;

    public function __construct(MedalRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(MedalEntity $medal)
    {
        return $this->repository->create($medal);
    }

    public function update(MedalEntity $medal)
    {
        return $this->repository->update($medal);
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
}
