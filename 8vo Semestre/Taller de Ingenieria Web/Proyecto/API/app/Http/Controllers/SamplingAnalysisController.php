<?php

namespace App\Http\Controllers;

use App\Models\SamplingAnalysis;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\Point\Application\PointService;
use Src\Point\Infrastucture\EloquentPointRepository;
use Src\SamplingAnalysis\Application\SamplingAnalysisService;
use Src\SamplingAnalysis\Domain\SamplingAnalysisEntity;
use Src\SamplingAnalysis\Infrastucture\EloquentSamplingAnalysisRepository;

class SamplingAnalysisController extends Controller {
    private SamplingAnalysisService $samplingAnalysisService;
    private PointService $pointService;

    public function __construct()
    {
        $this->samplingAnalysisService = new SamplingAnalysisService(new EloquentSamplingAnalysisRepository());
        $this->pointService = new PointService(new EloquentPointRepository());
    }

    private function getRules()
    {
        return [
            'turbidity' => 'required|numeric',
            'water_velocity' => 'required|numeric',
            'width' => 'required|numeric',
            'average_depth' => 'required|numeric',
            'water_body_id' => 'required|integer|exists:water_bodies,id',
            'user_id' => 'required|integer|exists:users,id',
        ];
    }

    public function index()
    {
        $domainSamplings = $this->samplingAnalysisService->getAll();
        $municipalitys = [];
        foreach ($domainSamplings as $domainSampling) {
            $municipalitys[] = $domainSampling->toArray();
        }
        return response()->json($municipalitys, 201);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $samplingAnalysis = new SamplingAnalysisEntity(0,
            $request->turbidity,
            $request->water_velocity,
            $request->width,
            $request->average_depth,
            $request->water_body_id,
            $request->user_id,
            $request->field_trip_id
        );
        $samplingAnalysis = $this->samplingAnalysisService->create($samplingAnalysis);

        $this->pointService->addPoints($request->user_id, 30);
        return response()->json($samplingAnalysis->toArray(), 201);
    }

    public function show($id)
    {
        $samplingAnalysis = $this->samplingAnalysisService->getById($id);
        return response()->json($samplingAnalysis->toArray(), 201);
    }

    public function update(Request $request, $id){
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $samplingAnalysis = new SamplingAnalysisEntity(
            $id,
            $request->turbidity,
            $request->water_velocity,
            $request->width,
            $request->average_depth,
            $request->water_body_id,
            $request->user_id,
            $request->field_trip_id
        );
        $samplingAnalysis = $this->samplingAnalysisService->update($samplingAnalysis);
        return response()->json($samplingAnalysis->toArray(), 201);
    }

    public function destroy($id){
        $samplingAnalysis = $this->samplingAnalysisService->getById($id);
        $deleted = $this->samplingAnalysisService->delete($id);
        $this->pointService->subtractPoints($samplingAnalysis->getUserId(), 30);
        if ($deleted) {
            return response()->json(["message" => "Analisis eliminado correctamente"]);
        } else {
            return response()->json(["message" => "Error al eliminar el analisis"], 500);
        }
    }

    public function getByFieldTripIdAndUserId($fieldTripId, $userId)
    {
        $domainSamplings = $this->samplingAnalysisService->getByFieldTripIdAndUserId($fieldTripId, $userId);
        $municipalitys = [];
        foreach ($domainSamplings as $domainSampling) {
            $municipalitys[] = $domainSampling->toArray();
        }
        return response()->json($municipalitys, 201);
    }
}
