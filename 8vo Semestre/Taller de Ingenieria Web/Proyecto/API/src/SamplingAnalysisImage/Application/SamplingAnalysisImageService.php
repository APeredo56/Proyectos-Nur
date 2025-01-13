<?php

namespace Src\SamplingAnalysisImage\Application;


use Src\SamplingAnalysisImage\Domain\SamplingAnalysisImageEntity;

class SamplingAnalysisImageService
{
    private SamplingAnalysisImageRepository $repository;

    public function __construct(SamplingAnalysisImageRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(SamplingAnalysisImageEntity $samplingAnalysisImage)
    {
        return $this->repository->create($samplingAnalysisImage);
    }

    public function update(SamplingAnalysisImageEntity $samplingAnalysisImage)
    {
        return $this->repository->update($samplingAnalysisImage);
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
}
