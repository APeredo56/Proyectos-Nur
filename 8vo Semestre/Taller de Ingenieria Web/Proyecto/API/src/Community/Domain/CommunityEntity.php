<?php

namespace Src\Community\Domain;

class CommunityEntity
{
    private int $id;
    private string $name;
    private int $municipality_id;

    public function __construct(int $id, string $name, int $municipality_id)
    {
        $this->id = $id;
        $this->name = $name;
        $this->municipality_id = $municipality_id;
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
     * @return array
     */

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'name' => $this->name,
            'municipality_id' => $this->municipality_id,
        ];
    }
}
