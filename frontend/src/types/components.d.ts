import type { Dispatch, MouseEventHandler, ReactNode, SetStateAction } from "react";

import type GlobalTypes from "@/types/globals";
import type { AlertColor } from "@mui/material";
import type { GridColDef, GridPaginationModel, GridRowsProp } from "@mui/x-data-grid";
import type { UseMutateAsyncFunction } from "@tanstack/react-query";

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
  interface SubmitButtonProps {
    label: string;
    isLoading: boolean;
    isSuccess: boolean;
  }
  interface AuthCheckProps {
    children: ReactNode;
  }
  interface FormHeaderProps {
    title: string;
    subtitle: string;
  }
  interface TranslatePopoverProps {
    anchor: HTMLButtonElement | null;
    setAnchor: (anchor: HTMLButtonElement | null) => void;
  }
  interface IconButtonProps {
    icon: ReactNode;
    tooltipText?: string;
    onclick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  }
  interface ThemeContextProps {
    isLightMode: boolean;
    setIsLightMode: (isLightMode: boolean) => void;
  }
  interface ThemeProviderProps {
    children: ReactNode;
  }
  interface SidebarLinksProps {
    section: "resource" | "algorithm" | "external";
  }
}
export default ComponentTypes;
