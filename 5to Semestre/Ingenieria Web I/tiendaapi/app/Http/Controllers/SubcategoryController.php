<?php

namespace App\Http\Controllers;

use App\Models\Subcategory;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;

class SubcategoryController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $subcategories = Subcategory::all();
        return $subcategories;
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
            'nombre'         => 'required',
            'categoria_id'   => 'required'
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
        }

        $subcategory = new Subcategory();
        $subcategory->fill($request->json()->all());
        $subcategory->save();

        return $subcategory;
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $subcategory = Subcategory::find($id);
        if($subcategory == null){
            return response()->json(['message' => 'Subcategory not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        return $subcategory;
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Subcategory $subcategory)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        $subcategory = Subcategory::find($id);
        if($subcategory == null){
            return response()->json(['message' => 'Subcategory not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        if ($request->method() != "PATCH") {
            $validator = Validator::make($request->json()->all(), [
                'nombre'         => 'required',
                'categoria_id'   => 'required'
            ]);
            if ($validator->fails()) {
                return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
            }
        }
        $subcategory->fill($request->json()->all());
        $subcategory->save();

        return $subcategory;
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $category = Subcategory::find($id);
        if ($category == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $category->delete();

        return response()->json(['message' => 'Subcategory deleted successfully.']);
    }
}
