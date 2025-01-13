import { Button, Dialog, DialogBody, DialogFooter, DialogHeader } from "@material-tailwind/react";

type Props = {
    isOpen: boolean,
    onCancel: () => void,
    onConfirm: () => void,
    title: string,
    message: string
}

const DeleteConfirmModal = ({ isOpen, onCancel, onConfirm, title, message } : Props) => {
    return (<Dialog open={isOpen} handler={onCancel} className="border">
            <DialogHeader className="text-red-500 border-0 border-b">{title}</DialogHeader>
            <DialogBody className="text-black">
                {message}
            </DialogBody>
            <DialogFooter className="border-0 border-t">
                <Button variant="outlined" onClick={onCancel} className="mr-1">
                    <span>Cancelar</span>
                </Button>
                <Button color="red" onClick={onConfirm}>
                    <span>Confirmar</span>
                </Button>
            </DialogFooter>
        </Dialog>);
}

export default DeleteConfirmModal;