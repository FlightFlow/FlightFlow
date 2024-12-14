import PlaneTypes from "@/types/controllers/plane";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";


const usePlaneUpdateMutation = () => {
  const queryClient = useQueryClient();

  const updatePlane = useMutation({
    mutationKey: ["updatePlaneMutation"],
    mutationFn: async (usePlaneUpdateData: PlaneTypes.Mutations.UpdateMutationParams) => {
      const { accessToken, ...requestData } = usePlaneUpdateData;
      const response = await new Requester({
        method: "PATCH",
        endpoint: { controller: "plane", action: "update" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();

      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["planeQuery"] }),
  });
  return updatePlane;
};

export default usePlaneUpdateMutation;
