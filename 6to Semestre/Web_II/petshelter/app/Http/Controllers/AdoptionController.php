<?php

namespace App\Http\Controllers;

use App\Models\Adoption;
use App\Models\Client;
use App\Models\Pet;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;

class AdoptionController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return Adoption::with('pet', 'client')->get();
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
            'pet_id'         => 'required',
            'client_id'           => 'required'
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
        }
        $adoption = new Adoption();
        $adoption->fill($request->json()->all());
        $adoption->save();
        $pet = Pet::find($adoption->pet_id);
        $pet->availability = 0;
        $pet->save();
        return $adoption;
    }

    /**
     * Display the specified resource.
     */
    public function show(Adoption $adoption)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Adoption $adoption)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Adoption $adoption)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $ids = explode("-", $id);
        $idPet = intval($ids[0]);
        $idClient = intval($ids[1]);
        $adoption = Adoption::where('pet_id', $idPet)->where('client_id', $idClient)->first();
        if ($adoption == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $client = Client::find($idClient);
        $client->pets()->detach($idPet);
        return response()->json(['message' => 'Adoption deleted successfully.']);
    }
}
