import AirportTypes from "@/types/controllers/airport";
import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

const useAirportAllQuery = () => {
  const airports = useQuery({
    queryKey: ["airportQuery"],
    queryFn: async () => {
      return await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "airport", action: "getAll" },
        })
        .sendRequest<AirportTypes.Queries.AirportQueryResponseParams>();
    },
  });
  return airports;
};

export default useAirportAllQuery;
