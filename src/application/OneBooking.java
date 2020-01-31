package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class OneBooking extends Stage
{
    // declaring the stage
    public Stage primaryStage;
    
    // declaing the next view
    public HomeView backHomeView ;    
    
    // Host name instead of IP addres, and port number
    String host = "localhost";
    int port = 8888;
    
    public GridPane grid = new GridPane();
    
    // creating components to display
    Label title = new Label("BOOKINGS");
    Label subTitle = new Label("Search Result");
    Label[] lbl = new Label[10];
    static TextField[] txt = new TextField[10];
    Button[] btnUpdate = new Button[10];
    
    // array used to describe each index element - also used to populate each label's (lbl) text
    String[] list = new String[10];
    
    Button btnDelete = new Button ("Delete this booking");
    Button btnBack = new Button("Back");
    static Text msg = new Text();
    
    public Parent asParent()
    {
        return grid;
    }
    
    
    public OneBooking(Stage stage)
    {
        // instantiating the stage
        this.primaryStage = stage;
    
        // elements of the array "list"
        list[0] = "BOOKING ID: ";
        list[1] = "CLIENT ID: ";
        list[2] = "CLIENT NAME: ";
        list[3] = "TRAINER ID: ";
        list[4] = "TRAINER NAME: ";
        list[5] = "STAFF ID: ";
        list[6] = "TYPE ID: ";
        list[7] = "DATE: ";
        list[8] = "START TIME: ";
        list[9] = "FINISH TIME: ";
        
        // assigning values into elements of each array - lbl, txt, btn
        // also setting fonts and position on the grid pane
        for (int i = 0; i < list.length; i++)
        {
            lbl[i] = new Label (list[i]);
            txt[i] = new TextField();
            btnUpdate[i] = new Button ("Update");
            
            lbl[i].setFont(new Font("Calibri Bold", 20));
            GridPane.setConstraints(lbl[i], 0, (i+3));
            GridPane.setHalignment(lbl[i], HPos.RIGHT);
            
            txt[i].setFont(new Font("Calibri", 20));
            txt[i].setPrefSize(500, 30);
            GridPane.setConstraints(txt[i], 1, (i+3));
            txt[i].setDisable(true);
            
            btnUpdate[i].setFont(new Font("Calibri", 14));
        }
        
        // setting components' fonts
        title.setFont(new Font("Calibri Bold", 50));        
        subTitle.setFont(new Font("Calibri", 30));
        btnDelete.setFont(new Font("Calibri", 30));
        btnBack.setFont(new Font("Calibri", 20));
        msg.setFont(new Font("Calibri Bold", 20));
        msg.setFill(Color.RED);
        
        // defining components' position on the grid pane    
        GridPane.setConstraints(subTitle, 0, 2);        
        GridPane.setConstraints(btnUpdate[0], 3, 10);
        GridPane.setConstraints(btnUpdate[1], 3, 11);
        GridPane.setConstraints(btnUpdate[2], 3, 12);
        GridPane.setConstraints(msg, 0, 13, 10, 1);
        GridPane.setConstraints(btnDelete, 0, 15);
        GridPane.setConstraints(btnBack, 0, 20); 
        
        // setting the date and time fields to be editable - so that the user can update this information
        txt[7].setDisable(false);
        txt[7].setEditable(true);
        txt[8].setDisable(false);
        txt[8].setEditable(true);
        txt[9].setDisable(false);
        txt[9].setEditable(true);
        
        
        // actions upon clicking any Update button
        btnUpdate[0].setOnAction(event ->
        {
            // clear any previous error message
            msg.setText("");
            
            String bookingId = txt[0].getText();
            String trainerId = txt[3].getText();
            String dateUpdate = txt[7].getText();
            String startUpdate = txt[8].getText();
            String finishUpdate = txt[9].getText();

            try
            {
                 // connect to server to add booking, and in return expect the number of rows affected and the booking id
                Socket socket = new Socket(host, port); 

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);                
                
                // send request information to the server
                writer.println("UPDATEBOOKING");
                writer.println(bookingId);
                writer.println(trainerId);
                writer.println(dateUpdate);
                writer.println(startUpdate);
                writer.println(finishUpdate);
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
        
        btnUpdate[1].setOnAction(event ->
        {
            // clear any previous error message
            msg.setText("");
            
            String bookingId = txt[0].getText();
            String trainerId = txt[3].getText();
            String dateUpdate = txt[7].getText();
            String startUpdate = txt[8].getText();
            String finishUpdate = txt[9].getText();

            try
            {
                 // connect to server to add booking, and in return expect the number of rows affected and the booking id
                Socket socket = new Socket(host, port); 

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);                
                
                // send request information to the server
                writer.println("UPDATEBOOKING");
                writer.println(bookingId);
                writer.println(trainerId);
                writer.println(dateUpdate);
                writer.println(startUpdate);
                writer.println(finishUpdate);
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
        
        btnUpdate[2].setOnAction(event ->
        {
            // clear any previous error message
            msg.setText("");
            
            String bookingId = txt[0].getText();
            String trainerId = txt[3].getText();
            String dateUpdate = txt[7].getText();
            String startUpdate = txt[8].getText();
            String finishUpdate = txt[9].getText();

            try
            {
                // connect to server to add booking, and in return expect the number of rows affected and the booking id
                Socket socket = new Socket(host, port); 

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);                
                
                // send request information to the server
                writer.println("UPDATEBOOKING");
                writer.println(bookingId);
                writer.println(trainerId);
                writer.println(dateUpdate);
                writer.println(startUpdate);
                writer.println(finishUpdate);
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
        
        
        // action upon clicking the delete button
        btnDelete.setOnAction(event ->
        {
            // clear any previous error message
            msg.setText("");
            
            String bookingId = txt[0].getText();
            
            try
            {
                // connect to server to add booking, and in return expect the number of rows affected and the booking id
                Socket socket = new Socket(host, port); 

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);                
                
                // send request information to the server
                writer.println("DELETEBOOKING");
                writer.println(bookingId);
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
        for (int i = 0; i < 10; i++)
        {
            grid.getChildren().addAll(lbl[i], txt[i]);
        }
        grid.getChildren().addAll(subTitle, btnUpdate[0], btnUpdate[1], btnUpdate[2], msg, btnDelete, btnBack);
    }
    
// obtaining data to fill in the textfields    
    public static void addText(String[] _txt)
    {
        for (int i = 0; i < 10; i++)
        {
            txt[i].setText(_txt[i]);
        }
    }
    
    
    // method called from the Controller
    // to display an error message when no matching records are found
    public static void displayMessage(String _msg)
    {
        msg.setText(_msg);
    }
    
}
