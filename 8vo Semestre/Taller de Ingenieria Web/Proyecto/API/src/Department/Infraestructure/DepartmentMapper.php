<?php

namespace Src\Department\Infraestructure;

use App\Models\Department;
use Src\Department\Application\DepartmentDTO;
use Src\Department\Domain\DepartmentEntity;
use Src\Municipality\Infrastructure\MunicipalityMapper;

class DepartmentMapper
{
    public static function toEloquent(DepartmentEntity $departmentEntity): Department
    {
        $eloquentDepartment = new Department($departmentEntity->toArray());
        $eloquentDepartment->id = $departmentEntity->getId();
        return $eloquentDepartment;
    }

    public static function toDTO(Department $eloquentDepartment, $depth = 1)
    {
        $departmentDTO = new DepartmentDTO(
            $eloquentDepartment->id,
            $eloquentDepartment->name,
        );
        if ($eloquentDepartment->municipalities  && $depth > 0) {
            $municipalities = [];
            foreach ($eloquentDepartment->municipalities as $municipality) {
                $municipalities[] = MunicipalityMapper::toDTO($municipality, $depth - 1);
            }
            $departmentDTO->setMunicipalities($municipalities);
        }
        return $departmentDTO;
    }
}
