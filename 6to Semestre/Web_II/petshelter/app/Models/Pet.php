<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Pet extends Model
{
    use HasFactory;

    protected $fillable = [
        'name',
        'type',
        'breed',
        'color',
        'size',
        'availability'
    ];

    protected $primaryKey = 'id';

    public function clients()
    {
        return $this->belongsToMany(Client::class, 'adoptions',
            'pet_id', 'client_id')->withPivot('date');
    }
}
