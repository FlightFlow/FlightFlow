import GlobalTypes from "@/types/globals";
import UtilTypes from "@/types/utils";
import axios, {
  AxiosError,
  AxiosInstance,
  AxiosRequestConfig,
  AxiosResponse,
  RawAxiosRequestHeaders,
} from "axios";
import i18next from "i18next";

import Logger from "./logger";

const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;
const SERVER_PORT = import.meta.env.VITE_APP_SERVER_PORT;
const SERVER_API_VERSION = import.meta.env.VITE_APP_SERVER_API_VERSION;
const SERVER_API_PREFIX = `/api/${SERVER_API_VERSION}`;

const POSSIBLE_STATUS_CODES = [200, 201, 400, 401, 404, 409, 500]; // TODO

class Requester {
  private protocol: UtilTypes.RequesterBaseTypes.RequestProtocols = "http";
  private baseURL: string = SERVER_URL.replace(/\/$/, "");
  private port: string = SERVER_PORT;
  private endpoint: UtilTypes.RequesterBaseTypes.EndpointProps = {
    controller: "user",
    action: "auth/validate",
  };
  private method: UtilTypes.RequesterBaseTypes.RequestMethods = "POST";
  private contentType: string = "application/json";
  private headers?: RawAxiosRequestHeaders;
  private includeCookies: boolean = true;
  private query?: Record<string, string>;
  private payload?: object;
  private responseLanguage: string = i18next.language;

  private axiosInstance: AxiosInstance;

  constructor() {
    this.axiosInstance = axios.create();
  }

  public setConfig(requesterConfig: UtilTypes.RequesterBaseTypes.RequesterConfigParams): this {
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
    return this;
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
    const urlString: string = `${this.protocol}://${this.baseURL}:${this.port}${SERVER_API_PREFIX}`;
    const endpointString: string = this.generateEndpoint();
    const queryString: string = `?${this.query ? new URLSearchParams(this.query).toString() : ""}`;
    return `${urlString}${endpointString}${queryString}`;
  }

  private generateBaseURL(): string {
    return `${this.protocol}://${this.baseURL}:${this.port}${SERVER_API_PREFIX}`;
  }

  private async getNewAccessToken(): Promise<boolean> {
    const tokenEndpoint: string = `${this.generateBaseURL()}/user/auth/newAccessToken`;
    const axiosConfig: AxiosRequestConfig = {
      baseURL: tokenEndpoint,
      url: tokenEndpoint,
      method: "POST",
      withCredentials: true,
    };
    try {
      const response: AxiosResponse<GlobalTypes.ServerResponseParams, AxiosResponse> =
        await this.axiosInstance.request(axiosConfig);

      if (response.data.isSuccess) {
        Logger.debug(`Received a new access token.`);

        return true;
      }
      Logger.debug(`Couldn't received a new access token.`);
      return false;
    } catch (error) {
      Logger.debug(`An error ocurred while receiving a new access token: ${error}`);
      return false;
    }
  }

  async sendRequest<TResponseData = null, TRequestPayload = null>(): Promise<
    GlobalTypes.ServerResponseParams<TResponseData>
  > {
    this.axiosInstance.interceptors.response.use(
      (response: AxiosResponse<TResponseData, TRequestPayload>) => {
        return response;
      },
      (error: AxiosError) => {
        if (error.response && POSSIBLE_STATUS_CODES.includes(error.response.status)) {
          Logger.debug(
            `Received an error response from the server but error code is included in possible status codes.`,
          );
          return Promise.resolve(error.response.data);
        }
      },
    );

    const axiosConfig: AxiosRequestConfig = {
      baseURL: this.generateURL(),
      url: this.generateURL(),
      method: this.method,
      headers: this.headers,
      data: this.payload,
      withCredentials: this.includeCookies,
      validateStatus: function (status) {
        return POSSIBLE_STATUS_CODES.includes(status);
      },
    };
    const response: AxiosResponse<TResponseData, TRequestPayload> =
      await this.axiosInstance.request<
        GlobalTypes.ServerResponseParams<TResponseData>,
        AxiosResponse<TResponseData, TRequestPayload>
      >(axiosConfig);

    const responseData = response.data as GlobalTypes.ServerResponseParams<TResponseData>;

    if (!responseData.isSuccess && responseData.message == "Expired token") {
      Logger.debug(`Access token is expired. Trying to get a new access token.`);
      const tryGetNewAccessToken: boolean = await this.getNewAccessToken();

      if (!tryGetNewAccessToken) {
        return {
          isSuccess: false,
          message: "Your session is expired. Please login again.",
          data: undefined,
        } satisfies GlobalTypes.ServerResponseParams;
      }

      Logger.debug(`Re-sending the previous failed request.`);
      this.sendRequest<TResponseData, TRequestPayload>();
    }
    return responseData;
  }
}

export default Requester;
