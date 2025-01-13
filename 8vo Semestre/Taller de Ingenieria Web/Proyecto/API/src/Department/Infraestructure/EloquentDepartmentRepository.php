<?php

namespace Src\Department\Infraestructure;

use App\Models\Department;
use Src\Department\Application\DepartmentDTO;
use Src\Department\Application\DepartmentRepository;
use Src\Department\Domain\DepartmentEntity;

class EloquentDepartmentRepository implements DepartmentRepository
{
    public function create(DepartmentEntity $department): DepartmentDTO
    {
        $model = DepartmentMapper::toEloquent($department);
        $model->save();
        return DepartmentMapper::toDTO($model);
    }

    public function update(DepartmentEntity $department): DepartmentDTO
    {
        $model = Department::find($department->getId());
        $model->update($department->toArray());
        $model->load('municipalities');
        return DepartmentMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = Department::find($id);
        return $model->delete();
    }

    public function getById(int $id): DepartmentDTO
    {
        $model = Department::with('municipalities')->find($id);
        return DepartmentMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = Department::with('municipalities.communities')->get();
        $departments = [];
        foreach ($models as $model) {
            $departments[] = DepartmentMapper::toDTO($model);
        }
        return $departments;
    }
}
