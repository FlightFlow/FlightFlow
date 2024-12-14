import VehicleTypes from "@/types/controllers/vehicle";
import Requester from "@/utils/requester";
import { useQuery } from "@tanstack/react-query";



const useVehicleQuery = (
  vehicleQueryData : VehicleTypes.Queries.VehicleQueryRequestParams,
) => {
  const vehicle = useQuery ({
    queryKey : [ "vehicleQuery", vehicleQueryData],
    queryFn: async() => {
      const { accessToken,...requestData} = vehicleQueryData;
      const response = await new Requester ({
        method: "POST",
        endpoint: {controller:"vehicle" , action:"getAll"},
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest<VehicleTypes.Queries.VehicleQueryResponseParams[]>();
    return response;
    },
  });
  return vehicle;

}

export default useVehicleQuery
