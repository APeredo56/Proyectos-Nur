<?php

use App\Http\Controllers\PetController;
use App\Http\Controllers\ClientController;
use App\Http\Controllers\ReferenceController;
use App\Http\Controllers\AdoptionController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get('/pets', [PetController::class, "index"]);
Route::get('/pets/{id}', [PetController::class, "selectById"]);
Route::post('/pets', [PetController::class, "store"]);
Route::put('/pets/{id}', [PetController::class, "update"]);
Route::delete('/pets/{id}', [PetController::class, "destroy"]);
Route::get('/disponibles', [PetController::class, "selectAvailable"]);

Route::get('/clients', [ClientController::class, "index"]);
Route::post('/clients', [ClientController::class, "store"]);
Route::get('/clients/{id}', [ClientController::class, "selectById"]);
Route::put('/clients/{id}', [ClientController::class, "update"]);
Route::delete('/clients/{id}', [ClientController::class, "destroy"]);

Route::get('/references', [ReferenceController::class, "index"]);
Route::post('/references', [ReferenceController::class, "store"]);
Route::get('/references/{id}', [ReferenceController::class, "selectById"]);
Route::put('/references/{id}', [ReferenceController::class, "update"]);
Route::delete('/references/{id}', [ReferenceController::class, "destroy"]);

Route::get('/adoptions', [AdoptionController::class, "index"]);
Route::get('/adoptions/{id}', [AdoptionController::class, "selectById"]);
Route::put('/adoptions/{id}', [AdoptionController::class, "update"]);
Route::delete('/adoptions/{id}', [AdoptionController::class, "destroy"]);
Route::post('/adoptions/{petId}/{clientId}', [AdoptionController::class, "store"]);



