import { BrowserRouter } from "react-router";

import { Auth0Provider } from "@auth0/auth0-react";
import { Theme } from "@emotion/react";
import { createTheme, CssBaseline, ThemeProvider } from "@mui/material";
import { TypographyOptions } from "@mui/material/styles/createTypography";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

import appConfig from "./utils/appConfig";

import { LIGHT_COLOR, PRIMARY_COLOR } from "./styles/global.style";

import Routes from "./Routes";

const App = () => {
  const queryClient: QueryClient = new QueryClient({
    defaultOptions: {
      queries: { retry: 5, retryDelay: 2000 },
    },
  });

  const typography: TypographyOptions = {
    fontFamily: "Inter",
    fontSize: 14,
    htmlFontSize: 16,
    h1: { fontSize: "2.3em" },
    h2: { fontSize: "2rem" },
    h3: { fontSize: "1.6rem" },
    h4: { fontSize: "1.3rem" },
    h5: { fontSize: "1rem" },
    h6: { fontSize: "1rem" },
  };

  const lightTheme: Theme = createTheme({
    palette: {
      mode: "light",
      primary: { main: PRIMARY_COLOR },
      secondary: { main: "#ee6c4d" },
      background: { default: LIGHT_COLOR },
    },
    typography,
  });

  return (
    <BrowserRouter>
      <Auth0Provider
        domain={appConfig.VITE_APP_AUTH0_DOMAIN}
        clientId={appConfig.VITE_APP_AUTH0_CLIENT_ID}
        authorizationParams={{ redirect_uri: window.location.origin }}>
        <ThemeProvider theme={lightTheme}>
          <CssBaseline />
          <QueryClientProvider client={queryClient}>
            <Routes />
            <ReactQueryDevtools initialIsOpen={false} />
          </QueryClientProvider>
        </ThemeProvider>
      </Auth0Provider>
    </BrowserRouter>
  );
};

export default App;
