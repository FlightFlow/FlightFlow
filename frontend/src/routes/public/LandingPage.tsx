import { CSSProperties, useEffect } from "react";
import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router";

import { useAuth0 } from "@auth0/auth0-react";
import { LoginRounded } from "@mui/icons-material";
import { Button, Grid2 as Grid, SxProps, Typography } from "@mui/material";

const MAIN_STYLES: CSSProperties = {
  width: "100vw",
  height: "100vh",
  backgroundImage: "url(../../../public/assets/background.jpg)",
  backgroundPosition: "center center",
  backgroundRepeat: "no-repeat",
  backgroundSize: "cover",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  paddingBottom: 100,
};

const CONTAINER_STYLES: SxProps = {
  display: "flex",
  width: "fit-content",
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
  rowGap: 2,
  borderRadius: 2,
  boxSizing: "border-box",
};

const LOGO_STYLES: CSSProperties = {
  width: "120px",
  height: "120px",
};

const BUTTON_CONTAINER_STYLES: SxProps = {
  flexDirection: "column",
  rowGap: 1,
};

const BUTTON_STYLES: SxProps = {
  borderRadius: 10,
  borderWidth: 2,
  paddingTop: 1.5,
  paddingBottom: 1.5,
  paddingLeft: 3,
  paddingRight: 3,
  boxSizing: "border-box",
};

const LandingPage = () => {
  const { loginWithRedirect, isAuthenticated } = useAuth0();
  const { t } = useTranslation();
  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated) {
      navigate("/app/certification");
    }
    return;
  }, [isAuthenticated, navigate]);

  return (
    <main style={MAIN_STYLES}>
      <Grid sx={CONTAINER_STYLES}>
        <img
          src="../../../public/assets/logo.png"
          alt="FlightCoordinator Logo"
          style={LOGO_STYLES}
        />
        <Typography variant="h1" color="Background" fontWeight={550}>
          {t("introPage.pageTitle")} 👋
        </Typography>
        <Grid container sx={BUTTON_CONTAINER_STYLES}>
          <Button
            variant="contained"
            startIcon={<LoginRounded />}
            sx={BUTTON_STYLES}
            onClick={() => loginWithRedirect()}>
            {t("introPage.authButton")}
          </Button>
        </Grid>
      </Grid>
    </main>
  );
};

export default LandingPage;
