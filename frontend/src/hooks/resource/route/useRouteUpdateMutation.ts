import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useRouteUpdateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const updateRoute = useMutation({
    mutationKey: ["updateRouteMutation"],
    mutationFn: async (useRouteUpdateData: ResourceTypes.Route.Mutations.UpdateMutationParams) => {
      const { routeId, ...requestData } = useRouteUpdateData;
      const response = await new Requester()
        .setConfig({
          method: "PATCH",
          endpoint: { controller: "route", action: "update" },
          payload: { routeId: routeId, ...requestData },
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["routeQuery"] }),
  });
  return updateRoute;
};

export default useRouteUpdateMutation;
