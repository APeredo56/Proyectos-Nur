<?php

namespace Src\infraestructure\repositories;

use App\Models\EloquentTask;
use Src\application\dto\TaskDTO;
use Src\application\dto\UserDTO;
use Src\application\repositories\UserTasksRepository;
use Src\infraestructure\mappers\TaskMapper;

class EloquentUserTasksRepository implements UserTasksRepository
{

    public function getTaskWithUsers(int $taskId): TaskDTO
    {
        $task = EloquentTask::with('users')->find($taskId);
        return TaskMapper::toDTO($task);
    }

    public function getTasksWithUsers(): array
    {
        $tasks = EloquentTask::with('users')->get();
        $tasksDTO = [];
        foreach ($tasks as $task) {
            $tasksDTO[] = TaskMapper::toDTO($task);
        }
        return $tasksDTO;
    }

    public function getUserWithTasks(int $userId): UserDTO
    {
        // TODO: Implement getUserWithTasks() method.
    }

    public function assignTaskToUser(int $taskId, int $userId): bool
    {
        $task = EloquentTask::find($taskId);
        $task->users()->attach($userId);
        return $task->users()->exists($userId);
    }

    public function unassignTaskToUser(int $taskId, int $userId): bool
    {
        $task = EloquentTask::find($taskId);
        return $task->users()->detach($userId) > 0;
    }
}
