<?php

namespace Src\WaterBodyImage\Domain;

class WaterBodyImageEntity
{
    private int $id;
    private string $image_url;
    private int $water_body_id;

    public function __construct(int $id, string $image_url, int $water_body_id)
    {
        $this->id = $id;
        $this->image_url = $image_url;
        $this->water_body_id = $water_body_id;
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
    public function getImageUrl(): string
    {
        return $this->image_url;
    }

    /**
     * @param string $image_url
     */
    public function setImageUrl(string $image_url): void
    {
        $this->image_url = $image_url;
    }

    /**
     * @return int
     */
    public function getWaterBodyId(): int
    {
        return $this->water_body_id;
    }

    /**
     * @param int $water_body_id
     */
    public function setWaterBodyId(int $water_body_id): void
    {
        $this->water_body_id = $water_body_id;
    }



    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'image_url' => $this->image_url,
            'water_body_id' => $this->water_body_id,
        ];
    }
}
