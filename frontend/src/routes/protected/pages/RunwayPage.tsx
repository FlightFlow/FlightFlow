import { useTranslation } from "react-i18next";

import { GridColDef } from "@mui/x-data-grid";

import useRunwayQuery from "@/hooks/resource/runway/useRunwayAllQuery";
import useRunwayCreateMutation from "@/hooks/resource/runway/useRunwayCreateMutation";
import useRunwayDeleteMutation from "@/hooks/resource/runway/useRunwayDeleteMutation";
import useRunwayUpdateMutation from "@/hooks/resource/runway/useRunwayUpdateMutation";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import Enums from "@/constants/enums";
import EnumValues from "@/constants/enumValues";

import DataTransfer from "@/types/dto";
import ResourceTypes from "@/types/resource";

const RunwayPage = () => {
  const { t } = useTranslation(["data_grid"]);

  const { mutateAsync: runwayCreateMutation } = useRunwayCreateMutation();
  const { mutateAsync: runwayUpdateMutation } = useRunwayUpdateMutation();
  const { mutateAsync: runwayDeleteMutation } = useRunwayDeleteMutation();

  const { data: runwayQueryData, isLoading: isRunwayQueryLoading } = useRunwayQuery();

  if (isRunwayQueryLoading) {
    return <GridOverlay type="loading" />;
  }
  if (!runwayQueryData?.isSuccess) {
    return <GridOverlay type="error" message={runwayQueryData?.message} />;
  }

  const runways = runwayQueryData.data;

  if (!runways) {
    return <GridOverlay type="error" message={runwayQueryData.message} />;
  }

  const parsedRunwayData: ResourceTypes.Runway.Page.RowParams[] = runways.map((runway, index) => ({
    id: index + 1,
    uniqueId: runway.id,
    length: runway.length,
    width: runway.width,
    surfaceType: runway.surfaceType,
    maxWeightCapacity: runway.maxWeightCapacity,
    orientation: runway.orientation,
    airportId: runway.airportId,
  }));

  const runwayColumns: GridColDef[] = [
    {
      field: "id",
      type: "string",
      headerName: t("columns.id"),
      width: 80,
      editable: false,
    },
    {
      field: "uniqueId",
      type: "string",
      headerName: t("columns.uniqueId"),
      width: 150,
      editable: false,
    },
    {
      field: "length",
      type: "number",
      headerName: t("columns.runway.length"),
      flex: 1,
      editable: true,
    },
    {
      field: "width",
      type: "number",
      headerName: t("columns.runway.width"),
      flex: 1,
      editable: true,
    },
    {
      field: "surfaceType",
      type: "singleSelect",
      headerName: t("columns.runway.surfaceType"),
      flex: 1,
      editable: true,
      valueOptions: Object.values(EnumValues.RunwaySurfaceType),
    },
    {
      field: "maxWeightCapacity",
      type: "number",
      headerName: t("columns.runway.maxWeightCapacity"),
      flex: 1,
      editable: true,
    },
    {
      field: "orientation",
      type: "string",
      headerName: t("columns.runway.orientation"),
      flex: 1,
      editable: true,
    },
    {
      field: "airportId",
      type: "string",
      headerName: t("columns.runway.airportId"),
      flex: 1,
      editable: true,
    },
  ];

  const columnVisibilities: Record<GridColDef["field"], boolean> = {
    length: true,
    width: true,
    surfaceType: true,
    maxWeightCapacity: true,
    orientation: true,
    airportId: true,
  };

  const columnEditibilityStates: Record<GridColDef["field"], boolean> = {
    length: true,
    width: true,
    surfaceType: true,
    maxWeightCapacity: true,
    orientation: true,
    airportId: true,
  };

  const runwayNewDataObject: Omit<DataTransfer.RunwayDTO, "id"> = {
    length: 0,
    width: 0,
    surfaceType: Enums.RunwaySurfaceType.ASPHALT,
    maxWeightCapacity: 0,
    orientation: "",
    airportId: "",
  };

  return (
    <DataGrid<
      ResourceTypes.Runway.Mutations.CreateMutationParams,
      ResourceTypes.Runway.Mutations.UpdateMutationParams,
      ResourceTypes.Runway.Mutations.DeleteMutationParams
    >
      key={JSON.stringify(parsedRunwayData)}
      rowsProp={parsedRunwayData}
      columnsProp={runwayColumns}
      dataCategory="runway"
      newDataObject={runwayNewDataObject}
      newDataFunction={runwayCreateMutation}
      updateDataFunction={runwayUpdateMutation}
      deleteDataFunction={runwayDeleteMutation}
      columnVisibilityStates={columnVisibilities}
      columnEditibilityStates={columnEditibilityStates}
    />
  );
};

export default RunwayPage;
