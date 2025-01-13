<?php

namespace Src\Medal\Application;

use Src\Medal\Domain\MedalEntity;

interface MedalRepository
{
    public function create(MedalEntity $medalEntity): MedalDTO;
    public function update(MedalEntity $medalEntity): MedalDTO;
    public function delete(int $id): bool;
    public function getById(int $id): MedalDTO;
    public function getAll(): array;
}
