<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class WaterBodyImage extends Model
{
    protected $fillable = [
        'image_url',
        'water_body_id',
    ];

    public function water_body()
    {
        return $this->belongsTo(WaterBody::class);
    }
}
