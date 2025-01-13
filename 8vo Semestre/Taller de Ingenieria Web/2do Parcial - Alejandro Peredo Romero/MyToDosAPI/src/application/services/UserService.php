<?php

namespace Src\application\services;

use Src\application\repositories\UserRepository;
use Src\domain\entities\user\User;

class UserService
{
    private UserRepository $userRepository;

    /**
     * @param UserRepository $userRepository
     */
    public function __construct(UserRepository $userRepository)
    {
        $this->userRepository = $userRepository;
    }

    public function createUser(User $user)
    {
        return $this->userRepository->createUser($user);
    }

    public function updateUser(User $user)
    {
        return $this->userRepository->updateUser($user);
    }

    public function deleteUser(int $id)
    {
        return $this->userRepository->deleteUser($id);
    }

    public function getUser(int $id)
    {
        return $this->userRepository->getUser($id);
    }

    public function getUsers()
    {
        return $this->userRepository->getUsers();
    }


}
