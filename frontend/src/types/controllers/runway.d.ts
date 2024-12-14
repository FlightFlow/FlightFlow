import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace RunwayTypes {
  export namespace Mutations {
    export type CreateMutationParams = GlobalTypes.BaseRequestParams &
      Omit<EntityTypes.RunwayEntity, "id">;
    export type UpdateMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.RunwayEntity;
    export type DeleteMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type RunwayQueryRequestParams = GlobalTypes.BaseRequestParams;
    export type RunwayQueryResponseParams = EntityTypes.RunwayEntity[];
  }
}
export default RunwayTypes;

