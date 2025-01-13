<?php

namespace Src\Community\Application;


use Src\Municipality\Application\MunicipalityDTO;

class CommunityDTO
{
    private int $id;
    private string $name;
    private int $municipality_id;
    private ?MunicipalityDTO $municipality;
    private array $waterBodies;

    public function __construct(int $id, string $name, int $departmentId)
    {
        $this->id = $id;
        $this->name = $name;
        $this->municipality_id = $departmentId;
        $this->municipality = null;
        $this->waterBodies = [];
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
    public function getMunicipalityId(): int
    {
        return $this->municipality_id;
    }

    /**
     * @param int $municipality_id
     */
    public function setMunicipalityId(int $municipality_id): void
    {
        $this->municipality_id = $municipality_id;
    }

    /**
     * @return MunicipalityDTO
     */
    public function getMunicipality(): MunicipalityDTO
    {
        return $this->municipality;
    }

    /**
     * @param MunicipalityDTO $municipality
     */
    public function setMunicipality(MunicipalityDTO $municipality): void
    {
        $this->municipality = $municipality;
    }

    /**
     * @return array
     */
    public function getWaterBodies(): array
    {
        return $this->waterBodies;
    }

    /**
     * @param array $waterBodies
     */
    public function setWaterBodies(array $waterBodies): void
    {
        $this->waterBodies = $waterBodies;
    }

    /**
     * @return array
     */

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'name' => $this->name,
            'municipality_id' => $this->municipality_id,
            'municipality' => $this->municipality?->toArray(),
            'water_bodies' => array_map(fn($waterBody) => $waterBody->toArray(), $this->waterBodies)
        ];
    }
}
