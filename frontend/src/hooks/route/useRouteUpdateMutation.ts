import RouteTypes from "@/types/controllers/route";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";

const useRouteUpdateMutation = () => {
  const queryClient = useQueryClient();

  const updateRoute = useMutation({
    mutationKey: ["updateRouteMutation"],
    mutationFn: async (useRouteUpdateData: RouteTypes.Mutations.UpdateMutationParams) => {
      const { accessToken, ...requestData } = useRouteUpdateData;
      const response = await new Requester({
        method: "PATCH",
        endpoint: { controller: "route", action: "update" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();

      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["routeQuery"] }),
  });
  return updateRoute;
};

export default useRouteUpdateMutation;
