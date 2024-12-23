import { useState } from "react";
import { Outlet } from "react-router";

import { useAuth0 } from "@auth0/auth0-react";
import { Article, ArticleRounded, GitHub, LogoutRounded, MenuRounded } from "@mui/icons-material";
import { AppBar, Box, Grid2 as Grid, IconButton, Tooltip, Typography } from "@mui/material";

import AuthCheck from "@/components/AuthCheck";
import SidebarLinks from "@/components/SidebarLinks";

import { LIGHT_COLOR } from "@/styles/global.style";
import {
  resourcePageContentStyles,
  resourcePageWrapperStyles,
  sidebarCloseStyles,
  sidebarOpenStyles,
} from "@/styles/resourcePage.style";

const ResourcePageLayout = () => {
  const { isAuthenticated, logout, user } = useAuth0();
  const [isSidebarToggled, setIsSidebarToggled] = useState<boolean>(true);

  return (
    <AuthCheck>
      <main>
        <Grid
          container
          sx={{
            paddingLeft: 1,
            paddingRight: 1,
            paddingTop: 1,
            paddingBottom: 1,
            bgcolor: "primary.main",
            color: LIGHT_COLOR,
            alignItems: "center",
            justifyContent: "space-between",
          }}>
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              columnGap: 1,
            }}>
            <IconButton>
              <MenuRounded sx={{ color: LIGHT_COLOR }} />
            </IconButton>
            <Box
              sx={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                columnGap: 1.5,
              }}>
              <img
                src="../../../public/assets/logo_rounded.png"
                style={{ width: "50px", height: "50px" }}
              />
              <Typography variant="h3" fontWeight={500}>
                FlightCoordinator
              </Typography>
            </Box>
          </Box>
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              columnGap: 1,
            }}>
            <Box
              sx={{
                paddingLeft: 1,
                paddingRight: 1,
              }}>
              {isAuthenticated ? user?.name : "Please re-login."}
            </Box>
            <IconButton onClick={() => logout()}>
              <Tooltip title="Logout">
                <LogoutRounded sx={{ color: LIGHT_COLOR }} />
              </Tooltip>
            </IconButton>
            <IconButton>
              <Tooltip title="See Documentation">
                <ArticleRounded sx={{ color: LIGHT_COLOR }} />
              </Tooltip>
            </IconButton>
            <IconButton>
              <Tooltip title="See GitHub Repository">
                <GitHub sx={{ color: LIGHT_COLOR }} />
              </Tooltip>
            </IconButton>
          </Box>
        </Grid>
        <Grid container sx={resourcePageWrapperStyles}>
          <Grid container sx={isSidebarToggled ? sidebarOpenStyles : sidebarCloseStyles}>
            <SidebarLinks />
          </Grid>
          <Grid container sx={resourcePageContentStyles}>
            <Grid>
              <Outlet />
            </Grid>
          </Grid>
        </Grid>
      </main>
    </AuthCheck>
  );
};

export default ResourcePageLayout;
