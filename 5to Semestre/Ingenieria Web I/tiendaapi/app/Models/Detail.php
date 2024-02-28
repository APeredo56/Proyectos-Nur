<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Detail extends Model
{
    use HasFactory;
    protected $fillable = [
        'cantidad',
        'precio',
        'producto_id',
        'pedido_id'
        ];

    public function product(){
        return $this->belongsTo(Product::class, 'producto_id');
    }
}
