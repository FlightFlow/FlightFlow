import { useMutation, useQueryClient } from "@tanstack/react-query";

import Requester from "@/utils/requester";

import ResourceTypes from "@/types/resource";

import useAccessToken from "../../useAccessToken";

const useCertificationDeleteMutation = () => {
  const queryClient = useQueryClient();
  const accessToken = useAccessToken();
  const deleteCertification = useMutation({
    mutationKey: ["deleteCertificationMutation"],
    mutationFn: async (
      certificationDeleteData: ResourceTypes.Certification.Mutations.DeleteMutationParams,
    ) => {
      const response = await new Requester()
        .setConfig({
          method: "DELETE",
          endpoint: { controller: "certification", action: "delete" },
          payload: certificationDeleteData,
          accessToken: accessToken,
        })
        .sendRequest();
      return response;
    },
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["certificationQuery"] }),
  });
  return deleteCertification;
};

export default useCertificationDeleteMutation;
