import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useFlightUpdateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const updateFlight = useMutation({
    mutationKey: ["updateFlightMutation"],
    mutationFn: async (
      useFlightUpdateData: ResourceTypes.Flight.Mutations.UpdateMutationParams,
    ) => {
      const { flightId, ...requestData } = useFlightUpdateData;
      const response = await new Requester()
        .setConfig({
          method: "PATCH",
          endpoint: { controller: "flight", action: "update" },
          payload: { flightId: flightId, ...requestData },
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["flightQuery"] }),
  });
  return updateFlight;
};

export default useFlightUpdateMutation;
