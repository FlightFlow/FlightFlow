import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace CertificationTypes {
  export namespace Mutations {
    export type CreateMutationParams = GlobalTypes.BaseRequestParams &
      Omit<EntityTypes.CertificationEntity, "id">;
    export type UpdateMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.CertificationEntity;
    export type DeleteMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type CertificateQueryRequestParams = GlobalTypes.BaseRequestParams;
    export type CertificateQueryResponseParams = EntityTypes.CertificationEntity[];
  }
}
export default CertificationTypes;

