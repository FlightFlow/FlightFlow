import { useEffect, useState } from "react";

import { useAuth0 } from "@auth0/auth0-react";

import appConfig from "@/utils/appConfig";
import Logger from "@/utils/logger";

const useAccessToken = () => {
  const { getAccessTokenSilently } = useAuth0();
  const [accessToken, setAccessToken] = useState<string | undefined>(undefined);
  useEffect(() => {
    (async () => {
      try {
        const receivedAccessToken = await getAccessTokenSilently({
          authorizationParams: {
            audience: appConfig.VITE_APP_AUTH0_DOMAIN,
          },
        });
        setAccessToken(receivedAccessToken);
      } catch (error) {
        Logger.error(`An error occurred while getting access token: ${error}`);
      }
    })();
  });
  return accessToken;
};

export default useAccessToken;
