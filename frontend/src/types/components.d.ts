import { Dispatch, MouseEventHandler, ReactNode, SetStateAction } from "react";

import GlobalTypes from "@/types/globals";
import { AlertColor } from "@mui/material";
import { GridColDef, GridPaginationModel, GridRowsProp } from "@mui/x-data-grid";
import { UseMutateAsyncFunction } from "@tanstack/react-query";

namespace ComponentTypes {
  export interface BaseDataGridRowParams {
    id: number;
    uniqueId: string;
  }
  export interface DataGridProps<TNew, TUpdate, TDelete> {
    rowsProp: GridRowsProp;
    columnsProp: GridColDef[];
    paginationModel?: GridPaginationModel;
    dataCategory: string;
    newDataObject: object;
    newDataFunction: UseMutateAsyncFunction<GlobalTypes.ServerResponseParams, Error, TNew, unknown>;
    updateDataFunction: UseMutateAsyncFunction<
      GlobalTypes.ServerResponseParams,
      Error,
      TUpdate,
      unknown
    >;
    deleteDataFunction: UseMutateAsyncFunction<
      GlobalTypes.ServerResponseParams,
      Error,
      TDelete,
      unknown
    >;
  }
  export interface GridOverlayProps {
    type: "loading" | "error";
    message?: string;
  }
  export interface DataStateProps {
    snackbarState?: { isOpen: boolean; message: string };
    isLoading: boolean;
    isSuccess: boolean;
  }
  export interface SnackbarProps {
    snackbarState: DataStateProps;
    setSnackbarState: Dispatch<SetStateAction<DataStateProps>>;
    severity: AlertColor;
  }
}
export default ComponentTypes;
