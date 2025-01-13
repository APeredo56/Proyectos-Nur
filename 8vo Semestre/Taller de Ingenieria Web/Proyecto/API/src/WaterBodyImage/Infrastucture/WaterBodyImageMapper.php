<?php

namespace Src\WaterBodyImage\Infrastucture;

use App\Models\WaterBodyImage;
use Src\WaterBody\Infrastucture\WaterBodyMapper;
use Src\WaterBodyImage\Application\WaterBodyImageDTO;
use Src\WaterBodyImage\Domain\WaterBodyImageEntity;

class WaterBodyImageMapper
{
    public static function toEloquent(WaterBodyImageEntity $waterBodyImageEntity): WaterBodyImage
    {
        $eloquentWaterBodyImage = new WaterBodyImage($waterBodyImageEntity->toArray());
        $eloquentWaterBodyImage->id = $waterBodyImageEntity->getId();
        return $eloquentWaterBodyImage;
    }

    public static function toDTO(WaterBodyImage $eloquentWaterBodyImage, $depth = 1)
    {
        $waterBodyImageDTO = new WaterBodyImageDTO(
            $eloquentWaterBodyImage->id,
            $eloquentWaterBodyImage->image_url,
            $eloquentWaterBodyImage->water_body_id
        );
        if ($eloquentWaterBodyImage->water_body && $depth > 0) {
            $waterBodyImageDTO->setWaterBody(WaterBodyMapper::toDTO($eloquentWaterBodyImage->water_body, $depth - 1));
        }

        return $waterBodyImageDTO;
    }
}
