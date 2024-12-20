import { Outlet } from "react-router";

import AuthCheck from "@/components/AuthCheck";

const ResourcePageLayout = () => {
  return (
    <AuthCheck>
      <Outlet />
    </AuthCheck>
  );
};

export default ResourcePageLayout;
