import { CSSProperties, useState } from "react";
import { Outlet } from "react-router";

import { BACKGROUND, BORDER, DARK_GRAY_COLOR, LIGHT_COLOR } from "@/shared/global.style";
import { useAuth0 } from "@auth0/auth0-react";
import {
  AutoMode,
  DarkModeRounded,
  LightModeRounded,
  LogoutRounded,
  SettingsBrightness,
  SettingsBrightnessRounded,
  SettingsRounded,
} from "@mui/icons-material";
import {
  Box,
  Button,
  ButtonGroup,
  Card,
  Divider,
  Grid2 as Grid,
  Modal,
  SxProps,
  Typography,
} from "@mui/material";

import AuthCheck from "@/components/AuthCheck";
import IconButton from "@/components/IconButton";
import SidebarLinks from "@/components/SidebarLinks";

const MAIN_STYLES: CSSProperties = {
  display: "flex",
  alignItems: "start",
  justifyContent: "center",
};

const SIDEBAR_WRAPPER_STYLES: SxProps = {
  bgcolor: BACKGROUND,
  borderRight: `solid 1px ${BORDER}`,
  width: "280px",
  height: "100vh",
};

const SIDEBAR_MENU_STYLES: SxProps = {
  width: "280px",
  height: "calc(100vh - 69px)",
  alignItems: "start",
  justifyContent: "start",
  flexWrap: "nowrap",
  flexDirection: "column",
  overflowY: "auto",
  overflowX: "hidden",
};

const SIDEBAR_USER_INFO_STYLES: SxProps = {
  borderTop: `solid 1px ${BORDER}`,
  width: "280px",
  flexDirection: "row",
  alignItems: "center",
  justifyContent: "start",
  columnGap: 1,
  padding: 1.5,
  boxSizing: "border-box",
  height: "69px",
};

const SIDEBAR_USER_PHOTO_STYLES: CSSProperties = {
  width: "44px",
  height: "44px",
  borderRadius: "5px",
};

const SIDEBAR_BLANK_USER_PHOTO_STYLES: SxProps = {
  bgcolor: BORDER,
  width: "44px",
  height: "44px",
  borderRadius: "5px",
};

const SIDEBAR_USER_EMAIL_STYLES: SxProps = {
  color: DARK_GRAY_COLOR,
  fontSize: "0.8em",
  paddingBottom: 0.25,
  boxSizing: "border-box",
};

const SIDEBAR_USER_DETAILS_STYLES: SxProps = {
  width: "154px",
};

const CONTENT_STYLES: SxProps = {
  width: "calc(100vw - 280px)",
  height: "100vh",
  paddingLeft: 3,
  paddingRight: 3,
  paddingTop: 1,
  paddingBottom: 1,
  boxSizing: "border-box",
};

const SIDEBAR_PADDING: SxProps = {
  width: "100%",
  padding: 1,
  boxSizing: "border-box",
};

const SETTIGS_BUTTON_STYLES: SxProps = {
  paddingLeft: 3,
  paddingRight: 3,
  paddingTop: 1.5,
  paddingBottom: 1.5,
};

const ResourcePageLayout = () => {
  const { isAuthenticated, user, logout } = useAuth0();
  const [isSettingsToggled, setIsSettingsToggled] = useState<boolean>(false);

  return (
    <AuthCheck>
      <Modal
        open={isSettingsToggled}
        onClose={() => setIsSettingsToggled((settingsState) => !settingsState)}>
        <Card
          sx={{
            width: "400px",
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            padding: 2,
            display: "flex",
            flexDirection: "column",
            justifyContent: "start",
            alignItems: "start",
            rowGap: 1,
          }}>
          <Typography variant="h3">Settings</Typography>
          <Divider flexItem />
          <Grid container>
            <Typography>Theme</Typography>
            <ButtonGroup>
              <Button startIcon={<DarkModeRounded />} sx={SETTIGS_BUTTON_STYLES}>
                Dark
              </Button>
              <Button startIcon={<SettingsBrightnessRounded />} sx={SETTIGS_BUTTON_STYLES}>
                System
              </Button>
              <Button startIcon={<LightModeRounded />} sx={SETTIGS_BUTTON_STYLES}>
                Light
              </Button>
            </ButtonGroup>
          </Grid>
          <Grid container>
            <Typography>Language</Typography>
            <ButtonGroup>
              <Button sx={SETTIGS_BUTTON_STYLES}>English</Button>
              <Button sx={SETTIGS_BUTTON_STYLES}>Türkçe</Button>
            </ButtonGroup>
          </Grid>
          {/* logout */}
          <Button
            variant="contained"
            color="error"
            onClick={() => logout()}
            startIcon={<LogoutRounded sx={{ color: LIGHT_COLOR }} />}>
            Logout
          </Button>
        </Card>
      </Modal>
      <main style={MAIN_STYLES}>
        <Grid container sx={SIDEBAR_WRAPPER_STYLES}>
          <Grid container sx={SIDEBAR_MENU_STYLES}>
            <Grid
              container
              sx={{
                width: "100%",
                padding: 2,
                boxSizing: "border-box",
                flexDirection: "row",
                alignItems: "center",
                justifyContent: "start",
                columnGap: 1.25,
              }}>
              <img
                src="../../../public/assets/logo.png"
                alt="FlightCoordinator Logo"
                style={{
                  width: "60px",
                  height: "60px",
                }}
              />
              <Typography variant="h3" height={60} fontWeight={700}>
                Flight
                <br />
                Coordinator
              </Typography>
            </Grid>
            <Divider flexItem />
            <Box sx={SIDEBAR_PADDING}>
              <SidebarLinks section="algorithm" />
            </Box>
            <Divider flexItem />
            <Box sx={SIDEBAR_PADDING}>
              <SidebarLinks section="resource" />
            </Box>
            <Divider flexItem />
            <Box sx={SIDEBAR_PADDING}>
              <SidebarLinks section="external" />
            </Box>
          </Grid>
          <Grid container sx={SIDEBAR_USER_INFO_STYLES}>
            {user?.picture ? (
              <img src={user.picture} style={SIDEBAR_USER_PHOTO_STYLES} />
            ) : (
              <Box sx={SIDEBAR_BLANK_USER_PHOTO_STYLES} />
            )}
            <Box sx={SIDEBAR_USER_DETAILS_STYLES}>
              <Typography noWrap={true} fontWeight={600}>
                {user?.nickname}
              </Typography>
              <Typography noWrap={true} sx={SIDEBAR_USER_EMAIL_STYLES}>
                {isAuthenticated ? user?.email : "Please re-login."}
              </Typography>
            </Box>
            <IconButton
              icon={<SettingsRounded />}
              tooltipText="Settings"
              onclick={() => setIsSettingsToggled(true)}
            />
          </Grid>
        </Grid>
        <Grid container sx={CONTENT_STYLES}>
          <Outlet />
        </Grid>
      </main>
    </AuthCheck>
  );
};

export default ResourcePageLayout;
