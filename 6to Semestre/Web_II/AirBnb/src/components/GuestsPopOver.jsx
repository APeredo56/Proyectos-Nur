import { Button } from "react-bootstrap";
import { Popover } from "react-tiny-popover";
import Proptypes from 'prop-types';

const GuestsPopOver = ({ isPopoverOpen, setIsPopoverOpen, adults, onAdultsChange, children, 
    onChildrenChange, }) => {
    
    return (<>
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
                                onClick={() => onAdultsChange(-1)}>
                                <i className="fa fa-minus"></i>
                            </Button>
                            <span className="mx-2">{adults}</span>
                            <Button variant="outline"
                                className="rounded-circle primary-color primary-border d-flex align-items-center 
                            justify-content-center fw-bold" style={{ width: 40, height: 40 }}
                                onClick={() => onAdultsChange(1)}>
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
                                onClick={() => onChildrenChange(-1)}>
                                <i className="fa fa-minus"></i>
                            </Button>
                            <span className="mx-2">{children}</span>
                            <Button variant="outline"
                                className="rounded-circle primary-color primary-border d-flex align-items-center 
                            justify-content-center fw-bold" style={{ width: 40, height: 40 }}
                                onClick={() => onChildrenChange(1)}>
                                <i className="fa fa-plus"></i>
                            </Button>
                        </div>
                    </div>
                </div>}
        >
            <div className="d-flex align-items-center mx-2 p-2 border rounded cursor-pointer"
                onClick={() => setIsPopoverOpen(!isPopoverOpen)}>
                {(adults + children !== 0 ? adults+children + " " : "") + "Huéspedes"}
            </div>
        </Popover>
    </>);
}

GuestsPopOver.propTypes = {
    isPopoverOpen: Proptypes.bool.isRequired,
    setIsPopoverOpen: Proptypes.func.isRequired,
    adults: Proptypes.number.isRequired,
    onAdultsChange: Proptypes.func.isRequired,
    children: Proptypes.number.isRequired,
    onChildrenChange: Proptypes.func.isRequired,
}

export default GuestsPopOver;