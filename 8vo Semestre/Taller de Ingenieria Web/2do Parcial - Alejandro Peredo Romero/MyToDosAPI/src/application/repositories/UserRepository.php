<?php

namespace Src\application\repositories;

use Src\application\dto\UserDTO;
use Src\domain\entities\user\User;

interface UserRepository
{
    public function createUser(User $user): UserDTO;
    public function updateUser(User $user): UserDTO;
    public function deleteUser(int $id): bool;
    public function getUser(int $id): UserDTO;
    public function getUsers(): array;
}
