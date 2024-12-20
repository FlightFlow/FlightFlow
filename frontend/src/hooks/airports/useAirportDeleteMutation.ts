import AirportTypes from "@/types/controllers/airport";
import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

const useAirportDeleteMutation = () => {
  const queryClient = useQueryClient();
  const deleteAirport = useMutation({
    mutationKey: ["deleteAirportMutation"],
    mutationFn: async (airportDeleteData: AirportTypes.Mutations.DeleteMutationParams) => {
      return await new Requester()
        .setConfig({
          method: "DELETE",
          endpoint: { controller: "airport", action: "delete" },
          payload: airportDeleteData,
        })
        .sendRequest();
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["airportQuery"] }),
  });
  return deleteAirport;
};

export default useAirportDeleteMutation;
