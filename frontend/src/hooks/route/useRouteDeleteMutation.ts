import RouteTypes from "@/types/controllers/route";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";


const useRouteDeleteMutation = () => {
  const queryClient = useQueryClient();

  const deleteRoute = useMutation({
    mutationKey: ["deleteRouteMutation"],
    mutationFn: async (routeDeleteData: RouteTypes.Mutations.DeleteMutationParams) => {
      const { accessToken, ...requestData } = routeDeleteData;
      const response = await new Requester({
        method: "DELETE",
        endpoint: { controller: "route", action: "delete" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["routeQuery"] }),
  });
  return deleteRoute;
};

export default useRouteDeleteMutation;
