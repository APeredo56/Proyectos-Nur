<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Spatie\Permission\Models\Permission;
use Spatie\Permission\Models\Role;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {

        $adminRole     = Role::create(['name' => 'admin']);
        $clientRole    = Role::create(['name' => 'client']);
        $editPersona   = Permission::create(['name' => 'edit personas']);
        $insertPersona = Permission::create(['name' => 'insert personas']);
        $deletePersona = Permission::create(['name' => 'delete personas']);
        $showPersona   = Permission::create(['name' => 'show personas']);
        $editMascota   = Permission::create(['name' => 'edit mascotas']);
        $insertMascota = Permission::create(['name' => 'insert mascotas']);
        $deleteMascota = Permission::create(['name' => 'delete mascotas']);
        $showMascota   = Permission::create(['name' => 'show mascotas']);

        $adminRole->syncPermissions([
            $editPersona,
            $insertPersona,
            $deletePersona,
            $showPersona,
            $editMascota,
            $insertMascota,
            $deleteMascota,
            $showMascota
        ]);
        $clientRole->syncPermissions([
            $showPersona,
            $showMascota
        ]);
    }
}
