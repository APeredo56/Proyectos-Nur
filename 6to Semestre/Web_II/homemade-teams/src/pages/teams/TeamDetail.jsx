import { useNavigate, useParams } from "react-router-dom";
import Menu from "../../components/Menu";
import { useEffect, useState } from "react";
import { getTeamById } from "../../services/TeamService";
import { Card, Container, Row } from "react-bootstrap";
import './team.css';
import DeleteModal from "../../components/DeleteModal";
import { deleteChannel, getTeamChannelsList } from "../../services/ChannelService";
import ChannelModal from "../../components/ChannelModal";

const TeamDetail = () => {
    const { id } = useParams();
    const [teamName, setTeamName] = useState("Cargando...");
    const [channelsList, setChannelsList] = useState(null);
    const [teamDescription, setTeamDescription] = useState("");
    const [selectedChannelId, setSelectedChannelId] = useState(null);
    const [showChannelModal, setShowChannelModal] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const navigate = useNavigate();
    
    useEffect(() => {
        if (!id) {
            return;
        }
        getTeamById(id).then((response) => {
            setTeamName(response.name);
            setTeamDescription(response.description);
        });
        fetchChannelsList(id);
    }
    , [id]);

    const fetchChannelsList = (teamId) => {
        getTeamChannelsList(teamId).then(
            (response) => {
                setChannelsList(response);
            }
        );
    }

    const onChannelClick = (channelId) => {
        navigate('/channels/' + channelId);
    }

    const onChannelUpdate = () => {
        fetchChannelsList(id);
        setShowChannelModal(false);
        setSelectedChannelId(null);
    };

    const onDeleteConfirm = (channelId) => {
        setSelectedChannelId(null);
        setShowDeleteModal(false);
        deleteChannel(channelId).then( () => fetchChannelsList(id));
    }

    return ( <>
        <Menu />
        <h1 className="secondary-color fs-bold p-4">{teamName}</h1>
        <div className="container ">
            <p className="text-white primary-bg rounded-4 d-flex p-3 w-100">{teamDescription}</p>
        </div>
        <Container>
        <h3 className="secondary-color fs-bold p-4">Lista de Canales Disponibles</h3>
        <Row>
            {channelsList && channelsList.map((channel) => {
                return <Card key={channel.id} className="primary-bg rounded-4 channel-card-w h-100 p-0 m-3"
                    onClick={() =>onChannelClick(channel.id)}>
                    <Card.Body>
                    <div className="d-flex justify-content-between">
                        <Card.Title className="text-white fw-bold cursor-pointer w-100">
                            <div className="d-flex float-right">
                                <Card.Text className="secondary-color fw-bold m-0 px-2 fs-4">
                                    <i className="cursor-pointer fa-solid fa-pen-to-square" 
                                        onClick={(e) => { 
                                            e.stopPropagation();
                                            setSelectedChannelId(channel.id); 
                                            setShowChannelModal(true);
                                        }}>
                                    </i>
                                </Card.Text>
                                <Card.Text className="text-danger fw-bold m-0 fs-4">
                                    <i className="cursor-pointer fa-solid fa-trash" 
                                        onClick={(e) => { 
                                            e.stopPropagation();
                                            setSelectedChannelId(channel.id); 
                                            setShowDeleteModal(true);
                                        }}>
                                    </i>
                                </Card.Text>
                            </div>
                            {channel.name} 
                        </Card.Title>
                    </div>
                    </Card.Body>
                </Card>
            })}
            <Card className="rounded-4 add-channel-card p-0 m-3 align-self-center bg-transparent
                border-5 secondary-border btn" onClick={()=>setShowChannelModal(true)}>
                <Card.Body className="d-flex justify-content-center align-items-center">
                    <Card.Title className="secondary-color fw-bold">
                        <i className="fa-solid fa-plus fs-add"></i>
                    </Card.Title>
                </Card.Body>
            </Card>
            </Row>
        </Container>
        <ChannelModal show={showChannelModal} channelId={selectedChannelId} teamId={parseInt(id)}
            onConfirm={onChannelUpdate} 
            onCancel={()=> setShowChannelModal(false)}/>
        <DeleteModal show={showDeleteModal} title="Eliminar Canal" 
            message="¿Está seguro que desea eliminar el canal?"
            objectId={selectedChannelId} 
            onConfirm={() => onDeleteConfirm(selectedChannelId)} 
            onCancel={()=> setShowDeleteModal(false)}/>
    </> );
}

export default TeamDetail;