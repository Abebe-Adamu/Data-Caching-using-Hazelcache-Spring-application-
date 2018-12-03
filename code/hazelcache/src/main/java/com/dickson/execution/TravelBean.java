package com.dickson.execution;

import java.io.Serializable;

public class TravelBean implements Serializable{
  private long id;
  private int flightNumber;
  private String airline;
  private double price;
  private String originationCity;
  private String destinationCity;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(int flightNumber) {
    this.flightNumber = flightNumber;
  }

  public String getAirline() {
    return airline;
  }

  public void setAirline(String airline) {
    this.airline = airline;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getOriginationCity() {
    return originationCity;
  }

  public void setOriginationCity(String originationCity) {
    this.originationCity = originationCity;
  }

  public String getDestinationCity() {
    return destinationCity;
  }

  public void setDestinationCity(String destinationCity) {
    this.destinationCity = destinationCity;
  }

}
