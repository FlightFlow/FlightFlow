
import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";
import CrewTypes from "@/types/controllers/crew";

const useCrewUpdateMutation = () => {
  const queryClient = useQueryClient();

  const updateCrew = useMutation({
    mutationKey: ["updateCrewMutation"],
    mutationFn: async (useCrewUpdateData: CrewTypes.Mutations.UpdateMutationParams) => {
      const { accessToken, ...requestData } = useCrewUpdateData;
      const response = await new Requester({
        method: "PATCH",
        endpoint: { controller: "crew", action: "update" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();

      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["crewQuery"] }),
  });
  return updateCrew;
};

export default useCrewUpdateMutation;
