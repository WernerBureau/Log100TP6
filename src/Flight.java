public class Flight {
  
  /**
   * Constant strings for status
   */
  public static final String ONTIME = "ON TIME";
  public static final String BOARDING = "BOARDING";
  public static final String DELAYED = "DELAYED";
  public static final String CANCELLED = "CANCELLED";

  // Instance variable
  private String company;
  private int flightNumber;
  private String destination;
  private int departureTime;
  private String gate;
  private String status;
  
  public Flight(String company, int flightNumber, String destination, int departureTime, String gate, String status) {
    this.company = company;
    this.flightNumber = flightNumber;
    this.destination = destination;
    this.departureTime = departureTime;
    this.gate = gate;
    this.status = status;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public int getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(int flightNumber) {
    this.flightNumber = flightNumber;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public int getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(int departureTime) {
    this.departureTime = departureTime;
  }

  public String getGate() {
    return gate;
  }

  public void setGate(String gate) {
    this.gate = gate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return company + flightNumber + " " + destination + " " + String.format("%04d", departureTime) + " " + gate + " " + status;
  }
}
