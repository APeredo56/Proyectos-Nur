<?php

namespace Database\Seeders;

use App\Models\Point;
use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Spatie\Permission\Models\Role;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        // User::factory(10)->create();

        User::factory()->create([
            'name' => 'Test User',
            'email' => 'test@example.com',
        ]);

        $rolAdministrativo     = Role::create(['name' => 'Administrativo']);
        $rolTecnico    = Role::create(['name' => 'Tecnico']);

        $admin = User::create([
            'name' => 'Admin',
            'email' => 'admin@example.com',
            'password' => bcrypt('password'),
        ]);

        $admin->assignRole('Administrativo');

        $tecnico = User::create([
            'name' => 'Tecnico',
            'email' => 'tecnico@example.com',
            'password' => bcrypt('password')
        ]);

        $tecnico->assignRole('Tecnico');

        $tecnicoPoints = Point::create([
            'user_id' => $tecnico->id,
            'total_points' => 0,
            'level' => 0
        ]);

    }
}
