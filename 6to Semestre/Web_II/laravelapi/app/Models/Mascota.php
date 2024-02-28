<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Mascota extends Model
{
    use HasFactory;

    protected $fillable = [
        "nombre", "tipo", "persona_id"
    ];

    public function propietario()
    {
        return $this->belongsTo(Persona::class,"persona_id")
                    ->select(["id", "nombres", "apellidos"]);
    }
}
