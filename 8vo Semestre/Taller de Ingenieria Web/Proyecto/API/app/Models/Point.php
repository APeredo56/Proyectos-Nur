<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Point extends Model
{
    protected $fillable = [
        'user_id',
        'total_points',
        'level',
    ];

    public function user()
    {
        return $this->belongsTo(User::class);
    }
}