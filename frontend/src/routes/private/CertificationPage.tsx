import { useTranslation } from "react-i18next";

import Enums from "@/constants/enums";
import DataTransfer from "@/types/dto";


import DataGrid from "@/components/DataGrid";
import GridOverlay from "@/components/GridOverlay";

import useCertificationQuery from "@/hooks/certification/useCertificationAllQuery";
import useCertificationCreateMutation from "@/hooks/certification/useCertificationCreateMutation";
import useCertificationDeleteMutation from "@/hooks/certification/useCertificationDeleteMutation";
import useCertificationUpdateMutation from "@/hooks/certification/useCertificationUpdateMutation";
import ResourceTypes from "@/types/resource";

const CertificationsPage = () => {
  const { t } = useTranslation(["data_grid"]);

  const { mutateAsync: certificationCreateMutation } = useCertificationCreateMutation();
  const { mutateAsync: certificationUpdateMutation } = useCertificationUpdateMutation();
  const { mutateAsync: certificationDeleteMutation } = useCertificationDeleteMutation();

  const { data: certificationQueryData, isLoading: isCertificationQueryLoading } = useCertificationQuery();

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
      issuer: Enums.CertificationIssuer[certification.issuer],
      issuingCountry: Enums.CertificationIssuingCountry[certification.issuingCountry],
      expirationDate: certification.expirationDate.toLocaleDateString(),
      validityPeriod: `${certification.validityPeriod} years`,
      assignableRole: certification.assignableRole.join(", "),
      description: certification.description,
    }),
  );

  const certificationColumns: xDataGrid.GridColDef[] = [
    {
      field: "id",
      headerName: t("columns.certification.id"),
      width: 100,
      editable: false,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "uniqueId",
      type: "string",
      headerName: t("columns.certification.uniqueId"),
      flex: 1,
      editable: false,
      headerAlign: "left",
      align: "left",
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
      field: "issuer",
      type: "string",
      headerName: t("columns.certification.issuer"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(Enums.CertificationIssuer),
    },
    {
      field: "issuingCountry",
      type: "string",
      headerName: t("columns.certification.issuingCountry"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
      valueOptions: Object.values(Enums.CertificationIssuingCountry),
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
      type: "string",
      headerName: t("columns.certification.validityPeriod"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
    },
    {
      field: "assignableRole",
      type: "string",
      headerName: t("columns.certification.assignableRole"),
      flex: 1,
      editable: true,
      headerAlign: "left",
      align: "left",
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
  ];

  const certificationNewDataObject: Omit<DataTransfer.CertificationDTO, "id"> = {
    name: "",
    issuer: Enums.CertificationIssuer.DEFAULT,
    issuingCountry: Enums.CertificationIssuingCountry.DEFAULT,
    expirationDate: new Date(),
    validityPeriod: 1,
    assignableRole: [],
    description: "",
  };

  return (
    <DataGrid<
      Resource.Types.Certification.Mutations.CreateMutationParams,
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
    />
  );
};

export default CertificationsPage;
