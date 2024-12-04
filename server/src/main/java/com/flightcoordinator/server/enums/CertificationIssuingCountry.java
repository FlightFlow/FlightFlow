package com.flightcoordinator.server.enums;

public enum CertificationIssuingCountry {
  US("United States"),
  EU("European Union"),
  CANADA("Canada"),
  INDIA("India"),
  CHINA("China"),
  AUSTRALIA("Australia"),
  UAE("United Arab Emirates"),
  GLOBAL("Global");

  public final String country;

  private CertificationIssuingCountry(String country) {
    this.country = country;
  }
}
