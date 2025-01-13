<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\Point\Application\PointService;
use Src\Point\Domain\PointEntity;
use Src\Point\Infrastucture\EloquentPointRepository;
use Src\User\Application\UserService;
use Src\User\Domain\UserEntity;
use Src\User\Infrastructure\EloquentUserRepository;

class UserController extends Controller
{
    private UserService $userService;

    public function __construct()
    {
        $this->userService = new UserService(new EloquentUserRepository());
    }

    private function getRules()
    {
        return [
            'name' => 'required|string',
            'email' => 'required|string',
            'role' => 'required|string|in:Administrativo,Tecnico',
        ];
    }

    public function index()
    {
        $domainUsers = $this->userService->getAll();
        $users = [];
        foreach ($domainUsers as $domainUser) {
            $users[] = $domainUser->toArray();
        }
        return response()->json($users, 201);
    }

    public function store(Request $request)
    {
        $rules = $this->getRules();
        $rules['email'] = 'required|email|unique:users';
        $rules['password'] = 'required|string';
        $validator = Validator::make($request->all(), $rules);
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $user = new UserEntity(0,
            $request->name,
            $request->email,
            $request->password,
            true
        );
        $user = $this->userService->create($user);

        $eloquentUser = User::find($user->getId());
        $eloquentUser->assignRole($request->role);

        $pointService = new PointService(new EloquentPointRepository());
        $pointEntity = new PointEntity(0, $user->getId(), 0);
        $pointService->create($pointEntity);

        return response()->json($user->toArray(), 201);
    }

    public function show($id)
    {
        $user = $this->userService->getById($id);
        $roles = User::with('roles')->find($id)->roles;
        $response = $user->toArray();
        $response['role'] = $roles[0]->name;
        return response()->json($response, 201);
    }

    public function update(Request $request, $id){
        $rules = $this->getRules();
        $rules['status'] = 'required|boolean';
        $validator = Validator::make($request->all(), $rules);
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $user = new UserEntity(
            $id,
            $request->name,
            $request->email,
            "",
            $request->status
        );
        $user = $this->userService->update($user);
        return response()->json($user->toArray(), 201);
    }

    public function destroy($id){
        $deleted = $this->userService->delete($id);
        if ($deleted) {
            return response()->json(["message" => "Usuario eliminado correctamente"], 201);
        } else {
            return response()->json(["message" => "Error al eliminar el usuario"], 500);
        }
    }

    public function getTechnicians()
    {
        $users = User::with('roles')->whereHas('roles', function($q){
            $q->where('name', 'Tecnico');
        })->get();
        return response()->json($users, 201);
    }

    public function getUserPoints()
    {
        $user = auth()->user();
        $pointService = new PointService(new EloquentPointRepository());
        $points = $pointService->getByUserId($user->id);
        return response()->json($points, 201);
    }
}

