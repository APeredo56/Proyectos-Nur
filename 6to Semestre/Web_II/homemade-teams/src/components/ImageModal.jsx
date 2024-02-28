import { Button, Form, FormControl, FormLabel, Image, Modal } from "react-bootstrap";
import PropTypes from 'prop-types';
import { useState } from "react";
import axios from "axios";

const ImageModal = ({ show, onClose, channelId}) => {
    const [title, setTitle] = useState("");
    const [image, setImage] = useState("");
    const [imageFile, setImageFile] = useState(null);
    const [validated, setValidated] = useState(false);
    const username = sessionStorage.getItem("username");

    const onFormSubmit = (e) => {
        const form = e.currentTarget;
        e.preventDefault();
        e.stopPropagation();

        const isValid = form.checkValidity();
        setValidated(true);

        if (!isValid) {
            return;
        }
        sendMessage();
    }

    const sendMessage = () => {
        axios.post('http://localhost:8080/Web_II/teamsapi/message/insert/', {
            title: title,
            content: imageFile.name,
            sender_name: username,
            message_type: 1,
            channel_id: channelId
        }).then((response) => {
            sendImage(response.data.id);
        }).catch((error) => {
            console.log(error);
        });
    }


    const sendImage = (messageId) => {
        const formParams = new FormData();
        formParams.append('image', imageFile);
        axios.post('http://localhost:8080/Web_II/teamsapi/message/picture/' + messageId, formParams,
            {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(() => {
                resetValues();
                onClose();
            }
            ).catch((error) => {
                console.log(error);
            }
            );
    }

    const resetValues = () => {
        setTitle("");
        setImage("");
        setImageFile(null);
        setValidated(false);
    }

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        setImageFile(file);
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                setImage(reader.result);
            };
            reader.readAsDataURL(file);
        }
    };

    return ( <Modal show={show} centered backdrop="static">
    <Form noValidate validated={!!validated} onSubmit={onFormSubmit}>
        <Modal.Header className="primary-bg secondary-border">
            <Modal.Title className="text-white pt-0">Enviar Imagen</Modal.Title>
        </Modal.Header>
        <Modal.Body className="primary-variant-bg text-white">
            <div className="mb-2">
                <FormLabel>Titulo</FormLabel>
                <FormControl type="text" placeholder="Titulo del mensaje" value={title} 
                    onChange={(e) => setTitle(e.target.value)} required />
            </div>
            <div >
                <FormLabel>Imagen</FormLabel>
                <div className="d-flex justify-content-center">
                    <Image src={image} fluid className="img-preview mb-2"/>
                </div>
                <FormControl type="file" placeholder="Imagen"
                    onChange={(e) => handleImageChange(e)} required />
            </div>
        </Modal.Body>
        <Modal.Footer className="primary-bg secondary-border">
            <Button className="bg-danger text-white" onClick={() => {onClose(); resetValues();}}>
                Cerrar
            </Button>
            <Button type="submit" className="secondary-bg text-white">Enviar Mensaje</Button>
        </Modal.Footer>
    </Form>
</Modal>  );
}

ImageModal.propTypes = {
    show: PropTypes.bool.isRequired,
    onClose: PropTypes.func.isRequired,
    channelId: PropTypes.number.isRequired
}

export default ImageModal;
