import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace CrewTypes {
  export namespace Mutations {
    export type CreateMutationParams = {} & Omit<EntityTypes.CrewEntity, "id">;
    export type UpdateMutationParams = {} & EntityTypes.CrewEntity;
    export type DeleteMutationParams = {} & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type CrewQueryRequestParams = {};
    export type CrewQueryResponseParams = EntityTypes.CrewEntity[];
  }
}
export default CrewTypes;
