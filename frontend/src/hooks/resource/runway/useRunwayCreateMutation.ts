import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useRunwayCreateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const createRunway = useMutation({
    mutationKey: ["createRunwayMutation"],
    mutationFn: async (runwayCreateData: ResourceTypes.Runway.Mutations.CreateMutationParams) => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "runway", action: "create" },
          payload: runwayCreateData,
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["runwayQuery"] }),
  });
  return createRunway;
};

export default useRunwayCreateMutation;
