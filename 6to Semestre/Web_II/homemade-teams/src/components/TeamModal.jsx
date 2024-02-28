import { Button, Form, FormControl, FormLabel, Modal } from "react-bootstrap";
import PropTypes from 'prop-types';
import { useEffect, useState } from "react";
import axios from "axios";
import { getTeamById } from "../services/TeamService";

const TeamModal = ({ show, teamId, onConfirm, onCancel }) => {
    const [teamName, setTeamName] = useState("");
    const [teamDescription, setTeamDescription] = useState("");
    const [validated, setValidated] = useState(false);

    useEffect(() => {
        if (!teamId) {
            return;
        }
        getTeamById(teamId).then((response) => {
            setTeamName(response.name);
            setTeamDescription(response.description);
        });
    }
    , [teamId]);

    const onFormSubmit = (e) => {
        const form = e.currentTarget;
        e.preventDefault();
        e.stopPropagation();

        const isValid = form.checkValidity();
        setValidated(true);

        if (!isValid) {
            return;
        }
        doSave();
    }

    const doSave = () => {
        if (teamId) {
            doUpdate();
        } else {
            doInsert();
        }
    }

    const doUpdate = () => {
        axios.put('http://localhost:8080/Web_II/teamsapi/team/update/' + teamId, {
            name: teamName,
            description: teamDescription
        }).then(() => {
            onConfirm();
            resetValues();
        }).catch((error) => {
            console.log(error);
        });
    }
    const doInsert = () => {
        axios.post('http://localhost:8080/Web_II/teamsapi/team/insert/', {
            name: teamName,
            description: teamDescription
        }).then(() => {
            onConfirm();
            resetValues();
        }).catch((error) => {
            console.log(error);
        });
    }

    const resetValues = () => {
        setTeamName("");
        setTeamDescription("");
        setValidated(false);
    }
    
    return (<Modal show={show} centered backdrop="static">
        <Form noValidate validated={validated} onSubmit={onFormSubmit}>
            <Modal.Header className="primary-bg secondary-border">
                <Modal.Title className="text-white">{teamId?"Editar":"Agregar"} Equipo</Modal.Title>
            </Modal.Header>
            <Modal.Body className="primary-variant-bg text-white">
                <div className="mb-3">
                    <FormLabel>Nombre</FormLabel>
                    <FormControl type="text" placeholder="Nombre del Equipo" value={teamName} 
                        onChange={(e) => setTeamName(e.target.value)} required />
                </div>
                <div>
                    <FormLabel>Descripción</FormLabel>
                    <FormControl type="text" placeholder="Descripción del Equipo" value={teamDescription} 
                        onChange={(e) => setTeamDescription(e.target.value)} required/>
                </div>
            </Modal.Body>
            <Modal.Footer className="primary-bg secondary-border">
                <Button className="bg-danger text-white" onClick={() => {onCancel(); resetValues();}}>
                    Cerrar
                </Button>
                <Button type="submit" className="secondary-bg text-white">Guardar Cambios</Button>
            </Modal.Footer>
        </Form>
    </Modal> );
}

TeamModal.propTypes = {
    show: PropTypes.bool.isRequired,
    teamId: PropTypes.number,
    onConfirm: PropTypes.func.isRequired,
    onCancel: PropTypes.func.isRequired
};

export default TeamModal;