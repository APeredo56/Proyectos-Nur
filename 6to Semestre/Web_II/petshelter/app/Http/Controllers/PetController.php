<?php

namespace App\Http\Controllers;

use App\Models\Pet;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;

class PetController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return Pet::all();
    }

    public static function selectById($id)
    {
        $pet = Pet::find($id);
        if ($pet == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        return $pet;
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {

        $validator = Validator::make($request->json()->all(), [
            'name'         => 'required',
            'type'           => 'required',
            'breed' => 'required',
            'color'           => 'required',
            'size'         => 'required',
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
        }

        $pet = new Pet();
        $pet->fill($request->json()->all());
        $pet->save();

        return $pet;
    }

    /**
     * Display the specified resource.
     */
    public function show(Pet $pet)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Pet $pet)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        $pet = Pet::find($id);
        if($pet == null){
            return response()->json(['message' => 'Pet not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        $validator = Validator::make($request->json()->all(), [
            'name'         => 'required',
            'type'           => 'required',
            'breed'         => 'required',
            'color'           => 'required',
            'size'         => 'required',
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
        }

        $pet->fill($request->json()->all());
        $pet->save();

        return $pet;
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $pet = Pet::find($id);
        if ($pet == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $pet->delete();
        return response()->json(['message' => 'Pet deleted successfully.']);
    }

    public function selectAvailable()
    {
        return Pet::where('availability', '=', '1')->get();
    }
}
