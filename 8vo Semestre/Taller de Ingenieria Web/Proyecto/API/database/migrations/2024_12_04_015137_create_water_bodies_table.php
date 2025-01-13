<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;
use Src\WaterBody\Domain\WaterBodyType;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('water_bodies', function (Blueprint $table) {
            $table->id();
            $table->string('name');
            $table->enum('water_body_type', array_column(WaterBodyType::cases(), 'value'));
            $table->float('latitude');
            $table->float('longitude');
            $table->foreignId('community_id')->constrained()->cascadeOnDelete();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('water_bodies');
    }
};
