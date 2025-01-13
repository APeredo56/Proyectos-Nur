<?php

namespace App\Http\Controllers;

use App\Models\FieldTrip;
use App\Models\User;
use App\Models\WaterBody;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\FieldTrip\Application\FieldTripService;
use Src\FieldTrip\Application\FieldTripUserService;
use Src\FieldTrip\Application\FieldTripWaterBodyService;
use Src\FieldTrip\Domain\FieldTripEntity;
use Src\FieldTrip\Infrastucture\EloquentFieldTripRepository;
use Src\FieldTrip\Infrastucture\EloquentFieldTripUserRepository;
use Src\FieldTrip\Infrastucture\EloquentFieldTripWaterBodyRepository;

class FieldTripController extends Controller{
    private FieldTripService $fieldTripService;
    private FieldTripUserService $fieldTripUserService;
    private FieldTripWaterBodyService $fieldTripWaterBodyService;

    public function __construct()
    {
        $this->fieldTripService = new FieldTripService(new EloquentFieldTripRepository());
        $this->fieldTripUserService = new FieldTripUserService(new EloquentFieldTripUserRepository());
        $this->fieldTripWaterBodyService = new FieldTripWaterBodyService(new EloquentFieldTripWaterBodyRepository());
    }

    private function getRules()
    {
        return [
            'description' => 'required|string',
            'start_date' => 'required|date',
            'end_date' => 'required|date',
        ];
    }

    public function index()
    {
        $domainFieldTrips = $this->fieldTripService->getAll();
        $fieldTrips = [];
        foreach ($domainFieldTrips as $domainFieldTrip) {
            $fieldTrips[] = $domainFieldTrip->toArray();
        }
        return response()->json($fieldTrips, 201);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $fieldTrip = new FieldTripEntity(0,
            $request->description,
            Carbon::parse($request->start_date),
            Carbon::parse($request->end_date),
        );
        $fieldTrip = $this->fieldTripService->create($fieldTrip);
        return response()->json($fieldTrip->toArray(), 201);
    }

    public function show($id)
    {
        $fieldTrip = $this->fieldTripService->getById($id);
        return response()->json($fieldTrip->toArray(), 201);
    }

    public function update(Request $request, $id){
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $fieldTrip = new FieldTripEntity(
            $id,
            $request->description,
            Carbon::parse($request->start_date),
            Carbon::parse($request->end_date),
        );
        $fieldTrip = $this->fieldTripService->update($fieldTrip);
        return response()->json($fieldTrip->toArray(), 201);
    }

    public function destroy($id){
        $deleted = $this->fieldTripService->delete($id);
        if ($deleted) {
            return response()->json(["message" => "Salida de campo eliminada correctamente"]);
        } else {
            return response()->json(["message" => "Error al eliminar la salida de campo"], 500);
        }
    }

    public function getByUserId($userId){
        $domainFieldTrips = $this->fieldTripService->getByUserId($userId);
        $fieldTrips = [];
        foreach ($domainFieldTrips as $domainFieldTrip) {
            $fieldTrips[] = $domainFieldTrip->toArray();
        }
        return response()->json($fieldTrips, 201);
    }

    public function assignUser(Request $request, FieldTrip $fieldTrip, User $user){
        if ($fieldTrip->users->contains($user)) {
            return response()->json(["message" => "Usuario ya asignado a la salida de campo"], 422);
        }
        if (!$user->hasRole('Tecnico')) {
            return response()->json(["message" => "Solo se pueden asignar usuarios con rol de técnico"], 422);
        }
        $this->fieldTripUserService->assignUserToFieldTrip($fieldTrip->id, $user->id);
        return response()->json(["message" => "Usuario asignado a la salida de campo"]);
    }

    public function unassignUser(Request $request, FieldTrip $fieldTrip, User $user){

        if (!$fieldTrip->users->contains($user)) {
            return response()->json(["message" => "El usuario no esta asignado a la salida de campo"], 422);
        }
        $this->fieldTripUserService->unAssignUserToFieldTrip($fieldTrip->id, $user->id);
        return response()->json(["message" => "Usuario desasignado de la salida de campo"]);
    }

    public function assignWaterBody(Request $request, FieldTrip $fieldTrip, WaterBody $waterBody){
        if ($fieldTrip->water_bodies->contains($waterBody)) {
            return response()->json(["message" => "Cuerpo de agua ya asignado a la salida de campo"], 422);
        }
        $this->fieldTripWaterBodyService->assignWaterBodyToFieldTrip($fieldTrip->id, $waterBody->id);
        return response()->json(["message" => "Cuerpo de agua asignado a la salida de campo"]);
    }

    public function unassignWaterBody(Request $request, FieldTrip $fieldTrip, WaterBody $waterBody){
        if (!$fieldTrip->water_bodies->contains($waterBody)) {
            return response()->json(["message" => "El cuerpo de agua no esta asignado a la salida de campo"], 422);
        }
        $this->fieldTripWaterBodyService->unAssignWaterBodyToFieldTrip($fieldTrip->id, $waterBody->id);
        return response()->json(["message" => "Cuerpo de agua desasignado de la salida de campo"]);
    }
}
