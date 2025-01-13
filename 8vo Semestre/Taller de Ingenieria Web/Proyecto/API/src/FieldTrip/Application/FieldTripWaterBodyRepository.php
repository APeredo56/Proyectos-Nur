<?php

namespace Src\FieldTrip\Application;

interface FieldTripWaterBodyRepository
{
    public function assignWaterBodyToFieldTrip(int $waterBodyId, int $fieldTripId) : bool;
    public function unAssignWaterBodyToFieldTrip(int $waterBodyId, int $fieldTripId) : bool;
}
