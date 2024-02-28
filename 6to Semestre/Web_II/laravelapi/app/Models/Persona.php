<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Persona
 *
 * @property int $id
 * @property string $nombres
 * @property string $apellidos
 * @property string $ciudad
 * @property int $edad
 * @property string $fechaNacimiento
 * @property string $genero
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @method static \Illuminate\Database\Eloquent\Builder|Persona newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Persona newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|Persona query()
 * @method static \Illuminate\Database\Eloquent\Builder|Persona whereApellidos($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Persona whereCiudad($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Persona whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Persona whereEdad($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Persona whereFechaNacimiento($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Persona whereGenero($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Persona whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Persona whereNombres($value)
 * @method static \Illuminate\Database\Eloquent\Builder|Persona whereUpdatedAt($value)
 * @mixin \Eloquent
 */
class Persona extends Model
{
    use HasFactory;

    protected $fillable = [
        'nombres', 'apellidos',
        'ciudad', 'edad',
        'fechaNacimiento', 'genero'
    ];
    protected $hidden = [
        'created_at', 'updated_at'
    ];

    public function mascotas()
    {
        return $this->hasMany(Mascota::class)->select(["id", "nombre", "tipo", "persona_id"]);
    }
}
