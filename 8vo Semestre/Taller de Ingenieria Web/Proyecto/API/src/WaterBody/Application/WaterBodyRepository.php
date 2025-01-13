<?php

namespace Src\WaterBody\Application;

use Src\WaterBody\Domain\WaterBodyEntity;

interface WaterBodyRepository
{
    public function create(WaterBodyEntity $waterBodyEntity): WaterBodyDTO;
    public function update(WaterBodyEntity $waterBodyEntity): WaterBodyDTO;
    public function delete(int $id): bool;
    public function getById(int $id): WaterBodyDTO;
    public function getAll(): array;
}
