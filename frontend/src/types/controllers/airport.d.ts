import type ComponentTypes from "../components";

import type EntityTypes from "../entity";
import type GlobalTypes from "../globals";

namespace AirportTypes {
  export namespace Mutations {
    export type CreateMutationParams = Omit<EntityTypes.AirportEntity, "id">;
    export type UpdateMutationParams = { airportId: string } & Omit<
      EntityTypes.AirportEntity,
      "id"
    >;
    export type DeleteMutationParams = { airportId: string };
  }
  export namespace Queries {
    export type AirportByIdQueryRequestParams = { airportId: string };
    export type AirportQueryResponseParams = EntityTypes.AirportEntity[];
  }
  export type AirportPageRowParams = ComponentTypes.BaseDataGridRowParams &
    Omit<EntityTypes.AirportEntity, "id">;
}
export default AirportTypes;
