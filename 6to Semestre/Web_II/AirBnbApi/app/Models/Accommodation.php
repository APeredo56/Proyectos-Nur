<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Accommodation extends Model
{
    use HasFactory;

    protected $fillable = [
        'name',
        'bedrooms',
        'bathrooms',
        'max_guests',
        'price_per_night',
        'address',
        'description',
        'cleaning_fee',
        'has_wifi',
        'property_type',
        'num_beds',
        'latitude',
        'longitude',
        'user_id',
        'city_id'
    ];

    public function city()
    {
        return $this->belongsTo(City::class);
    }

    public function images()
    {
        return $this->hasMany(Image::class);
    }

    public function bookings()
    {
        return $this->hasMany(Booking::class);
    }

    public function user()
    {
        return $this->belongsTo(User::class);
    }
}
