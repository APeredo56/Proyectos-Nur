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
        Schema::create('accommodations', function (Blueprint $table) {
            $table->id();
            $table->string('name', 200);
            $table->integer('bedrooms');
            $table->integer('bathrooms');
            $table->integer('max_guests');
            $table->decimal('price_per_night');
            $table->string('address', 400);
            $table->decimal('latitude', 10, 8);
            $table->decimal('longitude', 10, 8);
            $table->text('description');
            $table->decimal('cleaning_fee');
            $table->boolean('has_wifi');
            $table->string('property_type', 200);
            $table->integer('num_beds');
            $table->bigInteger('user_id')->unsigned();
            $table->bigInteger('city_id')->unsigned();
            $table->foreign('user_id')->references('id')->on('users');
            $table->foreign('city_id')->references('id')->on('cities');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('accomodations');
    }
};
