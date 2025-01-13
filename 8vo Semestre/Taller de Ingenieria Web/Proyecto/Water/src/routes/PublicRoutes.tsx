import { RouteObject } from 'react-router-dom';
import HomePage from '../pages/HomePage';
import { Routes } from './CONSTANTS';
import LoginPage from '../pages/LoginPage';
import DepartmentListPage from '../pages/departments/DepartmentListPage';
import DepartmentFormPage from '../pages/departments/DepartmentFormPage';
import MunicipalityListPage from '../pages/municipalities/MunicipalityListPage';
import MunicipalityFormPage from '../pages/municipalities/MunicipalityFormPage';
import CommunityListPage from '../pages/communities/CommunityListPage';
import CommunityFormPage from '../pages/communities/CommunityFormPage';
import WaterBodyListPage from '../pages/waterBodies/WaterBodyListPage';
import WaterBodyFormPage from '../pages/waterBodies/WaterBodyFormPage';
import FieldTripListPage from '../pages/fieldTrips/FieldTripListPage';
import FieldTripFormPage from '../pages/fieldTrips/FieldTripFormPage';
import FieldTripAssignmentsPage from '../pages/fieldTrips/FieldTripAssignmentsPage';
import UserListPage from '../pages/users/UserListPage';
import UserFormPage from '../pages/users/UserFormPage';
import OwnFieldTripsPage from '../pages/fieldTrips/OwnFieldTripsPage';
import UserAnalysesPage from '../pages/analysis/UserAnalysesPage';
import SamplingAnalysisFormPage from '../pages/analysis/SamplingAnalysisFormPage';
import UserRankingPage from '../pages/users/UserRanking';
import MedalPage from '../pages/users/MedalPage';
export const PublicRoutes: RouteObject[] = [
    {
        path: '/',
        element: <HomePage />
    },
    {
        path: Routes.LOGIN,
        element: <LoginPage /> 
    },
    //Departments
    {
        path: Routes.DEPARTMENT.LIST,
        element: <DepartmentListPage />
    },
    {
        path: Routes.DEPARTMENT.CREATE,
        element: <DepartmentFormPage />
    },
    {
        path: Routes.DEPARTMENT.EDIT,
        element: <DepartmentFormPage />
    },
    //Municipalities
    {
        path: Routes.MUNICIPALITY.LIST,
        element: <MunicipalityListPage />
    },
    {
        path: Routes.MUNICIPALITY.CREATE,
        element: <MunicipalityFormPage />
    },
    {
        path: Routes.MUNICIPALITY.EDIT,
        element: <MunicipalityFormPage />
    },
    //Communities
    {
        path: Routes.COMMUNITY.LIST,
        element: <CommunityListPage />
    },
    {
        path: Routes.COMMUNITY.CREATE,
        element: <CommunityFormPage />
    },
    {
        path: Routes.COMMUNITY.EDIT,
        element: <CommunityFormPage />
    },
    //Water Bodies
    {
        path: Routes.WATERBODY.LIST,
        element: <WaterBodyListPage />
    },
    {
        path: Routes.WATERBODY.CREATE,
        element: <WaterBodyFormPage />
    },
    {
        path: Routes.WATERBODY.EDIT,
        element: <WaterBodyFormPage />
    },
    //Field Trips
    {
        path: Routes.FIELDTRIP.LIST,
        element: <FieldTripListPage />
    },
    {
        path: Routes.FIELDTRIP.CREATE,
        element: <FieldTripFormPage />
    },
    {
        path: Routes.FIELDTRIP.EDIT,
        element: <FieldTripFormPage />
    },
    {
        path: Routes.FIELDTRIP.ASSIGNMENTS,
        element: <FieldTripAssignmentsPage />
    },
    {
        path: Routes.FIELDTRIP.OWN,
        element: <OwnFieldTripsPage />
    },
    {
        path: Routes.FIELDTRIP.ANALYSES,
        element: <UserAnalysesPage />
    },
    //Users
    {
        path: Routes.USER.LIST,
        element: <UserListPage />
    },
    {
        path: Routes.USER.CREATE,
        element: <UserFormPage />
    },
    {
        path: Routes.USER.EDIT,
        element: <UserFormPage />
    },
    {
        path: Routes.USER.RANKING,
        element: <UserRankingPage />
    },
    //Analyses
    {
        path: Routes.ANALYSIS.CREATE,
        element: <SamplingAnalysisFormPage />
    },
    {
        path: Routes.ANALYSIS.EDIT,
        element: <SamplingAnalysisFormPage />
    },
    //Medals
    {
        path: Routes.MEDAL.MY_MEDALS,
        element: <MedalPage />
    },
];