import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useCertificationAllQuery = () => {
  const accessToken = useAccessToken();
  const certifications = useQuery({
    queryKey: ["certificationQuery"],
    queryFn: async () => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "certification", action: "getAll" },
          accessToken: accessToken,
        })
        .sendRequest<ResourceTypes.Certification.Queries.QueryResponseParams>();
      return response;
    },
  });
  return certifications;
};

export default useCertificationAllQuery;
