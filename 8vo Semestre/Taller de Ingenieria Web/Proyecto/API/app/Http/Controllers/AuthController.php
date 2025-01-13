<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\Auth;

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

    public function userInfo()
    {
        $user = Auth::user();
        $user->load('roles');
        return response()->json($user);
    }
}
