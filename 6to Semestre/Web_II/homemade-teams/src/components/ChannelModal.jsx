import { Button, Form, FormControl, FormLabel, Modal } from "react-bootstrap";
import PropTypes from 'prop-types';
import { useEffect, useState } from "react";
import axios from "axios";
import { getChannelById } from "../services/ChannelService";

const ChannelModal = ({ show, channelId, teamId, onConfirm, onCancel }) => {
    const [channelName, setChannelName] = useState("");
    const [validated, setValidated] = useState(false);

    useEffect(() => {
        if (!channelId) {
            return;
        }
        getChannelById(channelId).then((response) => {
            setChannelName(response.name);
        });
    }
    , [channelId]);

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
        if (channelId) {
            doUpdate();
        } else {
            doInsert();
        }
    }

    const doUpdate = () => {
        axios.put('http://localhost:8080/Web_II/teamsapi/channel/update/' + channelId, {
            name: channelName,
            team_id: teamId
        }).then(() => {
            onConfirm();
            resetValues();
        }).catch((error) => {
            console.log(error);
        });
    }
    const doInsert = () => {
        axios.post('http://localhost:8080/Web_II/teamsapi/channel/insert/', {
            name: channelName,
            team_id: teamId
        }).then(() => {
            onConfirm();
            resetValues();
        }).catch((error) => {
            console.log(error);
        });
    }

    const resetValues = () => {
        setChannelName("");
        setValidated(false);
    }
    
    return (<Modal show={show} centered backdrop="static">
        <Form noValidate validated={validated} onSubmit={onFormSubmit}>
            <Modal.Header className="primary-bg secondary-border">
                <Modal.Title className="text-white">{channelId?"Editar":"Agregar"} Canal</Modal.Title>
            </Modal.Header>
            <Modal.Body className="primary-variant-bg text-white">
                <div className="mb-3">
                    <FormLabel>Nombre del Canal</FormLabel>
                    <FormControl type="text" placeholder="Nombre del Equipo" value={channelName} 
                        onChange={(e) => setChannelName(e.target.value)} required />
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

ChannelModal.propTypes = {
    show: PropTypes.bool.isRequired,
    channelId: PropTypes.number,
    teamId: PropTypes.number.isRequired,
    onConfirm: PropTypes.func.isRequired,
    onCancel: PropTypes.func.isRequired
};

export default ChannelModal;