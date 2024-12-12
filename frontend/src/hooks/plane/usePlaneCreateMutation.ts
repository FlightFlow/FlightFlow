import PlaneTypes from "@/types/controllers/plane";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";


const usePlaneCreateMutation = () => {
  const queryClient = useQueryClient();

  const createPlane = useMutation({
    mutationKey: ["createPlaneMutation"],
    mutationFn: async (planeCreateData: PlaneTypes.Mutations.CreateMutationParams) => {
      const { accessToken, ...requestData } = planeCreateData;
      const response = await new Requester({
        method: "POST",
        endpoint: { controller: "plane", action: "create" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["planeQuery"] }),
  });
  return createPlane;
};

export default usePlaneCreateMutation;
