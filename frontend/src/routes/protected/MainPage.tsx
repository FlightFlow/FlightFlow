import { useTranslation } from "react-i18next";

import { Grid2 as Grid, SxProps, Typography } from "@mui/material";

const CONTAINER_STYLES: SxProps = {
  display: "flex",
  flex: 1,
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "column",
  rowGap: 1,
};

const MainPage = () => {
  const { t } = useTranslation();

  return (
    <Grid sx={CONTAINER_STYLES}>
      <Typography variant="h3" fontWeight={"bold"}>
        {t("mainResourcePage.title")}
      </Typography>
      <Typography variant="subtitle1">{t("mainResourcePage.subtitle")}</Typography>
    </Grid>
  );
};

export default MainPage;
