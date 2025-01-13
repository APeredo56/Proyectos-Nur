import { Outlet } from "react-router-dom";
import { UserProvider } from "../contexts/user/UserProvider";
import TopNavbarComponent from "../components/common/TopNavbarComponent";

const MainLayout = () => {
    return (<UserProvider>
        <TopNavbarComponent />
        <main className="max-w-screen-2xl 2xl:mx-auto w-full px-8 sm:px-12 md:px-16 lg:px-24">
            <Outlet />
        </main>
    </UserProvider>);
}

export default MainLayout;