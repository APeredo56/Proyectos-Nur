<?php

namespace Src\FieldTrip\Infrastucture;

use App\Models\FieldTrip;
use Src\FieldTrip\Application\FieldTripUserRepository;

class EloquentFieldTripUserRepository implements FieldTripUserRepository
{

    public function assignUserToFieldTrip(int $fieldTripId, int $userId): bool
    {
        $fieldTrip = FieldTrip::find($fieldTripId);
        $fieldTrip->users()->attach($userId);
        return $fieldTrip->users()->exists($userId);
    }

    public function unAssignUserToFieldTrip(int $fieldTripId, int $userId): bool
    {
        $fieldTrip = FieldTrip::find($fieldTripId);
        return $fieldTrip->users()->detach($userId) > 0;
    }
}
