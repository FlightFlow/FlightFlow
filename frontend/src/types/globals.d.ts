namespace GlobalTypes {
  export interface BaseRequestParams {} // For future uses
  export interface ServerResponseParams<TResponseData = null> {
    isSuccess: boolean;
    responseMessage: string;
    responseData?: TResponseData;
  }
}
export default GlobalTypes;
