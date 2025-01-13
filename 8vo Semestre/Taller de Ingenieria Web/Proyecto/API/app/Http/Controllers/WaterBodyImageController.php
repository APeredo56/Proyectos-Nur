<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\WaterBodyImage\Application\WaterBodyImageService;
use Src\WaterBodyImage\Domain\WaterBodyImageEntity;
use Src\WaterBodyImage\Infrastucture\EloquentWaterBodyImageRepository;

class WaterBodyImageController extends Controller{
    private WaterBodyImageService $waterBodyImageService;

    public function __construct()
    {
        $this->waterBodyImageService = new WaterBodyImageService(new EloquentWaterBodyImageRepository());
    }

    private function getRules()
    {
        return [
            'image' => 'required|image',
            'water_body_id' => 'required|integer|exists:water_bodies,id',
        ];
    }

    public function index()
    {
        $domainWaterBodyImages = $this->waterBodyImageService->getAll();
        $waterBodyImages = [];
        foreach ($domainWaterBodyImages as $domainWaterBodyImage) {
            $waterBodyImages[] = $domainWaterBodyImage->toArray();
        }
        return response()->json($waterBodyImages, 201);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $waterBodyImage = new WaterBodyImageEntity(0,
            '',
            $request->water_body_id,
        );
        $waterBodyImage = $this->waterBodyImageService->create($waterBodyImage);
        $image = $request->file('image');
        $path = 'uploads/water_bodies/';
        $name = $waterBodyImage->getId() . '.' . $image->extension();
        $image->move($path, $name);
        $waterBodyImage->setImageUrl($path . $name);

        $updatedWaterBodyImage = new WaterBodyImageEntity(
            $waterBodyImage->getId(),
            $waterBodyImage->getImageUrl(),
            $waterBodyImage->getWaterBodyId(),
        );
        $updatedWaterBodyImage = $this->waterBodyImageService->update($updatedWaterBodyImage);
        return response()->json($updatedWaterBodyImage->toArray(), 201);
    }

    public function show($id)
    {
        $waterBodyImage = $this->waterBodyImageService->getById($id);
        return response()->json($waterBodyImage->toArray(), 201);
    }

    public function update(Request $request, $id){
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $image = $request->file('image');
        $path = 'uploads/water_bodies/';
        $name = $id . '.' . $image->extension();
        $image->move($path, $name);
        $waterBody = new WaterBodyImageEntity(
            $id,
            $path . $name,
            $request->water_body_id,
        );
        $waterBody = $this->waterBodyImageService->update($waterBody);
        return response()->json($waterBody->toArray(), 201);
    }

    public function destroy($id){
        $deleted = $this->waterBodyImageService->delete($id);
        if ($deleted) {
            return response()->json(["message" => "Imagen eliminada correctamente"]);
        } else {
            return response()->json(["message" => "Error al eliminar la imagen"], 500);
        }
    }
}
