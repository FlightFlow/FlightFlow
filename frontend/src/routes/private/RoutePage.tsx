import { useTranslation } from "react-i18next";

import DataTransfer from "@/types/dto";
import { GridColDef } from "@mui/x-data-grid";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import useRouteQuery from "@/hooks/route/useRouteAllQuery";
import useRouteCreateMutation from "@/hooks/route/useRouteCreateMutation";
import useRouteUpdateMutation from "@/hooks/route/useRouteUpdateMutation";
import useRouteDeleteMutation from "@/hooks/route/useRouteDeleteMutation";
import ResourceTypes from "@/types/resource";

const RoutePage = () => {
  const { t } = useTranslation(["data_grid"]);

  const { mutateAsync: routeCreateMutation } = useRouteCreateMutation();
  const { mutateAsync: routeUpdateMutation } = useRouteUpdateMutation();
  const { mutateAsync: routeDeleteMutation } = useRouteDeleteMutation();

  const { data: routeQueryData, isLoading: isRouteQueryLoading } = useRouteQuery();

  if (isRouteQueryLoading) {
    return <GridOverlay type="loading" />;
  }
  if (!routeQueryData?.isSuccess) {
    return <GridOverlay type="error" message={routeQueryData?.message} />;
  }

  const routes = routeQueryData.data;

  if (!routes) {
    return <GridOverlay type="error" message={routeQueryData.message} />;
  }

  const parsedRouteData: ResourceTypes.Route.Page.RowParams[] = routes.map((route, index) => ({
    id: index + 1,
    uniqueId: route.id,
    originAirportId: route.originAirportId,
    destinationAirportId: route.destinationAirportId,
    distance: route.distance,
    estimatedTime: route.estimatedTime,
  }));

  const routeColumns: GridColDef[] = [
    { field: "id", headerName: t("columns.route.id"), width: 80, editable: false },
    { field: "uniqueId", headerName: t("columns.route.uniqueId"), width: 150, editable: false },
    { field: "originAirportId", headerName: t("columns.route.originAirportId"), flex: 1, editable: true },
    { field: "destinationAirportId", headerName: t("columns.route.destinationAirportId"), flex: 1, editable: true },
    { field: "distance", headerName: t("columns.route.distance"), type: "number", flex: 1, editable: true },
    { field: "estimatedTime", headerName: t("columns.route.estimatedTime"), type: "number", flex: 1, editable: true },
  ];

  const routeNewDataObject: Omit<DataTransfer.RouteDTO, "id"> = {
    originAirportId: "",
    destinationAirportId: "",
    distance: 0,
    estimatedTime: 0,
  };

  return (
    <DataGrid<
      ResourceTypes.Route.Mutations.CreateMutationParams,
      ResourceTypes.Route.Mutations.UpdateMutationParams,
      ResourceTypes.Route.Mutations.DeleteMutationParams
    >
      key={JSON.stringify(parsedRouteData)}
      rowsProp={parsedRouteData}
      columnsProp={routeColumns}
      dataCategory="route"
      newDataObject={routeNewDataObject}
      newDataFunction={routeCreateMutation}
      updateDataFunction={routeUpdateMutation}
      deleteDataFunction={routeDeleteMutation}
    />
  );
};

export default RoutePage;
