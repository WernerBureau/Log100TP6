import java.util.List;

public class GateScreen extends Observer{

    private Gate gate;
    private ScreenDialog screenDialog;

    public GateScreen(Gate gate, ScreenDialog screenDialog) {
        this.gate = gate;
        this.screenDialog = screenDialog;
    }

    @Override
    public void update() {
        List<Flight> flights = gate.getFlights();
        Observer.displayFlights(flights, screenDialog);
    }
}