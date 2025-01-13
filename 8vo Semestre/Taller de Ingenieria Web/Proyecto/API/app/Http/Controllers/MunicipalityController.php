<?php

namespace App\Http\Controllers;

use App\Models\Municipality;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\Municipality\Application\MunicipalityService;
use Src\Municipality\Domain\MunicipalityEntity;
use Src\Municipality\Infrastructure\EloquentMunicipalityRepository;

class MunicipalityController extends Controller {
    private MunicipalityService $municipalityService;

    public function __construct()
    {
        $this->municipalityService = new MunicipalityService(new EloquentMunicipalityRepository());
    }

    private function getRules()
    {
        return [
            'name' => 'required|string',
            'department_id' => 'required|integer|exists:departments,id',
        ];
    }

    public function index()
    {
        $domainMunicipalities = $this->municipalityService->getAll();
        $municipalitys = [];
        foreach ($domainMunicipalities as $domainMunicipality) {
            $municipalitys[] = $domainMunicipality->toArray();
        }
        return response()->json($municipalitys, 201);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $municipality = new MunicipalityEntity(0,
            $request->name,
            $request->department_id
        );
        $municipality = $this->municipalityService->create($municipality);
        return response()->json($municipality->toArray(), 201);
    }

    public function show($id)
    {
        $municipality = $this->municipalityService->getById($id);
        return response()->json($municipality->toArray(), 201);
    }

    public function update(Request $request, $id){
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $municipality = new MunicipalityEntity(
            $id,
            $request->name,
            $request->department_id
        );
        $municipality = $this->municipalityService->update($municipality);
        return response()->json($municipality->toArray(), 201);
    }

    public function destroy($id){
        $deleted = $this->municipalityService->delete($id);
        if ($deleted) {
            return response()->json(["message" => "Municipalidad eliminada correctamente"]);
        } else {
            return response()->json(["message" => "Error al eliminar la municipalidad"], 500);
        }
    }
}
