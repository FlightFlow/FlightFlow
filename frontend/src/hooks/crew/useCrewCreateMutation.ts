import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";
import CrewTypes from "@/types/controllers/crew";

const useCrewCreateMutation = () => {
  const queryClient = useQueryClient();

  const createCrew = useMutation({
    mutationKey: ["createCrewMutation"],
    mutationFn: async (crewCreateData: CrewTypes.Mutations.CreateMutationParams) => {
      const { accessToken, ...requestData } = crewCreateData;
      const response = await new Requester({
        method: "POST",
        endpoint: { controller: "crew", action: "create" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["crewQuery"] }),
  });
  return createCrew;
};

export default useCrewCreateMutation;
