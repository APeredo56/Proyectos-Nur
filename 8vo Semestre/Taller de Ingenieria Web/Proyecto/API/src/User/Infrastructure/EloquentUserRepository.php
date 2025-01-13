<?php

namespace Src\User\Infrastructure;

use App\Models\User;
use Src\User\Application\UserDTO;
use Src\User\Application\UserRepository;
use Src\User\Domain\UserEntity;

class EloquentUserRepository implements UserRepository
{

    public function create(UserEntity $user): UserDTO
    {
        $model = UserMapper::toEloquent($user);
        $model->save();
        return UserMapper::toDTO($model);
    }

    public function update(UserEntity $user): UserDTO
    {
        $model = User::find($user->getId());
        $user->setPassword($model->password);
        $model->update($user->toArray());
        return UserMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = User::find($id);
        return $model->delete();
    }

    public function getById(int $id): UserDTO
    {
        $model = User::find($id);
        return UserMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = User::all();
        $users = [];
        foreach ($models as $model) {
            $users[] = UserMapper::toDTO($model);
        }
        return $users;
    }
}
