import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useAirportAllQuery = () => {
  const accessToken = useAccessToken();
  const airports = useQuery({
    queryKey: ["airportQuery"],
    queryFn: async () => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "airport", action: "getAll" },
          accessToken: accessToken,
        })
        .sendRequest<ResourceTypes.Airport.Queries.QueryResponseParams>();
      return response;
    },
  });
  return airports;
};

export default useAirportAllQuery;
