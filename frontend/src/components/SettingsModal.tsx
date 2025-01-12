import { useTranslation } from "react-i18next";

import { useAuth0 } from "@auth0/auth0-react";
import { CloseRounded, LogoutRounded } from "@mui/icons-material";
import {
  Button,
  ButtonGroup,
  Card,
  Divider,
  Grid2 as Grid,
  IconButton,
  Modal,
  SxProps,
  Typography,
} from "@mui/material";

import { BORDER, LIGHT_COLOR, LIGHT_ICON_STYLES } from "@/shared/global.style";

import ComponentTypes from "@/types/components";

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
  rowGap: 1,
};

const SETTINGS_HEADER_STYLES: SxProps = {
  width: "100%",
  alignItems: "center",
  justifyContent: "space-between",
};

const SETTINGS_ROW_STYLES: SxProps = {
  width: "100%",
  flexDirection: "columns",
  alignItems: "center",
  justifyContent: "start",
  rowGap: 0.5,
};

const BUTTON_GROUP_STYLES: SxProps = {
  width: "318px",
};

const SETTINGS_BUTTON_STYLES: SxProps = {
  paddingLeft: 3,
  paddingRight: 3,
  paddingTop: 1.25,
  paddingBottom: 1.25,
  bgcolor: LIGHT_COLOR,
};

const SETTINGS_ACTIVE_BUTTON_STYLES: SxProps = {
  ...SETTINGS_BUTTON_STYLES,
  bgcolor: BORDER,
};

const LOGOUT_BUTTON_STYLES: SxProps = {
  width: "100%",
  paddingLeft: 3,
  paddingRight: 3,
  paddingTop: 1.25,
  paddingBottom: 1.25,
};

const SettingsModal = ({
  isSettingsToggled,
  setIsSettingsToggled,
}: ComponentTypes.SettingsModelProps) => {
  const { logout } = useAuth0();
  const { t, i18n } = useTranslation();

  return (
    <Modal open={isSettingsToggled} onClose={setIsSettingsToggled}>
      <Card sx={SETTINGS_CONTAINER_STYLES}>
        <Grid container sx={SETTINGS_HEADER_STYLES}>
          <Typography variant="h3" fontWeight={650}>
            {t("settings.title")}
          </Typography>
          <IconButton onClick={() => setIsSettingsToggled(false)}>
            <CloseRounded />
          </IconButton>
        </Grid>
        <Divider flexItem />
        <Grid container sx={SETTINGS_ROW_STYLES}>
          <Typography textAlign={"center"} fontWeight={650}>
            {t("settings.language.title")}
          </Typography>
          <ButtonGroup fullWidth sx={BUTTON_GROUP_STYLES}>
            <Button
              sx={i18n.language === "en" ? SETTINGS_ACTIVE_BUTTON_STYLES : SETTINGS_BUTTON_STYLES}
              onClick={() => i18n.changeLanguage("en")}>
              {t("settings.language.english")}
            </Button>
            <Button
              sx={i18n.language === "tr" ? SETTINGS_ACTIVE_BUTTON_STYLES : SETTINGS_BUTTON_STYLES}
              onClick={() => i18n.changeLanguage("tr")}>
              {t("settings.language.turkish")}
            </Button>
          </ButtonGroup>
        </Grid>
        <Divider flexItem />
        <Grid container sx={SETTINGS_ROW_STYLES}>
          <Typography textAlign={"center"} fontWeight={650}>
            {t("settings.account.title")}
          </Typography>
          <Button
            variant="contained"
            startIcon={<LogoutRounded sx={LIGHT_ICON_STYLES} />}
            sx={LOGOUT_BUTTON_STYLES}
            onClick={() => logout()}>
            {t("settings.account.logout")}
          </Button>
        </Grid>
      </Card>
    </Modal>
  );
};

export default SettingsModal;
