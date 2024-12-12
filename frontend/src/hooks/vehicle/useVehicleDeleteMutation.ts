import VehicleTypes from "@/types/controllers/vehicle";
import Requester from "@/utils/requester";
import { useMutation, useQueryClient } from "@tanstack/react-query";


const useVehicleDeleteMutation = () => {
  const queryClient = useQueryClient();

  const deleteVehicle = useMutation({
    mutationKey: ["deleteVehicleMutation"],
    mutationFn: async (vehicleDeleteData: VehicleTypes.Mutations.DeleteMutationParams) => {
      const { accessToken, ...requestData } = vehicleDeleteData;
      const response = await new Requester({
        method: "DELETE",
        endpoint: { controller: "vehicle", action: "delete" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["vehicleQuery"] }),
  });
  return deleteVehicle;
};

export default useVehicleDeleteMutation;
