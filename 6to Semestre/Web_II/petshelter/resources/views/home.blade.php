@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
        <a class="card secondary-bg text-white card-width mx-3 text-center border border-2
        text-decoration-none" href="{{ route('pets') }}">
            <div class="card-body secondary-bg fs-3">
                Mascotas
            </div>
        </a>
        <a class="card secondary-bg text-white card-width mx-3 text-center border border-2
            text-decoration-none" href="{{ route('clients') }}">
            <div class="card-body secondary-bg fs-3">
                Clientes
            </div>
        </a>
        <a class="card secondary-bg text-white card-width mx-3 text-center border border-2
            text-decoration-none" href="{{ route('references') }}">
            <div class="card-body secondary-bg fs-3">
                Referencias
            </div>
        </a>
        <a class="card secondary-bg text-white card-width mx-3 text-center border border-2
            text-decoration-none" href="{{ route('adoptions') }}">
            <div class="card-body secondary-bg fs-3">
                Adopciones
            </div>
        </a>
    </div>
</div>
@endsection
