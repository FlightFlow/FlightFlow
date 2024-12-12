import RunwayTypes from "@/types/controllers/runway";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";


const useRunwayDeleteMutation = () => {
  const queryClient = useQueryClient();

  const deleteRunway = useMutation({
    mutationKey: ["deleteRunwayMutation"],
    mutationFn: async (runwayDeleteData: RunwayTypes.Mutations.DeleteMutationParams) => {
      const { accessToken, ...requestData } = runwayDeleteData;
      const response = await new Requester({
        method: "DELETE",
        endpoint: { controller: "runway", action: "delete" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["runwayQuery"] }),
  });
  return deleteRunway;
};

export default useRunwayDeleteMutation;
