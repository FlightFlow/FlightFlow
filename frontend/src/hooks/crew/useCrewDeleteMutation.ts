
import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";
import CrewTypes from "@/types/controllers/crew";

const useCrewDeleteMutation = () => {
  const queryClient = useQueryClient();

  const deleteCrew = useMutation({
    mutationKey: ["deleteCrewMutation"],
    mutationFn: async (crewDeleteData: CrewTypes.Mutations.DeleteMutationParams) => {
      const { accessToken, ...requestData } = crewDeleteData;
      const response = await new Requester({
        method: "DELETE",
        endpoint: { controller: "crew", action: "delete" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["crewQuery"] }),
  });
  return deleteCrew;
};

export default useCrewDeleteMutation;
