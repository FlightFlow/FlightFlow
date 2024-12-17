import { useTranslation } from "react-i18next";

import AirportTypes from "@/types/controllers/airport";
import EntityTypes from "@/types/entity";
import Enums from "@/types/enums";
import { GridColDef } from "@mui/x-data-grid";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import useAirportCreateMutation from "@/hooks/airports/useAirportCreateMutation";
import useAirportDeleteMutation from "@/hooks/airports/useAirportDeleteMutation";
import useAirportQuery from "@/hooks/airports/useAirportQuery";
import useAirportUpdateMutation from "@/hooks/airports/useAirportUpdateMutation";

const AirportsPage = () => {
  const { t } = useTranslation();

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

  const parsedAirportData: AirportTypes.AirportPageRowParams[] = airports.map((airport, index) => ({
    id: index + 1,
    uniqueId: airport.id,
    name: airport.name,
    iataCode: airport.iataCode,
    icaoCode: airport.icaoCode,
    countryCode: airport.countryCode,
    type: airport.type,
    runways: airport.runways,
    vehiclesPresent: airport.vehiclesPresent,
    planesPresent: airport.planesPresent,
    routesOriginatingFromAirport: airport.routesOriginatingFromAirport,
    routesDestinedForAirport: airport.routesDestinedForAirport,
    crewMembersPresent: airport.crewMembersPresent,
  }));

  const airportColumns: GridColDef[] = [
    {
      field: "id",
      headerName: t("airport.dataGrid.columns.id"),
      width: 100,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "uniqueId",
      type: "string",
      headerName: t("airport.dataGrid.columns.uniqueId"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "iataCode",
      type: "string",
      headerName: t("airport.dataGrid.columns.iataCode"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "icaoCode",
      type: "string",
      headerName: t("airport.dataGrid.columns.icaoCode"),
    },
  ];

  const airportNewDataObject: Omit<EntityTypes.AirportEntity, "id"> = {
    name: "",
    iataCode: "",
    icaoCode: "",
    countryCode: "",
    type: Enums.AirportType.INTERNATIONAL,
    runways: [],
    vehiclesPresent: [],
    planesPresent: [],
    routesOriginatingFromAirport: [],
    routesDestinedForAirport: [],
    crewMembersPresent: [],
  };

  return (
    <DataGrid<
      AirportTypes.Mutations.CreateMutationParams,
      AirportTypes.Mutations.UpdateMutationParams,
      AirportTypes.Mutations.DeleteMutationParams
    >
      key={JSON.stringify(parsedAirportData)}
      rowsProp={parsedAirportData}
      columnsProp={airportColumns}
      dataCategory="airport"
      newDataObject={airportNewDataObject}
      newDataFunction={airportCreateMutation}
      updateDataFunction={airportUpdateMutation}
      deleteDataFunction={airportDeleteMutation}
    />
  );
};

export default AirportsPage;
