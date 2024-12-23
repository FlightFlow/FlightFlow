import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router";

import ComponentTypes from "@/types/components";
import { useAuth0 } from "@auth0/auth0-react";

const AuthCheck = ({ children }: ComponentTypes.AuthCheckProps) => {
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const { isAuthenticated } = useAuth0();

  const isAtRoot: boolean = pathname === "/";

  useEffect(() => {
    if (isAuthenticated) {
      if (isAtRoot) {
        navigate("/app/airport");
        return;
      }
    } else {
      navigate("/");
      return;
    }
  }, [isAuthenticated, isAtRoot, navigate]);

  if (isAuthenticated && !isAtRoot) {
    return children;
  }

  return null;
};

export default AuthCheck;
