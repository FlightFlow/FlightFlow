import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const usePlaneCreateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const createPlane = useMutation({
    mutationKey: ["createPlaneMutation"],
    mutationFn: async (planeCreateData: ResourceTypes.Plane.Mutations.CreateMutationParams) => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "plane", action: "create" },
          payload: planeCreateData,
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["planeQuery"] }),
  });
  return createPlane;
};

export default usePlaneCreateMutation;
