<?php

namespace Src\SamplingAnalysis\Application;

use Src\SamplingAnalysis\Domain\SamplingAnalysisEntity;

class SamplingAnalysisService
{
    private SamplingAnalysisRepository $repository;

    public function __construct(SamplingAnalysisRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(SamplingAnalysisEntity $samplingAnalysis)
    {
        return $this->repository->create($samplingAnalysis);
    }

    public function update(SamplingAnalysisEntity $samplingAnalysis)
    {
        return $this->repository->update($samplingAnalysis);
    }

    public function delete(int $id)
    {
        return $this->repository->delete($id);
    }

    public function getById(int $id)
    {
        return $this->repository->getById($id);
    }

    public function getAll()
    {
        return $this->repository->getAll();
    }

    public function getByFieldTripIdAndUserId(int $fieldTripId, int $userId)
    {
        return $this->repository->getByFieldTripIdAndUserId($fieldTripId, $userId);
    }
}
