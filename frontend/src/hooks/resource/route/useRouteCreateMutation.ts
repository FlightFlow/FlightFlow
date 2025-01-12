import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useRouteCreateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const createRoute = useMutation({
    mutationKey: ["createRouteMutation"],
    mutationFn: async (routeCreateData: ResourceTypes.Route.Mutations.CreateMutationParams) => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "route", action: "create" },
          payload: routeCreateData,
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["routeQuery"] }),
  });
  return createRoute;
};

export default useRouteCreateMutation;
