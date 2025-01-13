<?php

namespace App\Http\Controllers;

use App\Models\Point;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\Point\Application\PointService;
use Src\Point\Infrastucture\EloquentPointRepository;

class PointController extends Controller{
    private PointService $pointService;

    public function __construct()
    {
        $this->pointService = new PointService(new EloquentPointRepository());
    }

    private function getRules()
    {
        return [
            'user_id' => 'required|integer|exists:users,id',
        ];
    }

    public function index()
    {
        $domainPoints = $this->pointService->getAll();
        $municipalitys = [];
        foreach ($domainPoints as $domainPoint) {
            $municipalitys[] = $domainPoint->toArray();
        }
        return response()->json($municipalitys, 201);
    }

    public function show($id)
    {
        $point = $this->pointService->getById($id);
        return response()->json($point->toArray(), 201);
    }

    public function getByUser(Request $request, $user_id){
        $points = $this->pointService->getByUserId($user_id);
        if (empty($points)){
            return response()->json(['message' => 'No points found'], 404);
        }
        return response()->json($points->toArray(), 201);
    }

    public function ranking(Request $request){
        $points = $this->pointService->getRanking();
        return response()->json($points, 201);
    }

}
