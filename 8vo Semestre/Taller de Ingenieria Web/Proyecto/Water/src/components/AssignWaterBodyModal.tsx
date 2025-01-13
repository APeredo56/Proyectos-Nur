import { Button, Dialog, DialogBody, DialogFooter, DialogHeader } from "@material-tailwind/react";
import { useEffect, useState } from "react";
import { faPlus, faX } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import SearchBarComponent from "./common/SearchBarComponent";
import { WaterBody } from "../models/WaterBody";
import { WaterBodyService } from "../services/WaterBodyService";

type Props = {
    isOpen: boolean,
    onCancel: () => void,
    onConfirm: (assignedUserId: number) => void,
    assignedWaterBodies: WaterBody[]
}

const AssignWaterBodyModal = ({ isOpen, onCancel, onConfirm, assignedWaterBodies }: Props) => {
    const [availableWaterBodies, setAvailableWaterBodies] = useState<WaterBody[]>([]);
    const [selectedWaterBodyId, setSelectedWaterBodyId] = useState(0);
    const [searchTerm, setSearchTerm] = useState("");
    const [isSaving, setIsSaving] = useState(false);

    const selectedWaterBody = availableWaterBodies.find((contacto) => contacto.id === selectedWaterBodyId);

    useEffect(() => {
        if (!isOpen) {
            handleClose();
            return;
        }
        fetchWaterBodies();
    }, [isOpen]);

    const fetchWaterBodies = () => {
        WaterBodyService.list().then((waterBodies) => {
            setAvailableWaterBodies(filterUsers(waterBodies));
        });
    }

    const filterUsers = (waterBodies: WaterBody[]) => {
        return waterBodies.filter((waterBody) => {
            return !assignedWaterBodies.some((c) => c.id === waterBody.id);
        });
    }

    const handleClose = () => {
        setSelectedWaterBodyId(0);
        setSearchTerm("");
        setAvailableWaterBodies([]);
        setIsSaving(false);
    }

    const handleConfirm = () => {
        if (!selectedWaterBodyId) return;
        setIsSaving(true);
        onConfirm(selectedWaterBodyId);
    }

    return (<Dialog open={isOpen} handler={onCancel} className="border">
        <DialogHeader className="text-blue-500 border-0 border-b">Asignar Cuerpos de Agua</DialogHeader>
        <DialogBody>
            <SearchBarComponent setSearchTerm={setSearchTerm} containerClassNames="mx-auto mb-4" />
            <div className="max-h-[40vh] overflow-y-auto scroll-container">
                {availableWaterBodies.map((waterBody) => (
                    (waterBody.name.toLowerCase().includes(searchTerm)) &&
                    <div key={waterBody.id} className="mb-3 border-b-2 border-blue-500 flex items-center justify-between">
                        <p className="text-gray-500">
                            {waterBody.name}
                        </p>
                        {selectedWaterBodyId === waterBody.id ?
                            <Button variant="text" color="white" onClick={() => setSelectedWaterBodyId(0)} className="p-0">
                                <FontAwesomeIcon color="red" icon={faX} className="h-4 w-4 p-2 m-0" />
                            </Button> :
                            <Button variant="text" color="white" onClick={() => setSelectedWaterBodyId(waterBody.id!)} className="p-0">
                                <FontAwesomeIcon color="blue" icon={faPlus} className="h-4 w-4 p-2 m-0" />
                            </Button>}
                    </div>
                ))}
            </div>
        </DialogBody>
        <DialogFooter className="border-0 border-t">
            <Button variant="outlined" onClick={onCancel} className="mr-1">
                Cancelar
            </Button>
            <Button color="blue" onClick={handleConfirm} disabled={!selectedWaterBody || isSaving}>
                {isSaving ? "Guardando..." : "Confirmar"}
            </Button>
        </DialogFooter>
    </Dialog>);
}

export default AssignWaterBodyModal;