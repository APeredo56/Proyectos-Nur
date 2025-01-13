<?php

namespace Src\SamplingAnalysisImage\Application;


use Src\SamplingAnalysisImage\Domain\SamplingAnalysisImageEntity;

interface SamplingAnalysisImageRepository
{
    public function create(SamplingAnalysisImageEntity $samplingAnalysisImageEntity): SamplingAnalysisImageDTO;
    public function update(SamplingAnalysisImageEntity $samplingAnalysisImageEntity): SamplingAnalysisImageDTO;
    public function delete(int $id): bool;
    public function getById(int $id): SamplingAnalysisImageDTO;
    public function getAll(): array;
}
