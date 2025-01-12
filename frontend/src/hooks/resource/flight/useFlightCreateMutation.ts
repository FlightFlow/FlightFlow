import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useFlightCreateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const createFlight = useMutation({
    mutationKey: ["createFlightMutation"],
    mutationFn: async (flightCreateData: ResourceTypes.Flight.Mutations.CreateMutationParams) => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "flight", action: "create" },
          payload: flightCreateData,
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["flightQuery"] }),
  });
  return createFlight;
};

export default useFlightCreateMutation;
