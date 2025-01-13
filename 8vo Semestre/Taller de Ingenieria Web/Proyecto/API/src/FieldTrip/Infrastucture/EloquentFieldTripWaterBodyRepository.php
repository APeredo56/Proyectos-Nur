<?php

namespace Src\FieldTrip\Infrastucture;

use App\Models\FieldTrip;
use Src\FieldTrip\Application\FieldTripWaterBodyRepository;

class EloquentFieldTripWaterBodyRepository implements FieldTripWaterBodyRepository
{

    public function assignWaterBodyToFieldTrip(int $waterBodyId, int $fieldTripId): bool
    {
        $fieldTrip = FieldTrip::find($fieldTripId);
        $fieldTrip->water_bodies()->attach($waterBodyId);
        return $fieldTrip->water_bodies()->exists($waterBodyId);
    }

    public function unAssignWaterBodyToFieldTrip(int $waterBodyId, int $fieldTripId): bool
    {
        $fieldTrip = FieldTrip::find($fieldTripId);
        return $fieldTrip->water_bodies()->detach($waterBodyId) > 0;
    }
}
