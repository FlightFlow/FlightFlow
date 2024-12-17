import ComponentTypes from "../components";

import EntityTypes from "../entity";
import GlobalTypes from "../globals";

namespace AirportTypes {
  export namespace Mutations {
    export type CreateMutationParams = Omit<EntityTypes.AirportEntity, "id">;
    export type UpdateMutationParams = EntityTypes.AirportEntity;
    export type DeleteMutationParams = EntityTypes.BaseEntity;
  }
  export namespace Queries {
    export type AirportQueryRequestParams = Pick<EntityTypes.BaseEntity, "id">;
    export type AirportQueryResponseParams = EntityTypes.AirportEntity[];
  }
  export type AirportPageRowParams = ComponentTypes.BaseDataGridRowParams &
    Omit<EntityTypes.AirportEntity, "id">;
}
export default AirportTypes;
