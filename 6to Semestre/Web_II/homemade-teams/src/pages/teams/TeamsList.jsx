import { Card, Container, Row } from "react-bootstrap";
import Menu from "../../components/Menu";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getTeamsList } from "../../services/TeamService";
import './team.css';
import TeamModal from "../../components/TeamModal";
import UsernameModal from "../../components/UsernameModal";

const TeamsList = () => {
    const navigate = useNavigate();
    const [teamsList, setTeamsList] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [selectedTeamId, setSelectedTeamId] = useState(null);

    useEffect(() =>{
        if (!showModal){
            fetchTeamsList();
            console.log("fetching teams list");
        }
    }, [showModal]);

    const fetchTeamsList = () => {
        getTeamsList().then(
            (response) => {
                setTeamsList(response);
            }
        );
    }

    const onTeamClick = (teamId) => {
        navigate('/teams/' + teamId);
    }
    
    const onTeamUpdate = () => {
        fetchTeamsList();
        setShowModal(false);
        setSelectedTeamId(null);
    };

    return (<>
        <Menu/>
        <Container className="p-4 w-100 mw-100 h-100">
            <h1 className="text-white">Equipos Disponibles</h1>
            <Row>
            {teamsList && teamsList.map((team) => {
                return <Card key={team.id} className="primary-bg rounded-4 team-card-w h-100 p-0 m-3"
                    onClick={() =>onTeamClick(team.id)}>
                    <Card.Img variant="top" src={"src/assets/team-default.jpg"} 
                        className="rounded-top-4 cursor-pointer"/>
                    <Card.Body>
                        <Card.Title className="secondary-color fw-bold cursor-pointer w-100">
                            <Card.Text className="secondary-color fw-bold float-right">
                                <i className="cursor-pointer fa-solid fa-pen-to-square " 
                                    onClick={(e) => { 
                                        e.stopPropagation();
                                        setSelectedTeamId(team.id); 
                                        setShowModal(true);
                                    }}>
                                </i>
                            </Card.Text>
                            {team.name}
                        </Card.Title>
                    </Card.Body>
                </Card>
            })}
            <Card className="rounded-4 team-card-w team-card-h p-0 m-3 align-self-center bg-transparent
                border-5 secondary-border btn" onClick={()=> setShowModal(true)}>
                <Card.Body className="d-flex justify-content-center align-items-center">
                    <Card.Title className="secondary-color fw-bold">
                        <i className="fa-solid fa-plus fs-add"></i>
                    </Card.Title>
                </Card.Body>
            </Card>
            </Row>
        </Container>
        <UsernameModal />
        <TeamModal show={showModal} teamId={selectedTeamId} onConfirm={onTeamUpdate}
            onCancel={()=>setShowModal(false)} />
    </>);
}

export default TeamsList;