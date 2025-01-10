package com.flightcoordinator.server.devtools;

import java.util.Arrays;
import java.util.List;

import com.flightcoordinator.server.enums.CertificationIssuer;
import com.flightcoordinator.server.enums.CertificationIssuingCountry;
import com.flightcoordinator.server.enums.CrewAvailability;
import com.flightcoordinator.server.enums.CrewRole;
import com.flightcoordinator.server.enums.PlaneAvailability;

public class SampleDataValues {
  public static List<String> sampleCrewMemberFullNames = Arrays.asList(
      "John Doe", "Jane Smith", "Robert Brown",
      "Emily Davis", "Michael Johnson", "Sarah Wilson",
      "David White", "Laura Green", "James Hall",
      "Emma King");

  public static List<String> sampleCrewMemberEmails = Arrays.asList(
      "johndoe@example.com", "janesmith@example.com", "robertbrown@example.com",
      "emilydavis@example.com", "michaeljohnson@example.com", "sarahwilson@example.com",
      "davidwhite@example.com", "lauragreen@example.com", "jameshall@example.com",
      "emmaking@example.com");

  public static List<CrewRole> sampleCrewMemberRoles = Arrays.asList(
      CrewRole.CAPTAIN, CrewRole.FIRST_OFFICER, CrewRole.SECOND_OFFICER,
      CrewRole.FLIGHT_ENGINEER, CrewRole.AIRBORNE_SENSOR_OPR, CrewRole.RELIEF_CREW_MEMBER,
      CrewRole.THIRD_OFFICER, CrewRole.CAPTAIN, CrewRole.FLIGHT_MEDIC,
      CrewRole.FIRST_OFFICER);

  public static List<CrewAvailability> sampleCrewMemberAvailabilities = Arrays.asList(
      CrewAvailability.AVAILABLE, CrewAvailability.ON_LEAVE, CrewAvailability.UNAVAILABLE,
      CrewAvailability.UNAVAILABLE, CrewAvailability.ON_DUTY, CrewAvailability.ON_DUTY,
      CrewAvailability.ON_DUTY, CrewAvailability.AVAILABLE, CrewAvailability.ON_DUTY,
      CrewAvailability.ON_LEAVE);

  public static List<String> samplePlaneModels = Arrays.asList(
      "Boeing 737 MAX", "Airbus A320neo", "Embraer E190-E2",
      "Bombardier CRJ900", "Boeing 777X", "Airbus A350-1000",
      "Cessna Citation Longitude", "Gulfstream G700", "ATR 72-600",
      "Dassault Falcon 8X");

  public static List<String> samplePlaneRegistrationNumbers = Arrays.asList(
      "N12345", "G-ABCD", "VH-ZYX", "JA7890", "C-GHJK",
      "F-HIJL", "D-ABXY", "EC-MNO", "B-LPQR", "HS-STUV");

  public static List<Float> samplePlaneMaxTakeoffWeights = Arrays.asList(
      (float) 82000, (float) 79000, (float) 62000, (float) 20000, (float) 351000,
      (float) 317000, (float) 18000, (float) 34000, (float) 23000, (float) 19500);

  public static List<Float> samplePlaneShortestRunwayLengths = Arrays.asList(
      (float) 2500, (float) 2400, (float) 2100, (float) 1800, (float) 3500,
      (float) 3300, (float) 1300, (float) 1500, (float) 1300, (float) 1400);

  public static List<Float> samplePlaneShortestRunwayWidths = Arrays.asList(
      (float) 45, (float) 45, (float) 40, (float) 30, (float) 60,
      (float) 60, (float) 30, (float) 30, (float) 30, (float) 40);

  public static List<Float> samplePlaneFuelEfficiencies = Arrays.asList(
      3.5F, 3.2F, 3.0F, 2.8F, 6.5F, 5.0F, 5.0F, 5.2F, 6.5F, 5.0F);

  public static List<Float> samplePlaneMaxFlightRanges = Arrays.asList(
      (float) 6500, (float) 6150, (float) 5000, (float) 2800, (float) 14000,
      (float) 15200, (float) 6500, (float) 11500, (float) 1600, (float) 11000);

  public static List<Integer> samplePlanePassengerCapacities = Arrays.asList(
      230, 240, 114, 90, 396, 440, 12, 19, 70, 12);

  public static List<PlaneAvailability> samplePlaneStatuses = Arrays.asList(
      PlaneAvailability.AVAILABLE, PlaneAvailability.IN_USE, PlaneAvailability.AVAILABLE,
      PlaneAvailability.UNDER_MAINTENANCE, PlaneAvailability.AVAILABLE, PlaneAvailability.IN_USE,
      PlaneAvailability.IN_USE, PlaneAvailability.IN_USE, PlaneAvailability.UNDER_MAINTENANCE,
      PlaneAvailability.AVAILABLE);

  public static List<String> sampleAircraftOperators = Arrays.asList(
      "Delta Airlines", "Emirates", "Lufthansa",
      "Air France", "British Airways", "Singapore Airlines",
      "Qatar Airways", "ANA (All Nippon Airways)", "Qantas",
      "Turkish Airlines");

