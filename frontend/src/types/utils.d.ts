namespace UtilTypes {
  export namespace RequesterBaseTypes {
    type RequestProtocols = "http" | "https";
    type RequestMethods = "POST" | "PATCH" | "DELETE";

    type EndpointRoutes =
      | "airport"
      | "algorithm/run"
      | "algorithm/result"
      | "certification"
      | "crew"
      | "flight"
      | "plane"
      | "route"
      | "runway"
      | "systemRole"
      | "user"
      | "vehicle";

    type AlgorithmActions = ""; // TODO
    type EndpointActions = "getById" | "getAll" | "create" | "update" | "delete";
    type AuthActions =
      | "auth/login"
      | "auth/register"
      | "auth/logout"
      | "auth/newRefreshToken"
      | "auth/validate";

    interface EndpointProps {
      controller: EndpointRoutes;
      action: EndpointActions | AuthActions | AlgorithmActions;
    }

    export interface RequesterConfigParams {
      protocol?: RequestProtocols;
      baseURL?: string;
      endpoint: EndpointProps;
      method: RequestMethods;
      headers?: RawAxiosRequestHeaders;
      includeCookies?: boolean;
      query?: Record<string, string>;
      payload?: object;
      accessToken?: string;
      refreshToken?: string;
    }
  }
}
export default UtilTypes;
