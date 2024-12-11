import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace VehicleTypes {
  export namespace Mutations {
    export type CreateMutationParams = GlobalTypes.BaseRequestParams &
      Omit<EntityTypes.VehicleEntity, "id">;
    export type UpdateMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.VehicleEntity;
    export type DeleteMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type CertificateQueryRequestParams = GlobalTypes.BaseRequestParams;
    export type CertificateQueryResponseParams = EntityTypes.VehicleEntity[];
  }
}
export default VehicleTypes;

