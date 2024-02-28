<?php /* @var App\Models\Pet $pet */ ?>

@extends('layouts.app')
@section('content')
    <div class="container">
        <div class="row">
            <div class="col-8">
                <div class="card mt-3 mb-3">
                    <div class="card-body primary-bg rounded">
                        <h5 class="card-title text-white">Formulario de Mascotas</h5>
                        <form action="/api/pets" method="POST" id="pet_form">
                            @csrf
                            <input type="hidden" id="hdnIdInput" name="id"
                                value="{{$pet?$pet->id:""}}">
                            <div class="mb-3">
                                <label for="name" class="form-label text-white">Nombre</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       value="{{$pet?$pet->name:""}}" required>
                            </div>
                            <div class="mb-3">
                                <label for="type" class="form-label text-white">Tipo</label>
                                <input type="text" class="form-control" id="type" name="type"
                                       value="{{$pet?$pet->type:""}}" required>
                            </div>
                            <div class="mb-3">
                                <label for="breed" class="form-label text-white">Raza</label>
                                <input type="text" class="form-control" id="breed" name="breed"
                                       value="{{$pet?$pet->breed:""}}" required>
                            </div>
                            <div class="mb-3">
                                <label for="color" class="form-label text-white">Color</label>
                                <input type="text" class="form-control" id="color" name="color"
                                       value="{{$pet?$pet->color:""}}" required>
                            </div>
                            <div class="mb-3">
                                <label for="size" class="form-label text-white">Tamaño</label>
                                <input type="number" step="0.01" class="form-control" id="size"
                                       name="size" value="{{$pet?$pet->size:""}}" required>
                            </div>
                            <button type="submit" class="btn secondary-bg text-white">Guardar</button>
                            <a href="{{ route('pets') }}" class="btn btn-danger">Cancelar</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>

        document.getElementById('pet_form').addEventListener('submit', function(event) {
            event.preventDefault(); // Prevenir la recarga de la página al enviar el formulario

            // Obtener los datos del formulario como un objeto
            const formData = new FormData(this);
            const formDataObject = {};
            formData.forEach((value, key) => {
                formDataObject[key] = value;
            });

            // Realizar la solicitud a la API usando fetch
            fetch('/api/pets{{$pet?"/$pet->id":""}}', {
                method: '{{ $pet ? 'PUT' : 'POST' }}',
                headers: {
                    'Content-Type': 'application/json', // Indicar que los datos se envían como JSON
                },
                body: JSON.stringify(formDataObject), // Convertir el objeto a JSON
            })
                .then(response => response.json()) // Parsear la respuesta JSON
                .then(data => {
                    window.location.href = '/pets';
                })
                .catch(error => {
                    console.error('Error:', error); // Manejar errores de la solicitud
                });
        });
    </script>
@endsection
