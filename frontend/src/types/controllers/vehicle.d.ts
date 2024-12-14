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
    export type VehicleQueryRequestParams = GlobalTypes.BaseRequestParams;
    export type VehicleQueryResponseParams = EntityTypes.VehicleEntity[];
  }
}
export default VehicleTypes;

