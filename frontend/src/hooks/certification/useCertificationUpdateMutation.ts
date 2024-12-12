
import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";
import CertificationTypes from "@/types/controllers/certification";


const useCertificationUpdateMutation = () => {
  const queryClient = useQueryClient();

  const updateCertification = useMutation({
    mutationKey: ["updateCertificationMutation"],
    mutationFn: async (useCertificationUpdateData: CertificationTypes.Mutations.UpdateMutationParams) => {
      const { accessToken, ...requestData } = useCertificationUpdateData;
      const response = await new Requester({
        method: "PATCH",
        endpoint: { controller: "certification", action: "update" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();

      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["certificationQuery"] }),
  });
  return updateCertification;
};

export default useCertificationUpdateMutation;
