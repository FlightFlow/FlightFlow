namespace UtilTypes {
  export namespace RequesterBaseTypes {
    type RequestProtocols = "http" | "https";
    type RequestMethods = "POST" | "PATCH" | "DELETE";

    type EndpointRoutes =
      | "airport"
      | "algorithm"
      | "certification"
      | "crew"
      | "plane"
      | "route"
      | "runway"
      | "vehicle";

    type AlgorithmActions = "TBD"; // TODO
    type EndpointActions = "getById" | "getAll" | "create" | "update" | "delete";
    type AuthActions = "login" | "register" | "logout" | "resetpassword";

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
      accessToken?: string;
      includeCookies?: boolean;
      query?: Record<string, string>;
      payload: object;
    }
  }
}
export default UtilTypes;
