<?php

namespace Src\User\Infrastructure;

use App\Models\User;
use Src\User\Application\UserDTO;
use Src\User\Domain\UserEntity;

class UserMapper
{
    public static function toEloquent(UserEntity $userEntity): User
    {
        $eloquentUser = new User($userEntity->toArray());
        $eloquentUser->id = $userEntity->getId();
        $eloquentUser->password = $userEntity->getPassword();
        return $eloquentUser;
    }

    public static function toDTO(User $eloquentUser)
    {
        return new UserDTO(
            $eloquentUser->id,
            $eloquentUser->name,
            $eloquentUser->email,
            $eloquentUser->status,
        );
    }
}
