<?php

namespace Src\Municipality\Application;

use Src\Department\Application\DepartmentDTO;

class MunicipalityDTO
{
    private int $id;
    private string $name;
    private int $department_id;
    private ?DepartmentDTO $department;
    private array $communities;

    public function __construct(int $id, string $name, int $departmentId)
    {
        $this->id = $id;
        $this->name = $name;
        $this->department_id = $departmentId;
        $this->department = null;
        $this->communities = [];
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getName(): string
    {
        return $this->name;
    }

    /**
     * @param string $name
     */
    public function setName(string $name): void
    {
        $this->name = $name;
    }

    /**
     * @return int
     */
    public function getDepartmentId(): int
    {
        return $this->department_id;
    }

    /**
     * @param int $department_id
     */
    public function setDepartmentId(int $department_id): void
    {
        $this->department_id = $department_id;
    }

    /**
     * @return DepartmentDTO
     */
    public function getDepartment(): DepartmentDTO
    {
        return $this->department;
    }

    /**
     * @param DepartmentDTO $department
     */
    public function setDepartment(DepartmentDTO $department): void
    {
        $this->department = $department;
    }

    /**
     * @return array
     */
    public function getCommunities(): array
    {
        return $this->communities;
    }

    /**
     * @param array $communities
     */
    public function setCommunities(array $communities): void
    {
        $this->communities = $communities;
    }

    /**
     * @return array
     */

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'name' => $this->name,
            'department_id' => $this->department_id,
            'department' => $this->department?->toArray(),
            'communities' => array_map(fn($community) => $community->toArray(), $this->communities)
        ];
    }
}
