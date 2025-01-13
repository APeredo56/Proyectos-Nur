<?php

namespace Src\Department\Application;

use Src\Department\Domain\DepartmentEntity;

class DepartmentService
{
    private DepartmentRepository $repository;

    public function __construct(DepartmentRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(DepartmentEntity $department)
    {
        return $this->repository->create($department);
    }

    public function update(DepartmentEntity $department)
    {
        return $this->repository->update($department);
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
