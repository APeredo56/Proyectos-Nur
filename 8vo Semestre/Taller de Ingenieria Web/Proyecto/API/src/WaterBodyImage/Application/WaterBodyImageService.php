<?php

namespace Src\WaterBodyImage\Application;


use Src\WaterBodyImage\Domain\WaterBodyImageEntity;

class WaterBodyImageService
{
    private WaterBodyImageRepository $repository;

    public function __construct(WaterBodyImageRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(WaterBodyImageEntity $waterBodyImage)
    {
        return $this->repository->create($waterBodyImage);
    }

    public function update(WaterBodyImageEntity $waterBodyImage)
    {
        return $this->repository->update($waterBodyImage);
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
