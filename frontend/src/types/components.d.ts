import type { Dispatch, MouseEventHandler, ReactNode, SetStateAction } from "react";

import type { AlertColor } from "@mui/material";
import type { GridColDef, GridPaginationModel, GridRowsProp } from "@mui/x-data-grid";
import type { UseMutateAsyncFunction } from "@tanstack/react-query";

import type GlobalTypes from "@/types/globals";

namespace ComponentTypes {
  interface BaseDataGridRowParams {
    id: number;
    uniqueId: string;
  }
  interface DataGridProps<TNew, TUpdate, TDelete> {
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
    columnVisibilityStates: Record<GridColDef["field"], boolean>;
    columnEditibilityStates: Record<GridColDef["field"], boolean>;
  }
  interface GridOverlayProps {
    type: "loading" | "error";
    message?: string;
  }
  interface DataStateProps {
    snackbarState?: { isOpen: boolean; message: string };
    isLoading: boolean;
    isSuccess: boolean;
  }
  interface SnackbarProps {
    snackbarState: DataStateProps;
    setSnackbarState: Dispatch<SetStateAction<DataStateProps>>;
    severity: AlertColor;
  }
  interface AuthCheckProps {
    children: ReactNode;
  }
  interface IconButtonProps {
    icon: ReactNode;
    tooltipText?: string;
    onclick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  }
  interface SidebarLinksProps {
    section: "resource" | "algorithm" | "external";
  }
  interface SettingsModelProps {
    isSettingsToggled: boolean;
    setIsSettingsToggled: (isSettingsToggled: boolean) => void;
  }
}
export default ComponentTypes;
