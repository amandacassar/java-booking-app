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

public class ListAllClients extends Stage
{
    // declaring the stage
    public Stage primaryStage;
    
    // declaing and next view
    public HomeView backHomeView ;    
        
    public GridPane pane = new GridPane();
    
    // creating components to display
    Label title = new Label("CLIENTS LIST");
    Label subTitle = new Label("Search Result List");
    
    public static TableView<Client> table = new TableView<>();  
    
    Button btnBack = new Button("Back");

    public Parent asParent()
    {
        return pane;
    }        
    
    
    public ListAllClients(Stage stage)
    {
        // instantiating the stage
        this.primaryStage = stage;

        // setting the table's columns titles
        TableColumn<Client, Integer> cIdCol = new TableColumn<>("Client Id");
        TableColumn<Client, String> nameCol = new TableColumn<>("Name");
        TableColumn<Client, String> surnameCol = new TableColumn<>("Surname");
        TableColumn<Client, String> dobCol = new TableColumn<>("Date of Birth");
        TableColumn<Client, String> genderCol = new TableColumn<>("Gender");
        TableColumn<Client, String> contactCol = new TableColumn<>("Contact");
        TableColumn<Client, String> emailCol = new TableColumn<>("Email");
        TableColumn<Client, String> addressCol = new TableColumn<>("Address");
        TableColumn<Client, String> cityCol = new TableColumn<>("City");
        TableColumn<Client, String> countryCol = new TableColumn<>("Country");
        TableColumn<Client, String> emergencyContactCol = new TableColumn<>("Emergency Contact");
        
        // defining the table's columns minimum width
        cIdCol.setMinWidth(100);
        nameCol.setMinWidth(100);
        surnameCol.setMinWidth(100);
        dobCol.setMinWidth(100);
        genderCol.setMinWidth(100);
        contactCol.setMinWidth(100);
        emailCol.setMinWidth(250);
        addressCol.setMinWidth(200);
        cityCol.setMinWidth(100);
        countryCol.setMinWidth(100);
        emergencyContactCol.setMinWidth(250);
        
        // declaring the variable to display in each field of the table
        cIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        emergencyContactCol.setCellValueFactory(new PropertyValueFactory<>("emergencyContact"));
        
        // setting components' fonts
        title.setFont(new Font("Calibri Bold", 50));        
        subTitle.setFont(new Font("Calibri", 30));
        btnBack.setFont(new Font("Calibri", 20));
        
        // defining components' position on the grid pane    
        GridPane.setConstraints(subTitle, 0, 2, 4, 1);     
        GridPane.setConstraints(table, 0, 5);
        GridPane.setConstraints(btnBack, 0, 20, 2, 1); 

        // adding the columns of the table to display them
        table.getColumns().add(cIdCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(surnameCol);
        table.getColumns().add(dobCol);
        table.getColumns().add(genderCol);
        table.getColumns().add(contactCol);
        table.getColumns().add(emailCol);
        table.getColumns().add(addressCol);
        table.getColumns().add(cityCol);
        table.getColumns().add(countryCol);
        table.getColumns().add(emergencyContactCol);
       
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
    
    
    // method to obtain the list of clients from the HomeView class
    public static void setClientsList(List<Client> clients)
    {
        ObservableList<Client> allClients = FXCollections.observableArrayList();
        
        // for each client that is present inside the ArrayList received from the HomeView class, add this clients to the ObservableList "allClients"
        for (int i = 0; i < clients.size(); i++)
        {
            int id = clients.get(i).getId();
            String name = clients.get(i).getName();
            String surname = clients.get(i).getSurname();
            String dob = clients.get(i).getDate();
            char gender = clients.get(i).getGender();
            String address = clients.get(i).getAddress();
            String city = clients.get(i).getCity();
            String country = clients.get(i).getCountry();
            String contact = clients.get(i).getContactNo();
            String email = clients.get(i).getEmail();
            String emergencyContact = clients.get(i).getEmergencyContact();
            
            allClients.add(new Client(id, name, surname, dob, gender, contact, email, address, city, country, emergencyContact));
        }       
        
        // add the list of clients into the table to be displayed
        table.setItems(allClients);
    }
    
}
