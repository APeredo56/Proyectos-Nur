@extends('layouts.app')

@section('content')
    <div class="container">
        <h1 class="text-white fs-1 fw-bold">Referencias</h1>
        <div class="container">
            <table class="table table-transparent" id="references_table">
                <thead class="bg-transparent">
                <tr>
                    <th><div class="text-white">Id</div></th>
                    <th><div class="text-white">Nombre</div></th>
                    <th><div class="text-white">Teléfono</div></th>
                    <th><div class="text-white">Parentesco</div></th>
                    <th><div class="text-white">Cliente Asociado</div></th>
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
        document.getElementById('hdnItemDelete').value = "references";
        // Realizar la petición AJAX para obtener los datos de las mascotas
        fetch('/api/references')
            .then(response => response.json())
            .then(data => {
                // Llenar la tabla con los datos obtenidos
                const tabla = document.getElementById('references_table').getElementsByTagName('tbody')[0];

                data.forEach(reference => {
                    const fila = tabla.insertRow();
                    fila.innerHTML = `
                        <td class="text-white">${reference.id}</td>
                        <td class="text-white">${reference.full_name}</td>
                        <td class="text-white">${reference.phone}</td>
                        <td class="text-white">${reference.relationship}</td>
                        <td class="text-white">
                            ${reference.client.name} ${reference.client.lastname}
                        </td>
                        <td>
                            <a href="/references/form/${reference.id}" class="btn btn-primary secondary-color">
                                <i class="cursor-pointer fa-solid fa-pen-to-square"></i>
                            </a>
                        </td>
                        <td>
                            <a class="btn btn-primary text-danger"
                                data-bs-toggle="modal" data-bs-target="#deleteItemModal"
                               onclick="seleccionarElemento(${reference.id})">
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
