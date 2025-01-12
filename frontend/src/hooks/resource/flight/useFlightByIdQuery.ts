import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useFlightAllQuery = () => {
  const accessToken = useAccessToken();
  const flights = useQuery({
    queryKey: ["flightQuery"],
    queryFn: async () => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "flight", action: "getAll" },
          accessToken: accessToken,
        })
        .sendRequest<ResourceTypes.Flight.Queries.QueryResponseParams>();
      return response;
    },
  });
  return flights;
};

export default useFlightAllQuery;
