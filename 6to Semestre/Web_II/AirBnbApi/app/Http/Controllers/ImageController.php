<?php

namespace App\Http\Controllers;

use App\Models\Image;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;

class ImageController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //
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
        //
    }

    /**
     * Display the specified resource.
     */
    public function show(Image $image)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Image $image)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Image $image)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $image = Image::with('accommodation')->find($id);
        if (!$image) {
            return response()->json(['message' => 'Image not found'], 404);
        }
        $user = auth()->user();
        if ($image->accommodation->user_id != $user->id) {
            return response()->json(['message' => 'You are not the owner of this image'], 403);
        }
        $image->delete();
        Storage::delete($image->url);
        return response()->json(['message' => 'Image deleted successfully'], 200);
    }
}
