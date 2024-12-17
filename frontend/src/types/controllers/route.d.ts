import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace RouteTypes {
  export namespace Mutations {
    export type CreateMutationParams = {} & Omit<EntityTypes.RouteEntity, "id">;
    export type UpdateMutationParams = {} & EntityTypes.RouteEntity;
    export type DeleteMutationParams = {} & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type RouteQueryRequestParams = {};
    export type RouteQueryResponseParams = EntityTypes.RouteEntity[];
  }
}
export default RouteTypes;
