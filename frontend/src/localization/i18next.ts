import { initReactI18next } from "react-i18next";

import i18next, { InitOptions } from "i18next";

import data_grid_en from "./translations/data_grid_en.json";
import data_grid_tr from "./translations/data_grid_tr.json";
import main_en from "./translations/main_en.json";
import main_tr from "./translations/main_tr.json";
import server_messages_en from "./translations/server_messages_en.json";
import server_messages_tr from "./translations/server_messages_tr.json";

i18next.use(initReactI18next).init({
  resources: {
    en: {
      main: main_en,
      data_grid: data_grid_en,
      server_msg: server_messages_en,
    },
    tr: {
      main: main_tr,
      data_grid: data_grid_tr,
      server_msg: server_messages_tr,
    },
  },
  lng: "en",
  fallbackLng: "en",
  ns: ["main", "data_grid", "server_msg"],
  defaultNS: "main",
  interpolation: {
    escapeValue: false,
  },
} as InitOptions);
