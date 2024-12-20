import { useLocation, useNavigate } from "react-router";

import ComponentTypes from "@/types/components";
import { Box, CircularProgress } from "@mui/material";

import useAuthValidationQuery from "@/hooks/auth/useAuthValidationQuery";

import { authCheckLoadingOverlayStyles } from "@/styles/components.style";

const AuthCheck = ({ children }: ComponentTypes.AuthCheckProps) => {
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const { isError, isLoading, data: queryResponse } = useAuthValidationQuery();

  const isAtRoot: boolean = pathname === "/";

  if (isLoading) {
    return (
      <Box sx={authCheckLoadingOverlayStyles}>
        <CircularProgress size={30} />
      </Box>
    );
  }

  if (isError) {
    navigate("/error");
    return null;
  }

  if (queryResponse?.isSuccess) {
    if (isAtRoot) {
      navigate("/app/airport");
      return null;
    }
    return children;
  } else {
    navigate("/unauthorized");
    return null;
  }
};

export default AuthCheck;
