namespace Enums {
  export enum AirportType {
    INTERNATIONAL = "INTERNATIONAL",
    REGIONAL = "REGIONAL",
    DOMESTIC = "DOMESTIC",
  }
  export enum CertificationIssuer {
    FAA = "FAA",
    EASA = "EASA",
    CAA = "CAA",
    TRANSPORT_CANADA = "TRANSPORT_CANADA",
    DGCA = "DGCA",
    CAAC = "CAAC",
    CASA = "CASA",
    ICAO = "ICAO",
    ANAC = "ANAC",
    GCAA = "GCAA",
    SACAA = "SACAA",
    BOEING = "BOEING",
    AIRBUS = "AIRBUS",
    CESSNA = "CESSNA",
    EMBRAER = "EMBRAER",
    BOMBARDIER = "BOMBARDIER",
    DASSAULT = "DASSAULT",
    GULFSTREAM = "GULFSTREAM",
    IATA = "IATA",
    RED_CROSS = "RED_CROSS",
    AHA = "AHA",
    FLIGHT_SAFETY = "FLIGHT_SAFETY",
    CAE = "CAE",
  }
  export enum CertificationIssuingCountry {
    US = "United States",
    EU = "European Union",
    CANADA = "Canada",
    INDIA = "India",
    CHINA = "China",
    AUSTRALIA = "Australia",
    UAE = "United Arab Emirates",
    GLOBAL = "Global",
  }
  export enum CrewAvailability {
    AVAILABLE = "Available",
    ON_DUTY = "On-Duty",
    ON_LEAVE = "On-Leave",
    UNAVAILABLE = "Unavailable",
  }
  export enum CrewRole {
    CAPTAIN = "Captain",
    FIRST_OFFICER = "First Officer",
    SECOND_OFFICER = "Seconds Officer",
    THIRD_OFFICER = "Third Officer",
    RELIEF_CREW_MEMBER = "Relief Crew Member",
    FLIGHT_ENGINEER = "Flight Engineer",
    AIRBORNE_SENSOR_OPR = "Airborne Sensor Opr.",
    PURSER = "Purser",
    FLIGHT_ATTENDANT = "Flight Attendant",
    FLIGHT_MEDIC = "Flight Medic",
    LOADMASTER = "Loadmaster",
  }
  export enum PlaneAvailability {
    AVAILABLE = "Available",
    UNDER_MAINTENANCE = "Under-Maintenance",
    IN_USE = "In-Use",
    RETIRED = "Retired",
  }
  export enum RunwaySurfaceType {
    ASPHALT = "Asphalt",
    CONCRETE = "Concrete",
    GRASS = "Grass",
    GRAVEL = "Gravel",
    DIRT = "Dirt",
    COMP_MATERIAL = "Composite Materials",
  }
  export enum GroundVehicleAvailability {
    AVAILABLE = "Available",
    IN_USE = "In-Use",
    UNDER_MAINTENANCE = "Under-Maintenance",
    OUT_OF_SERVICE = "Out-Of-Service",
  }
  export enum GroundVehicleType {
    TUG = "Tug",
    REFUELER = "Refueler",
    LOADER = "Loader",
    CATERING = "Catering",
    DE_ICER = "De-Icer",
    PUSHBACK = "Pushback",
    BUS = "Bus",
  }
}
export default Enums;
