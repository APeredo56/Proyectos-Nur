<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class SamplingAnalysisImage extends Model
{
    protected $fillable = [
        'image_url',
        'sampling_analysis_id',
    ];

    public function sampling_analysis()
    {
        return $this->belongsTo(SamplingAnalysis::class);
    }
}
