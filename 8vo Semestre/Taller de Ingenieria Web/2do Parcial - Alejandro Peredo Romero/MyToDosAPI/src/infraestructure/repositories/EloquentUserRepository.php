<?php

namespace Src\infraestructure\repositories;

use App\Models\EloquentUser;
use Src\application\dto\UserDTO;
use Src\application\repositories\UserRepository;
use Src\domain\entities\user\User;
use Src\infraestructure\mappers\UserMapper;

class EloquentUserRepository implements UserRepository
{

    public function createUser(User $user): UserDTO
    {
        $model = UserMapper::toEloquentUser($user);
        $model->save();
        return UserMapper::toDTO($model);
    }

    public function updateUser(User $user): UserDTO
    {
        $model = EloquentUser::find($user->getId());
        $model->update($user->toArray());
        return UserMapper::toDTO($model);
    }

    public function deleteUser(int $id): bool
    {
        $model = EloquentUser::find($id);
        return $model->delete();
    }

    public function getUser(int $id): UserDTO
    {
        $model = EloquentUser::find($id);
        return UserMapper::toDTO($model);
    }

    public function getUsers(): array
    {
        $models = EloquentUser::all();
        $users = [];
        foreach ($models as $model) {
            $users[] = UserMapper::toDTO($model);
        }
        return $users;
    }
}
