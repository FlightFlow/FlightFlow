import { useTranslation } from "react-i18next";

import DataTransfer from "@/types/dto";
import { GridColDef } from "@mui/x-data-grid";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import useFlightQuery from "@/hooks/flight/useFlightAllQuery";
import useFlightCreateMutation from "@/hooks/flight/useFlightCreateMutation";
import useFlightUpdateMutation from "@/hooks/flight/useFlightUpdateMutation";
import useFlightDeleteMutation from "@/hooks/flight/useFlightDeleteMutation";
import ResourceTypes from "@/types/resource";

const FlightPage = () => {
  const { t } = useTranslation(["data_grid"]);

  const { mutateAsync: flightCreateMutation } = useFlightCreateMutation();
  const { mutateAsync: flightUpdateMutation } = useFlightUpdateMutation();
  const { mutateAsync: flightDeleteMutation } = useFlightDeleteMutation();

  const { data: flightQueryData, isLoading: isFlightQueryLoading } = useFlightQuery();

  if (isFlightQueryLoading) {
    return <GridOverlay type="loading" />;
  }
  if (!flightQueryData?.isSuccess) {
    return <GridOverlay type="error" message={flightQueryData?.message} />;
  }

  const flights = flightQueryData.data;

  if (!flights) {
    return <GridOverlay type="error" message={flightQueryData.message} />;
  }

  const parsedFlightData: ResourceTypes.Flight.Page.RowParams[] = flights.map((flight, index) => ({
    id: index + 1,
    uniqueId: flight.id,
    passengerCount: flight.passengerCount,
    flightRouteId: flight.flightRouteId,
  }));

  const flightColumns: GridColDef[] = [
    {
      field: "id",
      headerName: t("columns.flight.id"),
      width: 100,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "uniqueId",
      type: "string",
      headerName: t("columns.flight.uniqueId"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "passengerCount",
      type: "number",
      headerName: t("columns.flight.passengerCount"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "flightRouteId",
      type: "string",
      headerName: t("columns.flight.flightRouteId"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
  ];

  const flightNewDataObject: Omit<DataTransfer.FlightDTO, "id"> = {
    passengerCount: 0,
    flightRouteId: "",
  };

  return (
    <DataGrid<
      ResourceTypes.Flight.Mutations.CreateMutationParams,
      ResourceTypes.Flight.Mutations.UpdateMutationParams,
      ResourceTypes.Flight.Mutations.DeleteMutationParams
    >
      key={JSON.stringify(parsedFlightData)}
      rowsProp={parsedFlightData}
      columnsProp={flightColumns}
      dataCategory="flight"
      newDataObject={flightNewDataObject}
      newDataFunction={flightCreateMutation}
      updateDataFunction={flightUpdateMutation}
      deleteDataFunction={flightDeleteMutation}
    />
  );
};

export default FlightPage;
