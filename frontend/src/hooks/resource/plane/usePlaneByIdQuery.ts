import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const usePlaneAllQuery = () => {
  const accessToken = useAccessToken();
  const planes = useQuery({
    queryKey: ["planeQuery"],
    queryFn: async () => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "plane", action: "getAll" },
          accessToken: accessToken,
        })
        .sendRequest<ResourceTypes.Plane.Queries.QueryResponseParams>();
      return response;
    },
  });
  return planes;
};

export default usePlaneAllQuery;
