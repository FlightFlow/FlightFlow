import GlobalTypes from "@/types/globals";
import UtilTypes from "@/types/utils";
import axios, {
  AxiosError,
  AxiosRequestConfig,
  AxiosResponse,
  RawAxiosRequestHeaders,
} from "axios";
import i18next from "i18next";

const BACKEND_URL = import.meta.env.VITE_APP_BACKEND_URL;
const BACKEND_PORT = import.meta.env.VITE_APP_BACKEND_PORT;
const BACKEND_API_VERSION = import.meta.env.VITE_APP_SERVER_API_VERSION;
const BACKEND_API_PREFIX = `/api/${BACKEND_API_VERSION}`;

const POSSIBLE_STATUS_CODES = [200, 201, 400, 401, 404, 409, 500]; // FIXME

class Requester {
  private protocol: UtilTypes.RequesterBaseTypes.RequestProtocols = "http";
  private baseURL: string = BACKEND_URL.replace(/\/$/, "");
  private port: string = BACKEND_PORT;
  private endpoint: UtilTypes.RequesterBaseTypes.EndpointProps;
  private method: string;
  private contentType: string = "application/json";
  private headers: RawAxiosRequestHeaders;
  private includeCookies: boolean = true;
  private query?: Record<string, string>;
  private payload?: object;
  private responseLanguage: string = i18next.language;

  constructor(requesterConfig: UtilTypes.RequesterBaseTypes.RequesterConfigParams) {
    this.endpoint = requesterConfig.endpoint;
    this.method = requesterConfig.method;
    this.includeCookies = requesterConfig.includeCookies ?? false;
    this.headers = {
      ...requesterConfig.headers,
      "Content-Type": this.contentType,
      "Accept-Language": this.responseLanguage,
    };
    this.payload = requesterConfig.payload;
    this.query = requesterConfig.query;
  }

  private generateEndpoint(): string {
    let endpoint: string = "";
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    Object.entries(this.endpoint).forEach(([_key, value]) => {
      const sanitizedValue = value.replace(/^\/|\/$/g, "");
      endpoint += `/${sanitizedValue}`;
    });
    return endpoint;
  }

  private generateURL(): string {
    const urlString: string = `${this.protocol}://${this.baseURL}:${this.port}${BACKEND_API_PREFIX}`;
    const endpointString: string = this.generateEndpoint();
    const queryString: string = `?${this.query ? new URLSearchParams(this.query).toString() : ""}`;
    return `${urlString}${endpointString}${queryString}`;
  }

  private generateBaseURL(): string {
    return `${this.protocol}://${this.baseURL}:${this.port}${BACKEND_API_PREFIX}`;
  }

  private async getNewAccessToken(): Promise<boolean> {
    const tokenEndpoint: string = `${this.generateBaseURL()}/user/auth/newAccessToken`;
    const axiosInstance = axios.create({ baseURL: tokenEndpoint });
    const axiosConfig: AxiosRequestConfig = {
      url: tokenEndpoint,
      method: "POST",
      withCredentials: true,
    };
    const response: AxiosResponse<GlobalTypes.ServerResponseParams, AxiosResponse> =
      await axiosInstance.request(axiosConfig);

    if (response.data.isSuccess) {
      return true;
    }
    return false;
  }

  async sendRequest<TResponseData = null, TRequestPayload = null>(): Promise<
    GlobalTypes.ServerResponseParams<TResponseData>
  > {
    const axiosInstance = axios.create({ baseURL: this.generateURL() });
    axiosInstance.interceptors.response.use(
      (response: AxiosResponse<TResponseData, TRequestPayload>) => {
        return response;
      },
      (error: AxiosError) => {
        if (error.response && POSSIBLE_STATUS_CODES.includes(error.response.status)) {
          return Promise.resolve(error.response.data);
        }
      },
    );

    const axiosConfig: AxiosRequestConfig = {
      url: this.generateURL(),
      method: this.method,
      headers: this.headers,
      data: this.payload,
      withCredentials: this.includeCookies,
      validateStatus: function (status) {
        return POSSIBLE_STATUS_CODES.includes(status);
      },
    };
    const response: AxiosResponse<TResponseData, TRequestPayload> = await axiosInstance.request<
      GlobalTypes.ServerResponseParams<TResponseData>,
      AxiosResponse<TResponseData, TRequestPayload>
    >(axiosConfig);

    const responseData = response.data as GlobalTypes.ServerResponseParams<TResponseData>;

    if (!responseData.isSuccess && responseData.message == "Expired token") {
      const tryGetNewAccessToken: boolean = await this.getNewAccessToken();

      if (!tryGetNewAccessToken) {
        return {
          isSuccess: false,
          message: "Your session is expired. Please login again.",
          data: undefined,
        } satisfies GlobalTypes.ServerResponseParams;
      }
    }
    return responseData;
  }
}

export default Requester;
