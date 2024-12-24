import type ComponentTypes from "./components";

import type EntityTypes from "./entity";
import type GlobalTypes from "./globals";

namespace ResourceTypes {
  export namespace Airport {
    type AirportId = { airportId: string };
    namespace Mutations {
      type CreateMutationParams = Omit<EntityTypes.AirportEntity, "id">;
      type UpdateMutationParams = AirportId & Omit<EntityTypes.AirportEntity, "id">;
      type DeleteMutationParams = AirportId;
    }
    namespace Queries {
      type QueryByIdRequestParams = AirportId;
      type QueryResponseParams = EntityTypes.AirportEntity[];
    }
    namespace Page {
      type RowParams = ComponentTypes.BaseDataGridRowParams & Omit<EntityTypes.AirportEntity, "id">;
    }
  }
  namespace Certification {
    type CertificationId = { certificationId: string };
    namespace Mutations {
      type CreateMutationParams = Omit<EntityTypes.CertificationEntity, "id">;
      type UpdateMutationParams = CertificationId & Omit<EntityTypes.CertificationEntity, "id">;
      type DeleteMutationParams = CertificationId;
    }
    namespace Queries {
      type QueryByIdRequestParams = CertificationId;
      type QueryResponseParams = EntityTypes.CertificationEntity[];
    }
    namespace Page {
      type RowParams = ComponentTypes.BaseDataGridRowParams &
        Omit<EntityTypes.CertificationEntity, "id">;
    }
  }
  namespace Crew {
    type CrewMemberId = { crewMemberId: string };
    namespace Mutations {
      type CreateMutationParams = Omit<EntityTypes.CrewEntity, "id">;
      type UpdateMutationParams = CrewMemberId & Omit<EntityTypes.CrewEntity, "id">;
      type DeleteMutationParams = CrewMemberId;
    }
    namespace Queries {
      type QueryByIdRequestParams = CrewMemberId;
      type QueryResponseParams = EntityTypes.CrewEntity[];
    }
    namespace Page {
      type RowParams = ComponentTypes.BaseDataGridRowParams & Omit<EntityTypes.CrewEntity, "id">;
    }
  }
  namespace Plane {
    type PlaneId = { planeId: string };
    namespace Mutations {
      type CreateMutationParams = Omit<EntityTypes.PlaneEntity, "id">;
      type UpdateMutationParams = PlaneId & Omit<EntityTypes.PlaneEntity, "id">;
      type DeleteMutationParams = PlaneId;
    }
    namespace Queries {
      type QueryByIdRequestParams = PlaneId;
      type QueryResponseParams = EntityTypes.PlaneEntity[];
    }
    namespace Page {
      type RowParams = ComponentTypes.BaseDataGridRowParams & Omit<EntityTypes.PlaneEntity, "id">;
    }
  }
  namespace Route {
    type RouteId = { routeId: string };
    namespace Mutations {
      type CreateMutationParams = Omit<EntityTypes.RouteEntity, "id">;
      type UpdateMutationParams = RouteId & Omit<EntityTypes.RouteEntity, "id">;
      type DeleteMutationParams = RouteId;
    }
    namespace Queries {
      type QueryByIdRequestParams = RouteId;
      type QueryResponseParams = EntityTypes.RouteEntity[];
    }
    namespace Page {
      type RowParams = ComponentTypes.BaseDataGridRowParams & Omit<EntityTypes.RouteEntity, "id">;
    }
  }
  namespace Runway {
    type RunwayId = { runwayId: string };
    namespace Mutations {
      type CreateMutationParams = Omit<EntityTypes.RunwayEntity, "id">;
      type UpdateMutationParams = RunwayId & EntityTypes.RunwayEntity;
      type DeleteMutationParams = RunwayId;
    }
    namespace Queries {
      type QueryByIdRequestParams = RunwayId;
      type QueryResponseParams = EntityTypes.RunwayEntity[];
    }
    namespace Page {
      type RowParams = ComponentTypes.BaseDataGridRowParams & Omit<EntityTypes.RunwayEntity, "id">;
    }
  }
  namespace Vehicle {
    type VehicleId = { vehicleId: string };
    namespace Mutations {
      type CreateMutationParams = Omit<EntityTypes.VehicleEntity, "id">;
      type UpdateMutationParams = VehicleId & EntityTypes.VehicleEntity;
      type DeleteMutationParams = VehicleId;
    }
    namespace Queries {
      type QueryByIdRequestParams = VehicleId;
      type QueryResponseParams = EntityTypes.VehicleEntity[];
    }
    namespace Page {
      type RowParams = ComponentTypes.BaseDataGridRowParams & Omit<EntityTypes.VehicleEntity, "id">;
    }
  }
}
export default ResourceTypes;
