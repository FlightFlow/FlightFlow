import { useMemo, useState } from "react";
import { useTranslation } from "react-i18next";

import { Add, Cancel, Delete, Edit, Save } from "@mui/icons-material";
import { Box, Button } from "@mui/material";
import {
  DataGrid as DataGridComponent,
  GridActionsCellItem,
  GridColDef,
  GridEventListener,
  GridRowEditStopReasons,
  GridRowId,
  GridRowModel,
  GridRowModes,
  GridRowModesModel,
  GridRowsProp,
  GridSlots,
  GridToolbarColumnsButton,
  GridToolbarContainer,
  GridToolbarDensitySelector,
  GridToolbarQuickFilter,
} from "@mui/x-data-grid";
import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";
import _ from "lodash";

import dataGridLocalization from "@/localization/dataGridLocalization";

import ComponentTypes from "@/types/components";

import Snackbar from "./Snackbar";

const DataGrid = <TNew, TUpdate, TDelete>({
  rowsProp,
  columnsProp,
  paginationModel,
  dataCategory,
  newDataObject,
  newDataFunction,
  updateDataFunction,
  deleteDataFunction,
  columnVisibilityStates,
  columnEditibilityStates,
}: ComponentTypes.DataGridProps<TNew, TUpdate, TDelete>) => {
  const { t } = useTranslation(["data_grid"]);

  const [dataState, setDataState] = useState<ComponentTypes.DataStateProps>({
    snackbarState: { isOpen: false, message: "" },
    isLoading: false,
    isSuccess: false,
  });
  const [rows, setRows] = useState<GridRowsProp>(rowsProp);
  const [rowModesModel, setRowModesModel] = useState<GridRowModesModel>({});

  const formattedDataCategory = dataCategory.charAt(0).toUpperCase() + dataCategory.slice(1);

  dayjs.extend(customParseFormat);

  const CustomToolbar = () => {
    const handleAddNewClick = async () => {
      const newId = rows.length + 1;
      setRows((rows) => [...rows, { id: newId, ...newDataObject, isNew: true }]);
      setRowModesModel((currentModel) => ({
        ...currentModel,
        [newId]: { mode: GridRowModes.Edit },
      }));
    };

    return (
      <GridToolbarContainer
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
        }}>
        <Box sx={{ display: "flex", alignItems: "center", columnGap: 1 }}>
          <GridToolbarColumnsButton />
          <GridToolbarDensitySelector />
          <Button color="primary" startIcon={<Add />} onClick={handleAddNewClick}>
            {t(`buttons.addNew${formattedDataCategory}Button`)}
          </Button>
        </Box>
        <GridToolbarQuickFilter variant="outlined" size="small" />
      </GridToolbarContainer>
    );
  };

  const handleRowEditStop: GridEventListener<"rowEditStop"> = (params, event) => {
    if (params.reason === GridRowEditStopReasons.rowFocusOut) {
      event.defaultMuiPrevented = true;
    }
  };

  const processRowUpdate = async (updatedRow: GridRowModel) => {
    const row = _.cloneDeep(updatedRow);
    let isNew = false;

    for (const key in row) {
      if (key === "isNew" && row[key] === true) {
        isNew = true;
      }
      if (key === "id") {
        delete row[key];
      }
      if (key === "uniqueId" && !isNew) {
        row["id"] = row["uniqueId"];
        delete row["uniqueId"];
      } else if (isNew) {
        delete row["uniqueId"];
      }

      if (columnEditibilityStates[key] && typeof row[key] !== "number" && !row[key]) {
        return setDataState(() => ({
          snackbarState: { isOpen: true, message: t(`validationFail`) + `: ${key}` },
          isLoading: false,
          isSuccess: false,
        }));
      }
      if (!columnEditibilityStates[key]) {
        delete row[key];
      }
    }

    setDataState((currentState) => ({ ...currentState, isLoading: true }));
    if (isNew) {
      const response = await newDataFunction({ ...row } as TNew);
      if (!response.isSuccess) {
        return setDataState(() => ({
          snackbarState: { isOpen: true, message: response.message },
          isLoading: false,
          isSuccess: response.isSuccess,
        }));
      }
      setDataState(() => ({
        snackbarState: { isOpen: true, message: response.message },
        isLoading: false,
        isSuccess: true,
      }));
    } else {
      const response = await updateDataFunction({ ...row } as TUpdate);
      if (!response.isSuccess) {
        return setDataState(() => ({
          snackbarState: { isOpen: true, message: response.message },
          isLoading: false,
          isSuccess: response.isSuccess,
        }));
      }
      setDataState(() => ({
        snackbarState: { isOpen: true, message: response.message },
        isLoading: false,
        isSuccess: true,
      }));
    }
    return updatedRow;
  };

  const handleRowModesModelChange = (newRowModesModel: GridRowModesModel) => {
    setRowModesModel(newRowModesModel);
  };

  const handleEditClick = (id: GridRowId) => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.Edit } });
  };

  const handleSaveClick = async (id: GridRowId) => {
    setRowModesModel({ ...rowModesModel, [id]: { mode: GridRowModes.View } });
  };

  const handleDeleteClick = async (id: GridRowId) => {
    const rowToBeDeleted = rows.filter((row) => row.id === id);
    setDataState((currentState) => ({ ...currentState, isLoading: true }));
    const response = await deleteDataFunction({ ["id"]: rowToBeDeleted[0].uniqueId } as TDelete);

    return setDataState(() => ({
      snackbarState: { isOpen: true, message: response.message },
      isLoading: false,
      isSuccess: true,
    }));
  };

  const handleCancelClick = (id: GridRowId) => {
    setRowModesModel({
      ...rowModesModel,
      [id]: { mode: GridRowModes.View, ignoreModifications: true },
    });

    const editedRow = rows.find((row) => row.id === id);
    if (editedRow!.isNew) {
      setRows(rows.filter((row) => row.id !== id));
      setRows((rows) => rows.map((row) => ({ ...row, id: row.id - 1 })));
    }
  };

  const filteredColumns: GridColDef[] = useMemo(() => {
    const visibleFields: string[] = [];
    columnsProp.forEach((column) => visibleFields.push(column.field));
    return columnsProp.filter((column) => visibleFields.includes(column.field));
  }, [columnsProp]);

  return (
    <>
      <Snackbar
        snackbarState={dataState}
        setSnackbarState={setDataState}
        severity={dataState.isSuccess ? "success" : "error"}
      />
      <DataGridComponent
        loading={dataState.isLoading ? true : false}
        sx={{ borderRadius: 0, border: 0 }}
        rows={rows}
        columns={[
          ...filteredColumns,
          {
            field: "actions",
            type: "actions",
            headerName: t("columns.actions"),
            width: 100,
            getActions: ({ id }) => {
              const isInEditMode = rowModesModel[id]?.mode === GridRowModes.Edit;

              if (isInEditMode) {
                return [
                  <GridActionsCellItem
                    icon={<Save />}
                    label={t("actions.saveActionLabel")}
                    onClick={() => handleSaveClick(id)}
                  />,
                  <GridActionsCellItem
                    icon={<Cancel />}
                    label={t("actions.cancelActionLabel")}
                    onClick={() => handleCancelClick(id)}
                  />,
                ];
              }

              return [
                <GridActionsCellItem
                  icon={<Edit />}
                  label={t("actions.editActionLabel")}
                  onClick={() => handleEditClick(id)}
                />,
                <GridActionsCellItem
                  icon={<Delete />}
                  label={t("actions.deleteActionLabel")}
                  onClick={() => handleDeleteClick(id)}
                />,
              ];
            },
          },
        ]}
        editMode="row"
        rowModesModel={rowModesModel}
        onRowModesModelChange={handleRowModesModelChange}
        onRowEditStop={handleRowEditStop}
        processRowUpdate={processRowUpdate}
        onProcessRowUpdateError={() => {}}
        initialState={{
          pagination: { paginationModel },
          columns: { columnVisibilityModel: columnVisibilityStates },
        }}
        slots={{
          toolbar: CustomToolbar as GridSlots["toolbar"],
        }}
        localeText={dataGridLocalization}
      />
    </>
  );
};

export default DataGrid;
