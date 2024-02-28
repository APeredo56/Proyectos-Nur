<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Client extends Model
{
    use HasFactory;

    protected $fillable = [
        'name',
        'lastname',
        'phone',
        'email',
    ];

    protected $primaryKey = 'id';

    public function references()
    {
        return $this->hasMany(Reference::class);
    }

    public function pets()
    {
        return $this->belongsToMany(Pet::class, 'adoptions',
            'client_id', 'pet_id')->withPivot('date');
    }
}
