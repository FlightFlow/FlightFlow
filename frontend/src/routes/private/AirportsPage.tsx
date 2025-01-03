import { useTranslation } from "react-i18next";

import Enums from "@/constants/enums";
import EnumValues from "@/constants/enumValues";
import DataTransfer from "@/types/dto";
import ResourceTypes from "@/types/resource";
import { GridColDef } from "@mui/x-data-grid";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import useAirportQuery from "@/hooks/airport/useAirportAllQuery";
import useAirportCreateMutation from "@/hooks/airport/useAirportCreateMutation";
import useAirportDeleteMutation from "@/hooks/airport/useAirportDeleteMutation";
import useAirportUpdateMutation from "@/hooks/airport/useAirportUpdateMutation";

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
      type: Enums.AirportType[airport.type as keyof typeof Enums.AirportType],
      runwayIds: airport.runwayIds.join(", "),
      vehiclesPresentIds: airport.vehiclesPresentIds.join(", "),
      planesPresentIds: airport.planesPresentIds.join(", "),
      routesOriginatingFromAirportIds: airport.routesOriginatingFromAirportIds.join(", ") as string,
      routesDestinedForAirportIds: airport.routesDestinedForAirportIds.join(", "),
      crewMembersPresentIds: airport.crewMembersPresentIds.join(", "),
    }),
  );

  const airportColumns: GridColDef[] = [
    {
      field: "id",
      headerName: t("columns.airport.id"),
      width: 100,
      editable: false,
      headerAlign: "left",
      align: "left",
      hideable: false,
    },
    {
      field: "uniqueId",
      type: "string",
      headerName: t("columns.airport.uniqueId"),
      flex: 1,
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
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "iataCode",
      type: "string",
      headerName: t("columns.airport.iataCode"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "icaoCode",
      type: "string",
      headerName: t("columns.airport.icaoCode"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "countryCode",
      type: "string",
      headerName: t("columns.airport.countryCode"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "type",
      type: "singleSelect",
      headerName: t("columns.airport.type"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: [
        EnumValues.AirportType.INTERNATIONAL,
        EnumValues.AirportType.DOMESTIC,
        EnumValues.AirportType.REGIONAL,
      ],
    },
    {
      field: "runwayIds",
      type: "string",
      headerName: t("columns.airport.runways"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "vehiclesPresentIds",
      type: "string",
      headerName: t("columns.airport.vehiclesPresent"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "planesPresentIds",
      type: "string",
      headerName: t("columns.airport.planesPresent"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "routesOriginatingFromAirportIds",
      type: "string",
      headerName: t("columns.airport.routesOriginatingFromAirport"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "routesDestinedForAirportIds",
      type: "string",
      headerName: t("columns.airport.routesDestinedForAirport"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "crewMembersPresentIds",
      type: "string",
      headerName: t("columns.airport.crewMembersPresent"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
  ];

  const columnVisibilities: Record<GridColDef["field"], boolean> = {
    id: true,
    uniqueId: true,
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
    />
  );
};

export default AirportsPage;
