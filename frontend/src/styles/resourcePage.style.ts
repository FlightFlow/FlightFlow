import { CSSProperties } from "react";

import { SxProps } from "@mui/material";

import { LIGHT_COLOR, PRIMARY_COLOR } from "@/styles/global.style";

export const resourcePageWrapperStyles: CSSProperties = {
  display: "flex",
  alignItems: "start",
  justifyContent: "center",
};

export const resourcePageContentStyles: SxProps = {
  width: "calc(100vw - 280px)",
  height: "100vh",
  overflow: "auto",
  paddingLeft: 3,
  paddingRight: 3,
  paddingTop: 1,
  paddingBottom: 1,
  boxSizing: "border-box",
  flex: 1,
};

export const sidebarCloseStyles: SxProps = {};

export const sidebarOpenStyles: SxProps = {
  bgcolor: PRIMARY_COLOR,
  width: "280px",
  height: "100vh",
  flexDirection: "column",
  padding: 1,
  boxSizing: "border-box",
  color: LIGHT_COLOR,
};

export const sidebarSectionTitleStyles: SxProps = {
  color: "#6D889C",
  paddingTop: 1,
  paddingLeft: 2,
  paddingRight: 2,
  boxSizing: "border-box",
};

export const sidebarMenuItemStyles: SxProps = {
  paddingLeft: 2,
  paddingRight: 2,
  paddingTop: 1,
  paddingBottom: 1,
  borderRadius: 2,
  boxSizing: "border-box",
};

export const sidebarMenuItemIconStyles: SxProps = {
  color: LIGHT_COLOR,
};
