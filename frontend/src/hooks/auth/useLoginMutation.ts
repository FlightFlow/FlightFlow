import DataTransfer from "@/types/datatransfer";
import { useMutation } from "@tanstack/react-query";

import Requester from "@/utils/requester";

const useLoginMutation = () => {
  const login = useMutation({
    mutationKey: ["loginMutation"],
    mutationFn: async (loginData: DataTransfer.LoginDetailsDTO) => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "user", action: "auth/login" },
          payload: loginData,
        })
        .sendRequest();
      return response;
    },
  });
  return login;
};

export default useLoginMutation;
