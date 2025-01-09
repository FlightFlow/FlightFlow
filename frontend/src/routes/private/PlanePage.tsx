import { useTranslation } from "react-i18next";

import DataTransfer from "@/types/dto";
import { GridColDef } from "@mui/x-data-grid";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import usePlaneQuery from "@/hooks/plane/usePlaneAllQuery";
import usePlaneCreateMutation from "@/hooks/plane/usePlaneCreateMutation";
import usePlaneUpdateMutation from "@/hooks/plane/usePlaneUpdateMutation";
import usePlaneDeleteMutation from "@/hooks/plane/usePlaneDeleteMutation";
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
    lastMaintenance: plane.lastMaintenance,
    totalFlightHours: plane.totalFlightHours,
    maxTakeoffWeight: plane.maxTakeoffWeight,
    shortestRunwayLengthRequired: plane.shortestRunwayLengthRequired,
    shortestRunwayWidthRequired: plane.shortestRunwayWidthRequired,
    planeStatus: plane.planeStatus,
    currentLocationId: plane.currentLocationId,
    aircraftOperator: plane.aircraftOperator,
  }));

  const planeColumns: GridColDef[] = [
    { field: "id", headerName: t("columns.plane.id"), width: 80, editable: false },
    { field: "uniqueId", headerName: t("columns.plane.uniqueId"), width: 150, editable: false },
    { field: "model", headerName: t("columns.plane.model"), flex: 1, editable: true },
    { field: "registrationNumber", headerName: t("columns.plane.registrationNumber"), flex: 1, editable: true },
    { field: "passengerCapacity", headerName: t("columns.plane.passengerCapacity"), type: "number", flex: 1, editable: true },
    { field: "fuelEfficiency", headerName: t("columns.plane.fuelEfficiency"), type: "number", flex: 1, editable: true },
    { field: "maxFlightRange", headerName: t("columns.plane.maxFlightRange"), type: "number", flex: 1, editable: true },
    { field: "lastMaintenance", headerName: t("columns.plane.lastMaintenance"), type: "date", flex: 1, editable: true },
    { field: "totalFlightHours", headerName: t("columns.plane.totalFlightHours"), type: "number", flex: 1, editable: true },
    { field: "maxTakeoffWeight", headerName: t("columns.plane.maxTakeoffWeight"), type: "number", flex: 1, editable: true },
    { field: "shortestRunwayLengthRequired", headerName: t("columns.plane.shortestRunwayLengthRequired"), type: "number", flex: 1, editable: true },
    { field: "shortestRunwayWidthRequired", headerName: t("columns.plane.shortestRunwayWidthRequired"), type: "number", flex: 1, editable: true },
    { field: "planeStatus", headerName: t("columns.plane.planeStatus"), flex: 1, editable: true },
    { field: "currentLocationId", headerName: t("columns.plane.currentLocationId"), flex: 1, editable: true },
    { field: "aircraftOperator", headerName: t("columns.plane.aircraftOperator"), flex: 1, editable: true },
  ];

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
    planeStatus: "AVAILABLE", // Varsayılan değer.
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
    />
  );
};

export default PlanePage;
