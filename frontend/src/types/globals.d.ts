namespace GlobalTypes {
  export interface BaseRequestParams {} // For future uses
  export interface ServerResponseParams<TResponseData = null> {
    isSuccess: boolean;
    message: string;
    data?: TResponseData;
  }
  export interface AuthDetailsParams {
    accessToken?: string;
    refreshToken?: string;
  }
  export interface EnvProps {
    VITE_APP_SERVER_URL: string;
    VITE_APP_SERVER_PORT: string;
    VITE_APP_SERVER_API_VERSION: string;
    VITE_APP_AUTH0_DOMAIN: string;
    VITE_APP_AUTH0_CLIENT_ID: string;
    VITE_APP_AUTH0_CALLBACK_URL: string;
  }
}
export default GlobalTypes;
