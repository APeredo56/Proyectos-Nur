<?php

namespace Src\WaterBody\Infrastucture;

use App\Models\WaterBody;
use Src\Community\Infrastucture\CommunityMapper;
use Src\WaterBody\Application\WaterBodyDTO;
use Src\WaterBody\Domain\WaterBodyEntity;
use Src\WaterBody\Domain\WaterBodyType;
use Src\WaterBodyImage\Infrastucture\WaterBodyImageMapper;

class WaterBodyMapper
{
    public static function toEloquent(WaterBodyEntity $waterBodyEntity): WaterBody
    {
        $eloquentWaterBody = new WaterBody($waterBodyEntity->toArray());
        $eloquentWaterBody->id = $waterBodyEntity->getId();
        return $eloquentWaterBody;
    }

    public static function toDTO(WaterBody $eloquentWaterBody, $depth = 1)
    {
        $waterBodyDTO = new WaterBodyDTO(
            $eloquentWaterBody->id,
            $eloquentWaterBody->name,
            WaterBodyType::from($eloquentWaterBody->water_body_type),
            $eloquentWaterBody->latitude,
            $eloquentWaterBody->longitude,
            $eloquentWaterBody->community_id,
        );
        if ($eloquentWaterBody->community && $depth > 0) {
            $waterBodyDTO->setCommunity(CommunityMapper::toDTO($eloquentWaterBody->community, $depth - 1));
        }
        if ($eloquentWaterBody->water_body_images() && $depth >= 0) {
            $water_body_images = [];
            foreach ($eloquentWaterBody->water_body_images as $water_body_image) {
                $water_body_images[] = WaterBodyImageMapper::toDTO($water_body_image, $depth - 1);
            }
            $waterBodyDTO->setWaterBodyImages($water_body_images);
        }

        return $waterBodyDTO;
    }
}
