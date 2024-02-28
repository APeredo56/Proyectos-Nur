<?php

namespace App\Http\Controllers;

use App\Models\Order;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Symfony\Component\HttpFoundation\Response as ResponseAlias;

class OrderController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $order = Order::all();
        return $order;
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
            'persona_id'         => 'required',
        ]);
        if ($validator->fails()) {
            return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
        }

        $order = new Order();
        $order->fill($request->json()->all());
        $order->save();

        return $order;
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $order = Order::find($id);
        if($order == null){
            return response()->json(['message' => 'Order not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        return $order;
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Order $order)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        $order = Order::find($id);
        if($order == null){
            return response()->json(['message' => 'Order not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }
        if ($request->method() != "PATCH") {
            $validator = Validator::make($request->json()->all(), [
                'persona_id'         => 'required',
            ]);
            if ($validator->fails()) {
                return response()->json($validator->messages(), ResponseAlias::HTTP_BAD_REQUEST);
            }
        }
        $order->fill($request->json()->all());
        $order->save();

        return $order;
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $order = Order::find($id);
        if ($order == null) {
            return response()->json(["message" => "Item not found"], ResponseAlias::HTTP_NOT_FOUND);
        }
        $order->delete();

        return response()->json(['message' => 'Order deleted successfully.']);
    }

    public function getWithDetails($user_id)
    {
        $order = Order::where('persona_id', $user_id)->with('details.product')->get();
        if($order == null){
            return response()->json(['message' => 'User not found.'], ResponseAlias::HTTP_NOT_FOUND);
        }

        return $order;
    }

    public function getFullOrders()
    {
        $order = Order::with('user', 'details.product')->get();
        return $order;
    }
}
