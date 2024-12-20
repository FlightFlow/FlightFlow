import DataTransfer from "@/types/datatransfer";
import { useMutation } from "@tanstack/react-query";

import Requester from "@/utils/requester";

const useRegisterMutation = () => {
  const register = useMutation({
    mutationKey: ["registerMutation"],
    mutationFn: async (registerData: DataTransfer.RegisterDetailsDTO) => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "user", action: "auth/register" },
          payload: registerData,
        })
        .sendRequest();
      return response;
    },
  });
  return register;
};

export default useRegisterMutation;
