import VehicleTypes from "@/types/controllers/vehicle";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";


const useVehicleUpdateMutation = () => {
  const queryClient = useQueryClient();

  const updateVehicle = useMutation({
    mutationKey: ["updateVehicleMutation"],
    mutationFn: async (useVehicleUpdateData: VehicleTypes.Mutations.UpdateMutationParams) => {
      const { accessToken, ...requestData } = useVehicleUpdateData;
      const response = await new Requester({
        method: "PATCH",
        endpoint: { controller: "vehicle", action: "update" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();

      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["vehicleQuery"] }),
  });
  return updateVehicle;
};

export default useVehicleUpdateMutation;
