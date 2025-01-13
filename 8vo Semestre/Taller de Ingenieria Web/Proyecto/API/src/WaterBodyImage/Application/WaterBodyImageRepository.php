<?php

namespace Src\WaterBodyImage\Application;


use Src\WaterBodyImage\Domain\WaterBodyImageEntity;

interface WaterBodyImageRepository
{
    public function create(WaterBodyImageEntity $waterBodyImageEntity): WaterBodyImageDTO;
    public function update(WaterBodyImageEntity $waterBodyImageEntity): WaterBodyImageDTO;
    public function delete(int $id): bool;
    public function getById(int $id): WaterBodyImageDTO;
    public function getAll(): array;
}
