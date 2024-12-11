import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace AirportTypes {
  export namespace Mutations {
    export type CreateMutationParams = GlobalTypes.BaseRequestParams &
      Omit<EntityTypes.AirportEntity, "id">;
    export type UpdateMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.AirportEntity;
    export type DeleteMutationParams = GlobalTypes.BaseRequestParams & EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type AirportQueryRequestParams = GlobalTypes.BaseRequestParams;
    export type AirportQueryResponseParams = EntityTypes.AirportEntity[];
  }
}
export default AirportTypes;
