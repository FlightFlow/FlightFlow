import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const usePlaneUpdateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const updatePlane = useMutation({
    mutationKey: ["updatePlaneMutation"],
    mutationFn: async (usePlaneUpdateData: ResourceTypes.Plane.Mutations.UpdateMutationParams) => {
      const { planeId, ...requestData } = usePlaneUpdateData;
      const response = await new Requester()
        .setConfig({
          method: "PATCH",
          endpoint: { controller: "plane", action: "update" },
          payload: { planeId: planeId, ...requestData },
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["planeQuery"] }),
  });
  return updatePlane;
};

export default usePlaneUpdateMutation;
