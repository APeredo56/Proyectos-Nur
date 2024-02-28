<?php /* @var App\Models\Adoption $adoption */ ?>

@extends('layouts.app')
@section('content')
    <div class="container">
        <div class="row">
            <div class="col-8">
                <div class="card mt-3 mb-3">
                    <div class="card-body primary-bg rounded">
                        <h5 class="card-title text-white">Formulario de Adopciones</h5>
                        <form id="adoption_form">
                            <input type="hidden" id="hdnIdClientInput" name="client_id">
                            <input type="hidden" id="hdnIdPetInput" name="pet_id">
                            @csrf
                            <div class="container primary-variant-bg border rounded-3 mb-3 p-2 text-white
                                h-sm" id="clients_container">

                            </div>
                            <div class="container primary-variant-bg border rounded-3 mb-3 p-2 text-white
                                h-sm" id="pets_container">
                            </div>
                            <button type="submit" class="btn secondary-bg text-white">Guardar</button>
                            <a href="{{ route('adoptions') }}" class="btn btn-danger">Cancelar</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        const clientsContainer = document.getElementById('clients_container');
        const petsContainer = document.getElementById('pets_container');
        fetch("/api/clients", {
            method: 'GET',
        }).then(response => response.json())
            .then(data =>{
               for (const client of data){
                   const clientItem = document.createElement('div');
                     clientItem.classList.add('rounded', 'border', 'p-2', 'mb-2', 'text-white', 'client-item');
                        clientItem.innerHTML = `
                            <span>${client.name}</span>
                            <span>${client.email}</span>
                            <span>${client.phone}</span>
                        `;
                        clientItem.addEventListener('click', function() {
                            const clientIdInput = document.getElementById('hdnIdClientInput');
                            const clientItems = document.querySelectorAll('.client-item');
                            clientItems.forEach(item => {
                                item.classList.remove('secondary-bg');
                            });
                            clientIdInput.value = client.id;
                            clientItem.classList.add('secondary-bg');
                        });
                        clientsContainer.appendChild(clientItem);
               }
            });
        fetch("/api/disponibles", {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json', // Indicar que los datos se envían como JSON
            },
        }).then(response=>response.json())
            .then(data=>{
               for(const pet of data){
                     const petItem = document.createElement('div');
                     petItem.classList.add('rounded', 'border', 'p-2', 'mb-2', 'text-white', 'pet-item');
                     petItem.innerHTML = `
                            <span>${pet.name}</span>
                            <span>${pet.type}</span>
                            <span>${pet.breed}</span>
                     `;
                     petItem.addEventListener('click', function() {
                          const petIdInput = document.getElementById('hdnIdPetInput');
                          const petItems = document.querySelectorAll('.pet-item');
                          petItems.forEach(item => {
                            item.classList.remove('secondary-bg');
                          });
                          petIdInput.value = pet.id;
                          petItem.classList.add('secondary-bg');
                     });
                     petsContainer.appendChild(petItem);
               }
            });

        document.getElementById('adoption_form').addEventListener('submit', function(event) {
            event.preventDefault(); // Prevenir la recarga de la página al enviar el formulario

            // Obtener los datos del formulario como un objeto
            const formData = new FormData(this);
            const formDataObject = {};
            formData.forEach((value, key) => {
                formDataObject[key] = value;
            });
            const petId = formDataObject.pet_id;
            const clientId = formDataObject.client_id;

            // Realizar la solicitud a la API usando fetch
            fetch(`/api/adoptions/${petId}/${clientId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json', // Indicar que los datos se envían como JSON
                },
                body: JSON.stringify(formDataObject), // Convertir el objeto a JSON
            })
                .then(response => response.json()) // Parsear la respuesta JSON
                .then(data => {
                    window.location.href = '/adoptions';
                })
                .catch(error => {
                    console.error('Error:', error); // Manejar errores de la solicitud
                });
        });
    </script>
@endsection
