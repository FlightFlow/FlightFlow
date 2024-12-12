import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace RouteTypes {
  export namespace Mutations {
    export type CreateMutationParams = GlobalTypes.BaseRequestParams &
      Omit<EntityTypes.RouteEntity, "id">;
    export type UpdateMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.RouteEntity;
    export type DeleteMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type RouteQueryRequestParams = GlobalTypes.BaseRequestParams;
    export type RouteQueryResponseParams = EntityTypes.RouteEntity[];
  }
}
export default RouteTypes;

