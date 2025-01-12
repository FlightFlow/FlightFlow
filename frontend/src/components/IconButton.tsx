import { IconButton as IconButtonComponent, Tooltip } from "@mui/material";

import ComponentTypes from "@/types/components";

const IconButton = ({ icon, tooltipText, onclick }: ComponentTypes.IconButtonProps) => {
  return (
    <IconButtonComponent onClick={onclick}>
      {tooltipText ? (
        <Tooltip title="Logout">
          <>{icon}</>
        </Tooltip>
      ) : (
        icon
      )}
    </IconButtonComponent>
  );
};

export default IconButton;
