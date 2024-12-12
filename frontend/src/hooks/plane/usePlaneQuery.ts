import PlaneTypes from "@/types/controllers/plane";
import Requester from "@/utils/requester";
import { useQuery } from "@tanstack/react-query";



const usePlaneQuery = (
  planeQueryData : PlaneTypes.Queries.PlaneQueryRequestParams,
) => {
  const plane = useQuery ({
    queryKey : [ "planeQuery", planeQueryData],
    queryFn: async() => {
      const { accessToken,...requestData} = planeQueryData;
      const response = await new Requester ({
        method: "POST",
        endpoint: {controller:"plane" , action:"getAll"},
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest<PlaneTypes.Queries.PlaneQueryResponseParams[]>();
    return response;
    },
  });
  return plane;

}

export default usePlaneQuery
