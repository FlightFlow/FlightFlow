import AirportTypes from "@/types/controllers/airport";
import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

const useAirportQuery = (airportQueryData: AirportTypes.Queries.AirportQueryRequestParams) => {
  const airport = useQuery({
    queryKey: ["airportQuery", airportQueryData],
    queryFn: async () => {
      const { accessToken, ...requestData } = airportQueryData;
      const response = await new Requester({
        method: "POST",
        endpoint: { controller  : "airport", action: "getAll" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest<AirportTypes.Queries.AirportQueryResponseParams[]>();
      return response;
    },
  });
  return airport;
};

export default useAirportQuery;
