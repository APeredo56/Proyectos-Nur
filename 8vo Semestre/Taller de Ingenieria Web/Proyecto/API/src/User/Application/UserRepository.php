<?php

namespace Src\User\Application;

use Src\User\Domain\UserEntity;

interface UserRepository
{
    public function create(UserEntity $user): UserDTO;
    public function update(UserEntity $user): UserDTO;
    public function delete(int $id): bool;
    public function getById(int $id): UserDTO;
    public function getAll(): array;
}
