import React from 'react'
import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'
import { routerConfig } from './routes/RouterConfig.tsx'
import './index.css'
import { ThemeProvider } from '@material-tailwind/react'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ThemeProvider>
      <RouterProvider router={routerConfig} />
    </ThemeProvider>
  </React.StrictMode>,
);
