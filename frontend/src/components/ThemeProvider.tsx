import { Theme } from "@emotion/react";
import { createTheme, ThemeProvider as MUIThemeProvider, PaletteOptions } from "@mui/material";
import { CssBaseline } from "@mui/material";
import { TypographyOptions } from "@mui/material/styles/createTypography";

import {
  BORDER,
  DARK_COLOR,
  LIGHT_COLOR,
  PRIMARY_COLOR,
  SECONDARY_COLOR,
} from "@/shared/global.style";

import ComponentTypes from "@/types/components";

const ThemeProvider = ({ children }: ComponentTypes.ThemeProviderProps) => {
  const typography: TypographyOptions = {
    fontFamily: "Inter",
    fontSize: 14,
    htmlFontSize: 16,
    fontWeightRegular: 450,
    h1: { fontSize: "2.3em" },
    h2: { fontSize: "2rem" },
    h3: { fontSize: "1.6rem" },
    h4: { fontSize: "1.3rem" },
    h5: { fontSize: "1rem" },
    h6: { fontSize: "1rem" },
  };

  const lightPalette: PaletteOptions = {
    mode: "light",
    common: {
      black: DARK_COLOR,
      white: LIGHT_COLOR,
    },
    primary: { main: PRIMARY_COLOR },
    secondary: { main: SECONDARY_COLOR },
    text: { primary: DARK_COLOR },
    divider: BORDER,
  };

  const theme: Theme = createTheme({
    palette: lightPalette,
    typography,
    components: {
      MuiSvgIcon: {
        styleOverrides: {
          root: {
            width: "22px",
            height: "22px",
            color: DARK_COLOR,
          },
        },
      },
    },
  });

  return (
    <MUIThemeProvider theme={theme}>
      <CssBaseline />
      {children}
    </MUIThemeProvider>
  );
};

export default ThemeProvider;
