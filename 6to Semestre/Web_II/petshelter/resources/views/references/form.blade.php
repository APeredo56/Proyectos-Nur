<?php /* @var App\Models\Reference $reference */ ?>

@extends('layouts.app')
@section('content')
    <div class="container">
        <div class="row">
            <div class="col-8">
                <div class="card mt-3 mb-3">
                    <div class="card-body primary-bg rounded">
                        <h5 class="card-title text-white">Formulario de Referencias</h5>
                        <form id="reference_form">
                            @csrf
                            <input type="hidden" id="hdnIdInput" name="id"
                                   value="{{$reference?$reference->id:""}}">
                            <input type="hidden" id="hdnClientIdInput" name="client_id"
                                   value="{{$reference?$reference->client_id:""}}">
                            <div class="mb-3">
                                <label for="name" class="form-label text-white">Nombre Completo</label>
                                <input type="text" class="form-control" id="name" name="full_name"
                                       value="{{$reference?$reference->full_name:""}}" required>
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label text-white">Teléfono</label>
                                <input type="number" class="form-control" id="phone" name="phone"
                                       value="{{$reference?$reference->phone:""}}" required>
                            </div>
                            <div class="mb-3">
                                <label for="relationship" class="form-label text-white">Parentesco</label>
                                <input type="text" class="form-control" id="relationship" name="relationship"
                                       value="{{$reference?$reference->relationship:""}}" required>
                            </div>
                            <button type="submit" class="btn secondary-bg text-white">Guardar</button>
                            <a href="{{ route('references') }}" class="btn btn-danger">Cancelar</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>

        document.getElementById('reference_form').addEventListener('submit', function(event) {
            event.preventDefault(); // Prevenir la recarga de la página al enviar el formulario

            // Obtener los datos del formulario como un objeto
            const formData = new FormData(this);
            const formDataObject = {};
            formData.forEach((value, key) => {
                formDataObject[key] = value;
            });

            // Realizar la solicitud a la API usando fetch
            fetch('/api/references{{$reference?"/$reference->id":""}}', {
                method: '{{ $reference ? 'PUT' : 'POST' }}',
                headers: {
                    'Content-Type': 'application/json', // Indicar que los datos se envían como JSON
                },
                body: JSON.stringify(formDataObject), // Convertir el objeto a JSON
            })
                .then(response => response.json()) // Parsear la respuesta JSON
                .then(data => {
                    window.location.href = '/references';
                })
                .catch(error => {
                    console.error('Error:', error); // Manejar errores de la solicitud
                });
        });
    </script>
@endsection
