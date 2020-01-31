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

public class ListAllTrainers extends Stage
{
    // declaring the stage
    public Stage primaryStage;
    
    // declaing and next view
    public HomeView backHomeView ;    
        
    public GridPane pane = new GridPane();
    
    // creating components to display
    Label title = new Label("TRAINERS LIST");
    Label subTitle = new Label("Search Result List");
    
    public static TableView<Trainer> table = new TableView<>();  
    
    Button btnBack = new Button("Back");

    public Parent asParent()
    {
        return pane;
    }        
    
    
    public ListAllTrainers(Stage stage)
    {
        // instantiating the stage
        this.primaryStage = stage;

        // setting the table's columns titles
        TableColumn<Trainer, Integer> tIdCol = new TableColumn<>("Trainer Id");
        TableColumn<Trainer, String> nameCol = new TableColumn<>("Name");
        TableColumn<Trainer, String> surnameCol = new TableColumn<>("Surname");
        TableColumn<Trainer, String> dobCol = new TableColumn<>("Date of Birth");
        TableColumn<Trainer, String> genderCol = new TableColumn<>("Gender");
        TableColumn<Trainer, String> contactCol = new TableColumn<>("Contact");
        TableColumn<Trainer, String> emailCol = new TableColumn<>("Email");
        
        // defining the table's columns minimum width
        tIdCol.setMinWidth(100);
        nameCol.setMinWidth(100);
        surnameCol.setMinWidth(100);
        dobCol.setMinWidth(100);
        genderCol.setMinWidth(100);
        contactCol.setMinWidth(100);
        emailCol.setMinWidth(250);
        
        // declaring the variable to display in each field of the table
        tIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        // setting components' fonts
        title.setFont(new Font("Calibri Bold", 50));        
        subTitle.setFont(new Font("Calibri", 30));
        btnBack.setFont(new Font("Calibri", 20));
        
        // defining components' position on the grid pane    
        GridPane.setConstraints(subTitle, 0, 2, 4, 1);     
        GridPane.setConstraints(table, 0, 5);
        GridPane.setConstraints(btnBack, 0, 20, 2, 1); 

        // adding the columns of the table to display them
        table.getColumns().add(tIdCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(surnameCol);
        table.getColumns().add(dobCol);
        table.getColumns().add(genderCol);
        table.getColumns().add(contactCol);
        table.getColumns().add(emailCol);
       
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
    
    
    // method to obtain the list of trainers from the HomeView class
    public static void setTrainersList(List<Trainer> trainers)
    {
        ObservableList<Trainer> allTrainers = FXCollections.observableArrayList();
        
        // for each trainer that is present inside the ArrayList received from the HomeView class, add this trainer to the ObservableList "allTrainers"
        for (int i = 0; i < trainers.size(); i++)
        {
            int id = trainers.get(i).getId();
            String name = trainers.get(i).getName();
            String surname = trainers.get(i).getSurname();
            String dob = trainers.get(i).getDate();
            char gender = trainers.get(i).getGender();
            String contact = trainers.get(i).getContactNo();
            String email = trainers.get(i).getEmail();
            
            allTrainers.add(new Trainer(id, name, surname, dob, gender, contact, email));
        }       
        
        // add the list of trainers into the table to be displayed
        table.setItems(allTrainers);
    }
}
