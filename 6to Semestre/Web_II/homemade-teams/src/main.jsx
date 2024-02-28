import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import TeamsList from './pages/teams/TeamsList.jsx'
import TeamDetail from './pages/teams/TeamDetail';
import ChannelDetail from './pages/channels/ChannelDetail.jsx';

const router = createBrowserRouter(
  [
    {
      path: '/',
      element: <TeamsList />
    },
    {
      path: '/teams',
      element: <TeamsList />
    },
    {
      path: '/teams/:id',
      element: <TeamDetail />
    },
    {
      path: '/channels/:id',
      element: <ChannelDetail />
    }

  ]
);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