  public static List<String> sampleRunwayOrientations = Arrays.asList(
      "09L/27R", "18/36", "14L/32R", "10/28", "06L/24R",
      "16R/34L", "04/22", "12L/30R", "15/33", "07/25");

  public static List<String> sampleGroundVehicleCodes = Arrays.asList(
      "GVC001", "GVC002", "GVC003", "GVC004", "GVC005",
      "GVC006", "GVC007", "GVC008", "GVC009", "GVC010");

  public static List<String> sampleCertificationNames = Arrays.asList(
      "Airline Transport Pilot License (ATPL)",
      "Multi-Engine Rating",
      "Instrument Rating",
      "Type Rating: Boeing 737",
      "Type Rating: Airbus A320",
      "Flight Instructor Certification",
      "Cabin Safety Certification",
      "Dangerous Goods Handling Certification",
      "Medical Emergency Response Certification",
      "Crew Resource Management (CRM) Certification");

  public static List<String> sampleCertificationDescriptions = Arrays.asList(
      "Required for pilots to act as the captain of a scheduled air carrier's aircraft.",
      "Allows pilots to operate aircraft with more than one engine.",
      "Grants pilots the ability to fly using only instruments during low visibility conditions.",
      "Authorizes pilots to operate Boeing 737 aircraft safely and efficiently.",
      "Authorizes pilots to operate Airbus A320 aircraft safely and efficiently.",
      "Certification for pilots who train and guide new aviators.",
      "Ensures crew members understand emergency evacuation and safety procedures.",
      "Provides training in handling dangerous goods in compliance with aviation regulations.",
      "Covers first-aid and emergency medical response protocols for crew members.",
      "Focuses on communication, teamwork, and decision-making skills for crews.");

  public static List<CrewRole> sampleCertificationAssignableRoles = Arrays.asList(
      CrewRole.CAPTAIN, CrewRole.CAPTAIN, CrewRole.CAPTAIN, CrewRole.CAPTAIN,
      CrewRole.CAPTAIN, CrewRole.FLIGHT_ENGINEER, CrewRole.PURSER, CrewRole.FLIGHT_ATTENDANT,
      CrewRole.FLIGHT_MEDIC, CrewRole.CAPTAIN);

  public static List<CertificationIssuer> sampleCertificationIssuers = Arrays.asList(
      CertificationIssuer.FAA, CertificationIssuer.EASA, CertificationIssuer.CAA,
      CertificationIssuer.BOEING, CertificationIssuer.AIRBUS, CertificationIssuer.FAA,
      CertificationIssuer.ICAO, CertificationIssuer.IATA, CertificationIssuer.RED_CROSS,
      CertificationIssuer.CAE);

  public static List<CertificationIssuingCountry> sampleCertificationIssuingCountries = Arrays.asList(
      CertificationIssuingCountry.US, CertificationIssuingCountry.EU, CertificationIssuingCountry.GLOBAL,
      CertificationIssuingCountry.GLOBAL, CertificationIssuingCountry.GLOBAL, CertificationIssuingCountry.US,
      CertificationIssuingCountry.GLOBAL, CertificationIssuingCountry.GLOBAL, CertificationIssuingCountry.GLOBAL,
      CertificationIssuingCountry.GLOBAL);

  public static List<String> sampleAirportNames = Arrays.asList(
      "Los Angeles International Airport",
      "John F. Kennedy International Airport",
      "London Heathrow Airport",
      "Tokyo Haneda Airport",
      "Dubai International Airport",
      "Frankfurt Airport",
      "Singapore Changi Airport",
      "Sydney Kingsford Smith Airport",
      "Charles de Gaulle Airport",
      "Amsterdam Schiphol Airport",
      "Hong Kong International Airport",
      "Zurich Airport",
      "Istanbul Airport",
      "San Francisco International Airport",
      "Denver International Airport",
      "Beijing Capital International Airport",
      "Toronto Pearson International Airport",
      "Incheon International Airport",
      "Munich Airport",
      "Miami International Airport");

  public static List<String> sampleAirportIATACodes = Arrays.asList(
      "LAX", "JFK", "LHR", "HND", "DXB",
      "FRA", "SIN", "SYD", "CDG", "AMS",
      "HKG", "ZRH", "IST", "SFO", "DEN",
      "PEK", "YYZ", "ICN", "MUC", "MIA");

  public static List<String> sampleAirportICAOCodes = Arrays.asList(
      "KLAX", "KJFK", "EGLL", "RJTT", "OMDB",
      "EDDF", "WSSS", "YSSY", "LFPG", "EHAM",
      "VHHH", "LSZH", "LTFM", "KSFO", "KDEN",
      "ZBAA", "CYYZ", "RKSI", "EDDM", "KMIA");

  public static List<String> sampleAirportCountryCodes = Arrays.asList(
      "USA", "USA", "GBR", "JPN", "UAE",
      "DEU", "SGP", "AUS", "FRA", "NLD",
      "HKG", "CHE", "TUR", "USA", "USA",
      "CHN", "CAN", "KOR", "DEU", "USA");
}
