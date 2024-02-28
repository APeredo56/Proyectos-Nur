import Slider from 'react-slick';
import PropTypes from 'prop-types';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const AccommodationSlider = ({ images, baseURL }) => {
    const settings = {
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000
    };

    return (<Slider {...settings}>
        {images.map((image, index) => (
            <div key={'sliderAcm-' + index}>
                <img src={baseURL + image.url} alt={`Accommodation ${index}`} />
            </div>
        ))}
    </Slider>);
}

AccommodationSlider.propTypes = {
    images: PropTypes.array.isRequired,
    baseURL: PropTypes.string.isRequired
};

export default AccommodationSlider;