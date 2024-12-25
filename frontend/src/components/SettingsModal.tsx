import { useContext } from "react";
import { useTranslation } from "react-i18next";

import { SECONDARY_COLOR } from "@/shared/global.style";
import ComponentTypes from "@/types/components";
import { DarkModeRounded, LightModeRounded } from "@mui/icons-material";
import {
  Button,
  ButtonGroup,
  Card,
  Divider,
  Grid2 as Grid,
  Modal,
  SxProps,
  Typography,
} from "@mui/material";

import { themeContext } from "./ThemeProvider";

const SETTINGS_CONTAINER_STYLES: SxProps = {
  width: "350px",
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  padding: 2,
  boxSizing: "border-box",
  display: "flex",
  flexDirection: "column",
  justifyContent: "start",
  alignItems: "start",
  rowGap: 2,
};

const SETTINGS_ROW_STYLES: SxProps = {
  flexDirection: "columns",
  alignItems: "center",
  justifyContent: "center",
};

const BUTTON_GROUP_STYLES: SxProps = {
  width: "318px",
};

const SETTINGS_BUTTON_STYLES: SxProps = {
  paddingLeft: 3,
  paddingRight: 3,
  paddingTop: 1.5,
  paddingBottom: 1.5,
  bgcolor: "ButtonFace",
};

const SETTINGS_ACTIVE_BUTTON_STYLES: SxProps = {
  ...SETTINGS_BUTTON_STYLES,
  bgcolor: SECONDARY_COLOR,
};

const SettingsModal = ({
  isSettingsToggled,
  setIsSettingsToggled,
}: ComponentTypes.SettingsModelProps) => {
  const { t, i18n } = useTranslation();
  const { isLightMode, setIsLightMode } = useContext(themeContext);

  return (
    <Modal open={isSettingsToggled} onClose={setIsSettingsToggled}>
      <Card sx={SETTINGS_CONTAINER_STYLES}>
        <Typography>{t("settings.title")}</Typography>
        <Divider flexItem />
        <Grid container sx={SETTINGS_ROW_STYLES}>
          <Typography textAlign={"center"}>{t("settings.theme.title")}</Typography>
          <ButtonGroup fullWidth sx={BUTTON_GROUP_STYLES}>
            <Button
              startIcon={<DarkModeRounded />}
              sx={isLightMode ? SETTINGS_BUTTON_STYLES : SETTINGS_ACTIVE_BUTTON_STYLES}
              onClick={() => setIsLightMode(!isLightMode)}>
              {t("settings.theme.darkTheme")}
            </Button>
            <Button
              startIcon={<LightModeRounded />}
              sx={isLightMode ? SETTINGS_ACTIVE_BUTTON_STYLES : SETTINGS_BUTTON_STYLES}
              onClick={() => setIsLightMode(!isLightMode)}>
              {t("settings.theme.lightTheme")}
            </Button>
          </ButtonGroup>
        </Grid>
        <Divider flexItem />
        <Grid container sx={SETTINGS_ROW_STYLES}>
          <Typography textAlign={"center"}>{t("settings.language.title")}</Typography>
          <ButtonGroup fullWidth sx={BUTTON_GROUP_STYLES}>
            <Button
              sx={i18n.language === "en" ? SETTINGS_ACTIVE_BUTTON_STYLES : SETTINGS_BUTTON_STYLES}
              onClick={() => i18n.changeLanguage("en")}>
              English
            </Button>
            <Button
              sx={i18n.language === "tr" ? SETTINGS_ACTIVE_BUTTON_STYLES : SETTINGS_BUTTON_STYLES}
              onClick={() => i18n.changeLanguage("tr")}>
              Türkçe
            </Button>
          </ButtonGroup>
        </Grid>
      </Card>
    </Modal>
  );
};

export default SettingsModal;
