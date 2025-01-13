<?php

namespace Src\Medal\Domain;

class MedalEntity
{
    private int $id;
    private string $name;
    private int $points_value;

    public function __construct(int $id, string $name, int $pointsValue)
    {
        $this->id = $id;
        $this->name = $name;
        $this->points_value = $pointsValue;
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
    public function getPointsValue(): int
    {
        return $this->points_value;
    }

    /**
     * @param int $points_value
     */
    public function setPointsValue(int $points_value): void
    {
        $this->points_value = $points_value;
    }

    /**
     * Convert the entity to an array representation.
     *
     * @return array
     */
    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'name' => $this->name,
            'points_value' => $this->points_value,
        ];
    }
}
