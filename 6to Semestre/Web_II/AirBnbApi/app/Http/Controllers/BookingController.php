<?php

namespace App\Http\Controllers;

use App\Models\Booking;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use App\Models\Accommodation;

class BookingController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        Booking::with('accommodation')->get();
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create(Request $request)
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validator = Validator::make($request->json()->all(), [
            'check_in'        => ['required', 'date'],
            'check_out'        => ['required', 'date'],
            'total_price'        => ['required', 'numeric'],
            'credit_card_name'        => ['required', 'string'],
            'credit_card_number'        => ['required', 'string'],
            'credit_card_cvv'        => ['required', 'string'],
            'credit_card_expiry'        => ['required', 'date'],
            'accommodation_id'        => ['required', 'integer'],
            'owner_id'        => ['required', 'integer'],
            'client_id'        => ['required', 'integer'],
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), 400);
        }
        $accommodation = Accommodation::find($request->json()->get('accommodation_id'));
        if ($accommodation == null) {
            return response()->json([
                'message' => 'Accommodation not found',
            ], 404);
        }
        if ($accommodation->user_id == $request->json()->get('client_id')) {
            return response()->json([
                'message' => 'You cannot book your own accommodation',
            ], 400);
        }
        $accommodationBookings = Booking::where('accommodation_id', $accommodation->id)->get();
        foreach ($accommodationBookings as $accommodationBooking) {
            if ($accommodationBooking->check_in <= $request->json()->get('check_in') && $accommodationBooking->check_out >= $request->json()->get('check_in')) {
                return response()->json([
                    'message' => 'Accommodation already booked for this period',
                ], 400);
            }
            if ($accommodationBooking->check_in <= $request->json()->get('check_out') && $accommodationBooking->check_out >= $request->json()->get('check_out')) {
                return response()->json([
                    'message' => 'Accommodation already booked for this period',
                ], 400);
            }
        }

        $booking = Booking::create($request->json()->all());
        return Booking::with('accommodation')->find($booking->id);
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $booking = Booking::with('accommodation')->find($id);
        if ($booking == null) {
            return response()->json([
                'message' => 'Booking not found',
            ], 404);
        }
        return $booking;
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Booking $booking)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Booking $booking)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $booking = Booking::find($id);
        if ($booking == null) {
            return response()->json([
                'message' => 'Booking not found',
            ], 404);
        }
        $user = auth()->user();
        if ($booking->owner_id != $user->id) {
            return response()->json([
                'message' => 'You are not the owner of this booking',
            ], 403);
        }

        $booking->delete();
        return response()->json([
            'message' => 'Booking deleted',
        ], 200);
    }

    public function getBookingsByUser()
    {
        $user = auth()->user();
        $bookings = Booking::with('accommodation.images', 'accommodation.city')
            ->where('client_id', $user->id)->orderBy('check_in', 'asc')
            ->get();
        return $bookings;
    }

    public function getBookingsByAccommodation($id)
    {
        $accommodation = Accommodation::find($id);
        if ($accommodation == null) {
            return response()->json([
                'message' => 'Accommodation not found',
            ], 404);
        }
        $user = auth()->user();
        if ($accommodation->user_id != $user->id) {
            return response()->json([
                'message' => 'You are not the owner of this accommodation',
            ], 403);
        }
        $bookings = Booking::with('accommodation')->with('client')
            ->where('accommodation_id', $id)->get();
        return $bookings;
    }
}
