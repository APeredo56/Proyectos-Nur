<?php

namespace Src\infraestructure\mappers;

use App\Models\EloquentTask;
use Carbon\Carbon;
use Src\application\dto\TaskDTO;
use Src\domain\entities\task\Task;

class TaskMapper
{
    public static function toEloquentTask(Task $task): EloquentTask
    {
        $eloquentTask = new EloquentTask([
            'title' => $task->getTitle(),
            'description' => $task->getDescription(),
            'start_date' => $task->getStartDate(),
            'end_date' => $task->getEndDate(),
            'completed' => $task->isCompleted()
        ]);
        $eloquentTask->id = $task->getId();
        return $eloquentTask;
    }

    public static function toDomainTask(EloquentTask $eloquentTask): Task
    {
        return new Task(
            $eloquentTask->id,
            $eloquentTask->title,
            $eloquentTask->description,
            Carbon::parse($eloquentTask->start_date),
            Carbon::parse($eloquentTask->end_date),
            $eloquentTask->completed
        );
    }

    public static function toDTO(EloquentTask $task): TaskDTO
    {
        $userDTOs = null;
        if ($task->users) {
            $userDTOs = [];
            foreach ($task->users as $user) {
                $userDTOs[] = UserMapper::toDTO($user);
            }
        }
        return new TaskDTO(
            $task->id,
            $task->title,
            $task->description,
            Carbon::parse($task->start_date),
            Carbon::parse($task->end_date),
            $task->completed,
            $userDTOs
        );
    }
}
