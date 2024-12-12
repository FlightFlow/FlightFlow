import RunwayTypes from "@/types/controllers/runway";
import Requester from "@/utils/requester";
import { useQuery } from "@tanstack/react-query";



const useRunwayQuery = (
  runwayQueryData : RunwayTypes.Queries.RunwayQueryRequestParams,
) => {
  const runway = useQuery ({
    queryKey : [ "runwayQuery", runwayQueryData],
    queryFn: async() => {
      const { accessToken,...requestData} = runwayQueryData;
      const response = await new Requester ({
        method: "POST",
        endpoint: {controller:"runway" , action:"getAll"},
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest<RunwayTypes.Queries.RunwayQueryResponseParams[]>();
    return response;
    },
  });
  return runway;

}

export default useRunwayQuery
