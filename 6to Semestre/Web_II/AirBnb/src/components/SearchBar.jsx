import { useState } from "react";
import { Typeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import { Button, Container } from "react-bootstrap";
import DatePicker from "react-datepicker";
import { Popover } from "react-tiny-popover";
import FilterModal from "../components/FilterModal";
import PropTypes from 'prop-types';
import { useEffect } from "react";
import { getCityList } from "../services/CityService";
import axios from "axios";
import moment from "moment/moment";

const SearchBar = ({ setAccommodationList, setSelectedCity }) => {
    const [cities, setCities] = useState([]);
    const [isPopoverOpen, setIsPopoverOpen] = useState(false);
    const [isFilterOpen, setIsFilterOpen] = useState(false);
    const [invalidSearchBar, setInvalidSearchBar] = useState(false);
    const [children, setChildren] = useState(0);
    const [adults, setAdults] = useState(0);
    const baseAPIUrl = import.meta.env.VITE_API_URL;
    const [checkInDate, setCheckInDate] = useState(null);
    const [checkOutDate, setCheckOutDate] = useState(null);

    const [searchParams, setSearchParams] = useState({
        city: [],
        checkIn: null,
        checkOut: null,
        guests: 0,
    });
    const [advSearchParams, setAdvSearchParams] = useState({
        maxPricePerNight: 0,
        minPricePerNight: 0,
        bedrooms: 0,
        bathrooms: 0,
        beds: 0,
        hasWifi: false,
        propertyType: ''
    });

    useEffect(() => {
        fetchCities();
    }, []);

    useEffect(() => {
        searchAccommodations();
    }, [searchParams, advSearchParams]);

    const updateSearchParams = (param, value) => {
        setSearchParams((prevParams) => ({
            ...prevParams,
            [param]: value,
        }));
    };

    const fetchCities = () => {
        getCityList().then((response) => {
            setCities(response);
        });
    }

    const handleSearch = () => {
        if (searchParams.city.length === 0) {
            setInvalidSearchBar(true);
            return;
        }
        searchAccommodations();
    }

    const searchAccommodations = () => {
        if (searchParams.city.length === 0) {
            return;
        }
        const params = {}
        params['city_id'] = searchParams.city[0].id;
        for (const key in searchParams) {
            if (searchParams[key] && key !== 'city') {
                params[key] = searchParams[key];
            }
        }
        for (const key in advSearchParams) {
            if (advSearchParams[key]) {
                params[key] = advSearchParams[key];
            }
        }
        axios.post(baseAPIUrl + 'accommodations/search', params).then((response) => {
            setAccommodationList(response.data);
        });
    }

    return (<> <Container className="my-4 d-flex">
        <Typeahead options={cities} labelKey={'name'} id="search-city" placeholder="Buscar una Ciudad"
            style={{ flex: 1 }}
            onChange={(selectedCity) => {
                updateSearchParams('city', selectedCity);
                setSelectedCity(selectedCity[0]);
                setInvalidSearchBar(false);
            }} isInvalid={invalidSearchBar} />
        <DatePicker
            selectsStart
            selected={checkInDate}
            startDate={checkInDate}
            endDate={checkOutDate}
            onChange={(date) => {
                updateSearchParams('checkIn', date!==null?moment(date).format('YYYY-MM-DD'): null);                
                setCheckInDate(date)
            }}
            minDate={new Date()}
            placeholderText="Fecha de Inicio"
            showMonthDropdown
            className="mx-2 border p-2 rounded"
        />
        <DatePicker
            selectsEnd
            selected={checkOutDate}
            startDate={checkInDate}
            endDate={checkOutDate}
            minDate={new Date()}
            onChange={(date) => {
                updateSearchParams('checkOut', date!==null?moment(date).format('YYYY-MM-DD'): null);
                setCheckOutDate(date);
            }}
            placeholderText="Fecha de Fin"
            showMonthDropdown
            className="mx-2 border p-2 rounded"
        />
        <Popover
            isOpen={isPopoverOpen}
            positions={['bottom']}
            onClickOutside={() => setIsPopoverOpen(false)}
            align="end"
            content={
                <div className="bg-white border mt-2 rounded p-2">
                    <div className="d-flex align-items-center justify-content-between mb-3">
                        <h5>Adultos</h5>
                        <div className="ms-3 d-flex align-items-center">
                            <Button variant="outline"
                                className="rounded-circle primary-color primary-border d-flex align-items-center 
                            justify-content-center fw-bold"style={{ width: 40, height: 40 }}
                                onClick={() => {
                                    updateSearchParams('guests', searchParams.guests - 1)
                                    setAdults(adults - 1)
                                }}>
                                <i className="fa fa-minus"></i>
                            </Button>
                            <span className="mx-2">{adults}</span>
                            <Button variant="outline"
                                className="rounded-circle primary-color primary-border d-flex align-items-center 
                            justify-content-center fw-bold" style={{ width: 40, height: 40 }}
                                onClick={() => {
                                    updateSearchParams('guests', searchParams.guests + 1)
                                    setAdults(adults + 1)
                                }}>
                                <i className="fa fa-plus"></i>
                            </Button>
                        </div>
                    </div>
                    <div className="d-flex align-items-center justify-content-between">
                        <h5>Niños</h5>
                        <div className="ms-3 d-flex align-items-center">
                            <Button variant="outline"
                                className="rounded-circle primary-color primary-border d-flex align-items-center 
                            justify-content-center fw-bold" style={{ width: 40, height: 40 }}
                                onClick={() => {
                                    updateSearchParams('guests', searchParams.guests - 1)
                                    setChildren(children - 1)
                                }}>
                                <i className="fa fa-minus"></i>
                            </Button>
                            <span className="mx-2">{children}</span>
                            <Button variant="outline"
                                className="rounded-circle primary-color primary-border d-flex align-items-center 
                            justify-content-center fw-bold" style={{ width: 40, height: 40 }}
                                onClick={() => {
                                    updateSearchParams('guests', searchParams.guests + 1)
                                    setChildren(children + 1)
                                }}>
                                <i className="fa fa-plus"></i>
                            </Button>
                        </div>
                    </div>
                </div>}
        >
            <div className="d-flex align-items-center mx-2 p-2 border rounded cursor-pointer"
                onClick={() => setIsPopoverOpen(!isPopoverOpen)}>
                {(searchParams.guests !== 0 ? searchParams.guests + " " : "") + "Huéspedes"}
            </div>
        </Popover>
        <Button className="mx-2 border" variant="outline" onClick={() => setIsFilterOpen(true)}>
            <i className="fa fa-filter me-1"></i>
            Filtros
        </Button>
        <Button className="mx-2 primary-bg border-0" onClick={handleSearch}>Buscar</Button>
    </Container>
        <FilterModal advSearchParams={advSearchParams} setAdvSearchParams={setAdvSearchParams}
            isFilterOpen={isFilterOpen} setIsFilterOpen={setIsFilterOpen} />
    </>);
}

SearchBar.propTypes = {
    setAccommodationList: PropTypes.func.isRequired,
    setSelectedCity: PropTypes.func.isRequired
};

export default SearchBar;