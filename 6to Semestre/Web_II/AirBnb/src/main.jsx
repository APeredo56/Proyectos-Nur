import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import Home from './pages/home/Home.jsx'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import 'react-bootstrap-typeahead/css/Typeahead.bs5.css';
import "react-datepicker/dist/react-datepicker.css";
import Auth from './pages/auth/Auth.jsx'
import { UserProvider } from './utilities/UserContext.jsx'
import AccommodationDetail from './pages/accommodation/AccommodationDetail.jsx'
import UserBookings from './pages/bookings/UserBookings.jsx'
import UserAccommodations from './pages/accommodation/UserAccommodations.jsx'
import AccommodationBookings from './pages/bookings/AccommodationBookings.jsx'
import AccommodationForm from './pages/accommodation/AccommodationForm.jsx'

const router = createBrowserRouter([
  {
    path: '/',
    element: <Home />,
  },
  {
    path: '/auth',
    element: <Auth />,
  },
  {
    path: '/accommodation/:id',
    element: <AccommodationDetail />,
  },
  {
    path: '/bookings',
    element: <UserBookings />,
  },
  {
    path: '/user/accommodations',
    element: <UserAccommodations />,
  },
  {
    path: '/accommodations/:id/bookings',
    element: <AccommodationBookings />,
  },
  {
    path: '/accommodations/form/:id?',
    element: <AccommodationForm />,
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <UserProvider>
      <RouterProvider router={router} />
    </UserProvider>
  </React.StrictMode>,
)
