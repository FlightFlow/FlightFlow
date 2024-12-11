namespace GlobalTypes {
  export interface ServerResponseParams<TResponseData = null> {
    isSuccess: boolean;
    message: string;
    data?: TResponseData;
  }
}
export default GlobalTypes;
