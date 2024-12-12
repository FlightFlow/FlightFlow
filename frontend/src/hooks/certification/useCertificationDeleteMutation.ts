
import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";
import CertificationTypes from "@/types/controllers/certification";


const useCertificationDeleteMutation = () => {
  const queryClient = useQueryClient();

  const deleteCertification = useMutation({
    mutationKey: ["deleteCertificationMutation"],
    mutationFn: async (certificationDeleteData: CertificationTypes.Mutations.DeleteMutationParams) => {
      const { accessToken, ...requestData } = certificationDeleteData;
      const response = await new Requester({
        method: "DELETE",
        endpoint: { controller: "certification", action: "delete" },
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["certificationQuery"] }),
  });
  return deleteCertification;
};

export default useCertificationDeleteMutation;
