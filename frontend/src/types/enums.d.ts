namespace Enums {
  export enum AirportTypes {
    INTERNATIONAL = "International",
    REGIONAL = "Regional",
    DOMESTIC = "Domestic",
  }
  export enum CertificationIssuers {
    FAA = "Federal Aviation Administration",
    EASA = "European Union Aviation Safety Agency",
    CAA = "Civil Aviation Authority",
    TRANSPORT_CANADA = "Transport Canada",
    DGCA = "Directorate General of Civil Aviation",
    CAAC = "Civil Aviation Administration of China",
    CASA = "Civil Aviation Safety Authority",
    ICAO = "International Civil Aviation Organization",
    ANAC = "Agência Nacional de Aviação Civil",
    GCAA = "General Civil Aviation Authority",
    SACAA = "South African Civil Aviation Authority",
    BOEING = "Boeing",
    AIRBUS = "Airbus",
    CESSNA = "Cessna",
    EMBRAER = "Embraer",
    BOMBARDIER = "Bombardier",
    DASSAULT = "Dassault Aviation",
    GULFSTREAM = "Gulfstream",
    IATA = "International Air Transport Association",
    RED_CROSS = "Red Cross",
    AHA = "American Heart Association",
    FLIGHT_SAFETY = "FlightSafety International",
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
  export enum CrewRoles {
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
    IS_USE = "In-Use",
    RETIRED = "Retired",
  }
  export enum RunwaySurfaceTypes {
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
  export enum GroundVehicleTypes {
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
