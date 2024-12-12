
import CrewTypes from '@/types/controllers/crew';
import Requester from '@/utils/requester';
import { useQuery } from '@tanstack/react-query'


const useCrewQuery = (
  crewQueryData : CrewTypes.Queries.CrewQueryRequestParams,
) => {
  const crew = useQuery ({
    queryKey : [ "crewQuery", crewQueryData],
    queryFn: async() => {
      const { accessToken,...requestData} = crewQueryData;
      const response = await new Requester ({
        method: "POST",
        endpoint: {controller:"crew" , action:"getAll"},
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest<CrewTypes.Queries.CrewQueryResponseParams[]>();
    return response;
    },
  });
  return crew;

}

export default useCrewQuery
