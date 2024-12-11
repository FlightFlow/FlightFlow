import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace PlaneTypes {
  export namespace Mutations {
    export type CreateMutationParams = GlobalTypes.BaseRequestParams &
      Omit<EntityTypes.PlaneEntity, "id">;
    export type UpdateMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.PlaneEntity;
    export type DeleteMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type CertificateQueryRequestParams = GlobalTypes.BaseRequestParams;
    export type CertificateQueryResponseParams = EntityTypes.PlaneEntity[];
  }
}
export default PlaneTypes;

