<?php

namespace Src\Point\Application;

use Src\Point\Domain\PointEntity;

class PointService
{
    private PointRepository $repository;

    public function __construct(PointRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(PointEntity $pointEntity)
    {
        return $this->repository->create($pointEntity);
    }

    public function update(PointEntity $pointEntity)
    {
        return $this->repository->update($pointEntity);
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

    public function addPoints(int $user_id, int $points): void
    {
        $this->repository->addPoints($user_id, $points);
    }

    public function subtractPoints(int $user_id, int $points): void
    {
        $this->repository->subtractPoints($user_id, $points);
    }

    public function getByUserId(int $userId)
    {
        return $this->repository->getByUserId($userId);
    }

    public function getRanking()
    {
        return $this->repository->getRanking();
    }
}
