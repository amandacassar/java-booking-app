package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListAllBookings extends Stage
{
    // declaring the stage
    public Stage primaryStage;
    
    // declaing and next view
    public HomeView backHomeView ;    
        
    public GridPane pane = new GridPane();
    
    // creating components to display
    Label title = new Label("BOOKINGS LIST");
    Label subTitle = new Label("Search Result List");
    
    public static TableView<Booking> table = new TableView<>();  
    
    Button btnBack = new Button("Back");

    public Parent asParent()
    {
        return pane;
    }        
    
    
    public ListAllBookings(Stage stage)
    {
        // instantiating the stage
        this.primaryStage = stage;

        // setting the table's columns titles
        TableColumn<Booking, Integer> idCol = new TableColumn<>("Booking Id");
        TableColumn<Booking, Integer> cIdCol = new TableColumn<>("Client Id");
        TableColumn<Booking, Integer> tIdCol = new TableColumn<>("Trainer Id");
        TableColumn<Booking, Integer> sIdCol = new TableColumn<>("Staff Id");
        TableColumn<Booking, String> typeIdCol = new TableColumn<>("Type Id");
        TableColumn<Booking, String> dateCol = new TableColumn<>("Date");
        TableColumn<Booking, String> startCol = new TableColumn<>("Start Time");
        TableColumn<Booking, String> finishCol = new TableColumn<>("Finish Time");
        
        // defining the table's columns minimum width
        idCol.setMinWidth(100);
        cIdCol.setMinWidth(100);
        tIdCol.setMinWidth(100);
        sIdCol.setMinWidth(100);
        typeIdCol.setMinWidth(100);
        dateCol.setMinWidth(100);
        startCol.setMinWidth(100);
        finishCol.setMinWidth(100);
        
        // declaring the variable to display in each field of the table
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cIdCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        tIdCol.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
        sIdCol.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        typeIdCol.setCellValueFactory(new PropertyValueFactory<>("typeId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        finishCol.setCellValueFactory(new PropertyValueFactory<>("finishTime"));
        
        // setting components' fonts
        title.setFont(new Font("Calibri Bold", 50));        
        subTitle.setFont(new Font("Calibri", 30));
        btnBack.setFont(new Font("Calibri", 20));
        
        // defining components' position on the grid pane    
        GridPane.setConstraints(subTitle, 0, 2, 4, 1);     
        GridPane.setConstraints(table, 0, 5);
        GridPane.setConstraints(btnBack, 0, 20, 2, 1); 

        // adding the columns of the table to display them
        table.getColumns().add(idCol);
        table.getColumns().add(cIdCol);
        table.getColumns().add(tIdCol);
        table.getColumns().add(sIdCol);
        table.getColumns().add(typeIdCol);
        table.getColumns().add(dateCol);
        table.getColumns().add(startCol);
        table.getColumns().add(finishCol);
       
        // action upon clicking the Back button
        btnBack.setOnAction(event ->
        {
            try
            {
                // clear the contents of the table - so that they won't display in the next run
                table.getColumns().clear();

                // instantiating the next view and scene
                backHomeView = new HomeView(primaryStage);
                Scene backHomeScene = new Scene(backHomeView.asParent(), 1200, 800);

                // go to the Home Scene
                primaryStage.setScene(backHomeScene);
                primaryStage.setTitle("Home");
                primaryStage.show();

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }                
        });        
        
        // setting the size of the grid pane and centre alignment
        pane.setMinSize(500, 300);
        pane.setAlignment(Pos.TOP_LEFT);
        
        // setting the padding and the vertical + horizontal gaps between columns
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(10);
        pane.setHgap(10);
        
        // adding all the components in the grid - including the column , row where to add the component
        pane.add(title, 0, 0, 4, 1);
        pane.getChildren().addAll(subTitle, btnBack, table);

    }
    
    
    // method to obtain the list of bookings from the HomeView class
    public static void setBookingsList(List<Booking> bookings)
    {
        ObservableList<Booking> allBookings = FXCollections.observableArrayList();
        
        // for each booking that is present inside the ArrayList received from the HomeView class, add this booking to the ObservableList "allBookings"
        for (int i = 0; i < bookings.size(); i++)
        {
            int id = bookings.get(i).getId();
            int cId = bookings.get(i).getClientId();
            int tId = bookings.get(i).getTrainerId();
            int sId = bookings.get(i).getStaffId();
            String typeId = bookings.get(i).getTypeId();
            String date = bookings.get(i).getDate();
            String start = bookings.get(i).getStartTime();
            String finish = bookings.get(i).getFinishTime();
            
            allBookings.add(new Booking(id, cId, tId, sId, typeId, date, start, finish));
        }       
        
        // add the list of bookings into the table to be displayed
        table.setItems(allBookings);
    }
      

}
