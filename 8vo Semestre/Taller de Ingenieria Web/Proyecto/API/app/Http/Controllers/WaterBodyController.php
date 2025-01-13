<?php

namespace App\Http\Controllers;

use App\Models\WaterBody;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\Rules\Enum;
use Src\WaterBody\Application\WaterBodyService;
use Src\WaterBody\Domain\WaterBodyEntity;
use Src\WaterBody\Domain\WaterBodyType;
use Src\WaterBody\Infrastucture\EloquentWaterBodyRepository;

class WaterBodyController extends Controller{
    private WaterBodyService $waterBodyService;

    public function __construct()
    {
        $this->waterBodyService = new WaterBodyService(new EloquentWaterBodyRepository());
    }

    private function getRules()
    {
        return [
            'name' => 'required|string',
            'water_body_type' => ['required', new Enum(WaterBodyType::class)],
            'latitude' => 'required|numeric',
            'longitude' => 'required|numeric',
            'community_id' => 'required|integer|exists:communities,id',
        ];
    }

    public function index()
    {
        $domainWaterBodies = $this->waterBodyService->getAll();
        $waterBodies = [];
        foreach ($domainWaterBodies as $domainWaterBody) {
            $waterBodies[] = $domainWaterBody->toArray();
        }
        return response()->json($waterBodies, 201);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $waterBody = new WaterBodyEntity(0,
            $request->name,
            WaterBodyType::from($request->water_body_type),
            $request->latitude,
            $request->longitude,
            $request->community_id
        );
        $waterBody = $this->waterBodyService->create($waterBody);
        return response()->json($waterBody->toArray(), 201);
    }

    public function show($id)
    {
        $waterBody = $this->waterBodyService->getById($id);
        return response()->json($waterBody->toArray(), 201);
    }

    public function update(Request $request, $id){
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $waterBody = new WaterBodyEntity(
            $id,
            $request->name,
            WaterBodyType::from($request->water_body_type),
            $request->latitude,
            $request->longitude,
            $request->community_id
        );
        $waterBody = $this->waterBodyService->update($waterBody);
        return response()->json($waterBody->toArray(), 201);
    }

    public function destroy($id){
        $deleted = $this->waterBodyService->delete($id);
        if ($deleted) {
            return response()->json(["message" => "Cuerpo de agua eliminado correctamente"]);
        } else {
            return response()->json(["message" => "Error al eliminar el cuerpo de agua"], 500);
        }
    }
}
