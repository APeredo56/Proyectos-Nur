<?php

namespace App\Http\Controllers;

use App\Models\Mascota;
use App\Models\Persona;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Validator;

class MascotaController extends Controller
{
    public function __construct()
    {
        $this->middleware("auth:sanctum");
        $this->middleware("can:show mascotas")->only("index", "show");
        $this->middleware("can:insert mascotas")->only("store");
        $this->middleware("can:edit mascotas")->only("update");
        $this->middleware("can:delete mascotas")->only("destroy");
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Database\Eloquent\Collection
     */
    public function index()
    {
        return Mascota::with("propietario")->get();
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
            "nombre"     => ['required', 'string'],
            "tipo"       => ['required', 'string', 'in:0,1,2,3,4'],
            "persona_id" => ['required', 'integer']
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(),
                Response::HTTP_BAD_REQUEST);
        }
        $personaId  = $request->json()->get("persona_id");
        $objPersona = Persona::find($personaId);
        if ($objPersona == null) {
            return response()->json(
                array(
                    "message" => "Persona no encontrada"
                ),
                Response::HTTP_NOT_FOUND
            );
        }
        $mascota = Mascota::create($request->json()->all());

        return Mascota::with("propietario")->find($mascota->id);
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
        $objMascota = Mascota::with("propietario")->find($id);
        if ($objMascota == null) {
            return response()->json(
                array(
                    "message" => "Mascota no encontrada"
                ),
                Response::HTTP_NOT_FOUND
            );
        }

        return $objMascota;
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
        $objMascota = Mascota::find($id);
        if ($objMascota == null) {
            return response()->json(
                array(
                    "message" => "Mascota no encontrada"
                ),
                Response::HTTP_NOT_FOUND
            );
        }
        if ($request->method() == "PUT") {
            $validator = Validator::make($request->json()->all(), [
                "nombre"     => ['required', 'string'],
                "tipo"       => ['required', 'string', 'in:0,1,2,3,4'],
                "persona_id" => ['required', 'integer']
            ]);
            if ($validator->fails()) {
                return response()->json($validator->messages(),
                    Response::HTTP_BAD_REQUEST);
            }
        }
        $objMascota->fill($request->json()->all());
        $objMascota->save();

        return Mascota::with("propietario")->find($objMascota->id);
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
        $objMascota = Mascota::find($id);
        if ($objMascota == null) {
            return response()->json(
                array(
                    "message" => "Mascota no encontrada"
                ),
                Response::HTTP_NOT_FOUND
            );
        }
        $objMascota->delete();

        return response()->json(
            array(
                "message" => "Mascota eliminada correctamente"
            )
        );
    }
}
