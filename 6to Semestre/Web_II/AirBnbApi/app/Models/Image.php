<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Image extends Model
{
    use HasFactory;

    protected $fillable = [
        'url',
        'accommodation_id',
    ];

    public function accommodation()
    {
        return $this->belongsTo(Accommodation::class);
    }
}
