<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Validator;
use Spatie\Permission\Models\Role;

class UserController extends Controller
{
    function assignAdmin(Request $request)
    {
        $validator = Validator::make($request->json()->all(), [
            "user_id" => ['required', 'integer']
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(),
                Response::HTTP_BAD_REQUEST);
        }
        $user = User::find($request->user_id);
        if ($user == null) {
            return response()->json([
                'message' => 'User not found'
            ], Response::HTTP_NOT_FOUND);
        }
        $user->assignRole("admin");

        return $user;
    }

    function me(Request $user)
    {
        $user  = User::find($user->user()->id);
        $roles = $user->getRoleNames();
        $user->setHidden(["roles", "password", "email_verified_at", "remember_token", "created_at", "updated_at"]);
        $user->parsedRoles = $roles;
        return $user;
    }
}
