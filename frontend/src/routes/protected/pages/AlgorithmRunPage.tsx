import { useTranslation } from "react-i18next";

import { ConstructionRounded } from "@mui/icons-material";
import { Grid2 as Grid, SxProps } from "@mui/material";

const CONTAINER_STYLES: SxProps = {
  display: "flex",
  flex: 1,
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "column",
  rowGap: 1,
};

const ICON_STYLES: SxProps = {
  width: "40px",
  height: "40px",
};

const AlgorithmRunPage = () => {
  const { t } = useTranslation();

  return (
    <Grid sx={CONTAINER_STYLES}>
      <ConstructionRounded sx={ICON_STYLES} />
      {t("pageNotReady")}
    </Grid>
  );
};

export default AlgorithmRunPage;
