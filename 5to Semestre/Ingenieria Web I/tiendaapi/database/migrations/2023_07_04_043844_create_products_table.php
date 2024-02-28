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
        Schema::create('products', function (Blueprint $table) {
            $table->id();
            $table->string('nombre', 200);
            $table->float('precio');
            $table->boolean('disponible')->default(true);
            $table->string('descripcion', 700);
            $table->string('img_url', 300);
            $table->bigInteger('id_categoria')->unsigned();
            $table->foreign('id_categoria')->references('id')->on('subcategories')->onDelete('cascade');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('products');
    }
};
