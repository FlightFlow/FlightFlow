import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useVehicleCreateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const createVehicle = useMutation({
    mutationKey: ["createVehicleMutation"],
    mutationFn: async (vehicleCreateData: ResourceTypes.Vehicle.Mutations.CreateMutationParams) => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "vehicle", action: "create" },
          payload: vehicleCreateData,
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["vehicleQuery"] }),
  });
  return createVehicle;
};

export default useVehicleCreateMutation;
