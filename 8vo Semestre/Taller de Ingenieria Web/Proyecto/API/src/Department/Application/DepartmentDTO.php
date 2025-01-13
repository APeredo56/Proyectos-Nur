<?php

namespace Src\Department\Application;

class DepartmentDTO
{
    private int $id;
    private string $name;
    private array $municipalities;

    /**
     * @param int $id
     * @param string $name
     */
    public function __construct(int $id, string $name)
    {
        $this->id = $id;
        $this->name = $name;
        $this->municipalities = [];
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
     * @return array
     */
    public function getMunicipalities(): array
    {
        return $this->municipalities;
    }

    /**
     * @param array $municipalities
     */
    public function setMunicipalities(array $municipalities): void
    {
        $this->municipalities = $municipalities;
    }

    /**
     * @return array
     */
    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'name' => $this->name,
            'municipalities' => array_map(fn($municipality) => $municipality->toArray(), $this->municipalities)
        ];
    }
}
