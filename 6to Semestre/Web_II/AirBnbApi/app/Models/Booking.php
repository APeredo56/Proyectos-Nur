<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Booking extends Model
{
    use HasFactory;

    protected $fillable = [
        'check_in',
        'check_out',
        'adults',
        'children',
        'total_price',
        'credit_card_name',
        'credit_card_number',
        'credit_card_cvv',
        'credit_card_expiry',
        'owner_id',
        'client_id',
        'accommodation_id',
    ];

    public function accommodation()
    {
        return $this->belongsTo(Accommodation::class);
    }

    public function owner()
    {
        return $this->belongsTo(User::class);
    }

    public function client()
    {
        return $this->belongsTo(User::class);
    }

}
