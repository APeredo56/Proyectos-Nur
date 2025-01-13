<?php

namespace Src\infraestructure\mappers;

use App\Models\EloquentUser;
use Src\application\dto\UserDTO;
use Src\domain\entities\user\User;

class UserMapper
{
    public static function toEloquentUser(User $user): EloquentUser
    {
        $eloquentUser = new EloquentUser([
            'name' => $user->getName(),
            'last_name' => $user->getLastName(),
            'email' => $user->getEmail(),
            'password' => $user->getPassword(),
            'phone' => $user->getPhone(),
            'date_of_birth' => $user->getDateOfBirth()
        ]);
        $eloquentUser->id = $user->getId();
        return $eloquentUser;
    }

    public static function toDomainUser(EloquentUser $eloquentUser): User
    {
        return new User(
            $eloquentUser->id,
            $eloquentUser->name,
            $eloquentUser->last_name,
            $eloquentUser->phone,
            $eloquentUser->email,
            $eloquentUser->password,
            $eloquentUser->date_of_birth
        );
    }

    public static function toDTO(EloquentUser $eloquentUser)
    {
        return new UserDTO(
            $eloquentUser->id,
            $eloquentUser->name,
            $eloquentUser->last_name,
            $eloquentUser->phone,
            $eloquentUser->email,
            $eloquentUser->date_of_birth
        );
    }
}
