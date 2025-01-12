import { useTranslation } from "react-i18next";

import { GridColDef } from "@mui/x-data-grid";
import dayjs from "dayjs";

import useVehicleQuery from "@/hooks/resource/vehicle/useVehicleAllQuery";
import useVehicleCreateMutation from "@/hooks/resource/vehicle/useVehicleCreateMutation";
import useVehicleDeleteMutation from "@/hooks/resource/vehicle/useVehicleDeleteMutation";
import useVehicleUpdateMutation from "@/hooks/resource/vehicle/useVehicleUpdateMutation";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import Enums from "@/constants/enums";
import EnumValues from "@/constants/enumValues";

import DataTransfer from "@/types/dto";
import ResourceTypes from "@/types/resource";

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

  const parsedVehicleData: ResourceTypes.Vehicle.Page.RowParams[] = vehicles.map(
    (vehicle, index) => ({
      id: index + 1,
      uniqueId: vehicle.id,
      type: vehicle.type,
      vehicleCode: vehicle.vehicleCode,
      capacity: vehicle.capacity,
      availability: vehicle.availability,
      maintenanceDue: dayjs(vehicle.maintenanceDue).toDate(),
      airportId: vehicle.airportId,
    }),
  );

  const vehicleColumns: GridColDef[] = [
    {
      field: "id",
      type: "string",
      headerName: t("columns.id"),
      width: 100,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "uniqueId",
      type: "string",
      headerName: t("columns.uniqueId"),
      width: 350,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "type",
      type: "singleSelect",
      headerName: t("columns.vehicle.type"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(EnumValues.GroundVehicleType),
    },
    {
      field: "vehicleCode",
      type: "string",
      headerName: t("columns.vehicle.vehicleCode"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "capacity",
      type: "number",
      headerName: t("columns.vehicle.capacity"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "availability",
      type: "singleSelect",
      headerName: t("columns.vehicle.availability"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(EnumValues.GroundVehicleAvailability),
    },
    {
      field: "maintenanceDue",
      type: "date",
      headerName: t("columns.vehicle.maintenanceDue"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "airportId",
      type: "string",
      headerName: t("columns.vehicle.airportId"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
  ];

  const columnVisibilities: Record<GridColDef["field"], boolean> = {
    type: true,
    vehicleCode: true,
    capacity: true,
    availability: true,
    maintenanceDue: true,
    airportId: true,
  };

  const columnEditibilityStates: Record<GridColDef["field"], boolean> = {
    type: true,
    vehicleCode: true,
    capacity: true,
    availability: true,
    maintenanceDue: true,
    airportId: true,
  };

  const vehicleNewDataObject: Omit<DataTransfer.VehicleDTO, "id"> = {
    type: Enums.GroundVehicleType.TUG,
    vehicleCode: "",
    capacity: 0,
    availability: Enums.GroundVehicleAvailability.AVAILABLE,
    maintenanceDue: new Date(),
    airportId: "",
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
      columnVisibilityStates={columnVisibilities}
      columnEditibilityStates={columnEditibilityStates}
    />
  );
};

export default VehiclePage;
