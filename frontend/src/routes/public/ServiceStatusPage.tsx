import { CSSProperties } from "react";
import { useTranslation } from "react-i18next";
import { Link as RouterLink } from "react-router";

import { CheckCircle, ErrorOutline } from "@mui/icons-material";
import {
  Alert,
  Box,
  CircularProgress,
  Grid2 as Grid,
  Link,
  Paper,
  SxProps,
  Typography,
} from "@mui/material";

import useServiceStatusQuery from "@/hooks/useServiceStatusQuery";

import { DARK_COLOR, LIGHT_COLOR } from "@/shared/global.style";

const MAIN_CONTAINER_STYLES: SxProps = {
  width: "100vw",
  height: "100vh",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "column",
  rowGap: 2,
};

const PAPER_STYLES: SxProps = {
  width: "400px",
  display: "flex",
  flexDirection: "column",
  padding: 3,
  boxSizing: "border-box",
  rowGap: 1.5,
  borderRadius: 3,
};

const LOGO_CONTAINER_STYLES: SxProps = {
  display: "flex",
  alignItems: "center",
  justifyContent: "start",
  columnGap: 1.5,
};

const TITLE_STYLES: SxProps = {
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
};

const LOGO_STYLES: CSSProperties = {
  width: "60px",
  height: "60px",
};

const RETURN_LINK_STYLES: SxProps = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  columnGap: 1,
  textDecoration: "none",
};

const LOADING_BOX_STYLES: SxProps = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "column",
  rowGap: 2,
};

const LIGHT_COLOR_STYLES: SxProps = { color: LIGHT_COLOR };

const ServiceStatusPage = () => {
  const { t } = useTranslation();

  const {
    dataServiceStatus: { data: dataServiceStatus, isLoading: isDataServiceStatusLoading },
    algorithmServiceStatus: {
      data: algorithmServiceStatus,
      isLoading: isAlgorithmServiceStatusLoading,
    },
  } = useServiceStatusQuery();

  const isDataServiceOperational: boolean = dataServiceStatus?.status === "UP";
  const isAlgorithmServiceOperational: boolean = algorithmServiceStatus?.status === "UP";

  return (
    <Grid sx={MAIN_CONTAINER_STYLES}>
      <Paper elevation={5} sx={PAPER_STYLES}>
        {isAlgorithmServiceStatusLoading || isDataServiceStatusLoading ? (
          <Box sx={LOADING_BOX_STYLES}>
            <CircularProgress size={30} />
            {t("statusPage.loading")}
          </Box>
        ) : (
          <>
            <Grid sx={LOGO_CONTAINER_STYLES}>
              <img src="./assets/logo.png" alt="FlightCoordinator Logo" style={LOGO_STYLES} />
              <Grid sx={TITLE_STYLES}>
                <Typography
                  variant="h3"
                  sx={{ fontWeight: "bold", letterSpacing: "-0.5px", color: DARK_COLOR }}>
                  FlightCoordinator
                </Typography>
                <Typography variant="subtitle1" sx={{ color: DARK_COLOR }}>
                  {t("statusPage.subTitle")}
                </Typography>
              </Grid>
            </Grid>
            <Alert
              icon={
                isDataServiceOperational ? (
                  <CheckCircle sx={LIGHT_COLOR_STYLES} />
                ) : (
                  <ErrorOutline sx={LIGHT_COLOR_STYLES} />
                )
              }
              severity={isDataServiceOperational ? "success" : "error"}
              variant="filled"
              sx={LIGHT_COLOR_STYLES}>
              {isDataServiceOperational
                ? t("statusPage.operational.dataService")
                : t("statusPage.hasProblems.dataService")}
            </Alert>
            <Alert
              icon={
                isAlgorithmServiceOperational ? (
                  <CheckCircle sx={LIGHT_COLOR_STYLES} />
                ) : (
                  <ErrorOutline sx={LIGHT_COLOR_STYLES} />
                )
              }
              severity={isAlgorithmServiceOperational ? "success" : "error"}
              variant="filled"
              sx={LIGHT_COLOR_STYLES}>
              {isAlgorithmServiceOperational
                ? t("statusPage.operational.algorithmService")
                : t("statusPage.hasProblems.algorithmService")}
            </Alert>
          </>
        )}
      </Paper>
      {!(isAlgorithmServiceStatusLoading || isDataServiceStatusLoading) && (
        <Link component={RouterLink} to="/" sx={RETURN_LINK_STYLES}>
          {t("statusPage.returnButton")}
        </Link>
      )}
    </Grid>
  );
};

export default ServiceStatusPage;
