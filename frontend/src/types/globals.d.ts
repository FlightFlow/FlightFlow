namespace GlobalTypes {
  export interface BaseRequestParams {} // For future uses
  export interface ServerResponseParams<TResponseData = null> {
    isSuccess: boolean;
    message: string;
    data?: TResponseData;
  }
}
export default GlobalTypes;
