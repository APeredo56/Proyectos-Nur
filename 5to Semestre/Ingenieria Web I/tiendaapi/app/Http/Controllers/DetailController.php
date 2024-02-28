<?php

namespace App\Http\Controllers;

use App\Models\Detail;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;

class DetailController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $detail = Detail::all();
        return $detail;
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
            'cantidad'         => 'required',
            'precio'         => 'required',
            'producto_id'         => 'required',
            'pedido_id'         => 'required',
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
        }

        $detail = new Detail();
        $detail->fill($request->json()->all());
        $detail->save();

        return $detail;
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $detail = Detail::find($id);
        if($detail == null){
            return response()->json(['message' => 'Detail not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        return $detail;
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Detail $detail)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        $detail = Detail::find($id);
        if($detail == null){
            return response()->json(['message' => 'Detail not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        if ($request->method() != "PATCH") {
            $validator = Validator::make($request->json()->all(), [
                'cantidad'         => 'required',
                'precio'         => 'required',
                'producto_id'         => 'required',
                'pedido_id'         => 'required',
            ]);
            if ($validator->fails()) {
                return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
            }
        }
        $detail->fill($request->json()->all());
        $detail->save();

        return $detail;
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $detail = Detail::find($id);
        if ($detail == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $detail->delete();

        return response()->json(['message' => 'Detail deleted successfully.']);
    }

    public function searchDetailByProduct($id)
    {
        $detail = Detail::where('producto_id', $id)->get();
        if($detail == null){
            return response()->json(['message' => 'Error inesperado'], ResponseAlias::HTTP_NOT_FOUND);
        }
        if (count($detail) == 0) {
            return response()->json(['message' => 'No se encontraron ventas para el producto'], ResponseAlias::HTTP_NOT_FOUND);
        }
        return response()->json(['message' => 'El producto tiene ventas asociadas.', 'detail' => $detail]);
    }
}
