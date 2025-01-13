import { createBrowserRouter } from "react-router-dom";
import { Routes } from "./CONSTANTS";
import HomePage from "../pages/HomePage";
import UserListPage from "../pages/user/UserListPage";
import UserFormPage from "../pages/user/UserFormPage";
import TaskDetailPage from "../pages/task/TaskDetailPage";
import TaskFormPage from "../pages/task/TaskFormPage";


export const routerConfig = createBrowserRouter([
  {
    path: Routes.HOME,
    element: <HomePage />,
  },
  {
    path: Routes.USER.LIST,
    element: <UserListPage />
  },
  {
    path: Routes.USER.CREATE,
    element: <UserFormPage />
  },
  {
    path: Routes.TASK.CREATE,
    element: <TaskFormPage />
  },
  {
    path: Routes.TASK.EDIT,
    element: <TaskFormPage />
  },
  {
    path: Routes.TASK.DETAIL,
    element: <TaskDetailPage />
  },
]);