<?php

namespace Src\Municipality\Application;

use Src\Municipality\Domain\MunicipalityEntity;

interface MunicipalityRepository
{
    public function create(MunicipalityEntity $municipalityEntity): MunicipalityDTO;
    public function update(MunicipalityEntity $municipalityEntity): MunicipalityDTO;
    public function delete(int $id): bool;
    public function getById(int $id): MunicipalityDTO;
    public function getAll(): array;
}
