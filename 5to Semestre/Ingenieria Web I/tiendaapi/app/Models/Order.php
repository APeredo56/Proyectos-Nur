<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Order extends Model
{
    use HasFactory;
    protected $fillable = [
        'persona_id'
        ];

//    public function details()
//    {
//        return $this->hasMany(Detail::class, 'pedido_id')->with(['details' =>function ($query) {
//            $query->select(['cantidad', 'precio', 'producto_id']);
//        }]);
//    }

    public function details()
    {
        return $this->hasMany(Detail::class, 'pedido_id', 'id')->select(['cantidad', 'precio', 'producto_id', 'pedido_id']);
    }

    public function user()
    {
        return $this->belongsTo(User::class, 'persona_id');
    }
}
