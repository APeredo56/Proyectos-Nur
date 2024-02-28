<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Validator;

class AuthController extends Controller
{
    public function login(Request $request)
    {
        $validator = Validator::make($request->json()->all(), [
            "email"    => ['required', 'string'],
            "password" => ['required', 'string']
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(),
                Response::HTTP_BAD_REQUEST);
        }

        if ( ! auth()->attempt($request->json()->all())) {
            return response()->json([
                'message' => 'Unauthenticated'
            ], Response::HTTP_UNAUTHORIZED);
        }
        $accessToken = auth()->user()->createToken('authToken')->plainTextToken;

        return response()->json([
            'access_token' => $accessToken
        ]);
    }

    public function register(Request $request)
    {
        $validator = Validator::make($request->json()->all(), [
            "name"     => ['required', 'string'],
            "email"    => ['required', 'string'],
            "password" => ['required', 'string']
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(),
                Response::HTTP_BAD_REQUEST);
        }
        $userExists = User::where("email", $request->json()->get("email"))->first();
        if ($userExists != null) {
            return response()->json([
                'message' => 'User already exists'
            ], Response::HTTP_CONFLICT);
        }

        $user           = User::create($request->json()->all());
        $user->password = bcrypt($user->password);
        $user->save();
        //$user->assignRole("client");

        return $user;
    }
}
