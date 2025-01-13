<?php

namespace Src\Point\Infrastucture;

use App\Models\Point;
use Src\Point\Application\PointDTO;
use Src\Point\Domain\PointEntity;
use Src\User\Infrastructure\UserMapper;

class PointMapper
{
    public static function toEloquent(PointEntity $pointEntity): Point
    {
        $eloquentPoint = new Point($pointEntity->toArray());
        $eloquentPoint->id = $pointEntity->getId();
        return $eloquentPoint;
    }

    public static function toDTO(Point $eloquentPoint, $depth = 1)
    {
        $pointDTO = new PointDTO(
            $eloquentPoint->id,
            $eloquentPoint->user_id,
            $eloquentPoint->total_points,
            $eloquentPoint->level,
        );
        if ($eloquentPoint->user && $depth > 0) {
            $pointDTO->setUser(UserMapper::toDTO($eloquentPoint->user, $depth - 1));
        }

        return $pointDTO;
    }
}
