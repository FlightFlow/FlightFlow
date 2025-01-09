import { useTranslation } from "react-i18next";

import Enums from "@/constants/enums";
import DataTransfer from "@/types/dto";
import { GridColDef } from "@mui/x-data-grid";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import useCrewQuery from "@/hooks/crew/useCrewAllQuery";
import useCrewCreateMutation from "@/hooks/crew/useCrewCreateMutation";
import useCrewDeleteMutation from "@/hooks/crew/useCrewDeleteMutation";
import useCrewUpdateMutation from "@/hooks/crew/useCrewUpdateMutation";
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
    role: Enums.CrewRoles[crew.role],
    certificationIds: crew.certificationIds.join(", "),
    totalFlightHours: crew.totalFlightHours,
    baseAirportId: crew.baseAirportId,
    availability: crew.availability,
  }));

  const crewColumns: GridColDef[] = [
    {
      field: "id",
      headerName: t("columns.crew.id"),
      width: 100,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "uniqueId",
      type: "string",
      headerName: t("columns.crew.uniqueId"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "fullName",
      type: "string",
      headerName: t("columns.crew.fullName"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "email",
      type: "string",
      headerName: t("columns.crew.email"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "phoneNumber",
      type: "number",
      headerName: t("columns.crew.phoneNumber"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "role",
      type: "string",
      headerName: t("columns.crew.role"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(Enums.CrewRole),
    },
    {
      field: "certificationIds",
      type: "string",
      headerName: t("columns.crew.certificationIds"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "totalFlightHours",
      type: "number",
      headerName: t("columns.crew.totalFlightHours"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "baseAirportId",
      type: "string",
      headerName: t("columns.crew.baseAirportId"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "availability",
      type: "string",
      headerName: t("columns.crew.availability"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
  ];

  const crewNewDataObject: Omit<DataTransfer.CrewDTO, "id"> = {
    fullName: "",
    email: "",
    phoneNumber: 0,
    role: Enums.CrewRole.DEFAULT,
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
    />
  );
};

export default CrewPage;
