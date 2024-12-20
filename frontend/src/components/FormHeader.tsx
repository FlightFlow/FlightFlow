import ComponentTypes from "@/types/components";
import { Box, Typography } from "@mui/material";

import { formHeaderStyles } from "@/styles/authPage.style";

const FormHeader = ({ title, subtitle }: ComponentTypes.FormHeaderProps) => {
  return (
    <Box sx={formHeaderStyles}>
      <Typography variant="h2" fontWeight={600} textAlign={"center"}>
        {title}
      </Typography>
      <Typography variant="body1" textAlign={"center"}>
        {subtitle}
      </Typography>
    </Box>
  );
};

export default FormHeader;
