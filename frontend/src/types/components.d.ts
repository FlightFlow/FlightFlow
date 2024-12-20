import type { Dispatch, MouseEventHandler, ReactNode, SetStateAction } from "react";

import type GlobalTypes from "@/types/globals";
import type { AlertColor } from "@mui/material";
import type { GridColDef, GridPaginationModel, GridRowsProp } from "@mui/x-data-grid";
import type { UseMutateAsyncFunction } from "@tanstack/react-query";

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
  export interface SubmitButtonProps {
    label: string;
    isLoading: boolean;
    isSuccess: boolean;
  }
  export interface AuthCheckProps {
    children: ReactNode;
  }
  export interface FormHeaderProps {
    title: string;
    subtitle: string;
  }
}
export default ComponentTypes;
