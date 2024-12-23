import { useEffect } from "react";
import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router";

import { useAuth0 } from "@auth0/auth0-react";
import { AccountCircleRounded } from "@mui/icons-material";
import { Button, Grid2 as Grid, Typography } from "@mui/material";

import {
  introContainerStyles,
  introMainStyles,
  introPageButtonContainerStyles,
  introPageButtonStyles,
  introPageLogoStyles,
} from "@/styles/introPage.style";

const IntroPage = () => {
  const { loginWithRedirect, isAuthenticated } = useAuth0();
  const { t } = useTranslation();
  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated) {
      navigate("/app/plane");
    }
    return;
  }, [isAuthenticated, navigate]);

  return (
    <main style={introMainStyles}>
      <Grid sx={introContainerStyles}>
        <img
          src="../../../public/assets/logo_rounded.png"
          alt="FlightCoordinator Logo"
          style={introPageLogoStyles}
        />
        <Typography variant="h1" color="Background" fontWeight={550}>
          {t("introPage.pageTitle")} 👋
        </Typography>
        <Grid container sx={introPageButtonContainerStyles}>
          <Button
            variant="contained"
            startIcon={<AccountCircleRounded />}
            sx={introPageButtonStyles}
            onClick={() => loginWithRedirect()}>
            {t("introPage.authButton")}
          </Button>
        </Grid>
      </Grid>
    </main>
  );
};

export default IntroPage;
