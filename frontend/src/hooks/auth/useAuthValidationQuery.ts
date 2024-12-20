import { useQuery } from "@tanstack/react-query";

import Requester from "@/utils/requester";

const useAuthValidationQuery = () => {
  const login = useQuery({
    queryKey: ["authValidationQuery"],
    queryFn: async () => {
      const response = await new Requester()
        .setConfig({
          method: "POST",
          endpoint: { controller: "user", action: "auth/validate" },
          includeCookies: true,
        })
        .sendRequest();
      return response;
    },
    retry: 3,
  });
  return login;
};

export default useAuthValidationQuery;
