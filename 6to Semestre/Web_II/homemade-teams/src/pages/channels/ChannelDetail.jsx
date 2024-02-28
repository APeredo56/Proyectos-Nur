import { useParams } from "react-router-dom";
import Menu from "../../components/Menu";
import { useEffect, useRef, useState } from "react";
import { getChannelById } from "../../services/ChannelService";
import { Form, FormControl, Image } from "react-bootstrap";
import "./channel.css";
import { deleteMessage, getChannelMessages } from "../../services/MessageService";
import axios from "axios";
import ImageModal from "../../components/ImageModal";
import DeleteModal from "../../components/DeleteModal";

const ChannelDetail = () => {
    const id = useParams().id;
    const [channelName, setChannelName] = useState();
    const [messageList, setMessageList] = useState(null);
    const [validated, setValidated] = useState(false);
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const username = sessionStorage.getItem("username");
    const messagesContainerRef = useRef(null);
    const [showImageModal, setShowImageModal] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [selectedMessageId, setSelectedMessageId] = useState(null);

    useEffect(() => {
        getChannelById(id).then((response) => {
            setChannelName(response.name);
        });
        getChannelMessages(id).then((response) => {
            setMessageList(response);
            const messagesContainer = messagesContainerRef.current;
            messagesContainer.scrollTop = messagesContainer.scrollHeight;
        });        
    }, [id]);

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
            content: content,
            sender_name: username,
            message_type: 0,
            channel_id: id
        }).then(() => {
            resetValues();
        }).catch((error) => {
            console.log(error);
        });

    }

    const resetValues = () => {
        setTitle("");
        setContent("");
        setValidated(false);
        refreshMessages();
    }

    const refreshMessages = () => {
        getChannelMessages(id).then(async (response) => {
            setMessageList(response);
            await new Promise((resolve) => setTimeout(resolve, 0));
            const messagesContainer = messagesContainerRef.current;
            messagesContainer.scrollTop = messagesContainer.scrollHeight;
        });
    }

    const renderMessageContent = (message) => {
        if (message.message_type === 0) {
            return <div className="text-white">{message.content}</div>
        } else {
            return <Image src={"http://localhost:8080/Web_II/teamsapi/img/messages/" + message.content} 
                alt={message.content}fluid className="img-message-height"/>
        }
    }

    const onDeleteConfirm = (messageId) => {
        setSelectedMessageId(null);
        setShowDeleteModal(false);
        deleteMessage(messageId).then(() => refreshMessages());
    }

    return ( <>
        <Menu />
        <h1 className="secondary-color fs-bold p-4 m-0">{channelName}</h1>
        <div className="main-container d-flex p-4 pt-0">
            <div className="d-flex h-100 w-100 rounded-4 flex-column border border-3 border-white">
                <div className=" rounded-top-4 message-container pb-3" ref={messagesContainerRef}>
                    {messageList && messageList.map((message) => {
                        return <div key={message.id} className="d-flex p-2 secondary-border rounded-end-4 
                                mt-3 me-3 primary-bg width-fit flex-column">
                            <div className="d-flex justify-content-between align-items-center">
                                <div className="secondary-color fw-bold">{message.sender_name}</div>
                                <i className="cursor-pointer fa-solid fa-trash text-danger ms-2" 
                                        onClick={() => { 
                                            setSelectedMessageId(message.id); 
                                            setShowDeleteModal(true);
                                        }}>
                                    </i>
                            </div>
                            <div className="text-white fw-bold">{message.title}</div>
                            {renderMessageContent(message)}
                        </div>
                    })}
                </div>
                <Form className="w-100 border-top border-3 border-white px-3" noValidate validated={validated} 
                    onSubmit={onFormSubmit}>
                    <div className="d-flex">
                        <FormControl className="primary-bg text-white secondary-border rounded-4 my-3"
                            placeholder="Escribe un título" required value={title} 
                            onChange={(e) => setTitle(e.target.value)}></FormControl>
                        <p className="btn h-100 bg-transparent border-0 mx-2 mb-0 d-flex align-self-center" 
                            onClick={() => setShowImageModal(true)}>
                            <i className="fa-solid fa-file-image secondary-color"></i>
                        </p>
                        <button type="submit" className="d-flex align-items-center bg-transparent border-0">
                            <i className="fa-solid fa-paper-plane secondary-color"></i>
                        </button>
                    </div>
                    <FormControl className="primary-bg text-white secondary-border rounded-4 no-resize"
                        placeholder="Escribe un mensaje" as="textarea" rows={1} required value={content}
                        onChange={(e) => setContent(e.target.value)}></FormControl>
                </Form>
            </div>
        </div>
        <ImageModal show={showImageModal} onClose={() => {setShowImageModal(false); refreshMessages()}} 
            channelId={parseInt(id)} />
        <DeleteModal show={showDeleteModal} title="Eliminar Mensaje" message="¿Desea eliminar el mensaje?"
            onConfirm={() => onDeleteConfirm(selectedMessageId)}
            onCancel={()=> setShowDeleteModal(false)} />
    </> );
}

export default ChannelDetail;