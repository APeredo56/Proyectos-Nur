import { createBrowserRouter } from "react-router-dom";
import HomePage from "../pages/HomePage";
import { Routes } from "./CONSTANTS";
import CategoryDetail from "../pages/CategoryDetail";
import AppDetail from "../pages/AppDetail";
import AdminPage from "../pages/AdminPage";
import CategoryForm from "../pages/CategoryForm";
import AppForm from "../pages/AppForm";

export const routerConfig = createBrowserRouter([
  {
    path: Routes.ADMIN,
    element: <AdminPage />,
  },
  {
    path: Routes.HOME,
    element: <HomePage />,
  },
  {
    path: Routes.CATEGORIES.DETAIL,
    element: <CategoryDetail />,
  },
  {
    path: Routes.CATEGORIES.CREATE,
    element: <CategoryForm />,
  },
  {
    path: Routes.CATEGORIES.EDIT,
    element: <CategoryForm />,
  },
  {
    path: Routes.APPS.DETAIL,
    element: <AppDetail />,
  },
  {
    path: Routes.APPS.CREATE,
    element: <AppForm />,
  },
  {
    path: Routes.APPS.EDIT,
    element: <AppForm />,
  },
  // {
  //   path: Routes.CLIENTS.LIST,
  //   element: <ClientList/>,
  // },
  // {
  //   path: Routes.CLIENTS.CREATE,
  //   element: <ClientForm/>,
  // },
  // {
  //   path: Routes.CLIENTS.EDIT,
  //   element: <ClientForm/>,
  // },
]);