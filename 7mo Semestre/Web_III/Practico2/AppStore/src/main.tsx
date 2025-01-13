import React from 'react'
import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'
import { routerConfig } from './routes/RouterConfig.tsx'
import 'bootstrap/dist/css/bootstrap.min.css';
import './styles/colors.css'
import './styles/styles.css'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={routerConfig} />
  </React.StrictMode>,
)
