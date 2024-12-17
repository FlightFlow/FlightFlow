import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace CertificationTypes {
  export namespace Mutations {
    export type CreateMutationParams = {} & Omit<EntityTypes.CertificationEntity, "id">;
    export type UpdateMutationParams = {} & EntityTypes.CertificationEntity;
    export type DeleteMutationParams = {} & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type CertificateQueryRequestParams = {};
    export type CertificateQueryResponseParams = EntityTypes.CertificationEntity[];
  }
}
export default CertificationTypes;
