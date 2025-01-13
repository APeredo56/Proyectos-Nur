<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('sampling_analyses', function (Blueprint $table) {
            $table->id();
            $table->float('turbidity');
            $table->float('water_velocity');
            $table->float('width');
            $table->float('average_depth');
            $table->float('flow_rate');
            $table->foreignId('water_body_id')->constrained();
            $table->foreignId('user_id')->constrained();
            $table->foreignId('field_trip_id')->constrained();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('sampling_analyses');
    }
};
