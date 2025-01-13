<?php

namespace Src\SamplingAnalysis\Application;

use Src\SamplingAnalysis\Domain\SamplingAnalysisEntity;

interface SamplingAnalysisRepository
{
    public function create(SamplingAnalysisEntity $samplingAnalysisEntity): SamplingAnalysisDTO;
    public function update(SamplingAnalysisEntity $samplingAnalysisEntity): SamplingAnalysisDTO;
    public function delete(int $id): bool;
    public function getById(int $id): SamplingAnalysisDTO;
    public function getAll(): array;
    public function getByFieldTripIdAndUserId(int $fieldTripId, int $userId): array;
}
