<?php

namespace Src\FieldTrip\Application;

interface FieldTripUserRepository
{
    public function assignUserToFieldTrip(int $fieldTripId, int $userId) : bool;
    public function unAssignUserToFieldTrip(int $fieldTripId, int $userId) : bool;
}
