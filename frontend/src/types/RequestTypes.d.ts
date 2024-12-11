export interface BaseRequestParams {
  token: string;
}

export interface AirportQueryParams extends BaseRequestParams {
  airportId?: string;
}
