import { useTranslation } from "react-i18next";

import { GridColDef } from "@mui/x-data-grid";
import dayjs from "dayjs";

import usePlaneQuery from "@/hooks/resource/plane/usePlaneAllQuery";
import usePlaneCreateMutation from "@/hooks/resource/plane/usePlaneCreateMutation";
import usePlaneDeleteMutation from "@/hooks/resource/plane/usePlaneDeleteMutation";
import usePlaneUpdateMutation from "@/hooks/resource/plane/usePlaneUpdateMutation";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import Enums from "@/constants/enums";
import EnumValues from "@/constants/enumValues";

import DataTransfer from "@/types/dto";
import ResourceTypes from "@/types/resource";

const PlanePage = () => {
  const { t } = useTranslation(["data_grid"]);

  const { mutateAsync: planeCreateMutation } = usePlaneCreateMutation();
  const { mutateAsync: planeUpdateMutation } = usePlaneUpdateMutation();
  const { mutateAsync: planeDeleteMutation } = usePlaneDeleteMutation();

  const { data: planeQueryData, isLoading: isPlaneQueryLoading } = usePlaneQuery();

  if (isPlaneQueryLoading) {
    return <GridOverlay type="loading" />;
  }
  if (!planeQueryData?.isSuccess) {
    return <GridOverlay type="error" message={planeQueryData?.message} />;
  }

  const planes = planeQueryData.data;

  if (!planes) {
    return <GridOverlay type="error" message={planeQueryData.message} />;
  }

  const parsedPlaneData: ResourceTypes.Plane.Page.RowParams[] = planes.map((plane, index) => ({
    id: index + 1,
    uniqueId: plane.id,
    model: plane.model,
    registrationNumber: plane.registrationNumber,
    passengerCapacity: plane.passengerCapacity,
    fuelEfficiency: plane.fuelEfficiency,
    maxFlightRange: plane.maxFlightRange,
    lastMaintenance: dayjs(plane.lastMaintenance).toDate(),
    totalFlightHours: plane.totalFlightHours,
    maxTakeoffWeight: plane.maxTakeoffWeight,
    shortestRunwayLengthRequired: plane.shortestRunwayLengthRequired,
    shortestRunwayWidthRequired: plane.shortestRunwayWidthRequired,
    planeStatus: plane.planeStatus,
    currentLocationId: plane.currentLocationId,
    aircraftOperator: plane.aircraftOperator,
  }));

  const planeColumns: GridColDef[] = [
    {
      field: "id",
      type: "string",
      headerName: t("columns.id"),
      width: 80,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "uniqueId",
      type: "string",
      headerName: t("columns.uniqueId"),
      width: 120,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "model",
      type: "string",
      headerName: t("columns.plane.model"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "registrationNumber",
      type: "string",
      headerName: t("columns.plane.registrationNumber"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "passengerCapacity",
      type: "number",
      headerName: t("columns.plane.passengerCapacity"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "fuelEfficiency",
      type: "number",
      headerName: t("columns.plane.fuelEfficiency"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "maxFlightRange",
      type: "number",
      headerName: t("columns.plane.maxFlightRange"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "lastMaintenance",
      type: "date",
      headerName: t("columns.plane.lastMaintenance"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "totalFlightHours",
      type: "number",
      headerName: t("columns.plane.totalFlightHours"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "maxTakeoffWeight",
      type: "number",
      headerName: t("columns.plane.maxTakeoffWeight"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "shortestRunwayLengthRequired",
      type: "number",
      headerName: t("columns.plane.shortestRunwayLengthRequired"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "shortestRunwayWidthRequired",
      headerName: t("columns.plane.shortestRunwayWidthRequired"),
      type: "number",
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "planeStatus",
      type: "singleSelect",
      headerName: t("columns.plane.planeStatus"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(EnumValues.PlaneAvailability),
    },
    {
      field: "currentLocationId",
      headerName: t("columns.plane.currentLocationId"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "aircraftOperator",
      headerName: t("columns.plane.aircraftOperator"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
  ];

  const columnVisibilities: Record<GridColDef["field"], boolean> = {
    model: true,
    registrationNumber: true,
    passengerCapacity: true,
    fuelEfficiency: false,
    maxFlightRange: false,
    lastMaintenance: true,
    totalFlightHours: true,
    maxTakeoffWeight: false,
    shortestRunwayLengthRequired: false,
    shortestRunwayWidthRequired: false,
    planeStatus: true,
    currentLocationId: true,
    aircraftOperator: true,
  };

  const columnEditibilityStates: Record<GridColDef["field"], boolean> = {
    id: false,
    uniqueId: false,
    model: true,
    registrationNumber: true,
    passengerCapacity: true,
    fuelEfficiency: true,
    maxFlightRange: true,
    lastMaintenance: true,
    totalFlightHours: true,
    maxTakeoffWeight: true,
    shortestRunwayLengthRequired: true,
    shortestRunwayWidthRequired: true,
    planeStatus: true,
    currentLocationId: true,
    aircraftOperator: true,
  };

  const planeNewDataObject: Omit<DataTransfer.PlaneDTO, "id"> = {
    model: "",
    registrationNumber: "",
    passengerCapacity: 0,
    fuelEfficiency: 0,
    maxFlightRange: 0,
    lastMaintenance: new Date(),
    totalFlightHours: 0,
    maxTakeoffWeight: 0,
    shortestRunwayLengthRequired: 0,
    shortestRunwayWidthRequired: 0,
    planeStatus: Enums.PlaneAvailability.AVAILABLE,
    currentLocationId: "",
    aircraftOperator: "",
  };

  return (
    <DataGrid<
      ResourceTypes.Plane.Mutations.CreateMutationParams,
      ResourceTypes.Plane.Mutations.UpdateMutationParams,
      ResourceTypes.Plane.Mutations.DeleteMutationParams
    >
      key={JSON.stringify(parsedPlaneData)}
      rowsProp={parsedPlaneData}
      columnsProp={planeColumns}
      dataCategory="plane"
      newDataObject={planeNewDataObject}
      newDataFunction={planeCreateMutation}
      updateDataFunction={planeUpdateMutation}
      deleteDataFunction={planeDeleteMutation}
      columnVisibilityStates={columnVisibilities}
      columnEditibilityStates={columnEditibilityStates}
    />
  );
};

export default PlanePage;
