import axios, {
  AxiosError,
  AxiosInstance,
  AxiosRequestConfig,
  AxiosResponse,
  RawAxiosRequestHeaders,
} from "axios";
import i18next from "i18next";

import GlobalTypes from "@/types/globals";
import UtilTypes from "@/types/utils";

import appConfig from "./appConfig";
import Logger from "./logger";
import ServerTranslator from "./serverTranslator";

const SERVER_URL = appConfig.VITE_APP_SERVER_URL;
const SERVER_PORT = appConfig.VITE_APP_SERVER_PORT;
const SERVER_API_VERSION = appConfig.VITE_APP_SERVER_API_VERSION;
const SERVER_API_PREFIX = `/api/${SERVER_API_VERSION}`;

const POSSIBLE_STATUS_CODES = [200, 201, 400, 401, 403, 404, 409, 500];

class Requester {
  private protocol: UtilTypes.RequesterBaseTypes.RequestProtocols = "http";
  private baseURL: string = SERVER_URL.replace(/\/$/, "");
  private port: string = SERVER_PORT;
  private endpoint: UtilTypes.RequesterBaseTypes.EndpointProps = {
    controller: "plane",
    action: "getAll",
  };
  private method: UtilTypes.RequesterBaseTypes.RequestMethods = "POST";
  private contentType: string = "application/json";
  private headers?: RawAxiosRequestHeaders;
  private includeCookies: boolean = true;
  private query?: Record<string, string>;
  private payload?: object;
  private responseLanguage: string = i18next.language;

  private accessToken?: string;

  private axiosInstance: AxiosInstance;

  constructor() {
    this.axiosInstance = axios.create();
  }

  public setConfig(requesterConfig: UtilTypes.RequesterBaseTypes.RequesterConfigParams): this {
    this.endpoint = requesterConfig.endpoint;
    this.method = requesterConfig.method;
    this.includeCookies = requesterConfig.includeCookies ?? false;
    this.accessToken = requesterConfig.accessToken;
    this.headers = {
      ...requesterConfig.headers,
      "Content-Type": this.contentType,
      "Accept-Language": this.responseLanguage,
      ...(this.accessToken && { Authorization: `Bearer ${this.accessToken}` }),
    };
    this.payload = requesterConfig.payload;
    this.query = requesterConfig.query;
    return this;
  }

  private generateEndpoint(): string {
    let endpoint: string = "";
    Object.entries(this.endpoint).forEach(([_key, value]) => {
      const sanitizedValue = value.replace(/^\/|\/$/g, "");
      endpoint += `/${sanitizedValue}`;
    });
    return endpoint;
  }

  private generateURL(): string {
    const urlString: string = `${this.protocol}://${this.baseURL}:${this.port}${SERVER_API_PREFIX}`;
    const endpointString: string = this.generateEndpoint();
    const queryString: string = `${this.query ? `?${new URLSearchParams(this.query).toString()}` : ""}`;
    return `${urlString}${endpointString}${queryString}`;
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
    try {
      const response: AxiosResponse<TResponseData, TRequestPayload> =
        await this.axiosInstance.request<
          GlobalTypes.ServerResponseParams<TResponseData>,
          AxiosResponse<TResponseData, TRequestPayload>
        >(axiosConfig);

      return ServerTranslator.translate(
        response.data as GlobalTypes.ServerResponseParams<TResponseData>,
      );
    } catch (error) {
      Logger.error(`An error occurred on the request process: ${error}`);
      return {
        isSuccess: false,
        message: i18next.t("requesterError.unknownError"),
        data: undefined,
      } satisfies GlobalTypes.ServerResponseParams;
    }
  }
}

export default Requester;
