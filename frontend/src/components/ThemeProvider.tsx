import { createContext, useState } from "react";

import {
  BACKGROUND,
  BORDER,
  DARK_COLOR,
  LIGHT_COLOR,
  PRIMARY_COLOR,
  SECONDARY_COLOR,
} from "@/shared/global.style";
import ComponentTypes from "@/types/components";
import { Theme } from "@emotion/react";
import { createTheme, ThemeProvider as MUIThemeProvider, PaletteOptions } from "@mui/material";
import { CssBaseline } from "@mui/material";
import { TypographyOptions } from "@mui/material/styles/createTypography";

export const themeContext = createContext<ComponentTypes.ThemeContextProps>({
  isLightMode: true,
  setIsLightMode: () => {},
});

const ThemeProvider = ({ children }: ComponentTypes.ThemeProviderProps) => {
  const [isLightMode, setIsLightMode] = useState<boolean>(true);

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

  const darkPalette: PaletteOptions = {
    mode: "dark",
    primary: { main: PRIMARY_COLOR },
    secondary: { main: SECONDARY_COLOR },
    background: { default: BACKGROUND },
    text: { primary: DARK_COLOR },
    divider: BORDER,
  };

  const lightPalette: PaletteOptions = {
    mode: "light",
    primary: { main: PRIMARY_COLOR },
    secondary: { main: SECONDARY_COLOR },
    background: { default: LIGHT_COLOR },
    text: { primary: DARK_COLOR },
    divider: BORDER,
  };

  const theme: Theme = createTheme({
    palette: isLightMode ? lightPalette : darkPalette,
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
      <themeContext.Provider value={{ isLightMode, setIsLightMode }}>
        <CssBaseline />
        {children}
      </themeContext.Provider>
    </MUIThemeProvider>
  );
};

export default ThemeProvider;
