import { useQuery } from "@tanstack/react-query";
import axios, { AxiosRequestConfig, AxiosResponse } from "axios";

interface ServerHealthResponseParams {
  status: string;
}

const useServerHealthQuery = () => {
  const serverHealth = useQuery({
    queryKey: ["serverHealth"],
    queryFn: async () => {
      const axiosConfig: AxiosRequestConfig = {
        baseURL: "http://localhost:8081/actuator/health",
        url: "http://localhost:8081/actuator/health",
        method: "GET",
      };
      try {
        const response: AxiosResponse = await axios.create().request(axiosConfig);
        return response.data as ServerHealthResponseParams;
      } catch (error) {
        return { status: "DOWN" } as ServerHealthResponseParams;
      }
    },
  });
  return serverHealth;
};

export default useServerHealthQuery;
