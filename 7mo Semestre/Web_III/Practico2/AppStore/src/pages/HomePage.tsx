import { useEffect, useState } from "react";
import NavbarComponent from "../components/NavbarComponent"
import { App } from '../models/App';
import { AppService } from "../services/AppService";
import Slider from "react-slick";
import { Container } from "react-bootstrap";
import { Routes } from "../routes/CONSTANTS";

const HomePage = () => {
    const [latestApps, setLatestApps] = useState<App[]>([])
    const [topFreeApps, setTopFreeApps] = useState<App[]>([])
    const [topPaidApps, setTopPaidApps] = useState<App[]>([])

    const sliderSettings = {
        dots: true,
        infinite: true,
        speed: 1000,
        slidesToShow: 3,
        slidesToScroll: 1,
    };

    const fetchLatestApps = () => {
        AppService.latest().then((apps) => {
            setLatestApps(apps)
        })
    }

    const fetchTopFreeApps = () => {
        AppService.topFree().then((apps) => {
            setTopFreeApps(apps)
        })
    }

    const fetchTopPaidApps = () => {
        AppService.topPaid().then((apps) => {
            setTopPaidApps(apps)
        })
    }

    useEffect(() => {
        fetchLatestApps();
        fetchTopFreeApps();
        fetchTopPaidApps();
    }, []);

    return (<>
        <NavbarComponent />
        <section className="p-3 banner">
            <h2 className="text-white  px-5 py-2">Ultimas Aplicaciones Agregadas</h2>
            <Container className="py-3">
                <Slider {...sliderSettings}>
                    {latestApps.map((app) => (
                        <div className="px-3 d-flex justify-content-center" key={"latest-app-" + app.id}>
                            <a className="card text-decoration-none w-fit"
                                href={Routes.APPS.DETAIL_PARAM(app.id)}>
                                <img src={"http://localhost:8000" + app.icon} alt={app.name}
                                    className="card-img-top preview-image" />
                                <div className="card-body">
                                    <div className="d-flex justify-content-between w-100">
                                        <h5 className="card-title">{app.name}</h5>
                                        <h5>{app.price == 0 ? "Gratis" : app.price + "$"}</h5>
                                    </div>
                                    <p className="card-text">{app.description}</p>
                                </div>
                            </a>
                        </div>
                    ))}
                </Slider>
            </Container>
        </section>
        <section className="p-3 bg-dark border-secondary border-bottom border-2">
            <h2 className="text-white px-5 py-2">Top Aplicaciones Gratuitas</h2>
            <Container className="py-3">
                <Slider {...sliderSettings}>
                    {topFreeApps.map((app) => (
                        <div className="px-3 d-flex justify-content-center" key={"top-free-app-" + app.id}>
                            <a className="card text-decoration-none w-fit"
                                href={Routes.APPS.DETAIL_PARAM(app.id)}>
                                <img src={" http://localhost:8000" + app.icon} alt={app.name}
                                    className="card-img-top preview-image" />
                                <div className="card-body">
                                    <h5 className="card-title">{app.name}</h5>
                                    <p className="card-text">{app.description}</p>
                                </div>
                            </a>
                        </div>
                    ))}
                </Slider>
            </Container>
        </section>
        <section className="p-3 bg-dark">
            <h2 className="text-white px-5 py-2">Top Aplicaciones De Pago</h2>
            <Container className="py-3">
                <Slider {...sliderSettings}>
                    {topPaidApps.map((app) => (
                        <div className="px-3 d-flex justify-content-center" key={"top-paid-app-" + app.id}>
                            <a className="card text-decoration-none w-fit"
                                href={Routes.APPS.DETAIL_PARAM(app.id)}>
                                <img src={"http://localhost:8000" + app.icon} alt={app.name}
                                    className="card-img-top preview-image" />
                                <div className="card-body">
                                    <div className="d-flex justify-content-between w-100">
                                        <h5 className="card-title">{app.name}</h5>
                                        <h5>{app.price} $</h5>
                                    </div>
                                    <p className="card-text">{app.description}</p>
                                </div>
                            </a>
                        </div>
                    ))}
                </Slider>
            </Container>
        </section>

    </>
    );
}

export default HomePage;