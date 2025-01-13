<?php


namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Medal extends Model
{
    // Campos permitidos para asignación masiva
    protected $fillable = [
        'name',
        'points_value',
    ];

    // Si necesitas reglas o relaciones, puedes definirlas aquí.
}
