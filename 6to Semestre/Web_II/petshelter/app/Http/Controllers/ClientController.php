<?php

namespace App\Http\Controllers;

use App\Models\Client;
use App\Models\Reference;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;
use function PHPUnit\Framework\isEmpty;

class ClientController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return Client::with('references')->get();
    }

    public static function selectById($id)
    {
        $client = Client::with('references')->find($id);
        if ($client == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        return $client;
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validator = Validator::make($request->json()->all(), [
            'name'         => 'required',
            'lastname'           => 'required',
            'phone' => 'required',
            'email'           => 'required',
            'references'         => 'required',
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
        }
        $client = new Client([
            'name' => $request->json('name'),
            'lastname' => $request->json('lastname'),
            'phone' => $request->json('phone'),
            'email' => $request->json('email'),
        ]);
        $client->save();
        foreach (json_decode($request->json('references')) as $reference) {
            $refObject = new Reference([
                'full_name' => $reference->full_name,
                'phone' => $reference->phone,
                'relationship' => $reference->relationship,
                'client_id' => $client->id,
            ]);
            $refObject->save();
        }
        return $client;
    }

    /**
     * Display the specified resource.
     */
    public function show(Client $client)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Client $client)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        $client = Client::find($id);
        if ($client == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $client->fill($request->json()->all());
        $client->save();

        $newReferences = json_decode($request->json('references'), true);
        $oldReferences = ReferenceController::selectByClient($id);
        $erasedReferences = [];
        foreach ($oldReferences as $oldReference) {
            $found = false;
            foreach ($newReferences as $newReference) {
                if (!isset($newReference["id"])){
                    continue;
                }
                if ($oldReference["id"] == $newReference["id"]) {
                    $found = true;
                    break;
                }
            }
            if (!$found) {
                $erasedReferences[] = $oldReference;
            }
        }

        foreach ($erasedReferences as $erasedReference) {
            ReferenceController::destroy($erasedReference["id"]);
        }
        foreach ($newReferences as $newReference) {
            if (isset($newReference["id"])) {
                $refObject = Reference::find($newReference["id"]);
                $refObject->fill($newReference);
            } else {
                $refObject = new Reference([
                    'full_name' => $newReference["full_name"],
                    'phone' => $newReference["phone"],
                    'relationship' => $newReference["relationship"],
                    'client_id' => $client["id"],
                ]);
            }
            $refObject->save();
        }
        return $client;
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $client = Client::find($id);
        if ($client == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $client->delete();
        return response()->json(['message' => 'Pet deleted successfully.']);
    }
}
