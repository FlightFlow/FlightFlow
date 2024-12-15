package com.flightcoordinator.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "route_table")
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull(message = "Origin airport id cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private AirportEntity originAirport;

    @NotNull(message = "Destination airport id cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private AirportEntity destinationAirport;  // This field corresponds to the 'destination' in AirportEntity

    @NotNull(message = "Distance cannot be null")
    @Min(value = 1, message = "Distance should be greater than '1'")
    @Column(name = "distance", nullable = false)
    private Float distance;

    @NotNull(message = "Estimated time cannot be null")
    @Column(name = "estimated_time", nullable = false)
    private Float estimatedTime;

    public RouteEntity() {
    }

    public AirportEntity getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(AirportEntity destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public AirportEntity getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(AirportEntity originAirport) {
        this.originAirport = originAirport;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Float estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((originAirport == null) ? 0 : originAirport.hashCode());
    result = prime * result + ((destinationAirport == null) ? 0 : destinationAirport.hashCode());
    result = prime * result + ((distance == null) ? 0 : distance.hashCode());
    result = prime * result + ((estimatedTime == null) ? 0 : estimatedTime.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RouteEntity other = (RouteEntity) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (originAirport == null) {
      if (other.originAirport != null)
        return false;
    } else if (!originAirport.equals(other.originAirport))
      return false;
    if (destinationAirport == null) {
      if (other.destinationAirport != null)
        return false;
    } else if (!destinationAirport.equals(other.destinationAirport))
      return false;
    if (distance == null) {
      if (other.distance != null)
        return false;
    } else if (!distance.equals(other.distance))
      return false;
    if (estimatedTime == null) {
      if (other.estimatedTime != null)
        return false;
    } else if (!estimatedTime.equals(other.estimatedTime))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "RouteEntity [id=" + id + ", originAirport=" + originAirport + ", destinationAirport=" + destinationAirport
        + ", distance=" + distance + ", estimatedTime=" + estimatedTime + ", getId()=" + getId()
        + ", getOriginAirport()=" + getOriginAirport() + ", getDestinationAirport()=" + getDestinationAirport()
        + ", getDistance()=" + getDistance() + ", getEstimatedTime()=" + getEstimatedTime() + ", hashCode()="
        + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
  }

  
}


 


