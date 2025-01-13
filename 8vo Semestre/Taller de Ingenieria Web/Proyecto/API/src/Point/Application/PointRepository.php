<?php

namespace Src\Point\Application;

use Src\Point\Domain\PointEntity;

interface PointRepository
{
    public function create(PointEntity $pointEntity): PointDTO;
    public function update(PointEntity $pointEntity): PointDTO;
    public function delete(int $id): bool;
    public function getById(int $id): PointDTO;
    public function getAll(): array;
    public function addPoints(int $user_id, int $points): void;
    public function subtractPoints(int $user_id, int $points): void;
    public function getByUserId(int $userId): PointDTO;
    public function getRanking(): array;
}
