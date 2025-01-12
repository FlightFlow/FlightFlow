import { useTranslation } from "react-i18next";

import { GridColDef } from "@mui/x-data-grid";

import useCrewQuery from "@/hooks/resource/crew/useCrewAllQuery";
import useCrewCreateMutation from "@/hooks/resource/crew/useCrewCreateMutation";
import useCrewDeleteMutation from "@/hooks/resource/crew/useCrewDeleteMutation";
import useCrewUpdateMutation from "@/hooks/resource/crew/useCrewUpdateMutation";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import Enums from "@/constants/enums";
import EnumValues from "@/constants/enumValues";

import DataTransfer from "@/types/dto";
import ResourceTypes from "@/types/resource";

const CrewPage = () => {
  const { t } = useTranslation(["data_grid"]);

  const { mutateAsync: crewCreateMutation } = useCrewCreateMutation();
  const { mutateAsync: crewUpdateMutation } = useCrewUpdateMutation();
  const { mutateAsync: crewDeleteMutation } = useCrewDeleteMutation();

  const { data: crewQueryData, isLoading: isCrewQueryLoading } = useCrewQuery();

  if (isCrewQueryLoading) {
    return <GridOverlay type="loading" />;
  }
  if (!crewQueryData?.isSuccess) {
    return <GridOverlay type="error" message={crewQueryData?.message} />;
  }

  const crews = crewQueryData.data;

  if (!crews) {
    return <GridOverlay type="error" message={crewQueryData.message} />;
  }

  const parsedCrewData: ResourceTypes.Crew.Page.RowParams[] = crews.map((crew, index) => ({
    id: index + 1,
    uniqueId: crew.id,
    fullName: crew.fullName,
    email: crew.email,
    phoneNumber: crew.phoneNumber,
    role: crew.role,
    certificationIds: crew.certificationIds.join(", "),
    totalFlightHours: crew.totalFlightHours,
    baseAirportId: crew.baseAirportId,
    availability: crew.availability,
  }));

  const crewColumns: GridColDef[] = [
    {
      field: "id",
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
      width: 160,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "fullName",
      type: "string",
      headerName: t("columns.crew.fullName"),
      width: 160,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "email",
      type: "string",
      headerName: t("columns.crew.email"),
      width: 250,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "phoneNumber",
      type: "number",
      headerName: t("columns.crew.phoneNumber"),
      width: 160,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "role",
      type: "singleSelect",
      headerName: t("columns.crew.role"),
      width: 160,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(EnumValues.CrewRole),
    },
    {
      field: "certificationIds",
      type: "string",
      headerName: t("columns.crew.certificationIds"),
      width: 250,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "totalFlightHours",
      type: "number",
      headerName: t("columns.crew.totalFlightHours"),
      width: 100,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "baseAirportId",
      type: "string",
      headerName: t("columns.crew.baseAirportId"),
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "availability",
      type: "singleSelect",
      headerName: t("columns.crew.availability"),
      width: 120,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(EnumValues.CrewAvailability),
    },
  ];

  const columnVisibilities: Record<GridColDef["field"], boolean> = {
    fullName: true,
    email: true,
    phoneNumber: true,
    role: true,
    certificationIds: true,
    totalFlightHours: false,
    baseAirportId: false,
    availability: true,
  };

  const columnEditibilityStates: Record<GridColDef["field"], boolean> = {
    id: false,
    uniqueId: false,
    fullName: true,
    email: true,
    phoneNumber: true,
    role: true,
    certificationIds: false,
    totalFlightHours: true,
    baseAirportId: true,
    availability: true,
  };

  const crewNewDataObject: Omit<DataTransfer.CrewDTO, "id"> = {
    fullName: "",
    email: "",
    phoneNumber: 0,
    role: Enums.CrewRole.FLIGHT_ATTENDANT,
    certificationIds: [],
    totalFlightHours: 0,
    baseAirportId: "",
    availability: Enums.CrewAvailability.AVAILABLE,
  };

  return (
    <DataGrid<
      ResourceTypes.Crew.Mutations.CreateMutationParams,
      ResourceTypes.Crew.Mutations.UpdateMutationParams,
      ResourceTypes.Crew.Mutations.DeleteMutationParams
    >
      key={JSON.stringify(parsedCrewData)}
      rowsProp={parsedCrewData}
      columnsProp={crewColumns}
      dataCategory="crew"
      newDataObject={crewNewDataObject}
      newDataFunction={crewCreateMutation}
      updateDataFunction={crewUpdateMutation}
      deleteDataFunction={crewDeleteMutation}
      columnVisibilityStates={columnVisibilities}
      columnEditibilityStates={columnEditibilityStates}
    />
  );
};

export default CrewPage;
