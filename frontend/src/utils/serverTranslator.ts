import i18next from "i18next";

import GlobalTypes from "@/types/globals";

import Logger from "./logger";

class ServerTranslator {
  public static translate<TResponseData = null>(
    serverResponse: GlobalTypes.ServerResponseParams<TResponseData>,
  ): GlobalTypes.ServerResponseParams<TResponseData> {
    return {
      isSuccess: serverResponse.isSuccess,
      message: this.translateResponse(serverResponse.message),
      data: serverResponse.data,
    } as GlobalTypes.ServerResponseParams<TResponseData>;
  }

  private static translateResponse(translationKey: string): string {
    if (translationKey.trim() !== "" && !i18next.exists(translationKey)) {
      Logger.error(`Translation key not found: ${translationKey}`);
      return translationKey;
    }
    return i18next.t(translationKey, { ns: "server_msg", lng: i18next.language });
  }
}

export default ServerTranslator;
