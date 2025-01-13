<?php

namespace Src\application\repositories;

use Src\domain\entities\task\Task;

interface TaskRepository
{
    public function createTask(Task $task): Task;
    public function updateTask(Task $task): Task;
    public function deleteTask(int $id): bool;
    public function getTask(int $id): Task;
    public function getTasks(): array;

}
