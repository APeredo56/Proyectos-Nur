<?php

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AccommodationController;
use App\Http\Controllers\CityController;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\BookingController;
use App\Http\Controllers\ImageController;

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

Route::get('/unauthenticated', function () {
    return response()->json([
        'message' => 'Unauthenticated'
    ], Response::HTTP_UNAUTHORIZED);
})->name('api.unauthenticated');

Route::group(['middleware' => ['auth:sanctum']], function () {
    Route::get('/auth/me', [AuthController::class, 'userInfo']);
    Route::get('/user/bookings', [BookingController::class, 'getBookingsByUser']);
    Route::get('/user/accommodations', [AccommodationController::class, 'getAccommodationsByUser']);

    Route::post('/bookings', [BookingController::class, 'store']);
    Route::get('/bookings/accommodation/{id}', [BookingController::class, 'getBookingsByAccommodation']);
    Route::delete('/bookings/{id}', [BookingController::class, 'destroy']);

    Route::post('/accommodations', [AccommodationController::class, 'store']);
    Route::post('/accommodations/image', [AccommodationController::class, 'saveAccommodationImage']);
    Route::put('/accommodations/{id}', [AccommodationController::class, 'update']);
    Route::delete('/accommodations/{id}', [AccommodationController::class, 'destroy']);

    Route::post('/cities', [CityController::class, 'store']);
    Route::delete('/images/{id}', [ImageController::class, 'destroy']);

});

//Public
Route::get('/cities', [CityController::class, 'index']);
Route::get('/accommodations/types', [AccommodationController::class, 'getAccommodationTypes']);
Route::get('/accommodations/{id}', [AccommodationController::class, 'show']);
Route::post('/accommodations/search', [AccommodationController::class, 'search']);
Route::post("/auth/login", [AuthController::class, "login"]);
Route::post("/auth/register", [AuthController::class, "register"]);


//Test
Route::get('/accommodations', [AccommodationController::class, 'index']);
