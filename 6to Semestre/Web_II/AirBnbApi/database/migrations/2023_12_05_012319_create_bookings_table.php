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
        Schema::create('bookings', function (Blueprint $table) {
            $table->id();
            $table->date('check_in');
            $table->date('check_out');
            $table->decimal('total_price');
            $table->string('credit_card_name', 200);
            $table->string('credit_card_number');
            $table->integer('credit_card_cvv');
            $table->date('credit_card_expiry');
            $table->bigInteger('owner_id')->unsigned();
            $table->bigInteger('client_id')->unsigned();
            $table->bigInteger('accommodation_id')->unsigned();
            $table->foreign('owner_id')->references('id')->on('users');
            $table->foreign('client_id')->references('id')->on('users');
            $table->foreign('accommodation_id')->references('id')->on('accommodations');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('bookings');
    }
};
