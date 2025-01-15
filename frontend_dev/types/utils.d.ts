namespace UtilTypes {
  namespace RequesterBaseTypes {
    type RequestProtocols = "http" | "https";
    type RequestMethods = "POST" | "PATCH" | "DELETE";

    type ResourceControllers =
      | "airport"
      | "algorithm/run"
      | "algorithm/result"
      | "certification"
      | "crew"
      | "flight"
      | "plane"
      | "route"
      | "runway"
      | "vehicle";

    type AlgorithmActions = "trigger" | "details" | "";
    type ResourceActions = "getById" | "getAll" | "create" | "update" | "delete";

    interface EndpointProps {
      controller: ResourceControllers;
      action: ResourceActions | AlgorithmActions;
    }

    interface RequesterConfigParams {
      protocol?: RequestProtocols;
      baseURL?: string;
      endpoint: EndpointProps;
      method: RequestMethods;
      headers?: RawAxiosRequestHeaders;
      includeCookies?: boolean;
      query?: Record<string, string>;
      payload?: object;
      accessToken?: string;
    }
  }
  namespace AppConfigTypes {
    interface EnvProps {
      VITE_APP_ENVIRONMENT: "DEV" | "TEST" | "PROD";
      VITE_APP_SERVER_URL: string;
      VITE_APP_SERVER_PORT: string;
      VITE_APP_SERVER_API_VERSION: string;
      VITE_APP_AUTH0_DOMAIN: string;
      VITE_APP_AUTH0_CLIENT_ID: string;
      VITE_APP_AUTH0_CALLBACK_URL: string;
    }
  }
}
export default UtilTypes;
