<?php

namespace routes;

use App\Http\Controllers\AuthController;
use App\Http\Controllers\CommunityController;
use App\Http\Controllers\DepartmentController;
use App\Http\Controllers\FieldTripController;
use App\Http\Controllers\MedalController;
use App\Http\Controllers\MunicipalityController;
use App\Http\Controllers\PointController;
use App\Http\Controllers\SamplingAnalysisController;
use App\Http\Controllers\SamplingAnalysisImageController;
use App\Http\Controllers\UserController;
use App\Http\Controllers\WaterBodyController;
use App\Http\Controllers\WaterBodyImageController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::group(["middleware" => "auth:sanctum"], function () {
    Route::get('/auth/me', [AuthController::class, 'userInfo']);

    Route::group(["middleware" => "role_or_permission:Administrativo"], function () {

        Route::post('/users', [UserController::class, 'store']);
        Route::put('/users/{id}', [UserController::class, 'update']);
        Route::delete('/users/{id}', [UserController::class, 'destroy']);

        Route::post('/departments', [DepartmentController::class, 'store']);
        Route::put('/departments/{id}', [DepartmentController::class, 'update']);
        Route::delete('/departments/{id}', [DepartmentController::class, 'destroy']);

        Route::post('/municipalities', [MunicipalityController::class, 'store']);
        Route::put('/municipalities/{id}', [MunicipalityController::class, 'update']);
        Route::delete('/municipalities/{id}', [MunicipalityController::class, 'destroy']);

        Route::post('/communities', [CommunityController::class, 'store']);
        Route::put('/communities/{id}', [CommunityController::class, 'update']);
        Route::delete('/communities/{id}', [CommunityController::class, 'destroy']);

        Route::post('/water-bodies', [WaterBodyController::class, 'store']);
        Route::put('/water-bodies/{id}', [WaterBodyController::class, 'update']);
        Route::delete('/water-bodies/{id}', [WaterBodyController::class, 'destroy']);

        Route::post('/water-bodies-images', [WaterBodyImageController::class, 'store']);
        Route::post('/water-bodies-images/update/{id}', [WaterBodyImageController::class, 'update']);
        Route::delete('/water-bodies-images/{id}', [WaterBodyImageController::class, 'destroy']);

        Route::post('/field-trips', [FieldTripController::class, 'store']);
        Route::put('/field-trips/{id}', [FieldTripController::class, 'update']);
        Route::delete('/field-trips/{id}', [FieldTripController::class, 'destroy']);

        Route::post('/field-trips/{fieldTrip}/users/{user}', [FieldTripController::class, 'assignUser']);
        Route::delete('/field-trips/{fieldTrip}/users/{user}', [FieldTripController::class, 'unassignUser']);

        Route::post('/field-trips/{fieldTrip}/water-bodies/{waterBody}', [FieldTripController::class, 'assignWaterBody']);
        Route::delete('/field-trips/{fieldTrip}/water-bodies/{waterBody}', [FieldTripController::class, 'unassignWaterBody']);

        Route::get('/users/tecnicos', [UserController::class, 'getTechnicians']);
    });

    Route::group(["middleware" => "role_or_permission:Administrativo|Tecnico"], function () {
        Route::get('/users/{userId}/field-trips', [FieldTripController::class, 'getByUserId']);

        Route::post('/sampling-analyses', [SamplingAnalysisController::class, 'store']);
        Route::put('/sampling-analyses/{id}', [SamplingAnalysisController::class, 'update']);
        Route::delete('/sampling-analyses/{id}', [SamplingAnalysisController::class, 'destroy']);

        Route::post('/sampling-analyses-images', [SamplingAnalysisImageController::class, 'store']);
        Route::post('/sampling-analyses-images/update/{id}', [SamplingAnalysisImageController::class, 'update']);
        Route::delete('/sampling-analyses-images/{id}', [SamplingAnalysisImageController::class, 'destroy']);
    });

    Route::get('/users', [UserController::class, 'index']);
    Route::get('/users/{id}', [UserController::class, 'show']);

    Route::get('/departments', [DepartmentController::class, 'index']);
    Route::get('/departments/{id}', [DepartmentController::class, 'show']);

    Route::get('/municipalities', [MunicipalityController::class, 'index']);
    Route::get('/municipalities/{id}', [MunicipalityController::class, 'show']);

    Route::get('/communities', [CommunityController::class, 'index']);
    Route::get('/communities/{id}', [CommunityController::class, 'show']);

    Route::get('/water-bodies', [WaterBodyController::class, 'index']);
    Route::get('/water-bodies/{id}', [WaterBodyController::class, 'show']);

    Route::get('/water-bodies-images', [WaterBodyImageController::class, 'index']);
    Route::get('/water-bodies-images/{id}', [WaterBodyImageController::class, 'show']);

    Route::get('/field-trips', [FieldTripController::class, 'index']);
    Route::get('/field-trips/{id}', [FieldTripController::class, 'show']);

    Route::get('/sampling-analyses/field-trips/{fieldTripId}/users/{userId}', [SamplingAnalysisController::class, 'getByFieldTripIdAndUserId']);
    Route::get('/sampling-analyses', [SamplingAnalysisController::class, 'index']);
    Route::get('/sampling-analyses/{id}', [SamplingAnalysisController::class, 'show']);

    Route::get('/sampling-analyses-images', [SamplingAnalysisImageController::class, 'index']);
    Route::get('/sampling-analyses-images/{id}', [SamplingAnalysisImageController::class, 'show']);

    Route::get('/users/{user_id}/points', [PointController::class, 'getByUser']);

    Route::get('/points/ranking', [PointController::class, 'ranking']);
    Route::get('/medals', [MedalController::class, 'index']);
});

Route::post("/auth/login", [AuthController::class, "login"]);

