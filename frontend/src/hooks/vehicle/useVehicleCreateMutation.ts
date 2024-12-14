import VehicleTypes from "@/types/controllers/vehicle";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";

const useVehicleCreateMutation = () => {
  const queryClient = useQueryClient();

  const createVehicle = useMutation({
    mutationKey: ["createVehicleMutation"],
    mutationFn: async (vehicleCreateData: VehicleTypes.Mutations.CreateMutationParams) => {
      const { accessToken, ...requestData } = vehicleCreateData;
      const response = await new Requester({
        method: "POST",
        endpoint: { controller: "vehicle", action: "create" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["vehicleQuery"] }),
  });
  return createVehicle;
};

export default useVehicleCreateMutation;
