import { CSSProperties, useEffect } from "react";
import { useTranslation } from "react-i18next";
import { Link as RouterLink, useNavigate } from "react-router";

import { useAuth0 } from "@auth0/auth0-react";
import { ArticleRounded, GitHub, MonitorHeartRounded } from "@mui/icons-material";
import { Box, Button, Grid2 as Grid, Link, SxProps, Typography } from "@mui/material";

import { LANDING_PAGE_LIGHT_COLOR, PRIMARY_COLOR, SECONDARY_COLOR } from "@/shared/global.style";

const MAIN_STYLES: CSSProperties = {
  width: "100vw",
  height: "100vh",
  background:
    "linear-gradient(0deg, rgba(14, 19, 27, 0.50) 0%, rgba(14, 19, 27, 0.50) 100%), url('./assets/background.jpg') lightgray 50% / cover no-repeat",
  backgroundPosition: "center center",
  backgroundRepeat: "no-repeat",
  backgroundSize: "cover",
  userSelect: "none",
};

const CONTAINER_STYLES: SxProps = {
  width: "100vw",
  height: "calc(100vh - 144px)",
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
  rowGap: 2,
  borderRadius: 2,
  boxSizing: "border-box",
};

const HEADER_CONTAINER_STYLES: SxProps = {
  width: "100vw",
  display: "flex",
  alignItems: "center",
  justifyContent: "space-between",
  padding: 1.5,
};

const HEADER_CONTAINER_LEFT_SECTION_STYLES: SxProps = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  columnGap: 1.5,
};

const HEADER_TITLE_STYLES: SxProps = {
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
};

const LOGO_STYLES: CSSProperties = {
  width: "60px",
  height: "60px",
};

const HEADER_CONTAINER_RIGHT_SECTION_STYLES: SxProps = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "row",
  columnGap: 4,
};

const HEADER_LINK_STYLES: SxProps = {
  textDecoration: "none",
  display: "flex",
  flexDirection: "row",
  alignItems: "center",
  justifyContent: "center",
  columnGap: 0.5,
};

const HERO_TEXT_STYLES: SxProps = {
  color: LANDING_PAGE_LIGHT_COLOR,
  fontSize: "50px",
  lineHeight: "60px",
  fontWeight: "550",
  letterSpacing: "-0.5px",
  textAlign: "center",
};

const FOOTER_STYLES: SxProps = {
  height: "60px",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  textAlign: "center",
  color: LANDING_PAGE_LIGHT_COLOR,
  columnGap: 0.5,
};

const PublicPageLayout = () => {
  const navigate = useNavigate();
  const { isAuthenticated, loginWithRedirect } = useAuth0();
  const { t } = useTranslation();

  useEffect(() => {
    if (isAuthenticated) {
      navigate("/app");
    }
    return;
  }, [isAuthenticated, navigate]);

  return (
    <main style={MAIN_STYLES}>
      <Grid sx={HEADER_CONTAINER_STYLES}>
        <Grid sx={HEADER_CONTAINER_LEFT_SECTION_STYLES}>
          <img src="./assets/logo_bordered.png" alt="FlightCoordinator Logo" style={LOGO_STYLES} />
          <Grid sx={HEADER_TITLE_STYLES}>
            <Typography
              variant="h3"
              sx={{ fontWeight: "bold", letterSpacing: "-0.5px", color: LANDING_PAGE_LIGHT_COLOR }}>
              FlightCoordinator
            </Typography>
            <Typography variant="subtitle1" sx={{ color: LANDING_PAGE_LIGHT_COLOR }}>
              {t("landingPage.subTitle")}
            </Typography>
          </Grid>
        </Grid>
        <Grid sx={HEADER_CONTAINER_RIGHT_SECTION_STYLES}>
          <Link
            component={RouterLink}
            to={"/status"}
            color={LANDING_PAGE_LIGHT_COLOR}
            sx={HEADER_LINK_STYLES}>
            <MonitorHeartRounded sx={{ color: LANDING_PAGE_LIGHT_COLOR }} />
            {t("landingPage.menu.statusLink")}
          </Link>
          <Link
            component={RouterLink}
            to={"https://github.com/FlightCoordinator/Documentation"}
            target="_blank"
            color={LANDING_PAGE_LIGHT_COLOR}
            sx={HEADER_LINK_STYLES}>
            <ArticleRounded sx={{ color: LANDING_PAGE_LIGHT_COLOR }} />
            {t("landingPage.menu.docsLink")}
          </Link>
          <Link
            component={RouterLink}
            to={"https://github.com/FlightCoordinator/FlightCoordinator"}
            target="_blank"
            color={LANDING_PAGE_LIGHT_COLOR}
            sx={HEADER_LINK_STYLES}>
            <GitHub sx={{ color: LANDING_PAGE_LIGHT_COLOR }} />
            {t("landingPage.menu.repoLink")}
          </Link>
          <Button
            variant="contained"
            onClick={() => loginWithRedirect()}
            sx={{ textTransform: "initial", bgcolor: SECONDARY_COLOR, color: PRIMARY_COLOR }}>
            {t("landingPage.authButton")}
          </Button>
        </Grid>
      </Grid>
      <Box sx={CONTAINER_STYLES}>
        <Typography sx={HERO_TEXT_STYLES}>
          {t("landingPage.hero.line_1")}
          <br />
          {t("landingPage.hero.line_2")}
        </Typography>
      </Box>
      <Grid sx={FOOTER_STYLES}>
        {t("landingPage.footer.main")}
        <Link
          component={RouterLink}
          to={"https://github.com/FlightCoordinator/FlightCoordinator/blob/main/LICENSE"}
          target="_blank"
          color={LANDING_PAGE_LIGHT_COLOR}
          sx={{ textDecoration: "none" }}>
          {t("landingPage.footer.license")}
        </Link>
      </Grid>
    </main>
  );
};

export default PublicPageLayout;
