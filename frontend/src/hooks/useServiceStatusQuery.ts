import { useQuery, UseQueryResult } from "@tanstack/react-query";
import axios, { AxiosResponse } from "axios";

interface UseServiceStatusQueryResults {
  dataServiceStatus: UseQueryResult<ServiceStatusParams, Error>;
  algorithmServiceStatus: UseQueryResult<ServiceStatusParams, Error>;
}

interface ServiceStatusParams {
  status: string;
}

const useServiceStatusQuery = () => {
  const dataServiceStatus = useQuery({
    queryKey: ["dataServiceStatus"],
    queryFn: async () => {
      try {
        const dataServiceResponse: AxiosResponse = await axios.create().request({
          baseURL: "http://localhost:8081/actuator/health",
          url: "http://localhost:8081/actuator/health",
          method: "GET",
        });
        return dataServiceResponse.data as ServiceStatusParams;
      } catch (error) {
        return { status: "DOWN" } as ServiceStatusParams;
      }
    },
  });

  const algorithmServiceStatus = useQuery({
    queryKey: ["algorithmServiceStatus"],
    queryFn: async () => {
      try {
        const algorithmServiceResponse: AxiosResponse = await axios.create().request({
          baseURL: "http://localhost:8082/health",
          url: "http://localhost:8082/health",
          method: "GET",
        });
        return algorithmServiceResponse.data as ServiceStatusParams;
      } catch (error) {
        return { status: "DOWN" } as ServiceStatusParams;
      }
    },
  });

  return {
    dataServiceStatus: dataServiceStatus,
    algorithmServiceStatus: algorithmServiceStatus,
  } as UseServiceStatusQueryResults;
};

export default useServiceStatusQuery;
