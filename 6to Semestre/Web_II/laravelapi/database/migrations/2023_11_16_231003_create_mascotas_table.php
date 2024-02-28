<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('mascotas', function (Blueprint $table) {
            $table->id();
            $table->string("nombre", 100);
            $table->enum("tipo", ["0", "1", "2", "3", "4"])
                  ->comment("0: Perro, 1: Gato, 2: Loro, 3: Llama, 4: Capibara");
            $table->bigInteger("persona_id")
                  ->unsigned();
            $table->foreign("persona_id")
                  ->references("id")
                  ->on("personas");
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('mascotas');
    }
};
