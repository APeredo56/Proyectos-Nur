<?php

namespace routes;

use App\Http\Controllers\EloquentTaskController;
use App\Http\Controllers\EloquentUserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');


Route::get('tasks', [EloquentTaskController::class, 'index']);
Route::get('tasks/{eloquentTask}', [EloquentTaskController::class, 'show']);
Route::post('tasks', [EloquentTaskController::class, 'store']);
Route::put('tasks/{eloquentTask}', [EloquentTaskController::class, 'update']);
Route::delete('tasks/{eloquentTask}', [EloquentTaskController::class, 'destroy']);

Route::post('tasks/{eloquentTask}/users/{eloquentUser}', [EloquentTaskController::class, 'assignUser']);
Route::delete('tasks/{eloquentTask}/users/{eloquentUser}', [EloquentTaskController::class, 'unassignUser']);

Route::get('users', [EloquentUserController::class, 'index']);
Route::get('users/{eloquentUser}', [EloquentUserController::class, 'show']);
Route::post('users', [EloquentUserController::class, 'store']);
Route::put('users/{eloquentUser}', [EloquentUserController::class, 'update']);
Route::delete('users/{eloquentUser}', [EloquentUserController::class, 'destroy']);
