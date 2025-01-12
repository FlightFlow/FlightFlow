import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useCertificationCreateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const createCertification = useMutation({
    mutationKey: ["createCertificationMutation"],
    mutationFn: async (
      certificationCreateData: ResourceTypes.Certification.Mutations.CreateMutationParams,
    ) => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "certification", action: "create" },
          payload: certificationCreateData,
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["certificationQuery"] }),
  });
  return createCertification;
};

export default useCertificationCreateMutation;
