import { Routes as BrowserRoutes, Route } from "react-router";

import { AirportPage, NotFoundPage } from "./routes";
import ResourcePageLayout from "./routes/private/layout/ResourcePageLayout";
import IntroPage from "./routes/public/IntroPage";

const Routes = () => {
  return (
    <BrowserRoutes>
      {/* Protected Routes */}
      <Route path="/app" element={<ResourcePageLayout />}>
        <Route path="plane" element={<></>} />
        <Route path="vehicle" element={<></>} />
        <Route path="flight" element={<></>} />
        <Route path="route" element={<></>} />
        <Route path="crew" element={<></>} />
        <Route path="certification" element={<></>} />
        <Route path="airport" element={<AirportPage />} />
        <Route path="runway" element={<></>} />
        <Route path="algorithm">
          <Route path="run" element={<></>} />
          <Route path="result" element={<></>} />
        </Route>
      </Route>

      {/* Public Routes */}
      <Route path="/" element={<IntroPage />} />

      {/* Not Found Page */}
      <Route path="*" element={<NotFoundPage />} />
    </BrowserRoutes>
  );
};

export default Routes;
