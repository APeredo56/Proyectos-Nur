<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Adoption extends Model
{
    use HasFactory;

    protected $fillable = [
        'pet_id',
        'client_id',
        'date',
    ];

    protected $primaryKey = ['pet_id', 'client_id'];

    public $incrementing = false;

    public function pet(){
        return $this->belongsTo(Pet::class, 'pet_id');
    }

    public function client(){
        return $this->belongsTo(Client::class, 'client_id');
    }
}
