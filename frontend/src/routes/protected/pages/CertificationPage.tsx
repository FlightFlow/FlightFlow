import { useTranslation } from "react-i18next";

import { GridColDef } from "@mui/x-data-grid";
import dayjs from "dayjs";

import useCertificationQuery from "@/hooks/resource/certification/useCertificationAllQuery";
import useCertificationCreateMutation from "@/hooks/resource/certification/useCertificationCreateMutation";
import useCertificationDeleteMutation from "@/hooks/resource/certification/useCertificationDeleteMutation";
import useCertificationUpdateMutation from "@/hooks/resource/certification/useCertificationUpdateMutation";

import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import Enums from "@/constants/enums";
import EnumValues from "@/constants/enumValues";

import DataTransfer from "@/types/dto";
import ResourceTypes from "@/types/resource";

const CertificationsPage = () => {
  const { t } = useTranslation(["data_grid"]);

  const { mutateAsync: certificationCreateMutation } = useCertificationCreateMutation();
  const { mutateAsync: certificationUpdateMutation } = useCertificationUpdateMutation();
  const { mutateAsync: certificationDeleteMutation } = useCertificationDeleteMutation();

  const { data: certificationQueryData, isLoading: isCertificationQueryLoading } =
    useCertificationQuery();

  if (isCertificationQueryLoading) {
    return <GridOverlay type="loading" />;
  }
  if (!certificationQueryData?.isSuccess) {
    return <GridOverlay type="error" message={certificationQueryData?.message} />;
  }

  const certifications = certificationQueryData.data;

  if (!certifications) {
    return <GridOverlay type="error" message={certificationQueryData.message} />;
  }

  const parsedCertificationData: ResourceTypes.Certification.Page.RowParams[] = certifications.map(
    (certification, index) => ({
      id: index + 1,
      uniqueId: certification.id,
      name: certification.name,
      certificationNumber: certification.certificationNumber,
      issuer: certification.issuer,
      issuingCountry: certification.issuingCountry,
      expirationDate: dayjs(certification.expirationDate).toDate(),
      validityPeriod: certification.validityPeriod,
      assignableRole: certification.assignableRole,
      description: certification.description,
      assignedCrewMember: certification.assignedCrewMember,
    }),
  );

  const certificationColumns: GridColDef[] = [
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
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
      hideable: false,
    },
    {
      field: "name",
      type: "string",
      headerName: t("columns.certification.name"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "certificationNumber",
      type: "string",
      headerName: t("columns.certification.certificationNumber"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "issuer",
      type: "singleSelect",
      headerName: t("columns.certification.issuer"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(EnumValues.CertificationIssuer),
    },
    {
      field: "issuingCountry",
      type: "singleSelect",
      headerName: t("columns.certification.issuingCountry"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(EnumValues.CertificationIssuingCountry),
    },
    {
      field: "expirationDate",
      type: "date",
      headerName: t("columns.certification.expirationDate"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "validityPeriod",
      type: "number",
      headerName: t("columns.certification.validityPeriod"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "assignableRole",
      type: "singleSelect",
      headerName: t("columns.certification.assignableRole"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(EnumValues.CrewRole),
    },
    {
      field: "description",
      type: "string",
      headerName: t("columns.certification.description"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "assignedCrewMember",
      type: "string",
      headerName: t("columns.certification.assignedCrewMember"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
  ];

  const columnVisibilities: Record<GridColDef["field"], boolean> = {
    name: true,
    certificationNumber: true,
    issuer: true,
    issuingCountry: true,
    expirationDate: true,
    validityPeriod: true,
    assignableRole: true,
    description: true,
  };

  const columnEditibilityStates: Record<GridColDef["field"], boolean> = {
    id: false,
    uniqueId: false,
    name: true,
    certificationNumber: true,
    issuer: true,
    issuingCountry: true,
    expirationDate: true,
    validityPeriod: true,
    assignableRole: true,
    description: true,
  };

  const certificationNewDataObject: Omit<DataTransfer.CertificationDTO, "id"> = {
    name: "",
    certificationNumber: "",
    issuer: Enums.CertificationIssuer.AIRBUS,
    issuingCountry: Enums.CertificationIssuingCountry.GLOBAL,
    expirationDate: new Date(),
    validityPeriod: 1,
    assignableRole: Enums.CrewRole.FLIGHT_ATTENDANT,
    description: "",
    assignedCrewMember: "",
  };

  return (
    <DataGrid<
      ResourceTypes.Certification.Mutations.CreateMutationParams,
      ResourceTypes.Certification.Mutations.UpdateMutationParams,
      ResourceTypes.Certification.Mutations.DeleteMutationParams
    >
      key={JSON.stringify(parsedCertificationData)}
      rowsProp={parsedCertificationData}
      columnsProp={certificationColumns}
      dataCategory="certification"
      newDataObject={certificationNewDataObject}
      newDataFunction={certificationCreateMutation}
      updateDataFunction={certificationUpdateMutation}
      deleteDataFunction={certificationDeleteMutation}
      columnVisibilityStates={columnVisibilities}
      columnEditibilityStates={columnEditibilityStates}
    />
  );
};

export default CertificationsPage;
