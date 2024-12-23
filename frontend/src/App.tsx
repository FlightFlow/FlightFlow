import { BrowserRouter } from "react-router";

import { Auth0Provider } from "@auth0/auth0-react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

import ThemeProvider from "./components/ThemeProvider";

import appConfig from "./utils/appConfig";

import Routes from "./Routes";

const App = () => {
  const queryClient: QueryClient = new QueryClient({
    defaultOptions: {
      queries: { retry: 3, retryDelay: 2000 },
    },
  });

  return (
    <BrowserRouter>
      <Auth0Provider
        domain={appConfig.VITE_APP_AUTH0_DOMAIN}
        clientId={appConfig.VITE_APP_AUTH0_CLIENT_ID}
        authorizationParams={{ redirect_uri: window.location.origin }}>
        <ThemeProvider>
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
