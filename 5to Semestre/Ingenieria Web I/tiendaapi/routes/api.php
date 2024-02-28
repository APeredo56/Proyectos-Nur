<?php

use App\Http\Controllers\AuthController;
use App\Http\Controllers\CategoryController;
use App\Http\Controllers\DetailController;
use App\Http\Controllers\OrderController;
use App\Http\Controllers\ProductController;
use App\Http\Controllers\SubcategoryController;
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



    Route::post("login", [AuthController::class, "login"]);
    Route::post("register", [AuthController::class, "register"]);

    Route::get("categoria", [CategoryController::class, "index"]);
    Route::post("categoria", [CategoryController::class, "store"]);
    Route::get("categoria/{id}", [CategoryController::class, "show"]);
    Route::put("categoria/{id}", [CategoryController::class, "update"]);
    Route::patch("categoria/{id}", [CategoryController::class, "update"]);
    Route::delete("categoria/{id}", [CategoryController::class, "destroy"]);

    Route::get("subcategoria", [SubcategoryController::class, "index"]);
    Route::post("subcategoria", [SubcategoryController::class, "store"]);
    Route::get("subcategoria/{id}", [SubcategoryController::class, "show"]);
    Route::put("subcategoria/{id}", [SubcategoryController::class, "update"]);
    Route::patch("subcategoria/{id}", [SubcategoryController::class, "update"]);
    Route::delete("subcategoria/{id}", [SubcategoryController::class, "destroy"]);

    Route::get("producto", [ProductController::class, "index"]);
    Route::post("producto", [ProductController::class, "store"]);
    Route::get("producto/{id}", [ProductController::class, "show"]);
    Route::put("producto/{id}", [ProductController::class, "update"]);
    Route::patch("producto/{id}", [ProductController::class, "update"]);
    Route::delete("producto/{id}", [ProductController::class, "destroy"]);
    Route::get("producto/categoria/{id}", [ProductController::class, "getByCategory"]);

    Route::get("pedido", [OrderController::class, "index"]);
    Route::post("pedido", [OrderController::class, "store"]);
    Route::get("pedido/{id}", [OrderController::class, "show"]);
    Route::put("pedido/{id}", [OrderController::class, "update"]);
    Route::patch("pedido/{id}", [OrderController::class, "update"]);
    Route::delete("pedido/{id}", [OrderController::class, "destroy"]);
    Route::get("pedido/usuario/{id}", [OrderController::class, "getWithDetails"]);
    Route::get("pedidos", [OrderController::class, "getFullOrders"]);

    Route::get("detalle", [DetailController::class, "index"]);
    Route::post("detalle", [DetailController::class, "store"]);
    Route::get("detalle/{id}", [DetailController::class, "show"]);
    Route::put("detalle/{id}", [DetailController::class, "update"]);
    Route::patch("detalle/{id}", [DetailController::class, "update"]);
    Route::delete("detalle/{id}", [DetailController::class, "destroy"]);
    Route::get("detalle/producto/{id}", [DetailController::class, "searchDetailByProduct"]);
