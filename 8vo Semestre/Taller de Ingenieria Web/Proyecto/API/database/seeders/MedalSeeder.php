<?php

namespace Database\Seeders;

use App\Models\Medal;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class MedalSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Medal::create([
            'name' => 'Medalla de Iniciación',
            'points_value' => 100,
        ]);

        Medal::create([
            'name' => 'Medalla de Superación',
            'points_value' => 200,
        ]);

        Medal::create([
            'name' => 'Medalla de Élite',
            'points_value' => 300,
        ]);
    }
}
