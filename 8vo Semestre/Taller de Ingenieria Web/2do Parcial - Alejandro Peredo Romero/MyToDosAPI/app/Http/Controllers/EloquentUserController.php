<?php

namespace App\Http\Controllers;

use App\Models\EloquentUser;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\application\services\UserService;
use Src\domain\entities\user\User;
use Src\infraestructure\repositories\EloquentUserRepository;

class EloquentUserController extends Controller
{
    private UserService $userService;

    public function __construct()
    {
        $this->userService = new UserService(new EloquentUserRepository());
    }

    private function getReglas()
    {
        return [
            'name' => 'required|string',
            'last_name' => 'required|string',
            'phone' => 'required|string',
            'email' => 'required|string',
            'password' => 'required|string',
            'date_of_birth' => 'required|date',
        ];
    }

    public function index()
    {
        $domainUsers = $this->userService->getUsers();
        $users = [];
        foreach ($domainUsers as $domainUser) {
            $users[] = $domainUser->toArray();
        }
        return response()->json($users, 201);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getReglas());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $user = new User(0,
            $request->name,
            $request->last_name,
            $request->phone,
            $request->email,
            $request->password,
            Carbon::parse($request->date_of_birth)
        );
        $this->userService->createUser($user);
        return response()->json($user->toArray(), 201);
    }

    public function show($id)
    {
        $user = $this->userService->getUser($id);
        return response()->json($user->toArray(), 201);
    }

    public function update(Request $request, EloquentUser $eloquentUser){
        $validator = Validator::make($request->all(), $this->getReglas());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $user = new User(
            $eloquentUser->id,
            $request->name,
            $request->last_name,
            $request->phone,
            $request->email,
            $request->password,
            Carbon::parse($request->date_of_birth)
        );
        $user = $this->userService->updateUser($user);
        return response()->json($user->toArray(), 201);
    }

    public function destroy(EloquentUser $eloquentUser){
        $deleted = $this->userService->deleteUser($eloquentUser->id);
        if ($deleted) {
            return response()->json(["message" => "User deleted"]);
        } else {
            return response()->json(["message" => "Failed to delete user"], 500);
        }
    }
}

