<?php

namespace App\Http\Controllers;

use App\Models\Community;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Src\Community\Application\CommunityService;
use Src\Community\Domain\CommunityEntity;
use Src\Community\Infrastucture\EloquentCommunityRepository;

class CommunityController extends Controller {
    private CommunityService $communityService;

    public function __construct()
    {
        $this->communityService = new CommunityService(new EloquentCommunityRepository());
    }

    private function getRules()
    {
        return [
            'name' => 'required|string',
            'municipality_id' => 'required|integer|exists:municipalities,id',
        ];
    }

    public function index()
    {
        $domainCommunities = $this->communityService->getAll();
        $communities = [];
        foreach ($domainCommunities as $domainCommunity) {
            $communities[] = $domainCommunity->toArray();
        }
        return response()->json($communities, 201);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }
        $community = new CommunityEntity(0,
            $request->name,
            $request->municipality_id
        );
        $community = $this->communityService->create($community);
        return response()->json($community->toArray(), 201);
    }

    public function show($id)
    {
        $community = $this->communityService->getById($id);
        return response()->json($community->toArray(), 201);
    }

    public function update(Request $request, $id){
        $validator = Validator::make($request->all(), $this->getRules());
        if ($validator->fails()) {
            return response()->json($validator->messages(), 422);
        }

        $community = new CommunityEntity(
            $id,
            $request->name,
            $request->municipality_id
        );
        $community = $this->communityService->update($community);
        return response()->json($community->toArray(), 201);
    }

    public function destroy($id){
        $deleted = $this->communityService->delete($id);
        if ($deleted) {
            return response()->json(["message" => "Comunidad eliminada correctamente"]);
        } else {
            return response()->json(["message" => "Error al eliminar la comunidad"], 500);
        }
    }
}
