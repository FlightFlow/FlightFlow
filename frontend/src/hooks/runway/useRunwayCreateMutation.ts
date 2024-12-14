import RunwayTypes from "@/types/controllers/runway";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";


const useRunwayCreateMutation = () => {
  const queryClient = useQueryClient();

  const createRunway = useMutation({
    mutationKey: ["createRunwayMutation"],
    mutationFn: async (runwayCreateData: RunwayTypes.Mutations.CreateMutationParams) => {
      const { accessToken, ...requestData } = runwayCreateData;
      const response = await new Requester({
        method: "POST",
        endpoint: { controller: "runway", action: "create" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["runwayQuery"] }),
  });
  return createRunway;
};

export default useRunwayCreateMutation;
