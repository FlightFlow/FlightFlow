import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useAlgorithmResultAllQuery = () => {
  const accessToken = useAccessToken();
  const algorithmResults = useQuery({
    queryKey: ["algorithmResultQuery"],
    queryFn: async () => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "algorithm/result", action: "getAll" },
          accessToken: accessToken,
        })
        .sendRequest<ResourceTypes.AlgortihmResult.Queries.QueryResponseParams>();
      return response;
    },
  });
  return algorithmResults;
};

export default useAlgorithmResultAllQuery;
