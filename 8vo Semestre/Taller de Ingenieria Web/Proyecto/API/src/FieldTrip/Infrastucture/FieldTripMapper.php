<?php

namespace Src\FieldTrip\Infrastucture;

use App\Models\FieldTrip;
use Carbon\Carbon;
use Src\FieldTrip\Application\FieldTripDTO;
use Src\FieldTrip\Domain\FieldTripEntity;
use Src\User\Infrastructure\UserMapper;
use Src\WaterBody\Infrastucture\WaterBodyMapper;

class FieldTripMapper
{
    public static function toEloquent(FieldTripEntity $fieldTripEntity): FieldTrip
    {
        $eloquentFieldTrip = new FieldTrip($fieldTripEntity->toArray());
        $eloquentFieldTrip->id = $fieldTripEntity->getId();
        return $eloquentFieldTrip;
    }

    public static function toDTO(FieldTrip $eloquentFieldTrip, $depth = 1)
    {
        $fieldTripDTO = new FieldTripDTO(
            $eloquentFieldTrip->id,
            $eloquentFieldTrip->description,
            Carbon::parse($eloquentFieldTrip->start_date),
            Carbon::parse($eloquentFieldTrip->end_date),
        );

        if ($eloquentFieldTrip->users && $depth > 0) {
            $users = [];
            foreach ($eloquentFieldTrip->users as $user) {
                $users[] = UserMapper::toDTO($user, $depth - 1);
            }
            $fieldTripDTO->setUsers($users);
        }

        if ($eloquentFieldTrip->water_bodies && $depth > 0) {
            $waterBodies = [];
            foreach ($eloquentFieldTrip->water_bodies as $water_body) {
                $waterBodies[] = WaterBodyMapper::toDTO($water_body, $depth - 1);
            }
            $fieldTripDTO->setWaterBodies($waterBodies);
        }

        return $fieldTripDTO;
    }
}
