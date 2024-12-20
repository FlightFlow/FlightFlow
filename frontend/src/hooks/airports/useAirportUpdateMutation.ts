import AirportTypes from "@/types/controllers/airport";
import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

const useAirportUpdateMutation = () => {
  const queryClient = useQueryClient();
  const updateAirport = useMutation({
    mutationKey: ["updateAirportMutation"],
    mutationFn: async (useAirportUpdateData: AirportTypes.Mutations.UpdateMutationParams) => {
      const { airportId, ...requestData } = useAirportUpdateData;
      return await new Requester({
        method: "PATCH",
        endpoint: { controller: "airport", action: "update" },
        query: { airportId: airportId, ...requestData },
      }).sendRequest();
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["airportQuery"] }),
  });
  return updateAirport;
};

export default useAirportUpdateMutation;
