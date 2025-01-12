import { CSSProperties, useState } from "react";
import { useTranslation } from "react-i18next";
import { Outlet } from "react-router";

import { useAuth0 } from "@auth0/auth0-react";
import { SettingsRounded } from "@mui/icons-material";
import { Box, Divider, Grid2 as Grid, SxProps, Typography } from "@mui/material";

import AuthCheck from "@/components/AuthCheck";
import IconButton from "@/components/IconButton";
import SettingsModal from "@/components/SettingsModal";
import SidebarLinks from "@/components/SidebarLinks";

import { BACKGROUND, BORDER, DARK_GRAY_COLOR } from "@/shared/global.style";

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
  minWidth: "280px",
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
  overflowX: "auto",
  height: "100vh",
  paddingLeft: 3,
  paddingRight: 3,
  paddingTop: 1,
  paddingBottom: 1,
  boxSizing: "border-box",
};

const SIDEBAR_LOGO_CONTAINER_STYLES: SxProps = {
  width: "100%",
  padding: 2,
  boxSizing: "border-box",
  flexDirection: "row",
  alignItems: "center",
  justifyContent: "start",
  columnGap: 1.25,
};

const SIDEBAR_LOGO_IMAGE_STYLES: CSSProperties = {
  width: "60px",
  height: "60px",
};

const SIDEBAR_PADDING: SxProps = {
  width: "100%",
  padding: 1,
  boxSizing: "border-box",
};

const ResourcePageLayout = () => {
  const { isAuthenticated, user } = useAuth0();
  const { t } = useTranslation();
  const [isSettingsToggled, setIsSettingsToggled] = useState<boolean>(false);

  return (
    <AuthCheck>
      <SettingsModal
        isSettingsToggled={isSettingsToggled}
        setIsSettingsToggled={() => setIsSettingsToggled((settingsState) => !settingsState)}
      />
      <main style={MAIN_STYLES}>
        <Grid container sx={SIDEBAR_WRAPPER_STYLES}>
          <Grid container sx={SIDEBAR_MENU_STYLES}>
            <Grid container sx={SIDEBAR_LOGO_CONTAINER_STYLES}>
              <img
                src="./assets/logo.png"
                alt="FlightCoordinator Logo"
                style={SIDEBAR_LOGO_IMAGE_STYLES}
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
                {isAuthenticated ? user?.email : t("sidebar.relogin")}
              </Typography>
            </Box>
            <IconButton icon={<SettingsRounded />} onclick={() => setIsSettingsToggled(true)} />
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
