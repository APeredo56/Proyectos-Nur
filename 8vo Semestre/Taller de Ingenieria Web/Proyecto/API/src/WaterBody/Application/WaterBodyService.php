<?php

namespace Src\WaterBody\Application;

use Src\WaterBody\Domain\WaterBodyEntity;

class WaterBodyService
{
    private WaterBodyRepository $repository;

    public function __construct(WaterBodyRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(WaterBodyEntity $waterBody)
    {
        return $this->repository->create($waterBody);
    }

    public function update(WaterBodyEntity $waterBody)
    {
        return $this->repository->update($waterBody);
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
