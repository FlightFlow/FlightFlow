import { useTranslation } from "react-i18next";

import DataTransfer from "@/types/dto";
import { GridColDef } from "@mui/x-data-grid";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import useVehicleQuery from "@/hooks/vehicle/useVehicleAllQuery";
import useVehicleCreateMutation from "@/hooks/vehicle/useVehicleCreateMutation";
import useVehicleUpdateMutation from "@/hooks/vehicle/useVehicleUpdateMutation";
import useVehicleDeleteMutation from "@/hooks/vehicle/useVehicleDeleteMutation";
import ResourceTypes from "@/types/resource";
import Enums from "@/constants/enums";

const VehiclePage = () => {
  const { t } = useTranslation(["data_grid"]);

  const { mutateAsync: vehicleCreateMutation } = useVehicleCreateMutation();
  const { mutateAsync: vehicleUpdateMutation } = useVehicleUpdateMutation();
  const { mutateAsync: vehicleDeleteMutation } = useVehicleDeleteMutation();

  const { data: vehicleQueryData, isLoading: isVehicleQueryLoading } = useVehicleQuery();

  if (isVehicleQueryLoading) {
    return <GridOverlay type="loading" />;
  }
  if (!vehicleQueryData?.isSuccess) {
    return <GridOverlay type="error" message={vehicleQueryData?.message} />;
  }

  const vehicles = vehicleQueryData.data;

  if (!vehicles) {
    return <GridOverlay type="error" message={vehicleQueryData.message} />;
  }

  const parsedVehicleData: ResourceTypes.Vehicle.Page.RowParams[] = vehicles.map((vehicle, index) => ({
    id: index + 1,
    uniqueId: vehicle.id,
    type: vehicle.type,
    vehicleCode: vehicle.vehicleCode,
    capacity: vehicle.capacity,
    availability: vehicle.availability,
    maintenanceDue: vehicle.maintenanceDue,
  }));

  const vehicleColumns: GridColDef[] = [
    { field: "id", headerName: t("columns.vehicle.id"), width: 80, editable: false },
    { field: "uniqueId", headerName: t("columns.vehicle.uniqueId"), width: 150, editable: false },
    { field: "type", headerName: t("columns.vehicle.type"), flex: 1, editable: true },
    { field: "vehicleCode", headerName: t("columns.vehicle.vehicleCode"), flex: 1, editable: true },
    { field: "capacity", headerName: t("columns.vehicle.capacity"), type: "number", flex: 1, editable: true },
    { field: "availability", headerName: t("columns.vehicle.availability"), flex: 1, editable: true },
    { field: "maintenanceDue", headerName: t("columns.vehicle.maintenanceDue"), type: "date", flex: 1, editable: true },
  ];

  const vehicleNewDataObject: Omit<DataTransfer.VehicleDTO, "id"> = {
    type: "" as Enums.GroundVehicleType,
    vehicleCode: "",
    capacity: 0,
    availability: "" as Enums.GroundVehicleAvailability,
    maintenanceDue: new Date(),
  };

  return (
    <DataGrid<
      ResourceTypes.Vehicle.Mutations.CreateMutationParams,
      ResourceTypes.Vehicle.Mutations.UpdateMutationParams,
      ResourceTypes.Vehicle.Mutations.DeleteMutationParams
    >
      key={JSON.stringify(parsedVehicleData)}
      rowsProp={parsedVehicleData}
      columnsProp={vehicleColumns}
      dataCategory="vehicle"
      newDataObject={vehicleNewDataObject}
      newDataFunction={vehicleCreateMutation}
      updateDataFunction={vehicleUpdateMutation}
      deleteDataFunction={vehicleDeleteMutation}
    />
  );
};

export default VehiclePage;
