<?php /* @var App\Models\Client $client */
    use App\Models\Reference;
?>


@extends('layouts.app')
@section('content')
    <div class="container">
        <div class="row">
            <div class="col-8">
                <div class="card mt-3 mb-3">
                    <div class="card-body primary-bg rounded">
                        <h5 class="card-title text-white">Formulario de Clientes</h5>
                        <form id="client_form">
                            @csrf
                            <input type="hidden" id="hdnIdInput" name="id"
                                   value="{{$client?$client->id:""}}">
                            <input type="hidden" id="hdnRefInput" name="references"
                                value="{{--$client?json_encode($client->$reference):""--}}">
                            <div class="mb-3">
                                <label for="name" class="form-label text-white">Nombres</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       value="{{$client?$client->name:""}}" required>
                            </div>
                            <div class="mb-3">
                                <label for="lastname" class="form-label text-white">Apellidos</label>
                                <input type="text" class="form-control" id="lastname" name="lastname"
                                       value="{{$client?$client->lastname:""}}" required>
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label text-white">Teléfono</label>
                                <input type="text" class="form-control" id="phone" name="phone"
                                       value="{{$client?$client->phone:""}}" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label text-white">Email</label>
                                <input type="text" class="form-control" id="email" name="email"
                                       value="{{$client?$client->email:""}}" required>
                            </div>
                            <div class="mb-3">
                                <div class="d-flex">
                                    <label for="size" class="form-label text-white">Referencias</label>
                                    <p class="btn secondary-bg rounded-circle text-white btn-sm ms-2"
                                            data-bs-toggle="modal" data-bs-target="#referenceModal">
                                        <i class="fa-solid fa-plus fs-add"></i>
                                    </p>
                                </div>
                                <p class="text-danger d-none m-0" id="reference_error">
                                    Debe agregar al menos un contacto de referencia
                                </p>
                                <div id="references_container" class="d-flex flex-wrap">
                                    @foreach($client?$references:[] as $reference)
                                        <div class="d-flex flex-column mb-2 mx-2 p-2 secondary-border rounded-3 w-fit
                                            text-white" id="reference_{{$reference->phone}}">
                                            <p class="h5">
                                                <a class="btn bg-transparent py-0 pe-0 text-danger float-end"
                                                   onclick="removeReference('{{ $reference->phone }}')">
                                                    <i class="cursor-pointer fa-solid fa-trash"></i>
                                                </a>
                                                <span class="fw-bold secondary-color">Nombre: </span>
                                                {{$reference->full_name}}
                                            </p>
                                            <p class="h5">
                                                <span class="fw-bold secondary-color">Teléfono: </span>
                                                {{$reference->phone}}
                                            </p>
                                            <p class="h5">
                                                <span class="fw-bold secondary-color">Parentesco: </span>
                                                {{$reference->relationship}}
                                            </p>
                                        </div>
                                    @endforeach
                                </div>
                            </div>
                            <button type="submit" class="btn secondary-bg text-white">Guardar</button>
                            <a href="{{ route('clients') }}" class="btn btn-danger">Cancelar</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    @include('components.referenceModal')
    <script>
        let references = [];
        const referencesContainer = document.getElementById('references_container');
        const referenceHdnInput = document.getElementById('hdnRefInput');
        const lblRefError = document.getElementById('reference_error');

        const clientData = {!! json_encode($client) !!};
        if (clientData && clientData.references) {
            references.push(...clientData.references);
            console.log("cargadas las referencias", references);
            referenceHdnInput.value = JSON.stringify(references);
        }

        document.getElementById('client_form').addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(this);

            if (formData.get("references") === "[]") {
                console.log("no hay referencias", references);
                lblRefError.classList.remove('d-none');
                return;
            }

            // Obtener los datos del formulario como un objeto
            const formDataObject = {};
            formData.forEach((value, key) => {
                formDataObject[key] = value;
            });

            // Realizar la solicitud a la API usando fetch
            fetch('/api/clients{{$client?"/$client->id":""}}', {
                method: '{{ $client ? 'PUT' : 'POST' }}',
                headers: {
                    'Content-Type': 'application/json', // Indicar que los datos se envían como JSON
                },
                body: JSON.stringify(formDataObject), // Convertir el objeto a JSON
            })
                .then(response => response.json()) // Parsear la respuesta JSON
                .then(data => {
                    window.location.href = '/clients';
                })
                .catch(error => {
                    console.error('Error:', error); // Manejar errores de la solicitud
                });
        });



        document.getElementById('reference_form').addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(this);

            const formDataObject = {};
            formData.forEach((value, key) => {
                formDataObject[key] = value;
            });
            references.push(formDataObject);
            referenceHdnInput.value = JSON.stringify(references);
            // Insertar la referencia obtenida en el contenedor
            const referenceBox = document.createElement('div');
            referenceBox.className = 'd-flex flex-column mb-2 mx-2 p-2 secondary-border rounded-3 w-fit text-white';
            referenceBox.id = 'reference_' + formDataObject.phone;

            // Crear el contenido del div
            const contenido = `
            <p class="h5">
                <a class="btn bg-transparent py-0 pe-0 text-danger float-end"
                   onclick="removeReference('${formDataObject.phone}')">
                    <i class="cursor-pointer fa-solid fa-trash"></i>
                </a>
                <span class="fw-bold secondary-color">Nombre: </span>
                ${formDataObject.full_name}
            </p>
            <p class="h5">
                <span class="fw-bold secondary-color">Teléfono: </span>
                ${formDataObject.phone}
            </p>
            <p class="h5">
                <span class="fw-bold secondary-color">Parentesco: </span>
                ${formDataObject.relationship}
            </p>
            `;
            referenceBox.innerHTML = contenido;
            referencesContainer.appendChild(referenceBox);
            document.getElementById('referenceModal').querySelector('button[data-bs-dismiss="modal"]').click();
            lblRefError.classList.add('d-none');
        });

        function removeReference(id) {
            const reference = document.getElementById('reference_' + id);
            if (reference) {
                reference.remove();
                references = references.filter(reference => reference.phone != id);
                referenceHdnInput.value = JSON.stringify(references);
            }
        }
    </script>
@endsection
