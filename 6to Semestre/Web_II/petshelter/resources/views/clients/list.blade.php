@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="d-flex align-items-center">
            <h1 class="text-white fs-1 fw-bold">Clientes</h1>
            <a href="{{ route('clients.form') }}" class="text-white secondary-bg rounded-circle px-3 py-2 ms-4">
                <i class="fa-solid fa-plus fs-add"></i>
            </a>
        </div>
        <div class="container">
            <table class="table table-transparent" id="clients_table">
                <thead class="bg-transparent">
                <tr>
                    <th><div class="text-white">Id</div></th>
                    <th><div class="text-white">Nombres</div></th>
                    <th><div class="text-white">Apellidos</div></th>
                    <th><div class="text-white">Telefono</div></th>
                    <th><div class="text-white">Email</div></th>
                    <th><div class="text-white">Referencias</div></th>
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
        document.getElementById('hdnItemDelete').value = "clients";
        // Realizar la petición AJAX para obtener los datos de las mascotas
        fetch('/api/clients')
            .then(response => response.json())
            .then(data => {
                // Llenar la tabla con los datos obtenidos
                const tabla = document.getElementById('clients_table').getElementsByTagName('tbody')[0];

                data.forEach(client => {
                    const fila = tabla.insertRow();
                    fila.innerHTML = `
                        <td class="text-white">${client.id}</td>
                        <td class="text-white">${client.name}</td>
                        <td class="text-white">${client.lastname}</td>
                        <td class="text-white">${client.phone}</td>
                        <td class="text-white">${client.email}</td>
                        <td class="text-white">
                            ${client.references.map(reference => {
                                return `<div>${reference.full_name}</div>`;
                            }).join('')}
                        </td>
                        <td>
                            <a href="/clients/form/${client.id}" class="btn btn-primary secondary-color">
                                <i class="cursor-pointer fa-solid fa-pen-to-square"></i>
                            </a>
                        </td>
                        <td>
                            <a class="btn btn-primary text-danger"
                                data-bs-toggle="modal" data-bs-target="#deleteItemModal"
                               onclick="seleccionarElemento(${client.id})">
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
