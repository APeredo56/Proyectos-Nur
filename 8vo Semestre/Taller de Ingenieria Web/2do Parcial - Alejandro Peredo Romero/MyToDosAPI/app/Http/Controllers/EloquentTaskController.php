<?php

namespace App\Http\Controllers;

use App\Models\EloquentTask;
use App\Models\EloquentUser;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;
use Illuminate\Support\Facades\Validator;
use Src\application\services\TaskService;
use Src\application\services\UserTasksService;
use Src\domain\entities\task\Task;
use Src\infraestructure\repositories\EloquentTaskRepository;
use Src\infraestructure\repositories\EloquentUserTasksRepository;

class EloquentTaskController extends Controller
{
    private TaskService $taskService;
    private UserTasksService $userTasksService;

    public function __construct()
    {
        $this->taskService = new TaskService(new EloquentTaskRepository());
        $this->userTasksService = new UserTasksService(new EloquentUserTasksRepository());
    }

    private function getReglas(){
        return [
            'title' => 'required|string',
            'description' => 'required|string',
            'start_date' => 'required|date',
            'end_date' => 'required|date',
            'completed' => 'boolean'
        ];
    }
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $tasks = $this->userTasksService->getTasksWithUsers();
        return response()->json(JsonResource::collection($tasks), 201);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getReglas());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $task = new Task(0,
            $request->title,
            $request->description,
            Carbon::parse($request->start_date),
            Carbon::parse($request->end_date),
            false
        );
        $this->taskService->createTask($task);
        return response()->json($task->toArray(), 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(EloquentTask $eloquentTask)
    {
        $task = $this->userTasksService->getTaskWithUsers($eloquentTask->id);
        return response()->json(new JsonResource($task), 201);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(EloquentTask $eloquentTask)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, EloquentTask $eloquentTask)
    {
        $reglas = $this->getReglas();
        $reglas['completed'] = 'required|boolean';
        $validator = Validator::make($request->all(), $reglas);
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $task = new Task($eloquentTask->id,
            $request->title,
            $request->description,
            Carbon::parse($request->start_date),
            Carbon::parse($request->end_date),
            $request->completed
        );
        $task = $this->taskService->updateTask($task);
        $task = $this->userTasksService->getTaskWithUsers($task->getId());
        return response()->json($task->toArray(), 201);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(EloquentTask $eloquentTask)
    {
        $deleted = $this->taskService->deleteTask($eloquentTask->id);
        if ($deleted) {
            return response()->json(["message" => "Task deleted"]);
        } else {
            return response()->json(["message" => "Failed to delete task"], 500);
        }
    }

    public function assignUser(EloquentTask $eloquentTask, EloquentUser $eloquentUser)
    {
        if ($eloquentTask->users()->wherePivot('eloquent_user_id', $eloquentUser->id)->exists()) {
            return response()->json(["message" => "User already assigned to task"], 422);
        }
        $this->userTasksService->assignTaskToUser($eloquentTask->id, $eloquentUser->id);
        return response()->json(["message" => "User assigned to task"]);
    }

    public function unassignUser(EloquentTask $eloquentTask, EloquentUser $eloquentUser)
    {
        if (!$eloquentTask->users()->exists($eloquentUser->id)) {
            return response()->json(["message" => "User not assigned to task"], 422);
        }
        $this->userTasksService->unassignTaskToUser($eloquentTask->id, $eloquentUser->id);
        return response()->json(["message" => "User unassigned to task"]);
    }
}
