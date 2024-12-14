import AirportTypes from "@/types/controllers/airport";
import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

const useAirportDeleteMutation = () => {
  const queryClient = useQueryClient();

  const deleteAirport = useMutation({
    mutationKey: ["deleteAirportMutation"],
    mutationFn: async (airportDeleteData: AirportTypes.Mutations.DeleteMutationParams) => {
      const { accessToken, ...requestData } = airportDeleteData;
      const response = await new Requester({
        method: "DELETE",
        endpoint: { controller: "airport", action: "delete" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["airportQuery"] }),
  });
  return deleteAirport;
};

export default useAirportDeleteMutation;
