package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddBooking extends Stage
{
    // declaring the stage
    public Stage primaryStage;
    
    // declaing and next view
    public HomeView backHomeView ;    
    
    // Host name instead of IP addres, and port number
    String host = "localhost";
    int port = 8888;
    
    public GridPane grid = new GridPane();
    
    // creating components to display
    Label title = new Label("BOOKINGS");
    Label subTitle = new Label("Add New Booking");
    Label[] lbl = new Label[7];
    TextField[] txt = new TextField[7];
    static ComboBox <String> boxTypes = new ComboBox<>();
    ComboBox <String> boxStart = new ComboBox<>();
    ComboBox <String> boxFinish = new ComboBox<>();
    
    // array used to describe each index element - also used to populate each label's (lbl) text
    String[] list = new String[7];
    
    Button btnAdd = new Button ("Add this Booking");
    Button btnBack = new Button("Back");
    static Text msg = new Text();

    public Parent asParent()
    {
        return grid;
    }        
    
    public AddBooking(Stage stage)
    {
        // instantiating the stage
        this.primaryStage = stage;
        
        // elements of the array "list"
        list[0] = "CLIENT ID: ";
        list[1] = "TRAINER ID: ";
        list[2] = "STAFF ID: ";
        list[3] = "TYPE ID: ";
        list[4] = "DATE: ";
        list[5] = "START TIME: ";
        list[6] = "FINISH TIME: ";
        
        // assigning values into elements of each array - lbl, txt, btn
        // also setting fonts and position on the grid pane
        for (int i = 0; i < list.length; i++)
        {
            lbl[i] = new Label (list[i]);
            txt[i] = new TextField();
            
            lbl[i].setFont(new Font("Calibri Bold", 20));
            GridPane.setConstraints(lbl[i], 0, (i+3));
            GridPane.setHalignment(lbl[i], HPos.RIGHT);
            
            txt[i].setFont(new Font("Calibri", 20));
            txt[i].setPrefSize(500, 30);
        }
        
        // setting components' fonts
        title.setFont(new Font("Calibri Bold", 50));        
        subTitle.setFont(new Font("Calibri", 30));
        btnAdd.setFont(new Font("Calibri", 30));
        btnBack.setFont(new Font("Calibri", 20));
        boxTypes.setStyle("-fx-font: 20px \"Calibri\";");
        boxStart.setStyle("-fx-font: 20px \"Calibri\";");
        boxFinish.setStyle("-fx-font: 20px \"Calibri\";");
        msg.setFont(new Font("Calibri Bold", 20));
        msg.setFill(Color.RED);

	boxTypes.setPromptText("Select one");
        
        // defining components' position on the grid pane    
        GridPane.setConstraints(subTitle, 0, 2);
        GridPane.setConstraints(txt[0], 1, 3);
        GridPane.setConstraints(txt[1], 1, 4);
        GridPane.setConstraints(txt[2], 1, 5);
        GridPane.setConstraints(boxTypes, 1, 6);
        GridPane.setConstraints(txt[3], 1, 7);
        GridPane.setConstraints(boxStart, 1, 8);
        GridPane.setConstraints(boxFinish, 1, 9);
        GridPane.setConstraints(msg, 0, 13, 10, 1);
        GridPane.setConstraints(btnAdd, 0, 15);
        GridPane.setConstraints(btnBack, 0, 20); 
        
                
        // date format prompt text
        txt[4].setPromptText("yyyy-mm-dd");
        
        // populating the start time and finish time combo boxes
        boxStart.getItems().addAll("06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00");
        boxFinish.getItems().addAll("07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00");
        
        
        // action upon clicking the Add button
        btnAdd.setOnAction(event ->
        {
            // clear any previous error message
            msg.setText("");
            
            String clientId = txt[0].getText();
            String trainerId = txt[1].getText();
            String staffId = txt[2].getText();
            String typeId = boxTypes.getValue();
            String date = txt[3].getText();
            String start = boxStart.getValue();
            String finish = boxFinish.getValue();

            try
            {
                // connect to server to add booking, and in return expect the number of rows affected and the booking id
                Socket socket = new Socket(host, port); 

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);                
                
                // send request information to the server
                writer.println("ADDBOOKING");
                writer.println(txt[0].getText());               //client id
                writer.println(txt[1].getText());               //trainer id
                writer.println(txt[2].getText());               //staff id
                writer.println(boxTypes.getValue());    //type id
                writer.println(txt[3].getText());              //date
                writer.println(boxStart.getValue());      //start time
                writer.println(boxFinish.getValue());    //finish time
                writer.flush();
                
                // obtain the message after attempting to add a new booking and display it in this scene
                if(scan.hasNextLine())
                {
                    String result = scan.nextLine();
                    msg.setText(result);
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                msg.setText("Some connection error.");
            }  
        });          
        
        // action upon clicking the Back button
        btnBack.setOnAction(event ->
            {
                // clear any previous error message
                msg.setText("");
                
                try
                {                    
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
        grid.setMinSize(500, 300);
        grid.setAlignment(Pos.TOP_LEFT);
        
        // setting the padding and the vertical + horizontal gaps between columns
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        
        // adding all the components in the grid - including the column , row where to add the component
        grid.add(title, 0, 0);
        grid.getChildren().addAll(subTitle, txt[0], txt[1], txt[2], txt[3], boxTypes, boxStart, boxFinish, msg, btnAdd, btnBack);
        for (int i = 0; i < 7; i++)
        {
            grid.getChildren().addAll(lbl[i]);
        }
    }
   
    // method to populate the types list combo box
    public static void setTypes(List<String> _types)
    {
	// clearing any previous items in the combobox
	boxTypes.getItems().clear();

	// inputting the new values in the combobox	
         for (int i = 0; i < _types.size(); i++)
         {
             boxTypes.getItems().addAll(_types.get(i));
         }  
    }
         
    // method called from the Controller
    // to display an error message when no matching records are found
    public static void displayMessage(String _msg)
    {
        msg.setText(_msg);
    }    
    
}
