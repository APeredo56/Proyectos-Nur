
<div class="modal fade" id="deleteItemModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <input id="hdnIdDelete" type="hidden"/>
    <input id="hdnItemDelete" type="hidden"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header primary-bg">
                <h1 class="modal-title fs-5 text-white fw-bold" id="exampleModalLabel">Eliminar</h1>
            </div>
            <div class="modal-body primary-variant-bg text-white">
                ¿Desea confirmar la eliminación?
            </div>
            <div class="modal-footer primary-bg text-white">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">No</button>
                <button type="button" class="btn secondary-bg text-white"
                        onclick="deleteItem()">
                            Si
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    function deleteItem() {
        const id = document.getElementById('hdnIdDelete').value;
        const item = document.getElementById('hdnItemDelete').value;
        fetch('/api/'+item+'/'+id, {
            method: 'DELETE',
        }).then(function (response) {
            if (response.ok) {
                window.location.href = '/'+item;
            } else {
                alert('Error al eliminar');
            }
        });
    }
</script>
