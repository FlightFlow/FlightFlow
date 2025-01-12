import { useQuery } from "@tanstack/react-query";
import axios, { AxiosResponse } from "axios";

interface ServerHealthResponseParams {
  dataServiceStatus: string;
  algorithmServiceStatus: string;
}

const useServerHealthQuery = () => {
  const serverHealth = useQuery({
    queryKey: ["serverHealth"],
    queryFn: async () => {
      try {
        const dataServiceResponse: AxiosResponse = await axios.create().request({
          baseURL: "http://localhost:8081/actuator/health",
          url: "http://localhost:8081/actuator/health",
          method: "GET",
        });
        const algorithmServiceResponse: AxiosResponse = await axios.create().request({
          baseURL: "http://localhost:8082/health",
          url: "http://localhost:8082/health",
          method: "GET",
        });
        return {
          dataServiceStatus: dataServiceResponse.data,
          algorithmServiceStatus: algorithmServiceResponse.data,
        } as ServerHealthResponseParams;
      } catch (error) {
        return {
          dataServiceStatus: "DOWN",
          algorithmServiceStatus: "DOWN",
        } as ServerHealthResponseParams;
      }
    },
  });
  return serverHealth;
};

export default useServerHealthQuery;
