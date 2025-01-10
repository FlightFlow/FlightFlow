import { useTranslation } from "react-i18next";

import DataTransfer from "@/types/dto";
import { GridColDef } from "@mui/x-data-grid";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import useRunwayQuery from "@/hooks/runway/useRunwayAllQuery";
import useRunwayCreateMutation from "@/hooks/runway/useRunwayCreateMutation";
import useRunwayUpdateMutation from "@/hooks/runway/useRunwayUpdateMutation";
import useRunwayDeleteMutation from "@/hooks/runway/useRunwayDeleteMutation";
import ResourceTypes from "@/types/resource";
import Enums from "@/constants/enums";

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
    { field: "id", headerName: t("columns.runway.id"), width: 80, editable: false },
    { field: "uniqueId", headerName: t("columns.runway.uniqueId"), width: 150, editable: false },
    { field: "length", headerName: t("columns.runway.length"), type: "number", flex: 1, editable: true },
    { field: "width", headerName: t("columns.runway.width"), type: "number", flex: 1, editable: true },
    { field: "surfaceType", headerName: t("columns.runway.surfaceType"), flex: 1, editable: true },
    { field: "maxWeightCapacity", headerName: t("columns.runway.maxWeightCapacity"), type: "number", flex: 1, editable: true },
    { field: "orientation", headerName: t("columns.runway.orientation"), flex: 1, editable: true },
    { field: "airportId", headerName: t("columns.runway.airportId"), flex: 1, editable: true },
  ];

  const runwayNewDataObject: Omit<DataTransfer.RunwayDTO, "id"> = {
    length: 0,
    width: 0,
    surfaceType: "" as Enums.RunwaySurfaceType,
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
    />
  );
};

export default RunwayPage;
