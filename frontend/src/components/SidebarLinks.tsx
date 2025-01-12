import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router";

import {
  AirportShuttleRounded,
  EditRoadRounded,
  FlightTakeoffRounded,
  LocalAirportRounded,
  LuggageRounded,
  PersonRounded,
  RestartAltRounded,
  RouteRounded,
  TaskRounded,
  TroubleshootRounded,
} from "@mui/icons-material";
import {
  Grid2 as Grid,
  ListItemIcon,
  ListItemText,
  MenuItem,
  MenuList,
  SxProps,
  Typography,
} from "@mui/material";

import { DARK_GRAY_COLOR } from "@/shared/global.style";

import ComponentTypes from "@/types/components";
import ConstantTypes from "@/types/constants";

const sidebarAlgorithmsList: ConstantTypes.SidebarSectionProps = {
  sectionKey: "algorithm",
  sectionName: "sidebar.sectionTitles.algorithm",
  elements: [
    {
      elementKey: "algorithmRun",
      elementIcon: <RestartAltRounded />,
      elementText: "sidebar.linkTexts.algorithmRun",
      elementPath: "/app/algorithm/run",
    },
    {
      elementKey: "algorithmResult",
      elementIcon: <TroubleshootRounded />,
      elementText: "sidebar.linkTexts.algorithmResult",
      elementPath: "/app/algorithm/result",
    },
  ],
};

const sidebarResourcesList: ConstantTypes.SidebarSectionProps = {
  sectionKey: "resources",
  sectionName: "sidebar.sectionTitles.resources",
  elements: [
    {
      elementKey: "plane",
      elementIcon: <LocalAirportRounded />,
      elementText: "sidebar.linkTexts.plane",
      elementPath: "/app/plane",
    },
    {
      elementKey: "vehicle",
      elementIcon: <AirportShuttleRounded />,
      elementText: "sidebar.linkTexts.vehicle",
      elementPath: "/app/vehicle",
    },
    {
      elementKey: "flight",
      elementIcon: <LuggageRounded />,
      elementText: "sidebar.linkTexts.flight",
      elementPath: "/app/flight",
    },
    {
      elementKey: "route",
      elementIcon: <RouteRounded />,
      elementText: "sidebar.linkTexts.route",
      elementPath: "/app/route",
    },
    {
      elementKey: "crew",
      elementIcon: <PersonRounded />,
      elementText: "sidebar.linkTexts.crew",
      elementPath: "/app/crew",
    },
    {
      elementKey: "certification",
      elementIcon: <TaskRounded />,
      elementText: "sidebar.linkTexts.certification",
      elementPath: "/app/certification",
    },
    {
      elementKey: "airport",
      elementIcon: <FlightTakeoffRounded />,
      elementText: "sidebar.linkTexts.airport",
      elementPath: "/app/airport",
    },
    {
      elementKey: "runway",
      elementIcon: <EditRoadRounded />,
      elementText: "sidebar.linkTexts.runway",
      elementPath: "/app/runway",
    },
  ],
};

const SECTION_CONTAINER_STYLES: SxProps = {
  flexDirection: "column",
  rowGap: 0,
};

const SECTION_TITLE_STYLES: SxProps = {
  fontWeight: 500,
  paddingLeft: 1.25,
  paddingRight: 1.25,
  boxSizing: "border-box",
  color: DARK_GRAY_COLOR,
};

const MENU_ITEM_STYLES: SxProps = {
  paddingLeft: 1.25,
  paddingRight: 1.25,
  paddingTop: 1,
  paddingBottom: 1,
  borderRadius: 2,
  boxSizing: "border-box",
};

const SidebarLinks = ({ section }: ComponentTypes.SidebarLinksProps) => {
  const { t } = useTranslation();
  const navigate = useNavigate();
  const selectedList: ConstantTypes.SidebarSectionProps =
    section === "algorithm" ? sidebarAlgorithmsList : sidebarResourcesList;

  return (
    <>
      <Grid container key={selectedList.sectionKey} sx={SECTION_CONTAINER_STYLES}>
        <Typography variant="subtitle1" sx={SECTION_TITLE_STYLES}>
          {t(selectedList.sectionName)}
        </Typography>
        <MenuList>
          {selectedList.elements.map((sectionElement) => (
            <MenuItem
              key={sectionElement.elementKey}
              sx={MENU_ITEM_STYLES}
              onClick={() => navigate(sectionElement.elementPath, {})}>
              <ListItemIcon>{sectionElement.elementIcon}</ListItemIcon>
              <ListItemText>{t(sectionElement.elementText)}</ListItemText>
            </MenuItem>
          ))}
        </MenuList>
      </Grid>
    </>
  );
};

export default SidebarLinks;
