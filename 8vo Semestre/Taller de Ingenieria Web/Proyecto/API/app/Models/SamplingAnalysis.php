<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class SamplingAnalysis extends Model
{
    protected $fillable = [
        'turbidity',
        'water_velocity',
        'width',
        'average_depth',
        'flow_rate',
        'water_body_id',
        'user_id',
        'field_trip_id'
    ];

    public function water_body()
    {
        return $this->belongsTo(WaterBody::class);
    }

    public function user()
    {
        return $this->belongsTo(User::class);
    }

    public function sampling_analysis_images()
    {
        return $this->hasMany(SamplingAnalysisImage::class);
    }

    public function field_trip()
    {
        return $this->belongsTo(FieldTrip::class);
    }
}
