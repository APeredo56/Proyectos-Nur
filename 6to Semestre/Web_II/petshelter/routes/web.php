<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PetController;
use App\Http\Controllers\ClientController;
use App\Http\Controllers\ReferenceController;


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/
Route::get('/', function () {
    return view('home');
});

//Pet Routes
Route::get('/pets', function () {
    return view('pets/list');
})->name('pets');

Route::get('/pets/form/{id?}', function ($id = null) {
    $pet = null;
    if ($id != null){
        $pet = PetController::selectById($id);
    }
    return view('pets/form', ['pet' => $pet]);
})->name('pets.form');

//Client Routes
Route::get('/clients', function () {
    return view('clients/list');
})->name('clients');

Route::get('/clients/form/{id?}', function ($id = null) {
    $client = null;
    if ($id != null){
        $client = ClientController::selectById($id);
    }
    return view('clients/form', ['client' => $client, 'references' => $client?->references]);
})->name('clients.form');


//References routes
Route::get('/references', function () {
    return view('references/list');
})->name('references');

Route::get('/references/form/{id?}', function ($id = null) {
    $reference = null;
    if ($id != null){
        $reference = ReferenceController::selectById($id);
    }
    return view('references/form', ['reference' => $reference]);
})->name('references.form');

//Adoptions routes
Route::get('/adoptions', function () {
    return view('adoptions/list');
})->name('adoptions');

Route::get('/adoptions/form/{id?}', function ($id = null) {
    $adoption = null;
    if ($id != null){
        $adoption = ReferenceController::selectById($id);
    }
    return view('adoptions/form', ['adoption' => $adoption]);
})->name('adoptions.form');
