import { CSSProperties } from "react";

import { SxProps } from "@mui/material";

// Layout
export const authPageMain: SxProps = {
  width: "100vw",
  minHeight: "100vh",
  height: "100%",
  backgroundImage: "url(../../../public/assets/form_page_background.jpg)",
  backgroundPosition: "center 85%",
  backgroundAttachment: "fixed",
  backgroundSize: "cover",
  backgroundRepeat: "no-repeat",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  flexDirection: "column",
  rowGap: 2,
  padding: 4,
  boxSizing: "border-box",
};

export const formContainer: SxProps = {
  width: "400px",
  borderRadius: 2,
  padding: 4,
  boxSizing: "border-box",
  display: "flex",
  flexDirection: "column",
  rowGap: 2,
  overflowX: "auto",
};

export const logoCardStyles: SxProps = {
  borderRadius: "100%",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
};

export const logoImageStyles: CSSProperties = {
  width: "115px",
  height: "115px",
  backgroundPosition: "center center",
  backgroundRepeat: "no-repeat",
  backgroundSize: "cover",
};

// Form page
export const formHeaderStyles: SxProps = {
  display: "flex",
  flexDirection: "column",
  rowGap: 1,
};

export const authFormElementStyles: CSSProperties = {
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
  rowGap: "10px",
};
