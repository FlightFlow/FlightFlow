import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace VehicleTypes {
  export namespace Mutations {
    export type CreateMutationParams = {} & Omit<EntityTypes.VehicleEntity, "id">;
    export type UpdateMutationParams = {} & EntityTypes.VehicleEntity;
    export type DeleteMutationParams = {} & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type VehicleQueryRequestParams = {};
    export type VehicleQueryResponseParams = EntityTypes.VehicleEntity[];
  }
}
export default VehicleTypes;
