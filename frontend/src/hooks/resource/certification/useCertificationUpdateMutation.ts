import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useCertificationUpdateMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const updateCertification = useMutation({
    mutationKey: ["updateCertificationMutation"],
    mutationFn: async (
      useCertificationUpdateData: ResourceTypes.Certification.Mutations.UpdateMutationParams,
    ) => {
      const { certificationId, ...requestData } = useCertificationUpdateData;
      const response = await new Requester()
        .setConfig({
          method: "PATCH",
          endpoint: { controller: "certification", action: "update" },
          payload: { certificationId: certificationId, ...requestData },
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["certificationQuery"] }),
  });
  return updateCertification;
};

export default useCertificationUpdateMutation;
