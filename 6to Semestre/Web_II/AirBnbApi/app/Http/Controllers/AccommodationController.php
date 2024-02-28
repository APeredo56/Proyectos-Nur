<?php

namespace App\Http\Controllers;

use App\Models\Accommodation;
use App\Models\City;
use App\Models\Image;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class AccommodationController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return Accommodation::with('city')->get();
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
            'name'        => ['required', 'string'],
            'bedrooms'        => ['required', 'integer'],
            'bathrooms'        => ['required', 'integer'],
            'max_guests'  => ['required', 'integer'],
            'price_per_night'        => ['required', 'numeric'],
            'address'        => ['required', 'string'],
            'description' => ['required', 'string'],
            'cleaning_fee'        => ['required', 'numeric'],
            'has_wifi'        => ['required', 'boolean'],
            'property_type'        => ['required', 'string'],
            'num_beds'        => ['required', 'integer'],
            'latitude'        => ['required', 'numeric'],
            'longitude'        => ['required', 'numeric'],
            'user_id'        => ['required', 'integer'],
            'city_id'        => ['required', 'integer'],
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), 400);
        }
        $accommodation = Accommodation::create($request->json()->all());
        return Accommodation::with('city')->find($accommodation->id);
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $accommodation = Accommodation::with('city')->with('images')->with('user')->find($id);
        if ($accommodation == null) {
            return response()->json([
                'message' => 'Accommodation not found'
            ], 404);
        }
        return $accommodation;
    }

    /**
     * Update the specified resource in storage.
     */
    public function edit(Accommodation $accommodation)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function update(Request $request, $id)
    {
        $validator = Validator::make($request->json()->all(), [
            'name'        => ['required', 'string'],
            'bedrooms'        => ['required', 'integer'],
            'bathrooms'        => ['required', 'integer'],
            'max_guests'  => ['required', 'integer'],
            'price_per_night'        => ['required', 'numeric'],
            'address'        => ['required', 'string'],
            'description' => ['required', 'string'],
            'cleaning_fee'        => ['required', 'numeric'],
            'has_wifi'        => ['required', 'boolean'],
            'property_type'        => ['required', 'string'],
            'num_beds'        => ['required', 'integer'],
            'latitude'        => ['required', 'numeric'],
            'longitude'        => ['required', 'numeric'],
            'user_id'        => ['required', 'integer'],
            'city_id'        => ['required', 'integer'],
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), 400);
        }
        $accommodation = Accommodation::find($id);
        if ($accommodation == null){
            return response()->json(["message" => "Accommodation not found"], 404);
        }
        $accommodation->update($request->json()->all());
        return Accommodation::with('city')->find($accommodation->id);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $accommodation = Accommodation::find($id);
        if ($accommodation == null){
            return response()->json(["message" => "Accommodation not found"], 404);
        }
        $user = auth()->user();
        if ($user->id != $accommodation->user_id){
            return response()->json(["message" => "Unauthorized"], 401);
        }
        $bookings = $accommodation->bookings;
        if ($bookings->count() > 0){
            return response()->json(["message" => "Cannot delete accommodation with bookings"], 403);
        }
        $accommodation->delete();
        return response()->json(["message" => "Accommodation deleted"], 200);
    }

    public function search(Request $request)
    {
        $validator = Validator::make($request->json()->all(), [
            "city_id" => ['required', 'integer'],
            "guests" => ['integer', 'min:1'],
            "checkIn" => ['nullable', 'date', 'after_or_equal:' . now()->toDateString()],
            "checkOut" => ['nullable', 'date', 'after:checkIn'],
            "minPricePerNight" => ['numeric', 'min:0'],
            "maxPricePerNight" => ['numeric', 'min:0'],
            "hasWifi" => ['boolean'],
            "propertyType" => ['string'],
            "bedrooms" => ['integer', 'min:0'],
            "bathrooms" => ['integer', 'min:0'],
            "beds" => ['integer', 'min:0'],
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), 400);
        }
        $city = City::find($request->input('city_id'));

        if (!$city) {
            return response()->json(['message' => 'City not found'], 404);
        }

        $accommodations = Accommodation::with('city')->with('images')->with('user')
            ->where('city_id', $city->id);

        if ($request->filled('guests')) {
            $accommodations->where('max_guests', '>=', $request->input('guests'));
        }

        if ($request->filled('checkIn')) {
            $accommodations->whereDoesntHave('bookings', function ($query) use ($request) {
                $query->where('check_in', '<=', $request->input('checkIn'))
                    ->where('check_out', '>=', $request->input('checkIn'));
            });
        }

        if ($request->filled('checkOut')) {
            $accommodations->whereDoesntHave('bookings', function ($query) use ($request) {
                $query->where('check_in', '<=', $request->input('checkOut'))
                    ->where('check_out', '>=', $request->input('checkOut'));
            });
        }

        if ($city->latitude && $city->longitude) {
            $latitude = $city->latitude;
            $longitude = $city->longitude;
            $distance = $city->searchAreaInKm;

            $accommodations->whereRaw("6371 * 2 * ASIN(SQRT(POW(SIN(({$latitude} - accommodations.latitude)
            * pi()/180 / 2), 2) + COS({$latitude} * pi()/180) * COS(accommodations.latitude * pi()/180)
            * POW(SIN(({$longitude} - accommodations.longitude) * pi()/180 / 2), 2))) <= ?", [$distance]);
        }

        if ($request->filled('minPricePerNight')) {
            $accommodations->where('price_per_night', '>=', $request->input('minPricePerNight'));
        }

        if ($request->filled('maxPricePerNight')) {
            $accommodations->where('price_per_night', '<=', $request->input('maxPricePerNight'));
        }

        if ($request->filled('hasWifi') && $request->input('has_wifi')) {
            $accommodations->where('has_wifi', $request->input('hasWifi'));
        }

        if ($request->filled('propertyType')) {
            $accommodations->where('property_type', $request->input('propertyType'));
        }

        if ($request->filled('bedrooms')) {
            $accommodations->where('bedrooms', '>=', $request->input('bedrooms'));
        }

        if ($request->filled('bathrooms')) {
            $accommodations->where('bathrooms', '>=', $request->input('bathrooms'));
        }

        if ($request->filled('beds')) {
            $accommodations->where('num_beds', '>=', $request->input('beds'));
        }

        $result = $accommodations->orderBy('price_per_night', 'asc')->get();

        return $result;
    }

    public function saveAccommodationImage(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'accommodation_id' => ['required', 'integer'],
            'image' => ['required', 'image'],
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), 400);
        }
        $accommodation = Accommodation::find($request->input('accommodation_id'));
        if ($accommodation == null){
            return response()->json(["message" => "Accommodation not found"], 404);
        }
        $imgObject = Image::create([
            'url' => '',
            'accommodation_id' => $accommodation->id,
        ]);
        $image = $request->file('image');
        $name = $imgObject->id . '.' . $image->extension();
        $imgObject->update([
            'url' => 'uploads/accommodations/' . $name,
        ]);
        $image->move('uploads/accommodations/', $name);
        return response()->json(["message" => "Image saved"], 200);
    }

    public function getAccommodationsByUser()
    {
        $user = auth()->user();
        $accommodations = Accommodation::with('images')->with('city')
            ->where('user_id', $user->id)->get();
        return $accommodations;
    }

    public function getAccommodationTypes()
    {
        $accommodationTypes = Accommodation::select('property_type')->distinct()->get();
        return $accommodationTypes;
    }
}
