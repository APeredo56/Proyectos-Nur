<?php

namespace Src\WaterBody\Domain;

class WaterBodyEntity
{
    private int $id;
    private string $name;
    private WaterBodyType $water_body_type;
    private float $latitude;
    private float $longitude;
    private int $community_id;



    public function __construct(int $id, string $name, WaterBodyType $waterBodyType, float $latitude, float $longitude, int $community_id)
    {
        $this->id = $id;
        $this->name = $name;
        $this->water_body_type = $waterBodyType;
        $this->latitude = $latitude;
        $this->longitude = $longitude;
        $this->community_id = $community_id;
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
     * @return WaterBodyType
     */
    public function getWaterBodytype(): WaterBodyType
    {
        return $this->water_body_type;
    }

    /**
     * @param WaterBodyType $water_body_type
     */
    public function setWaterBodytype(WaterBodyType $water_body_type): void
    {
        $this->water_body_type = $water_body_type;
    }

    /**
     * @return float
     */
    public function getLatitude(): float
    {
        return $this->latitude;
    }

    /**
     * @param float $latitude
     */
    public function setLatitude(float $latitude): void
    {
        $this->latitude = $latitude;
    }

    /**
     * @return float
     */
    public function getLongitude(): float
    {
        return $this->longitude;
    }

    /**
     * @param float $longitude
     */
    public function setLongitude(float $longitude): void
    {
        $this->longitude = $longitude;
    }

    /**
     * @return int
     */
    public function getCommunityId(): int
    {
        return $this->community_id;
    }

    /**
     * @param int $community_id
     */
    public function setCommunityId(int $community_id): void
    {
        $this->community_id = $community_id;
    }

    /**
     * @return array
     */

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'name' => $this->name,
            'water_body_type' => $this->water_body_type->value,
            'latitude' => $this->latitude,
            'longitude' => $this->longitude,
            'community_id' => $this->community_id,
        ];
    }
}
