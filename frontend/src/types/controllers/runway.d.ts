import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace RunwayTypes {
  export namespace Mutations {
    export type CreateMutationParams = {} & Omit<EntityTypes.RunwayEntity, "id">;
    export type UpdateMutationParams = {} & EntityTypes.RunwayEntity;
    export type DeleteMutationParams = {} & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type RunwayQueryRequestParams = {};
    export type RunwayQueryResponseParams = EntityTypes.RunwayEntity[];
  }
}
export default RunwayTypes;
