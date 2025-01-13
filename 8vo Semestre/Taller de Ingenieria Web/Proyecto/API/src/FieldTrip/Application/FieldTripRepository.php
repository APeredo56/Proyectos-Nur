<?php

namespace Src\FieldTrip\Application;


use Src\FieldTrip\Domain\FieldTripEntity;

interface FieldTripRepository
{
    public function create(FieldTripEntity $fieldTripEntity): FieldTripDTO;
    public function update(FieldTripEntity $fieldTripEntity): FieldTripDTO;
    public function delete(int $id): bool;
    public function getById(int $id): FieldTripDTO;
    public function getAll(): array;
    public function getByUserId(int $userId): array;
}
