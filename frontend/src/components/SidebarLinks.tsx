import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router";

import ConstantTypes from "@/types/constants";
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
  Typography,
} from "@mui/material";

import {
  sidebarMenuItemIconStyles,
  sidebarMenuItemStyles,
  sidebarSectionTitleStyles,
} from "@/styles/resourcePage.style";

const sidebarLinksList: ConstantTypes.SidebarSectionProps[] = [
  {
    sectionKey: "algorithm",
    sectionName: "sidebar.sectionTitles.algorithm",
    elements: [
      {
        elementKey: "algorithmRun",
        elementIcon: <RestartAltRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.algorithmRun",
        elementPath: "/app/algorithm/run",
      },
      {
        elementKey: "algorithmResult",
        elementIcon: <TroubleshootRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.algorithmResult",
        elementPath: "/app/algorithm/result",
      },
    ],
  },
  {
    sectionKey: "resources",
    sectionName: "sidebar.sectionTitles.resources",
    elements: [
      {
        elementKey: "plane",
        elementIcon: <LocalAirportRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.plane",
        elementPath: "/app/plane",
      },
      {
        elementKey: "vehicle",
        elementIcon: <AirportShuttleRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.vehicle",
        elementPath: "/app/vehicle",
      },
      {
        elementKey: "flight",
        elementIcon: <LuggageRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.flight",
        elementPath: "/app/flight",
      },
      {
        elementKey: "route",
        elementIcon: <RouteRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.route",
        elementPath: "/app/route",
      },
      {
        elementKey: "crew",
        elementIcon: <PersonRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.crew",
        elementPath: "/app/crew",
      },
      {
        elementKey: "certification",
        elementIcon: <TaskRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.certification",
        elementPath: "/app/certification",
      },
      {
        elementKey: "airport",
        elementIcon: <FlightTakeoffRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.airport",
        elementPath: "/app/airport",
      },
      {
        elementKey: "runway",
        elementIcon: <EditRoadRounded sx={sidebarMenuItemIconStyles} />,
        elementText: "sidebar.linkTexts.runway",
        elementPath: "/app/runway",
      },
    ],
  },
];

const SidebarLinks = () => {
  const { t } = useTranslation();
  const navigate = useNavigate();
  return (
    <Grid>
      {sidebarLinksList.map((section) => (
        <Grid key={section.sectionKey}>
          <Typography variant="subtitle1" sx={sidebarSectionTitleStyles}>
            {t(section.sectionName)}
          </Typography>
          <MenuList>
            {section.elements.map((sectionElement) => (
              <MenuItem
                key={sectionElement.elementKey}
                sx={sidebarMenuItemStyles}
                onClick={() => navigate(sectionElement.elementPath)}>
                <ListItemIcon>{sectionElement.elementIcon}</ListItemIcon>
                <ListItemText>{t(sectionElement.elementText)}</ListItemText>
              </MenuItem>
            ))}
          </MenuList>
        </Grid>
      ))}
    </Grid>
  );
};

export default SidebarLinks;
