import { createBrowserRouter } from "react-router-dom";
import MainLayout from "../layouts/MainLayout";
import { PublicRoutes } from "./PublicRoutes";


export const routerConfig = createBrowserRouter([
  {
    element: <MainLayout />,
    children: [  
      ...PublicRoutes,
    ]
  },
]);