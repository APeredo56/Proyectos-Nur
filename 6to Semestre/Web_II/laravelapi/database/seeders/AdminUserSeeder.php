<?php

namespace Database\Seeders;

use App\Models\User;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class AdminUserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $adminUser = User::create([
            "name"     => "admin",
            "email"    => "admin@admin.com",
            "password" => bcrypt("admin")
        ]);
        $adminUser->assignRole("admin");
    }
}
