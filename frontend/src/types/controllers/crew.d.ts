
import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace CrewTypes {
  export namespace Mutations {
    export type CreateMutationParams = GlobalTypes.BaseRequestParams &
      Omit<EntityTypes.CrewEntity, "id">;
    export type UpdateMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.CrewEntity;
    export type DeleteMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type CrewQueryRequestParams = GlobalTypes.BaseRequestParams;
    export type CrewQueryResponseParams = EntityTypes.CrewEntity[];
  }
}
export default CrewTypes;
