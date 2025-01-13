<?php

namespace Src\FieldTrip\Application;

class FieldTripUserService
{
    private FieldTripUserRepository $repository;

    public function __construct(FieldTripUserRepository $repository)
    {
        $this->repository = $repository;
    }

    public function assignUserToFieldTrip(int $userId, int $fieldTripId) : bool
    {
        return $this->repository->assignUserToFieldTrip($userId, $fieldTripId);
    }

    public function unAssignUserToFieldTrip(int $userId, int $fieldTripId) : bool
    {
        return $this->repository->unAssignUserToFieldTrip($userId, $fieldTripId);
    }
}
