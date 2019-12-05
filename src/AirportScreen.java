import java.util.List;

public class AirportScreen extends Observer{
    private Airport airport;
    private String airportName;
    private ScreenDialog screenDialog;
    
    public AirportScreen(Airport airport, String airportName, ScreenDialog screenDialog) {
        this.airport = airport;
        this.airportName = airportName;
        this.screenDialog = screenDialog;
    }

    @Override
    public void update() {
        List<Flight> flights = airport.getFlights();
        Observer.displayFlights(flights, screenDialog);
    }
}