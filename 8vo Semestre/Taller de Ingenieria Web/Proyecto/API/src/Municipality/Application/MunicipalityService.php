<?php

namespace Src\Municipality\Application;

use Src\Municipality\Domain\MunicipalityEntity;

class MunicipalityService
{
    private MunicipalityRepository $repository;

    public function __construct(MunicipalityRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(MunicipalityEntity $municipality)
    {
        return $this->repository->create($municipality);
    }

    public function update(MunicipalityEntity $municipality)
    {
        return $this->repository->update($municipality);
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
