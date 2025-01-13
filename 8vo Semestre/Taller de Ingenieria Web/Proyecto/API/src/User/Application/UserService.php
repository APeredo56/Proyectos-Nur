<?php

namespace Src\User\Application;

use Src\User\Domain\UserEntity;

class UserService
{
    private UserRepository $repository;

    public function __construct(UserRepository $repository)
    {
        $this->repository = $repository;
    }

    public function create(UserEntity $user)
    {
        return $this->repository->create($user);
    }

    public function update(UserEntity $user)
    {
        return $this->repository->update($user);
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
