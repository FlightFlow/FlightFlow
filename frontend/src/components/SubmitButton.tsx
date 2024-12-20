import ComponentTypes from "@/types/components";
import { Button, CircularProgress } from "@mui/material";

const SubmitButtonText = ({
  isLoading,
  label,
}: Omit<ComponentTypes.SubmitButtonProps, "isSuccess">) => {
  if (isLoading) {
    return <CircularProgress size={20} />;
  }
  return label;
};

const SubmitButton = ({ isLoading, isSuccess, label }: ComponentTypes.SubmitButtonProps) => {
  return (
    <Button
      variant="contained"
      type="submit"
      color="primary"
      fullWidth
      sx={{ height: "40px" }}
      disabled={isLoading || isSuccess}>
      <SubmitButtonText isLoading={isLoading} label={label} />
    </Button>
  );
};

export default SubmitButton;
