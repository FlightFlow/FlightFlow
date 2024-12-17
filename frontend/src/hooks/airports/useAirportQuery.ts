import AirportTypes from "@/types/controllers/airport";
import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

const useAirportQuery = (airportQueryData?: AirportTypes.Queries.AirportQueryRequestParams) => {
  const airports = useQuery({
    queryKey: ["airportQuery", airportQueryData],
    queryFn: async () => {
      const response = await new Requester({
        method: "POST",
        endpoint: { controller: "airport", action: "getAll" },
        query: airportQueryData,
      }).sendRequest<AirportTypes.Queries.AirportQueryResponseParams>();
      return response;
    },
  });
  return airports;
};

export default useAirportQuery;
