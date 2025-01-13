<?php

namespace App\Http\Controllers;

use App\Models\SamplingAnalysisImage;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\Point\Application\PointService;
use Src\Point\Infrastucture\EloquentPointRepository;
use Src\SamplingAnalysisImage\Application\SamplingAnalysisImageRepository;
use Src\SamplingAnalysisImage\Application\SamplingAnalysisImageService;
use Src\SamplingAnalysisImage\Domain\SamplingAnalysisImageEntity;
use Src\SamplingAnalysisImage\Infrastucture\EloquentSamplingAnalysisImageRepository;

class SamplingAnalysisImageController extends Controller{
    private SamplingAnalysisImageService $samplingAnalysisImageService;
    private PointService $pointService;

    public function __construct()
    {
        $this->samplingAnalysisImageService = new SamplingAnalysisImageService(new EloquentSamplingAnalysisImageRepository());
        $this->pointService = new PointService(new EloquentPointRepository());
    }

    private function getRules()
    {
        return [
            'image' => 'required|image',
            'sampling_analysis_id' => 'required|integer|exists:sampling_analyses,id',
        ];
    }

    public function index()
    {
        $domainSamplingAnalysisImages = $this->samplingAnalysisImageService->getAll();
        $samplingImages = [];
        foreach ($domainSamplingAnalysisImages as $domainSamplingAnalysisImage) {
            $samplingImages[] = $domainSamplingAnalysisImage->toArray();
        }
        return response()->json($samplingImages, 201);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $samplingAnalysisImage = new SamplingAnalysisImageEntity(0,
            '',
            $request->sampling_analysis_id,
        );
        $samplingAnalysisImage = $this->samplingAnalysisImageService->create($samplingAnalysisImage);
        $image = $request->file('image');
        $path = 'uploads/sampling_analyses/';
        $name = $samplingAnalysisImage->getId() . '.' . $image->extension();
        $image->move($path, $name);
        $samplingAnalysisImage->setImageUrl($path . $name);

        $updateSamplingAnalysisImage = new SamplingAnalysisImageEntity(
            $samplingAnalysisImage->getId(),
            $samplingAnalysisImage->getImageUrl(),
            $samplingAnalysisImage->getSamplinganalysisId(),
        );
        $updateSamplingAnalysisImage = $this->samplingAnalysisImageService->update($updateSamplingAnalysisImage);
        $this->pointService->addPoints($updateSamplingAnalysisImage->getSamplingAnalysis()->getUserId(), 10);
        return response()->json($updateSamplingAnalysisImage->toArray(), 201);
    }

    public function show($id)
    {
        $samplingAnalysisImage = $this->samplingAnalysisImageService->getById($id);
        return response()->json($samplingAnalysisImage->toArray(), 201);
    }

    public function update(Request $request, $id){
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $image = $request->file('image');
        $path = 'uploads/sampling_analyses/';
        $name = $id . '.' . $image->extension();
        $image->move($path, $name);
        $samplingAnalysisImage = new SamplingAnalysisImageEntity(
            $id,
            $path . $name,
            $request->sampling_analysis_id,
        );
        $samplingAnalysisImage = $this->samplingAnalysisImageService->update($samplingAnalysisImage);
        return response()->json($samplingAnalysisImage->toArray(), 201);
    }

    public function destroy($id){
        $samplingAnalysisImage = $this->samplingAnalysisImageService->getById($id);
        $deleted = $this->samplingAnalysisImageService->delete($id);
        $this->pointService->subtractPoints($samplingAnalysisImage->getSamplingAnalysis()->getUserId(), 10);
        if ($deleted) {
            return response()->json(["message" => "Imagen eliminada correctamente"]);
        } else {
            return response()->json(["message" => "Error al eliminar la imagen"], 500);
        }
    }
}
