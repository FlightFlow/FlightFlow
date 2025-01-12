import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useAlgorithmRunDeleteMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const deleteAlgorithmRun = useMutation({
    mutationKey: ["deleteAlgorithmRunMutation"],
    mutationFn: async (
      algorithmRunDeleteData: ResourceTypes.AlgorithmRun.Mutations.DeleteMutationParams,
    ) => {
      const response = await new Requester()
        .setConfig({
          method: "DELETE",
          endpoint: { controller: "algorithm/run", action: "delete" },
          payload: algorithmRunDeleteData,
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["algorithmRunQuery"] }),
  });
  return deleteAlgorithmRun;
};

export default useAlgorithmRunDeleteMutation;
