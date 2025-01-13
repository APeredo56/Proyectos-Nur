<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Src\Medal\Application\MedalService;
use Src\Medal\Infrastructure\EloquentMedalRepository;

class MedalController extends Controller
{
    private MedalService $medalService;

    public function __construct()
    {
        $this->medalService = new MedalService(new EloquentMedalRepository());
    }

    private function getRules()
    {
        return [
            'name' => 'required|string|max:255',
            'points_value' => 'required|integer',
        ];
    }

    public function index()
    {
        $domainMedals = $this->medalService->getAll();
        $medals = [];
        foreach ($domainMedals as $domainMedal) {
            $medals[] = $domainMedal->toArray();
        }
        return response()->json($medals, 200);
    }

    public function show($id)
    {
        $medal = $this->medalService->getById($id);
        return response()->json($medal->toArray(), 200);
    }

    public function store(Request $request)
    {
        $validated = $request->validate($this->getRules());

        $medalEntity = new \Src\Medal\Domain\MedalEntity(
            0, // Assuming ID will be auto-generated
            $validated['name'],
            $validated['points_value']
        );

        $medal = $this->medalService->create($medalEntity);

        return response()->json($medal->toArray(), 201);
    }

    public function update(Request $request, $id)
    {
        $validated = $request->validate($this->getRules());

        $medalEntity = new \Src\Medal\Domain\MedalEntity(
            $id,
            $validated['name'],
            $validated['points_value']
        );

        $medal = $this->medalService->update($medalEntity);

        return response()->json($medal->toArray(), 200);
    }

    public function destroy($id)
    {
        $this->medalService->delete($id);
        return response()->json(['message' => 'Medal deleted successfully'], 200);
    }
}
