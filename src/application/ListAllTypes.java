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

public class ListAllTypes extends Stage
{
    // declaring the stage
    public Stage primaryStage;
    
    // declaing and next view
    public HomeView backHomeView ;    
        
    public GridPane pane = new GridPane();
    
    // creating components to display
    Label title = new Label("TYPES LIST");
    Label subTitle = new Label("Search Result List");
    
    public static TableView<Type> table = new TableView<>();  
    
    Button btnBack = new Button("Back");

    public Parent asParent()
    {
        return pane;
    }        
    
    
    public ListAllTypes(Stage stage)
    {
        // instantiating the stage
        this.primaryStage = stage;

        // setting the table's columns titles
        TableColumn<Type, String> tIdCol = new TableColumn<>("Type Id");
        TableColumn<Type, String> descCol = new TableColumn<>("Description");
        
        // defining the table's columns minimum width
        tIdCol.setMinWidth(100);
        descCol.setMinWidth(100);
        
        // declaring the variable to display in each field of the table
        tIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        
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
        table.getColumns().add(descCol);
       
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
    
    
    // method to obtain the list of types from the HomeView class
    public static void setTypesList(List<Type> types)
    {
        ObservableList<Type> allTypes = FXCollections.observableArrayList();
        
        // for each type that is present inside the ArrayList received from the HomeView class, add this type to the ObservableList "allTypes"
        for (int i = 0; i < types.size(); i++)
        {
            String id = types.get(i).getId();
            String description = types.get(i).getDescription();
            
            allTypes.add(new Type(id, description));
        }       
        
        // add the list of types into the table to be displayed
        table.setItems(allTypes);
    }
}
