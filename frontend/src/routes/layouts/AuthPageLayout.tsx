import { Outlet } from "react-router";

import { Card, Grid2 as Grid } from "@mui/material";

import { authPageMain, formContainer } from "@/styles/authPage.style";

const AuthPageLayout = () => {
  return (
    <Grid container sx={authPageMain}>
      <Card sx={formContainer}>
        <Outlet />
      </Card>
    </Grid>
  );
};

export default AuthPageLayout;
