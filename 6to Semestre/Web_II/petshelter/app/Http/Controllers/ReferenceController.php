<?php

namespace App\Http\Controllers;

use App\Models\Reference;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;

class ReferenceController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return Reference::with('client')->get();
    }

    public static function selectById($id)
    {
        $reference = Reference::find($id);
        if ($reference == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        return $reference;
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
        //
    }



    /**
     * Display the specified resource.
     */
    public function show(Reference $references)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Reference $references)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        $reference = Reference::find($id);
        if ($reference == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $reference->full_name = $request->json('full_name');
        $reference->phone = $request->json('phone');
        $reference->relationship = $request->json('relationship');
        $reference->client_id = $request->json('client_id');
        $reference->save();
        return response()->json(["message" => "Item updated"], ResponseAlias::HTTP_OK);
    }

    /**
     * Remove the specified resource from storage.
     */
    public static function destroy($id)
    {
        $reference = Reference::find($id);
        if ($reference == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $reference->delete();
        return response()->json(["message" => "Item deleted"], ResponseAlias::HTTP_OK);
    }

    public static function selectByClient($client_id)
    {
        return Reference::where('client_id', $client_id)->get()->toArray();
    }
}
