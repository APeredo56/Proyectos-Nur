<?php

namespace Src\application\services;

use Src\application\repositories\TaskRepository;
use Src\domain\entities\task\Task;

class TaskService
{
    private TaskRepository $taskRepository;

    public function __construct(TaskRepository $taskRepository)
    {
        $this->taskRepository = $taskRepository;
    }

    public function createTask(Task $task)
    {
        return $this->taskRepository->createTask($task);
    }

    public function updateTask(Task $task)
    {
        return $this->taskRepository->updateTask($task);
    }

    public function deleteTask(int $id)
    {
        return $this->taskRepository->deleteTask($id);
    }

    public function getTask(int $id)
    {
        return $this->taskRepository->getTask($id);
    }

    public function getTasks()
    {
        return $this->taskRepository->getTasks();
    }
}
