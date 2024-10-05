import { useRoutes } from "react-router-dom";
import HomePage from "../pages/HomePage/HomePage";
import Login from "../pages/Login/Login";
import Register from "../pages/Register/Register";

// function ProtectedRoute() {
//     // Capitalized the component name
//     const isAuthenticated = useSelector(
//       (state: RootState) => state.user.isAuthenticated
//     );
//     return isAuthenticated ? <Outlet /> : <Navigate to="/login" />;
//   }

//   function RejectedRoute() {
//     // Capitalized the component name
//     const isAuthenticated = useSelector(
//       (state: RootState) => state.user.isAuthenticated
//     );
//     return !isAuthenticated ? <Outlet /> : <Navigate to="/" />;
//   }

function useRouterElement() {
  const routeElement = useRoutes([
    {
      path: "/",
      element: <HomePage />,
    },
    {
      path: "/login",
      element: <Login />,
    },
    {
      path: "/register",
      element: <Register />,
    },
  ]);
  return routeElement;
}

export default useRouterElement;
