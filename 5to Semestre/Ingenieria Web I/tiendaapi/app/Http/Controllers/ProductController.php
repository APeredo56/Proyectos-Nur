<?php

namespace App\Http\Controllers;

use App\Models\Product;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;

class ProductController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $products = Product::all();
        return $products;
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
            'precio'         => 'required',
            'disponible'     => 'required',
            'descripcion'    => 'required',
            'img_url'        => 'required',
            'id_categoria'   => 'required',
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
        }

        $product = new Product();
        $product->fill($request->json()->all());
        $product->save();

        return $product;
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $product = Product::find($id);
        if($product == null){
            return response()->json(['message' => 'Product not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        return $product;
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Product $product)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        $product = Product::find($id);
        if($product == null){
            return response()->json(['message' => 'Product not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        if ($request->method() != "PATCH") {
            $validator = Validator::make($request->json()->all(), [
                'nombre'         => 'required',
                'precio'         => 'required',
                'disponible'     => 'required',
                'descripcion'    => 'required',
                'img_url'        => 'required',
                'id_categoria'   => 'required',
            ]);
            if ($validator->fails()) {
                return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
            }
        }
        $product->fill($request->json()->all());
        $product->save();

        return $product;
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $product = Product::find($id);
        if ($product == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $product->delete();

        return response()->json(['message' => 'Product deleted successfully.']);
    }

    public function getByCategory($categoryId)
    {
        $products = Product::where('id_categoria', $categoryId)->get();
        if ($products == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        return $products;
    }z
}
