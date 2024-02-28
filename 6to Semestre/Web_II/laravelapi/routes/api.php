<?php

use App\Http\Controllers\AuthController;
use App\Http\Controllers\MascotaController;
use App\Http\Controllers\PersonaController;
use App\Http\Controllers\UserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
Route::group(["middleware" => "auth:sanctum"], function () {
    Route::group(["middleware" => "can:show personas"], function () {
        Route::get("/personas", [PersonaController::class, "index"]);
        Route::get("/personas/{id}", [PersonaController::class, "show"]);
    });

    Route::middleware("can:insert personas")->post("/personas", [PersonaController::class, "store"]);
    Route::middleware("can:edit personas")->match(["put", "patch"], "/personas/{id}",
        [PersonaController::class, "update"]);
    Route::middleware("can:delete personas")->delete("/personas/{id}", [PersonaController::class, "destroy"]);
    Route::post("/personas/{id}/profile", [PersonaController::class, "profilePicture"]);
});


Route::resource("/mascotas", MascotaController::class);

Route::middleware(["auth:sanctum", "role:admin"])->post("/assign-admin", [UserController::class, "assignAdmin"]);
Route::middleware(["auth:sanctum"])->get("/me", [UserController::class, "me"]);
Route::post("/login", [AuthController::class, "login"]);
Route::post("/register", [AuthController::class, "register"]);
