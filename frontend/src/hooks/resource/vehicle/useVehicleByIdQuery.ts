import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useVehicleAllQuery = () => {
  const accessToken = useAccessToken();
  const vehicles = useQuery({
    queryKey: ["vehicleQuery"],
    queryFn: async () => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "vehicle", action: "getAll" },
          accessToken: accessToken,
        })
        .sendRequest<ResourceTypes.Vehicle.Queries.QueryResponseParams>();
      return response;
    },
  });
  return vehicles;
};

export default useVehicleAllQuery;
