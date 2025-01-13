<?php

namespace App\Http\Controllers;

use App\Models\Department;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\Department\Application\DepartmentService;
use Src\Department\Domain\DepartmentEntity;
use Src\Department\Infraestructure\EloquentDepartmentRepository;

class DepartmentController extends Controller
{
    private DepartmentService $departmentService;

    public function __construct()
    {
        $this->departmentService = new DepartmentService(new EloquentDepartmentRepository());
    }

    private function getRules()
    {
        return [
            'name' => 'required|string',
        ];
    }

    public function index()
    {
        $domainDepartments = $this->departmentService->getAll();
        $departments = [];
        foreach ($domainDepartments as $domainDepartment) {
            $departments[] = $domainDepartment->toArray();
        }
        return response()->json($departments, 201);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $department = new DepartmentEntity(0,
            $request->name,
        );
        $department = $this->departmentService->create($department);
        return response()->json($department->toArray(), 201);
    }

    public function show($id)
    {
        $department = $this->departmentService->getById($id);
        return response()->json($department->toArray(), 201);
    }

    public function update(Request $request, $id){
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $department = new DepartmentEntity(
            $id,
            $request->name
        );
        $department = $this->departmentService->update($department);
        return response()->json($department->toArray(), 201);
    }

    public function destroy($id){
        $deleted = $this->departmentService->delete($id);
        if ($deleted) {
            return response()->json(["message" => "Departmento eliminado correctamente"]);
        } else {
            return response()->json(["message" => "Error al eliminar el deparmento"], 500);
        }
    }
}
