<?php

namespace App\Http\Controllers;

use App\Models\Category;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;

class CategoryController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $categories = Category::all();
        return $categories;
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
            'nombre'         => 'required, string ',
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
        }

        $category = new Category();
        $category->fill($request->json()->all());
        $category->save();

        return $category;
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $category = Category::find($id);
        if($category == null){
            return response()->json(['message' => 'Category not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        return $category;
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Category $category)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        $category = Category::find($id);
        if($category == null){
            return response()->json(['message' => 'Category not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        if ($request->method() != "PATCH") {
            $validator = Validator::make($request->json()->all(), [
                'nombre'         => 'required, string',
            ]);
            if ($validator->fails()) {
                return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
            }
        }
        $category->fill($request->json()->all());
        $category->save();

        return $category;
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $category = Category::find($id);
        if ($category == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $category->delete();

        return response()->json(['message' => 'Category deleted successfully.']);
    }
}
