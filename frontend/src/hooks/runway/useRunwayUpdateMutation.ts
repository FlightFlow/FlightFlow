import RunwayTypes from "@/types/controllers/runway";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";


const useRunwayUpdateMutation = () => {
  const queryClient = useQueryClient();

  const updateRunway = useMutation({
    mutationKey: ["updateRunwayMutation"],
    mutationFn: async (useRunwayUpdateData: RunwayTypes.Mutations.UpdateMutationParams) => {
      const { accessToken, ...requestData } = useRunwayUpdateData;
      const response = await new Requester({
        method: "PATCH",
        endpoint: { controller: "runway", action: "update" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();

      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["runwayQuery"] }),
  });
  return updateRunway;
};

export default useRunwayUpdateMutation;
