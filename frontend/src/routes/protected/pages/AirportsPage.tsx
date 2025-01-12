import { useTranslation } from "react-i18next";

import { GridColDef } from "@mui/x-data-grid";

import useAirportQuery from "@/hooks/resource/airport/useAirportAllQuery";
import useAirportCreateMutation from "@/hooks/resource/airport/useAirportCreateMutation";
import useAirportDeleteMutation from "@/hooks/resource/airport/useAirportDeleteMutation";
import useAirportUpdateMutation from "@/hooks/resource/airport/useAirportUpdateMutation";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import Enums from "@/constants/enums";
import EnumValues from "@/constants/enumValues";

import DataTransfer from "@/types/dto";
import ResourceTypes from "@/types/resource";

const AirportsPage = () => {
  const { t } = useTranslation(["data_grid"]);

  const { mutateAsync: airportCreateMutation } = useAirportCreateMutation();
  const { mutateAsync: airportUpdateMutation } = useAirportUpdateMutation();
  const { mutateAsync: airportDeleteMutation } = useAirportDeleteMutation();

  const { data: airportQueryData, isLoading: isAirportQueryLoading } = useAirportQuery();

  if (isAirportQueryLoading) {
    return <GridOverlay type="loading" />;
  }
  if (!airportQueryData?.isSuccess) {
    return <GridOverlay type="error" message={airportQueryData?.message} />;
  }

  const airports = airportQueryData.data;

  if (!airports) {
    return <GridOverlay type="error" message={airportQueryData.message} />;
  }

  const parsedAirportData: ResourceTypes.Airport.Page.RowParams[] = airports.map(
    (airport, index) => ({
      id: index + 1,
      uniqueId: airport.id,
      name: airport.name,
      iataCode: airport.iataCode,
      icaoCode: airport.icaoCode,
      countryCode: airport.countryCode,
      type: airport.type,
      runwayIds: airport.runwayIds,
      vehiclesPresentIds: airport.vehiclesPresentIds,
      planesPresentIds: airport.planesPresentIds,
      routesOriginatingFromAirportIds: airport.routesOriginatingFromAirportIds,
      routesDestinedForAirportIds: airport.routesDestinedForAirportIds,
      crewMembersPresentIds: airport.crewMembersPresentIds,
    }),
  );

  const airportColumns: GridColDef[] = [
    {
      field: "id",
      headerName: t("columns.id"),
      width: 100,
      editable: false,
      headerAlign: "left",
      align: "left",
      hideable: false,
    },
    {
      field: "uniqueId",
      type: "string",
      headerName: t("columns.uniqueId"),
      width: 200,
      editable: false,
      headerAlign: "left",
      align: "left",
      hideable: false,
    },
    {
      field: "name",
      type: "string",
      headerName: t("columns.airport.name"),
      width: 400,
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "iataCode",
      type: "string",
      headerName: t("columns.airport.iataCode"),
      width: 100,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "icaoCode",
      type: "string",
      headerName: t("columns.airport.icaoCode"),
      width: 100,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "countryCode",
      type: "string",
      headerName: t("columns.airport.countryCode"),
      width: 120,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "type",
      type: "singleSelect",
      headerName: t("columns.airport.type"),
      width: 150,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(EnumValues.AirportType),
    },
    {
      field: "runwayIds",
      type: "string",
      headerName: t("columns.airport.runways"),
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "vehiclesPresentIds",
      type: "string",
      headerName: t("columns.airport.vehiclesPresent"),
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "planesPresentIds",
      type: "string",
      headerName: t("columns.airport.planesPresent"),
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "routesOriginatingFromAirportIds",
      type: "string",
      headerName: t("columns.airport.routesOriginatingFromAirport"),
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "routesDestinedForAirportIds",
      type: "string",
      headerName: t("columns.airport.routesDestinedForAirport"),
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "crewMembersPresentIds",
      type: "string",
      headerName: t("columns.airport.crewMembersPresent"),
      editable: false,
      headerAlign: "left",
      align: "left",
    },
  ];

  const columnVisibilities: Record<GridColDef["field"], boolean> = {
    name: true,
    iataCode: true,
    icaoCode: true,
    countryCode: true,
    type: true,
    runwayIds: false,
    vehiclesPresentIds: false,
    planesPresentIds: false,
    routesOriginatingFromAirportIds: false,
    routesDestinedForAirportIds: false,
    crewMembersPresentIds: false,
  };

  const columnEditibilityStates: Record<GridColDef["field"], boolean> = {
    id: false,
    uniqueId: false,
    name: true,
    iataCode: true,
    icaoCode: true,
    countryCode: true,
    type: true,
    runwayIds: false,
    vehiclesPresentIds: false,
    planesPresentIds: false,
    routesOriginatingFromAirportIds: false,
    routesDestinedForAirportIds: false,
    crewMembersPresentIds: false,
  };

  const airportNewDataObject: Omit<DataTransfer.AirportDTO, "id"> = {
    name: "",
    iataCode: "",
    icaoCode: "",
    countryCode: "",
    type: Enums.AirportType.INTERNATIONAL,
    runwayIds: [],
    vehiclesPresentIds: [],
    planesPresentIds: [],
    routesOriginatingFromAirportIds: [],
    routesDestinedForAirportIds: [],
    crewMembersPresentIds: [],
  };

  return (
    <DataGrid<
      ResourceTypes.Airport.Mutations.CreateMutationParams,
      ResourceTypes.Airport.Mutations.UpdateMutationParams,
      ResourceTypes.Airport.Mutations.DeleteMutationParams
    >
      key={JSON.stringify(parsedAirportData)}
      rowsProp={parsedAirportData}
      columnsProp={airportColumns}
      dataCategory="airport"
      newDataObject={airportNewDataObject}
      newDataFunction={airportCreateMutation}
      updateDataFunction={airportUpdateMutation}
      deleteDataFunction={airportDeleteMutation}
      columnVisibilityStates={columnVisibilities}
      columnEditibilityStates={columnEditibilityStates}
    />
  );
};

export default AirportsPage;
