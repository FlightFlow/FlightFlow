import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace PlaneTypes {
  export namespace Mutations {
    export type CreateMutationParams = {} & Omit<EntityTypes.PlaneEntity, "id">;
    export type UpdateMutationParams = {} & EntityTypes.PlaneEntity;
    export type DeleteMutationParams = {} & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type PlaneQueryRequestParams = {};
    export type PlaneQueryResponseParams = EntityTypes.PlaneEntity[];
  }
}
export default PlaneTypes;
