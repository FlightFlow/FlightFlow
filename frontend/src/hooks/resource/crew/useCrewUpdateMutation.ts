import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useCrewUpdateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const updateCrew = useMutation({
    mutationKey: ["updateCrewMutation"],
    mutationFn: async (useCrewUpdateData: ResourceTypes.Crew.Mutations.UpdateMutationParams) => {
      const { crewMemberId, ...requestData } = useCrewUpdateData;
      const response = await new Requester()
        .setConfig({
          method: "PATCH",
          endpoint: { controller: "crew", action: "update" },
          payload: { crewMemberId: crewMemberId, ...requestData },
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["crewQuery"] }),
  });
  return updateCrew;
};

export default useCrewUpdateMutation;
