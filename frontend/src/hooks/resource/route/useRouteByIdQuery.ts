import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useRouteAllQuery = () => {
  const accessToken = useAccessToken();
  const routes = useQuery({
    queryKey: ["routeQuery"],
    queryFn: async () => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "route", action: "getAll" },
          accessToken: accessToken,
        })
        .sendRequest<ResourceTypes.Route.Queries.QueryResponseParams>();
      return response;
    },
  });
  return routes;
};

export default useRouteAllQuery;
