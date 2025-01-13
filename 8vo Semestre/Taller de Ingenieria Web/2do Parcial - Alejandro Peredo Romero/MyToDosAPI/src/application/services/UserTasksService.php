<?php

namespace Src\application\services;

use Src\application\repositories\UserTasksRepository;

class UserTasksService
{
    private UserTasksRepository $userTasksRepository;

    public function __construct(UserTasksRepository $userTasksRepository)
    {
        $this->userTasksRepository = $userTasksRepository;
    }

    public function getTaskWithUsers(int $taskId)
    {
        return $this->userTasksRepository->getTaskWithUsers($taskId);
    }

    public function getTasksWithUsers()
    {
        return $this->userTasksRepository->getTasksWithUsers();
    }

    public function getUserWithTasks(int $userId)
    {
        return $this->userTasksRepository->getUserWithTasks($userId);
    }

    public function assignTaskToUser(int $taskId, int $userId)
    {
        return $this->userTasksRepository->assignTaskToUser($taskId, $userId);
    }

    public function unassignTaskToUser(int $taskId, int $userId)
    {
        return $this->userTasksRepository->unassignTaskToUser($taskId, $userId);
    }

}
