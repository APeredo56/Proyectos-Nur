
<div class="modal fade" id="referenceModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <input id="hdnIdReference" type="hidden"/>
    <input id="hdnIdClient" type="hidden"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="reference_form">
            <div class="modal-header primary-bg">
                <h1 class="modal-title fs-5 text-white fw-bold" id="exampleModalLabel">
                    Actualizar Referencias
                </h1>
            </div>
            <div class="modal-body primary-variant-bg text-white">
                <div class="col-md-6 mb-3">
                    <label for="full_name" class="form-label">Nombre Completo</label>
                    <input type="text" class="form-control" id="full_name" name="full_name"
                           placeholder="Nombres" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="phone" class="form-label">Teléfono</label>
                    <input type="number" class="form-control" id="phone" name="phone"
                           placeholder="Teléfono" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="relationship" class="form-label">Parentesco</label>
                    <input type="text" class="form-control" id="relationship" name="relationship"
                           placeholder="Parentesco" required>
                </div>
            </div>
            <div class="modal-footer primary-bg text-white">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn secondary-bg text-white">
                    Guardar
                </button>
            </div>
            </form>
        </div>
    </div>
</div>
<script>
    const miModal = document.getElementById('referenceModal');
    miModal.addEventListener('hidden.bs.modal', function () {
        document.getElementById('reference_form').reset();
    });
</script>
