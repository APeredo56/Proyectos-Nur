<?php

namespace Src\infraestructure\repositories;

use App\Models\EloquentTask;
use Src\application\repositories\TaskRepository;
use Src\domain\entities\task\Task;
use Src\infraestructure\mappers\TaskMapper;

class EloquentTaskRepository implements TaskRepository
{

    public function createTask(Task $task): Task
    {
        $model = TaskMapper::toEloquentTask($task);
        $model->save();
        $task->setId($model->id);
        return $task;
    }

    public function updateTask(Task $task): Task
    {
        $model = EloquentTask::find($task->getId());
        $model->update($task->toArray());
        return $task;
    }

    public function deleteTask(int $id): bool
    {
        $model = EloquentTask::find($id);
        return $model->delete();
    }

    public function getTask(int $id): Task
    {
        $model = EloquentTask::find($id);
        return TaskMapper::toDomainTask($model);
    }

    public function getTasks(): array
    {
        $models = EloquentTask::all();
        $tasks = [];
        foreach ($models as $model) {
            $tasks[] = TaskMapper::toDomainTask($model);
        }
        return $tasks;
    }

}
