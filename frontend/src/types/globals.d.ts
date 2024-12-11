namespace GlobalTypes {
  export interface BaseRequestParams {
    userId: string;
    accessToken: string;
  }
  export interface ServerResponseParams<TResponseData = null> {
    isSuccess: boolean;
    message: string;
    data?: TResponseData;
  }
}
export default GlobalTypes;
