/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable no-unused-vars */
import type { Dispatch, MouseEventHandler, ReactNode, SetStateAction } from "react";

import type { AlertColor } from "@mui/material";
import type { GridColDef, GridPaginationModel, GridRowsProp } from "@mui/x-data-grid";
import type { UseMutateAsyncFunction } from "@tanstack/react-query";

import type GlobalTypes from "@/types/globals";

namespace ComponentTypes {
  /**
   * - Base types that every row object has.
   * - These row objects are passed down to datagrid component as props.
   */
  interface BaseDataGridRowParams {
    /**
     * - This param is the table ID which counts up from 1.
     */
    id: number;

    /**
     * - This param is the ID from the database.
     */
    uniqueId: string;
  }

  /**
   * - Custom data grid component's props.
   * - This data grid is customized version of the Mui's data grid.
   */
  interface DataGridProps<TNew, TUpdate, TDelete> {
    rowsProp: GridRowsProp;
    columnsProp: GridColDef[];
    paginationModel?: GridPaginationModel;
    dataCategory: string;

    /**
     * - Used for creating empty grid rows when creating new data.
     */
    newDataObject: object;

    /**
     * - ReactQuery's async mutation function.
     * - A custom hook that sends a http request to the backend should be passed to this prop.
     */
    newDataFunction: UseMutateAsyncFunction<GlobalTypes.ServerResponseParams, Error, TNew, unknown>;

    /**
     * - ReactQuery's async mutation function.
     * - A custom hook that sends a http request to the backend should be passed to this prop.
     */
    updateDataFunction: UseMutateAsyncFunction<
      GlobalTypes.ServerResponseParams,
      Error,
      TUpdate,
      unknown
    >;

    /**
     * - ReactQuery's async mutation function.
     * - A custom hook that sends a http request to the backend should be passed to this prop.
     */
    deleteDataFunction: UseMutateAsyncFunction<
      GlobalTypes.ServerResponseParams,
      Error,
      TDelete,
      unknown
    >;

    /**
     * - This record defines which columns are visible and which are not.
     */
    columnVisibilityStates: Record<GridColDef["field"], boolean>;

    /**
     * - This record defines which columns are editable and which are not.
     * - `editable: true` param in the row object should also be set.
     */
    columnEditibilityStates: Record<GridColDef["field"], boolean>;
  }
  /**
   * - This props are for the data grid's overlay.
   */
  interface GridOverlayProps {
    /**
     * - This is the overlay type.
     * - A loading overlay is displayed when fetching data from the backend,
     * or an error overlay is displayed when there is an error with data grid or the backend.
     */
    type: "loading" | "error";

    /**
     * - The message below the circular loading icon or the error message.
     */
    message?: string;
  }

  /**
   * - This props are used in the custom data grid component.
   */
  interface DataStateProps {
    /**
     * - Snackbar component's state inside the custom data grid component.
     */
    snackbarState?: { isOpen: boolean; message: string };

    /**
     * - This value is set to `true` when custom data grid component is waiting for a data from the backend.
     */
    isLoading: boolean;

    /**
     * - This value is set to `true` when custom data grid component is successfully received a data from the backend.
     */
    isSuccess: boolean;
  }

  /**
   * This
   */
  interface SnackbarProps {
    snackbarState: DataStateProps;
    setSnackbarState: Dispatch<SetStateAction<DataStateProps>>;
    severity: AlertColor;
  }

  /**
   * - This component is wrapper for the entire app to check if user is authorized.
   */
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
