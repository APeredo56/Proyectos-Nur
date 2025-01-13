import { useNavigate, useParams } from "react-router-dom";
import NavbarComponent from "../../components/NavbarComponent";
import { FormEvent, useEffect, useState } from "react";
import { TaskService } from "../../services/TaskService";
import { Button, Container, Form } from "react-bootstrap";
import { Routes } from "../../routes/CONSTANTS";
import DatePicker from "react-datepicker";

const TaskFormPage = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [task, setTask] = useState({
        id: 0,
        title: "",
        description: "",
        completed: false,
        start_date: "",
        end_date: "",
    });
    const [error, setError] = useState("");
    const [startDateError, setStartDateError] = useState("");
    const [endDateError, setEndDateError] = useState("");
    const [validated, setValidated] = useState(false);

    useEffect(() => {
        if (!id) return;
        fetchTask();
    }, [id]);

    const fetchTask = () => {
        TaskService.get(Number(id)).then(response => {
            setTask(response);
        });
    }

    const onSave = (e: FormEvent<HTMLFormElement>) => {
        const form = e.currentTarget;
        e.preventDefault();
        e.stopPropagation();
        setStartDateError("");
        setEndDateError("");
        
        const isValid = form.checkValidity();
        const areDatesValid = validateDates();
        
        setValidated(true);
        if (!isValid || !areDatesValid) return

        saveTask();
    }

    const validateDates = () => {
        let valid = true;
        if (!task.start_date) {
            setStartDateError("La fecha de inicio no debe estar vacia.");
            valid = false;
        }
        if (!task.end_date) {
            setEndDateError("La fecha de fin no debe estar vacia.");
            valid = false;
        }
        if (new Date(task.start_date) > new Date(task.end_date)) {
            setEndDateError("La fecha de fin no debe ser menor a la fecha de inicio.");
            valid = false;
        }
        return valid;
    }

    const saveTask = () => {
        if (id) {
            TaskService.update(task).then(() => {
                navigate(Routes.HOME);
            }).catch(() => setError("Ocurrio un error al actualizar la tarea."));
        } else {
            TaskService.create(task).then(() => {
                navigate(Routes.HOME);
            }).catch(() => setError("Ocurrio un error al crear la tarea."));
        }
    }

    const changeValue = (e: React.ChangeEvent<HTMLInputElement>) => {
        setTask({
            ...task,
            [e.target.name]: e.target.value
        });
    }


    const handleStartDateChange = (date: Date | null) => {
        if (!date) return;
        setTask({
            ...task,
            start_date: date.toISOString()
        });
    }

    const handleEndDateChange = (date: Date | null) => {
        if (!date) return;
        setTask({
            ...task,
            end_date: date.toISOString()
        });
    }

    const getDateObject = (date: string) => {
        if (!date) return null;
        return new Date(date);
    }

    return (<>
        <NavbarComponent />
        <Container>
            <h1 className="mt-3">{id ? 'Editar' : 'Agregar'} Tarea</h1>
            <Form className="p-3 primary-border border-top-0 rounded-bottom-4" noValidate validated={validated}
                onSubmit={onSave}>
                {error && <Form.Label className="text-danger error">{error}</Form.Label>}
                <Form.Group className="mb-3" controlId="title">
                    <Form.Label className="primary-color fw-bold">Titulo</Form.Label>
                    <Form.Control type="text" placeholder="Ingrese el titulo" name="title"
                        value={task.title} onChange={changeValue} required />
                    <Form.Control.Feedback type="invalid">
                        El titulo no debe estar vacio.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="description">
                    <Form.Label className="primary-color fw-bold">Descripcion</Form.Label>
                    <Form.Control type="text" placeholder="Ingrese la descripcion" value={task.description}
                        name="description" onChange={changeValue} required/>
                    <Form.Control.Feedback type="invalid">
                        La descripcion no debe estar vacia.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="start_date">
                    <Form.Label className="primary-color fw-bold d-block">Fecha de Inicio</Form.Label>
                    <DatePicker selected={getDateObject(task.start_date)} dateFormat={"dd/MM/yyyy"}
                        onChange={(date) => handleStartDateChange(date)} showYearDropdown={true} />
                    {startDateError && <Form.Label className="text-danger error">{startDateError}</Form.Label>}
                </Form.Group>
                <Form.Group className="mb-3" controlId="end_date">
                    <Form.Label className="primary-color fw-bold d-block">Fecha de Inicio</Form.Label>
                    <DatePicker selected={getDateObject(task.end_date)} dateFormat={"dd/MM/yyyy"}
                        onChange={(date) => handleEndDateChange(date)} showYearDropdown={true} />
                    {endDateError && <Form.Label className="text-danger error">{endDateError}</Form.Label>}
                </Form.Group>

                <Button className="btn primary-bg primary-border" type="submit">Guardar</Button>
            </Form>
        </Container>
    </>);
}

export default TaskFormPage;