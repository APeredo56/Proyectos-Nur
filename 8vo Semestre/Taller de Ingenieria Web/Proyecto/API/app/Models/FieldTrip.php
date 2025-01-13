<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class FieldTrip extends Model
{
    protected $fillable = [
        'description',
        'start_date',
        'end_date',
    ];

    public function water_bodies()
    {
        return $this->belongsToMany(WaterBody::class);
    }

    public function users()
    {
        return $this->belongsToMany(User::class);
    }

    public function sampling_analyses()
    {
        return $this->hasMany(SamplingAnalysis::class);
    }
}
