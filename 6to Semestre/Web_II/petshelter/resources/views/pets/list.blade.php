@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="d-flex align-items-center">
            <h1 class="text-white fs-1 fw-bold">Mascotas</h1>
            <a href="{{ route('pets.form') }}" class="text-white secondary-bg rounded-circle px-3 py-2 ms-4">
                <i class="fa-solid fa-plus fs-add"></i>
            </a>
        </div>
        <div class="container">
            <table class="table table-transparent" id="pets_table">
                <thead class="bg-transparent">
                    <tr>
                        <th><div class="text-white">Id</div></th>
                        <th><div class="text-white">Nombre</div></th>
                        <th><div class="text-white">Tipo</div></th>
                        <th><div class="text-white">Raza</div></th>
                        <th><div class="text-white">Color</div></th>
                        <th><div class="text-white">Tamaño</div></th>
                        <th><div class="text-white">Disponibilidad</div></th>
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
        document.getElementById('hdnItemDelete').value = "pets";
        // Realizar la petición AJAX para obtener los datos de las mascotas
        fetch('/api/pets')
            .then(response => response.json())
            .then(data => {
                // Llenar la tabla con los datos obtenidos
                const tabla = document.getElementById('pets_table').getElementsByTagName('tbody')[0];

                data.forEach(pet => {
                    const fila = tabla.insertRow();
                    fila.innerHTML = `
                        <td class="text-white">${pet.id}</td>
                        <td class="text-white">${pet.name}</td>
                        <td class="text-white">${pet.type}</td>
                        <td class="text-white">${pet.breed}</td>
                        <td class="text-white">${pet.color}</td>
                        <td class="text-white">${pet.size}</td>
                        <td class="text-white">${pet.availability===1?"Disponible":"Adoptado"}</td>
                        <td>
                            <a href="/pets/form/${pet.id}" class="btn btn-primary secondary-color">
                                <i class="cursor-pointer fa-solid fa-pen-to-square"></i>
                            </a>
                        </td>
                        <td>
                            <a class="btn btn-primary text-danger"
                                data-bs-toggle="modal" data-bs-target="#deleteItemModal"
                               onclick="seleccionarElemento(${pet.id})">
                                <i class="cursor-pointer fa-solid fa-trash"></i>
                            </a>
                        </td>
                    `;
                });
            })
            .catch(error => console.error('Error:', error));
        function seleccionarElemento(id) {
            document.getElementById('hdnIdDelete').value = id;
            return false;
        }
    </script>
@endsection
