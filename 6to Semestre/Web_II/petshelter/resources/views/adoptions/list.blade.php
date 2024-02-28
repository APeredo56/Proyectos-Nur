@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="d-flex align-items-center">
            <h1 class="text-white fs-1 fw-bold">Adopciones</h1>
            <a href="{{ route('adoptions.form') }}" class="text-white secondary-bg rounded-circle px-3 py-2 ms-4">
                <i class="fa-solid fa-plus fs-add"></i>
            </a>
        </div>
        <div class="container">
            <table class="table table-transparent" id="adoptions_table">
                <thead class="bg-transparent">
                <tr>
                    <th><div class="text-white">Fecha</div></th>
                    <th><div class="text-white">Cliente</div></th>
                    <th><div class="text-white">Mascota</div></th>
                    <th><div class="text-white">Tipo</div></th>
                    <th><div class="text-white">Raza</div></th>
                    <th><div class="text-white">Color</div></th>
                    <th><div class="text-white">Tamaño</div></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    @include('components.deleteModal')

    <script>
        document.getElementById('hdnItemDelete').value = "adoptions";
        // Realizar la petición AJAX para obtener los datos de las mascotas
        fetch('/api/adoptions')
            .then(response => response.json())
            .then(data => {
                // Llenar la tabla con los datos obtenidos
                const tabla = document.getElementById('adoptions_table').getElementsByTagName('tbody')[0];

                data.forEach(adoption => {
                    const fila = tabla.insertRow();
                    fila.innerHTML = `
                        <td class="text-white">${adoption.date}</td>
                        <td class="text-white">${adoption.client.name} ${adoption.client.lastname}</td>
                        <td class="text-white">${adoption.pet.name}</td>
                        <td class="text-white">${adoption.pet.type}</td>
                        <td class="text-white">${adoption.pet.breed}</td>
                        <td class="text-white">${adoption.pet.color}</td>
                        <td class="text-white">${adoption.pet.size}</td>
                        <td>
                            <a href="/adoptions/form/${adoption.id}" class="btn btn-primary secondary-color">
                                <i class="cursor-pointer fa-solid fa-pen-to-square"></i>
                            </a>
                        </td>
                        <td>
                            <a class="btn btn-primary text-danger"
                                data-bs-toggle="modal" data-bs-target="#deleteItemModal"
                               onclick="seleccionarElemento(${adoption.pet.id}, ${adoption.client.id})">
                                <i class="cursor-pointer fa-solid fa-trash"></i>
                            </a>
                        </td>
                    `;
                });
            })
            .catch(error => console.error('Error:', error));
        function seleccionarElemento(idPet, idClient) {
            document.getElementById('hdnIdDelete').value = idPet + "-" + idClient;
            return false;
        }
    </script>
@endsection
