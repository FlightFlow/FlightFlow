import { CSSProperties } from "react";

import { SxProps } from "@mui/material";

export const introMainStyles: CSSProperties = {
  width: "100vw",
  height: "100vh",
  backgroundImage: "url(../../../public/assets/background.jpg)",
  backgroundPosition: "center center",
  backgroundRepeat: "no-repeat",
  backgroundSize: "cover",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  paddingBottom: 100,
};

export const introContainerStyles: SxProps = {
  display: "flex",
  width: "fit-content",
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
  rowGap: 2,
  borderRadius: 2,
  boxSizing: "border-box",
};

export const introPageLogoStyles: CSSProperties = {
  width: "120px",
  height: "120px",
};

export const introPageButtonContainerStyles: SxProps = {
  flexDirection: "column",
  rowGap: 1,
};

export const introPageButtonStyles: SxProps = {
  borderRadius: 10,
  borderWidth: 2,
  paddingTop: 1.5,
  paddingBottom: 1.5,
  paddingLeft: 3,
  paddingRight: 3,
  boxSizing: "border-box",
};
