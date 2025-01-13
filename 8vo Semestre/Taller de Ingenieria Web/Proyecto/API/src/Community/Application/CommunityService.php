<?php

namespace Src\Community\Application;

use Src\Community\Domain\CommunityEntity;

class CommunityService
{
    private CommunityRepository $repository;

    public function __construct(CommunityRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(CommunityEntity $community)
    {
        return $this->repository->create($community);
    }

    public function update(CommunityEntity $community)
    {
        return $this->repository->update($community);
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
