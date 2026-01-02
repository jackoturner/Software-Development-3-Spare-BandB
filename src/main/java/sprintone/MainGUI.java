package sprintone;

import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainGUI extends Application {
    private BookingManager bookingManager;
    private RewardManager rewardManager;
    private ObservableList<User> users;
    private ObservableList<Accommodation> accommodations;
    private ListView<User> userListView;
    private ListView<Accommodation> accommodationListView;
    private TextArea outputArea;
    private TextArea userDetailsArea;
    private TextArea accommodationDetailsArea; 

    @Override
    public void start(Stage stage) {
        // Core managers
        bookingManager = BookingManager.getInstance();
        rewardManager = RewardManager.getInstance();
        // Data setup
        setupData();

        // Title
        Label title = new Label("SpareB&B Booking System");
        title.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 56));
        title.setTextFill(Color.web("#efefe4ff"));
        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(0, 0, 10, 0));

        // Left: Users panel
        userListView = new ListView<>(users);
        userListView.setMinWidth(220);
        userListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldUser, newUser) -> showUserDetails(newUser));
        
        userListView.setCellFactory(lv -> new ListCell<User>() {
            @Override protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                setText(empty || user == null ? null : user.getName());
            }
        });

        userDetailsArea = new TextArea();
        userDetailsArea.setEditable(false);
        userDetailsArea.setWrapText(true);

        VBox userBox = new VBox(10, new Label("Users"), userListView, new Label("User Details"), userDetailsArea);
        userBox.setPadding(new Insets(15));
        VBox.setVgrow(userListView, Priority.ALWAYS);
        VBox.setVgrow(userDetailsArea, Priority.ALWAYS);

        // Middle: Accommodations panel
        accommodationListView = new ListView<>(accommodations);
        accommodationListView.setMinWidth(220);  
        accommodationListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldAcc, newAcc) -> showAccommodationDetails(newAcc));  
        
        // Proper display names
        accommodationListView.setCellFactory(lv -> new ListCell<Accommodation>() {
            @Override protected void updateItem(Accommodation acc, boolean empty) {
                super.updateItem(acc, empty);
                setText(empty || acc == null ? null : acc.getName());
            }
        });

        accommodationDetailsArea = new TextArea(); 
        accommodationDetailsArea.setEditable(false);
        accommodationDetailsArea.setWrapText(true);

        VBox accommodationBox = new VBox(10,
                new Label("Accommodations"),
                accommodationListView,
                new Label("Accommodation Details"),
                accommodationDetailsArea    
        );
        accommodationBox.setPadding(new Insets(15));
        VBox.setVgrow(accommodationListView, Priority.ALWAYS);
        VBox.setVgrow(accommodationDetailsArea, Priority.ALWAYS); 

        // Right: Actions panel
        Button bookBtn = new Button("Book Selected");
        Button releaseBtn = new Button("Release Selected");
        bookBtn.setMaxWidth(Double.MAX_VALUE);
        releaseBtn.setMaxWidth(Double.MAX_VALUE);
        outputArea = new TextArea();
        outputArea.setEditable(false);
        VBox actionBox = new VBox(15, bookBtn, releaseBtn, new Label("System Output"), outputArea);
        actionBox.setPadding(new Insets(15));
        actionBox.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(outputArea, Priority.ALWAYS);

        // Button logic
        bookBtn.setOnAction(e -> handleBooking());
        releaseBtn.setOnAction(e -> handleRelease());

        // Main layout 
        HBox mainContent = new HBox(20, userBox, accommodationBox, actionBox);  // Reduced spacing for balance
        mainContent.setPadding(new Insets(20));
        HBox.setHgrow(userBox, Priority.ALWAYS);
        HBox.setHgrow(accommodationBox, Priority.ALWAYS);
        HBox.setHgrow(actionBox, Priority.ALWAYS);

        VBox root = new VBox(10, titleBox, mainContent);
        root.setPadding(new Insets(20));

        // Colour theme
        String darkGreenBg = "#7d7d7dff";
        String darkGreyPanel = "#2D2D2D";
        String panelBorder = "#202020ff";
        String lightText = "#efefe4ff";
        String buttonGreen = "#631313ff";
        String buttonRelease = "#181566ff";
        String controlBg = "#383838";

        root.setStyle("-fx-background-color: " + darkGreenBg + ";");
        userBox.setStyle("-fx-background-color: " + darkGreyPanel + "; -fx-border-color: " + panelBorder + "; -fx-border-width: 1;");
        accommodationBox.setStyle("-fx-background-color: " + darkGreyPanel + "; -fx-border-color: " + panelBorder + "; -fx-border-width: 1;");
        actionBox.setStyle("-fx-background-color: " + darkGreyPanel + "; -fx-border-color: " + panelBorder + "; -fx-border-width: 1;");

        // Styled labels
        Label usersLabel = new Label("Users");
        usersLabel.setTextFill(Color.web(lightText));
        usersLabel.setFont(Font.font(16));
        Label detailsLabel = new Label("User Details");
        detailsLabel.setTextFill(Color.web(lightText));
        detailsLabel.setFont(Font.font(16));
        Label accommodationsLabel = new Label("Accommodations");
        accommodationsLabel.setTextFill(Color.web(lightText));
        accommodationsLabel.setFont(Font.font(16));
        Label accDetailsLabel = new Label("Accommodation Details");
        accDetailsLabel.setTextFill(Color.web(lightText));
        accDetailsLabel.setFont(Font.font(16));
        Label outputLabel = new Label("System Output");
        outputLabel.setTextFill(Color.web(lightText));
        outputLabel.setFont(Font.font(16));

        // Replace labels
        userBox.getChildren().setAll(usersLabel, userListView, detailsLabel, userDetailsArea);
        accommodationBox.getChildren().setAll(accommodationsLabel, accommodationListView, accDetailsLabel, accommodationDetailsArea);
        actionBox.getChildren().setAll(bookBtn, releaseBtn, outputLabel, outputArea);

        // Buttons & Controls styling
        bookBtn.setStyle("-fx-background-color: " + buttonGreen + "; -fx-text-fill: white; -fx-font-weight: bold;");
        releaseBtn.setStyle("-fx-background-color: " + buttonRelease + "; -fx-text-fill: white; -fx-font-weight: bold;");
        String controlStyle = "-fx-control-inner-background: " + controlBg + "; -fx-text-fill: " + lightText + ";";
        userDetailsArea.setStyle(controlStyle);
        accommodationDetailsArea.setStyle(controlStyle);
        outputArea.setStyle(controlStyle);
        userListView.setStyle(controlStyle);
        accommodationListView.setStyle(controlStyle);

        // Scene & Stage
        Scene scene = new Scene(root, 1300, 750);
        stage.setTitle("SpareB&B Booking System");
        stage.setMinWidth(1100);
        stage.setMinHeight(650);
        stage.setScene(scene);
        stage.show();
    }

    private void showAccommodationDetails(Accommodation acc) {
        if (acc == null) {
            accommodationDetailsArea.clear();
            return;
        }

        String facilities = acc.getFacilities().isEmpty()
                ? "No facilities listed."
                : acc.getFacilities().stream()
                        .map(f -> "• " + f.getName() + ": " + f.getDescription())
                        .collect(Collectors.joining("\n"));

        String specificDetails = "";

        if (acc.getName().equals("City Centre Flat")) {
            specificDetails = "Type: Flat\nBedrooms: 8\nBathrooms: 3";
        } else if (acc.getName().equals("Seaside Hotel")) {
            specificDetails = "Type: Hotel\nStars: 5\nTotal Rooms: 50";
        } else if (acc.getName().equals("Sand Villa")) {
            specificDetails = "Type: Luxury Villa\nPool Size: 45.0 m²\nBedrooms: 4";
        }

        accommodationDetailsArea.setText(
                "Name: " + acc.getName() + "\n" +
                "Location: " + acc.getLocation() + "\n" +
                "Price per Night: £" + String.format("%.2f", acc.getPricePerNight()) + "\n\n" +
                specificDetails + "\n\n" +
                "Facilities:\n" + facilities
        );
    }

    private void handleBooking() {
        User user = userListView.getSelectionModel().getSelectedItem();
        Accommodation acc = accommodationListView.getSelectionModel().getSelectedItem();
        if (user == null || acc == null) {
            output("Please select a user and an accommodation.");
            return;
        }

        Booking booking = bookingManager.makeBooking(user, acc);
        if (booking == null) {
            output(acc.getName() + " is already booked and unavailable.");
        } else {
            output(user.getName() + " successfully booked " + acc.getName());
        }

        // Refresh user details after booking
        showUserDetails(user);
        // Force refresh of the currently displayed user details
        User currentlyDisplayedUser = userListView.getSelectionModel().getSelectedItem();
        if (currentlyDisplayedUser != null) {
            showUserDetails(currentlyDisplayedUser);
        }
    }

    private void handleRelease() {
        Accommodation acc = accommodationListView.getSelectionModel().getSelectedItem();
        if (acc == null) {
            output("Please select an accommodation to release.");
            return;
        }

        // Find out who currently has this accommodation booked (if anyone)
        User previousOwner = null;
        for (User user : users) {
            List<Booking> userBookings = bookingManager.getBookingsForUser(user);
            for (Booking booking : userBookings) {
                if (booking.getAccommodation().equals(acc)) {
                    previousOwner = user;
                    break;
                }
            }
            if (previousOwner != null) break;
        }

        // Release the booking
        bookingManager.releaseBooking(acc);
        output(acc.getName() + " has been released and is now available.");

        // Refresh the details of the user who just lost the booking
        if (previousOwner != null) {
            // If that user is currently selected, the panel will update.
            userListView.getSelectionModel().select(previousOwner);
            showUserDetails(previousOwner);
        }
        // Force refresh of the currently displayed user details
        User currentlyDisplayedUser = userListView.getSelectionModel().getSelectedItem();
        if (currentlyDisplayedUser != null) {
            showUserDetails(currentlyDisplayedUser);
        }   
    }

    private void showUserDetails(User user) {
        if (user == null) {
            userDetailsArea.clear();
            return;
        }
        List<Booking> bookings = bookingManager.getBookingsForUser(user);
        String bookingInfo = bookings.isEmpty()
                ? "No current bookings."
                : bookings.stream()
                        .map(b -> "- " + b.getAccommodation().getName())
                        .collect(Collectors.joining("\n"));
        userDetailsArea.setText(
                "Name: " + user.getName() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Loyalty Points: " + user.getPoints() + "\n\n" +
                "Bookings:\n" + bookingInfo
        );
    }

    private void output(String message) {
        outputArea.appendText(message + "\n");
    }

    private void setupData() {
        // Facilities
        Facility kitchen = new Facility(1, "Kitchen", "Fully stocked with modern appliances");
        Facility washer = new Facility(2, "Washing Machine", "Washer-dryer included");
        Facility wifi = new Facility(3, "Wi-Fi", "High-speed fibre internet");
        Facility pool = new Facility(4, "Pool", "Heated outdoor pool");

        // Accommodations
        Flat flat = new Flat(1, "City Centre Flat", "London", 100.0, 8, 3);
        flat.addFacility(kitchen);
        flat.addFacility(wifi);

        Hotel hotel = new Hotel(2, "Seaside Hotel", "Brighton", 150.0, 5, 50);
        hotel.addFacility(wifi);
        hotel.addFacility(washer);

        LuxuryVilla villa = new LuxuryVilla(3, "Sand Villa", "Ibiza", 500.0, 45.0, 4);
        villa.addFacility(kitchen);
        villa.addFacility(pool);

        accommodations = FXCollections.observableArrayList(flat, hotel, villa);

        // Users
        User jack = new User("Jack Turner", "jack@outlook.com");
        User luke = new User("Luke Pring", "luke@gmail.com");
        User cara = new User("Cara Cecan", "cara@icloud.com");
        rewardManager.addObserver(jack);
        rewardManager.addObserver(luke);
        rewardManager.addObserver(cara);
        users = FXCollections.observableArrayList(jack, luke, cara);
    }

    public static void main(String[] args) {
        launch(args);
    }
}