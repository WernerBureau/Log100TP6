import java.awt.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;

public class DelayFlightDialog extends JDialog {
  
  public static final int WIDTH = 300;
  public static final int HEIGHT = 350;
  
  private GuiApp app;
  private String currentState = null;
  
  private JButton okButton, cancelButton;
  private JPanel controlPanel;
  private JTextField companyField, flightNumberField, destinationField;
  private JTextField departureTimeField, terminalField, gateNumberField;
  private JComboBox statusBox, flightsBox;
  
  public DelayFlightDialog(GuiApp app, List<Flight> flights) {
    
    super(app, "Delay Flight");
    this.app = app;
    
    okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        delayFlight();
        setVisible(false);
      }
    });
    
    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(
          DelayFlightDialog.this,
          "Do you want to cancel?",
          "Cancel",
          JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
          setVisible(false);
        }
      }
    });
    
    String[] statuses = { Flight.ONTIME, Flight.CANCELLED, Flight.BOARDING, Flight.DELAYED };
    JComboBox<String> statusBox = new JComboBox<String>(statuses);
    statusBox.setSelectedItem(Flight.DELAYED);
    statusBox.setEditable(false);
    statusBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println((String)statusBox.getSelectedItem());
        currentState = (String)statusBox.getSelectedItem();
      }
    });
    
    //Assigning flights to object array
    int tabFlightsDimension = flights.size();
    int index = 0;
    
    Flight[] tabFlights = new Flight[tabFlightsDimension];
    for (Flight flight : flights) {
		tabFlights[index++] = flight;
	}
    
    JComboBox flightsBox = new JComboBox(new DefaultComboBoxModel(tabFlights));
    
    //Redifining combo box to show Flight object name
    flightsBox.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(value instanceof Flight){
                Flight flight = (Flight) value;
                setText(flight.getCompany() + flight.getFlightNumber());
            }
            return this;
        }
    } );
    
    JPanel flightsPanel = new JPanel();
    flightsPanel.add(new JLabel("Existing Flights:"));
    flightsPanel.add(flightsBox);
    
    //Already implemented.
    JPanel companyPanel = new JPanel();
    companyField = new JTextField(10);
    companyPanel.add(new JLabel("Company: "));
    companyPanel.add(companyField);
    
    JPanel flightNumberPanel = new JPanel();
    flightNumberField = new JTextField(10);
    flightNumberPanel.add(new JLabel("Flight Number: "));
    flightNumberPanel.add(flightNumberField);
    
    JPanel destinationPanel = new JPanel();
    destinationField = new JTextField(10);
    destinationPanel.add(new JLabel("Destination: "));
    destinationPanel.add(destinationField);
    
    JPanel departureTimePanel = new JPanel();
    departureTimeField = new JTextField(10);
    departureTimePanel.add(new JLabel("Departure Time: "));
    departureTimePanel.add(departureTimeField);
    
    JPanel terminalPanel = new JPanel();
    terminalField = new JTextField(10);
    terminalPanel.add(new JLabel("Terminal: "));
    terminalPanel.add(terminalField);
    
    JPanel gateNumberPanel = new JPanel();
    gateNumberField = new JTextField(10);
    gateNumberPanel.add(new JLabel("Gate Number: "));
    gateNumberPanel.add(gateNumberField);
    
    JPanel flightStatusPanel = new JPanel();
    flightStatusPanel.add(new JLabel("Flight Status: "));
    flightStatusPanel.add(statusBox);
    
    controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
    controlPanel.add(okButton);
    controlPanel.add(cancelButton);
    
    Container c = getContentPane();
    c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
    c.add(flightsPanel);
    c.add(companyPanel);
    c.add(flightNumberPanel);
    c.add(destinationPanel);
    c.add(departureTimePanel);
    c.add(terminalPanel);
    c.add(gateNumberPanel);
    c.add(flightStatusPanel);
    c.add(controlPanel);
    
    //Affecting fields upon choosing flight
    Flight selectedFlight = (Flight) flightsBox.getSelectedItem();
    companyField.setText(selectedFlight.getCompany());
    flightNumberField.setText(Integer.toString(selectedFlight.getFlightNumber()));
    destinationField.setText(selectedFlight.getDestination());
    departureTimeField.setText(Integer.toString(selectedFlight.getDepartureTime()));
    terminalField.setText(selectedFlight.getGate().substring(0,1));
    gateNumberField.setText(selectedFlight.getGate().substring(2,3));
    currentState = (String)statusBox.getSelectedItem();
    
    //Disabling fields that shouldn't be edited
    companyField.setEditable(false);
    flightNumberField.setEditable(false);
    destinationField.setEditable(false);
    terminalField.setEditable(false);
    gateNumberField.setEditable(false);
    
    setSize(new Dimension(WIDTH, HEIGHT));
    setLocationRelativeTo(app);
  
  }
  
  public void delayFlight() {
    
    String company = companyField.getText();
    int flightNumber = Integer.parseInt(flightNumberField.getText());
    String destination = destinationField.getText();
    int departureTime = Integer.parseInt(departureTimeField.getText());
    String terminal = terminalField.getText();
    int gateNumber = Integer.parseInt(gateNumberField.getText());
    String statusStr = currentState;
    
    Flight flight = new Flight(
      company, flightNumber, destination, departureTime,
      terminal + "-" + gateNumber, statusStr);
    
    app.delayFlight(flight);
    app.appendToDisplayArea("Flight delayed:\n" + flight);
    
  }

}
