import RouteTypes from "@/types/controllers/route";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";

const useRouteCreateMutation = () => {
  const queryClient = useQueryClient();

  const createRoute = useMutation({
    mutationKey: ["createRouteMutation"],
    mutationFn: async (routeCreateData: RouteTypes.Mutations.CreateMutationParams) => {
      const { accessToken, ...requestData } = routeCreateData;
      const response = await new Requester({
        method: "POST",
        endpoint: { controller: "route", action: "create" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["routeQuery"] }),
  });
  return createRoute;
};

export default useRouteCreateMutation;
