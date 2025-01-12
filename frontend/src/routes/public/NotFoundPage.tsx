import { CSSProperties } from "react";
import { useTranslation } from "react-i18next";
import { Link as RouterLink } from "react-router";

import { ArrowBackRounded } from "@mui/icons-material";
import { Grid2 as Grid, Link, SxProps, Typography } from "@mui/material";

import { DARK_COLOR, LIGHT_COLOR, SECONDARY_COLOR } from "@/shared/global.style";

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
  height: "100vh",
  display: "flex",
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

const TITLE_STYLES: SxProps = {
  color: LIGHT_COLOR,
  fontWeight: "600",
};

const SUBTITLE_STYLES: SxProps = {
  color: LIGHT_COLOR,
};

const LINK_STYLES: SxProps = {
  bgcolor: SECONDARY_COLOR,
  color: DARK_COLOR,
  textDecoration: "none",
  paddingX: 2,
  paddingY: 1,
  borderRadius: 1,
  display: "flex",
  flexDirection: "row",
  alignItems: "center",
  justifyContent: "center",
  columnGap: 1,
};

const NotFoundPage = () => {
  const { t } = useTranslation();

  return (
    <main style={MAIN_STYLES}>
      <Grid sx={CONTAINER_STYLES}>
        <img src="./assets/logo_bordered.png" alt="FlightCoordinator Logo" style={LOGO_STYLES} />
        <Typography variant="h2" sx={TITLE_STYLES}>
          {t("notFoundPage.title")}
        </Typography>
        <Typography sx={SUBTITLE_STYLES}> {t("notFoundPage.subtitle")}</Typography>
        <Link component={RouterLink} to="/" sx={LINK_STYLES}>
          <ArrowBackRounded />
          {t("notFoundPage.returnButton")}
        </Link>
      </Grid>
    </main>
  );
};

export default NotFoundPage;
