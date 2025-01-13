<?php

namespace Src\Medal\Infrastructure;

use App\Models\Medal;
use Src\Medal\Application\MedalDTO;
use Src\Medal\Domain\MedalEntity;

class MedalMapper
{
    /**
     * Convert a MedalEntity to an Eloquent Medal model.
     *
     * @param MedalEntity $medalEntity
     * @return Medal
     */
    public static function toEloquent(MedalEntity $medalEntity): Medal
    {
        $eloquentMedal = new Medal($medalEntity->toArray());
        $eloquentMedal->id = $medalEntity->getId();
        return $eloquentMedal;
    }

    /**
     * Convert an Eloquent Medal model to a MedalDTO.
     *
     * @param Medal $eloquentMedal
     * @return MedalDTO
     */
    public static function toDTO(Medal $eloquentMedal): MedalDTO
    {
        return new MedalDTO(
            $eloquentMedal->id,
            $eloquentMedal->name,
            $eloquentMedal->points_value
        );
    }

    /**
     * Convert an Eloquent Medal model to a MedalEntity.
     *
     * @param Medal $eloquentMedal
     * @return MedalEntity
     */
    public static function toEntity(Medal $eloquentMedal): MedalEntity
    {
        return new MedalEntity(
            $eloquentMedal->id,
            $eloquentMedal->name,
            $eloquentMedal->points_value
        );
    }
}
