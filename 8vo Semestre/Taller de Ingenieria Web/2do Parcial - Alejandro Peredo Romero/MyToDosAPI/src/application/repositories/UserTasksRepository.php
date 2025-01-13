<?php

namespace Src\application\repositories;

use Src\application\dto\TaskDTO;
use Src\application\dto\UserDTO;

interface UserTasksRepository
{
    public function getTaskWithUsers(int $taskId) : TaskDTO;
    public function getTasksWithUsers() : array;
    public function getUserWithTasks(int $userId) : UserDTO;
    public function assignTaskToUser(int $taskId, int $userId) : bool;
    public function unassignTaskToUser(int $taskId, int $userId) : bool;

}
