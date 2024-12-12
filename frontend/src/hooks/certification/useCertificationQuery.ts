
import CertificationTypes from '@/types/controllers/certification';
import Requester from '@/utils/requester';
import { useQuery } from '@tanstack/react-query'


const useCertificationQuery = (
  certificationQueryData : CertificationTypes.Queries.CertificateQueryRequestParams,
) => {
  const certification = useQuery ({
    queryKey : [ "certificationQuery", certificationQueryData],
    queryFn: async() => {
      const { accessToken,...requestData} = certificationQueryData;
      const response = await new Requester ({
        method: "POST",
        endpoint: {controller:"certification" , action:"getAll"},
        accessToken: accessToken,
        payload: requestData,
      }).sendRequest<CertificationTypes.Queries.CertificateQueryResponseParams[]>();
    return response;
    },
  });
  return certification;

}

export default useCertificationQuery

