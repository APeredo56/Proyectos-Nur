<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class WaterBody extends Model
{
    protected $fillable = [
        'name',
        'water_body_type',
        'latitude',
        'longitude',
        'community_id',
    ];

    public function community()
    {
        return $this->belongsTo(Community::class);
    }

    public function water_body_images()
    {
        return $this->hasMany(WaterBodyImage::class);
    }

    public function field_trips()
    {
        return $this->belongsToMany(FieldTrip::class);
    }

    public function sampling_analyses()
    {
        return $this->belongsToMany(SamplingAnalysis::class);
    }
}
