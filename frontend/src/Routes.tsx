import { BrowserRouter, Routes as BrowserRoutes, Route } from "react-router";

import { AirportPage, LoginPage, NotFoundPage, RegisterPage } from "./routes";
import AuthPageLayout from "./routes/layouts/AuthPageLayout";
import ResourcePageLayout from "./routes/layouts/ResourcePageLayout";

const Routes = () => {
  return (
    <BrowserRouter>
      <BrowserRoutes>
        {/* Protected Routes */}
        <Route path="/app" element={<ResourcePageLayout />}>
          <Route path="sairport" element={<AirportPage />} />
        </Route>

        {/* Public Routes */}
        <Route path="/" element={<AuthPageLayout />}>
          <Route path="login" element={<LoginPage />} />
          <Route path="register" element={<RegisterPage />} />
        </Route>

        {/* Not Found Page */}
        <Route path="*" element={<NotFoundPage />} />

        {/* Unauthorized Page */}
        <Route path="*" element={<NotFoundPage />} />
      </BrowserRoutes>
    </BrowserRouter>
  );
};

export default Routes;
