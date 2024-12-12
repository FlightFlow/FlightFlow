import PlaneTypes from "@/types/controllers/plane";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";


const usePlaneDeleteMutation = () => {
  const queryClient = useQueryClient();

  const deletePlane = useMutation({
    mutationKey: ["deletePlaneMutation"],
    mutationFn: async (planeDeleteData: PlaneTypes.Mutations.DeleteMutationParams) => {
      const { accessToken, ...requestData } = planeDeleteData;
      const response = await new Requester({
        method: "DELETE",
        endpoint: { controller: "plane", action: "delete" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["planeQuery"] }),
  });
  return deletePlane;
};

export default usePlaneDeleteMutation;
