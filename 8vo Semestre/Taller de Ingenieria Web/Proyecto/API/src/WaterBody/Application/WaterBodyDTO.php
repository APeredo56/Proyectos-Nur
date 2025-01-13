<?php

namespace Src\WaterBody\Application;

use Src\Community\Application\CommunityDTO;
use Src\WaterBody\Domain\WaterBodyType;

class WaterBodyDTO
{
    private int $id;
    private string $name;
    private WaterBodyType $water_body_type;
    private float $latitude;
    private float $longitude;
    private int $community_id;
    private ?CommunityDTO $community;
    private array $water_body_images;

    public function __construct(int $id, string $name, WaterBodyType $waterBodyType, float $latitude, float $longitude, int $community_id)
    {
        $this->id = $id;
        $this->name = $name;
        $this->water_body_type = $waterBodyType;
        $this->latitude = $latitude;
        $this->longitude = $longitude;
        $this->community_id = $community_id;
        $this->community = null;
        $this->water_body_images = [];
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
    public function getWaterBodyType(): WaterBodyType
    {
        return $this->water_body_type;
    }

    /**
     * @param WaterBodyType $water_body_type
     */
    public function setWaterBodyType(WaterBodyType $water_body_type): void
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
     * @return CommunityDTO|null
     */
    public function getCommunity(): ?CommunityDTO
    {
        return $this->community;
    }

    /**
     * @param CommunityDTO|null $community
     */
    public function setCommunity(?CommunityDTO $community): void
    {
        $this->community = $community;
    }

    /**
     * @return array
     */
    public function getWaterBodyImages(): array
    {
        return $this->water_body_images;
    }

    /**
     * @param array $water_body_images
     */
    public function setWaterBodyImages(array $water_body_images): void
    {
        $this->water_body_images = $water_body_images;
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
            'community' => $this->community?->toArray(),
            'water_body_images' => array_map(fn($image) => $image->toArray(), $this->water_body_images)
        ];
    }
}
