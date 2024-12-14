import RouteTypes from "@/types/controllers/route";
import Requester from "@/utils/requester";
import { useQuery } from "@tanstack/react-query";


const useRouteQuery = (
  routeQueryData : RouteTypes.Queries.RouteQueryRequestParams,
) => {
  const route = useQuery ({
    queryKey : [ "routeQuery", routeQueryData],
    queryFn: async() => {
      const { accessToken,...requestData} = routeQueryData;
      const response = await new Requester ({
        method: "POST",
        endpoint: {controller:"route" , action:"getAll"},
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest<RouteTypes.Queries.RouteQueryResponseParams[]>();
    return response;
    },
  });
  return route;

}

export default useRouteQuery
