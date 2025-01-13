<?php

namespace Src\Department\Application;

use Src\Department\Domain\DepartmentEntity;

interface DepartmentRepository
{
    public function create(DepartmentEntity $department): DepartmentDTO;
    public function update(DepartmentEntity $department): DepartmentDTO;
    public function delete(int $id): bool;
    public function getById(int $id): DepartmentDTO;
    public function getAll(): array;
}
