<?php

namespace Src\FieldTrip\Application;

class FieldTripWaterBodyService
{
    private FieldTripWaterBodyRepository $repository;

    public function __construct(FieldTripWaterBodyRepository $repository)
    {
        $this->repository = $repository;
    }

    public function assignWaterBodyToFieldTrip(int $waterBodyId, int $fieldTripId) : bool
    {
        return $this->repository->assignWaterBodyToFieldTrip($waterBodyId, $fieldTripId);
    }

    public function unAssignWaterBodyToFieldTrip(int $waterBodyId, int $fieldTripId) : bool
    {
        return $this->repository->unAssignWaterBodyToFieldTrip($waterBodyId, $fieldTripId);
    }
}
