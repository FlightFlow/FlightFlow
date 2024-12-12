import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";
import CertificationTypes from "@/types/controllers/crew";

const useCertificationCreateMutation = () => {
  const queryClient = useQueryClient();

  const createCertification = useMutation({
    mutationKey: ["createCertificationMutation"],
    mutationFn: async (certificationCreateData: CertificationTypes.Mutations.CreateMutationParams) => {
      const { accessToken, ...requestData } = certificationCreateData;
      const response = await new Requester({
        method: "POST",
        endpoint: { controller: "certification", action: "create" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["certificationQuery"] }),
  });
  return createCertification;
};

export default useCertificationCreateMutation;
