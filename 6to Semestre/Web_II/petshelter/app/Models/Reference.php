<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Reference extends Model
{
    use HasFactory;

    protected $fillable = [
        'full_name',
        'phone',
        'relationship',
        'client_id'
    ];

    protected $primaryKey = 'id';

    public function client(){
        return $this->belongsTo(Client::class, 'client_id');
    }
}
