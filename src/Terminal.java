import java.util.LinkedList;
import java.util.List;

public class Terminal extends Subject{
    private List<Flight> flightList;
    private String terminalName;

    public Terminal(String terminalName) {
        this.terminalName = terminalName;
        this.flightList = new LinkedList<>();
    }

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
        return flightList;
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
    
    public String getName() {
        return this.terminalName;
    }
}