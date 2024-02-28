<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Validator;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;
use Symfony\Component\HttpFoundation\Response;
class AuthController extends Controller
{


    public function login(Request $request)
    {
        $credentials = $request->only('correo', 'password');

        if (Auth::attempt($credentials)) {
            // Credenciales válidas, usuario autenticado
            $user = Auth::user();
            return response()->json(['message' => 'Inicio de sesión exitoso', 'user' => $user]);
        } else {
            // Credenciales inválidas, inicio de sesión fallido
            return response()->json(['message' => 'Credenciales inválidas'], Response::HTTP_UNAUTHORIZED);
        }
    }


    public function register(Request $request)
    {
        $validator = Validator::make($request->json()->all(), [
            'nombre' => 'required',
            'apellidos' => 'required',
            'correo' => 'required|unique:users,correo',
            'password' => 'required',
            'telefono' => 'required',
            'edad' => 'required',
            'isAdmin' => 'required'
        ]);
        if ($validator->fails()) {
            return response()->json(['message'=>'Error al crear un usuario', 'reason' => $validator->messages()], ResponseAlias::HTTP_BAD_REQUEST);
        }
        $user = new User();
        $user->fill($request->json()->all());
        $user->password = bcrypt($request->password);
        $user->save();

        return response()->json(['message'=> 'Usuario creado correctamente', 'user' => $user]);
    }



}
