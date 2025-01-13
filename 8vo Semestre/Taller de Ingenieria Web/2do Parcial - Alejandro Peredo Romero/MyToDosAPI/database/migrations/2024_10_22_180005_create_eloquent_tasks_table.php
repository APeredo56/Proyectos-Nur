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
        Schema::create('eloquent_tasks', function (Blueprint $table) {
            $table->id();
            $table->string('title', 200);
            $table->text('description');
            $table->date('start_date');
            $table->date('end_date');
            $table->boolean('completed')->default(false);
            $table->timestamps();
        });

        Schema::create('user_tasks', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('eloquent_task_id');
            $table->unsignedBigInteger('eloquent_user_id');
            $table->foreign('eloquent_task_id')->references('id')->on('eloquent_tasks')->onDelete('cascade');
            $table->foreign('eloquent_user_id')->references('id')->on('eloquent_users')->onDelete('cascade');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('user_tasks');
        Schema::dropIfExists('eloquent_tasks');
    }
};
