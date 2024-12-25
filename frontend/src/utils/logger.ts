class Logger {
  private static DEV_MODE: boolean = true;

  private static GENERAL_PREFIX: string = "FlightCoordinator Client";
  private static SPESIFIC_PREFIX = {
    error: `[${this.GENERAL_PREFIX}] - ERROR:`,
    debug: `[${this.GENERAL_PREFIX}] - DEBUG:`,
    info: `[${this.GENERAL_PREFIX}] - INFO:`,
  };

  public static error(message: string): void {
    if (this.DEV_MODE) {
      console.error(`${this.SPESIFIC_PREFIX.error} ${message}`);
    }
    return;
  }

  public static debug(message: string): void {
    if (this.DEV_MODE) {
      console.debug(`${this.SPESIFIC_PREFIX.debug} ${message}`);
    }
    return;
  }

  public static info(message: string): void {
    if (this.DEV_MODE) {
      console.info(`${this.SPESIFIC_PREFIX.info} ${message}`);
    }
    return;
  }
}

export default Logger;
