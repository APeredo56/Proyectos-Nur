<?php

namespace App\Http\Controllers;

use App\Models\Persona;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Validator;

class PersonaController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Database\Eloquent\Collection
     */
    public function index()
    {
        return Persona::with("mascotas")->get();
    }


    /**
     * Store a newly created resource in storage.
     *
     * @param  Request  $request
     *
     * @return \Illuminate\Database\Eloquent\Builder|\Illuminate\Database\Eloquent\Builder[]|\Illuminate\Database\Eloquent\Collection|\Illuminate\Database\Eloquent\Model|JsonResponse
     */
    public function store(Request $request)
    {
        $validator = Validator::make($request->json()->all(), [
            "nombres"         => ['required', 'string'],
            "apellidos"       => ['required', 'string'],
            "ciudad"          => ['required', 'string'],
            "edad"            => ['required', 'integer'],
            "fechaNacimiento" => ['required', 'date'],
            "genero"          => ['required', 'in:M,F,O'],
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(),
                Response::HTTP_BAD_REQUEST);
        }
        $persona = Persona::create($request->json()->all());

        return Persona::with("mascotas")->find($persona->id);
    }

    /**
     * Display the specified resource.
     *
     * @param $id
     *
     * @return JsonResponse
     */
    public function show($id)
    {
        $objPersona = Persona::with("mascotas")->find($id);
        if ($objPersona == null) {
            return response()->json(
                array(
                    "message" => "Persona no encontrada"
                ),
                Response::HTTP_NOT_FOUND
            );
        }

        return $objPersona;
    }


    /**
     * Update the specified resource in storage.
     *
     * @param  Request  $request
     * @param $id
     *
     * @return \Illuminate\Database\Eloquent\Builder|\Illuminate\Database\Eloquent\Builder[]|\Illuminate\Database\Eloquent\Collection|\Illuminate\Database\Eloquent\Model|JsonResponse
     */
    public function update(Request $request, $id)
    {
        $objPersona = Persona::find($id);
        if ($objPersona == null) {
            return response()->json(
                array(
                    "message" => "Persona no encontrada"
                ),
                Response::HTTP_NOT_FOUND
            );
        }
        if ($request->method() == "PUT") {
            $validator = Validator::make($request->json()->all(), [
                "nombres"         => ['required', 'string'],
                "apellidos"       => ['required', 'string'],
                "ciudad"          => ['required', 'string'],
                "edad"            => ['required', 'integer'],
                "fechaNacimiento" => ['required', 'date'],
                "genero"          => ['required', 'in:M,F,O'],
            ]);
            if ($validator->fails()) {
                return response()->json($validator->messages(),
                    Response::HTTP_BAD_REQUEST);
            }
        }
        $objPersona->fill($request->json()->all());
        $objPersona->save();

        return Persona::with("mascotas")->find($objPersona->id);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param $id
     *
     * @return JsonResponse
     */
    public function destroy($id)
    {
        $objPersona = Persona::find($id);
        if ($objPersona == null) {
            return response()->json(
                array(
                    "message" => "Persona no encontrada"
                ),
                Response::HTTP_NOT_FOUND
            );
        }
        $objPersona->delete();

        return response()->json(
            array(
                "message" => "Persona eliminada correctamente"
            )
        );
    }

    public function profilePicture(Request $request, $id)
    {
        $validator = Validator::make($request->all(), [
            "image" => ['required', 'image']
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(),
                Response::HTTP_BAD_REQUEST);
        }
        $persona = Persona::find($id);
        if ($persona == null) {
            return response()->json(
                array(
                    "message" => "Persona no encontrada"
                ),
                Response::HTTP_NOT_FOUND
            );
        }
        $file     = $request->file('image');
        $filename = $id.'.jpg';
        $file->move('uploads/personas/', $filename);


        return Persona::with("mascotas")->find($id);
    }
}
