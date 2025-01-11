import { Routes as BrowserRoutes, Route } from "react-router";

import * as AppRoutes from "./routes";
import ResourcePageLayout from "./routes/protected/layout/ResourcePageLayout";

const Routes = () => {
  return (
    <BrowserRoutes>
      {/* Protected Routes */}
      <Route path="/app" element={<ResourcePageLayout />}>
        <Route path="/app" element={<AppRoutes.MainPage />} />
        <Route path="plane" element={<AppRoutes.PlanePage />} />
        <Route path="vehicle" element={<AppRoutes.VehiclePage />} />
        <Route path="flight" element={<AppRoutes.FlightPage />} />
        <Route path="route" element={<AppRoutes.RoutePage />} />
        <Route path="crew" element={<AppRoutes.CrewPage />} />
        <Route path="certification" element={<AppRoutes.CertificationPage />} />
        <Route path="airport" element={<AppRoutes.AirportPage />} />
        <Route path="runway" element={<AppRoutes.RunwayPage />} />
        <Route path="algorithm">
          <Route path="run" element={<AppRoutes.AlgorithmRunPage />} />
          <Route path="result" element={<AppRoutes.AlgorithmResultPage />} />
        </Route>
      </Route>

      {/* Public Routes */}
      <Route path="/" element={<AppRoutes.LandingPage />} />
      <Route path="/status" element={<AppRoutes.ServiceStatus />} />
      <Route path="*" element={<AppRoutes.NotFoundPage />} />
    </BrowserRoutes>
  );
};

export default Routes;
