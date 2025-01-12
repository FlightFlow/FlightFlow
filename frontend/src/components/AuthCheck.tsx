import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router";

import { useAuth0 } from "@auth0/auth0-react";

import ComponentTypes from "@/types/components";

const AuthCheck = ({ children }: ComponentTypes.AuthCheckProps) => {
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const { isAuthenticated, loginWithRedirect } = useAuth0();

  const isAtRoot: boolean = pathname === "/";

  useEffect(() => {
    if (isAuthenticated) {
      if (isAtRoot) {
        navigate("/app");
        return;
      }
    } else {
      loginWithRedirect();
      return;
    }
  }, [isAuthenticated, isAtRoot, navigate]);

  if (isAuthenticated && !isAtRoot) {
    return children;
  }

  return null;
};

export default AuthCheck;
