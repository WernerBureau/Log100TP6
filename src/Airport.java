import java.util.LinkedList;
import java.util.List;

public class Airport extends Subject{
    private List<Flight> flightList = new LinkedList<>();

    @Override
    public void addFlight(Flight flight) {
        flightList.add(flight);
        notifyObservers();
    }

    @Override
    public void removeFlight(Flight flight) {
        flightList.remove(flight);
        notifyObservers();
    }

    @Override
    public List<Flight> getFlights() {
        return this.flightList;
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
  
    public Flight getFlight(String flightCode) {
        for (Flight flight : flightList){
          if (flightCode.equals(flight.getCompany()+flight.getFlightNumber())){
            return flight;
          }
        }
        return null;
    }
}