package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeView extends Stage
{
    // Host name instead of IP addres, and port number
    String host = "localhost";
    int port = 8888;
    
    // declaring the stage
    public Stage primaryStage;
    
    // declaing the next views
    public ListAllBookings listAllBookingsView;
    public OneBooking oneBookingView;
    public AddBooking addBookingView;
    public ListAllClients listAllClientsView;
    public ListAllTrainers listAllTrainersView;
    public ListAllTypes listAllTypesView;
    
    
    public GridPane pane = new GridPane();
    
    // creating components to display
    Label title = new Label("HOME");
    
    Label lblBookingId = new Label("Booking Id:");
    Label lblClientId = new Label("Booking's Client Id:");
    Label lblTrainerId = new Label("Booking's Trainer Id:");
    Label lblDate = new Label("Booking's Date:");
    
    TextField txtBookingId = new TextField();
    TextField txtClientId = new TextField();
    TextField txtTrainerId = new TextField();
    TextField txtDate = new TextField();
    
    Button btnBookingId = new Button ("Search Booking By Booking Id");
    Button btnClientId = new Button ("Search Booking By Client Id");
    Button btnTrainerId = new Button ("Search Booking By Trainer Id");
    Button btnDate = new Button ("Search Booking By Date");
    Button btnListBookings = new Button ("List All Bookings");
    Button btnListClients = new Button ("List All Clients");
    Button btnListTrainers = new Button ("List All Trainers");
    Button btnListTypes = new Button ("List All Types of Sessions");
    Button btnAddBooking = new Button ("Add Booking");
    
    static Text msg = new Text();

    public Parent asParent()
    {
        return pane;
    }    

    
    public HomeView(Stage stage)
    {      
        // instantiating the stage
        this.primaryStage = stage;
        
        // instantiating the next views and scenes
        listAllBookingsView = new ListAllBookings(primaryStage);
        Scene listAllBookingsScene = new Scene(listAllBookingsView.asParent(), 1300, 1000);
        
        oneBookingView = new OneBooking(primaryStage);
        Scene oneBookingScene = new Scene(oneBookingView.asParent(), 1300, 1000);
        
        addBookingView = new AddBooking(primaryStage);
        Scene addBookingScene = new Scene(addBookingView.asParent(), 1300, 1000);
        
        listAllClientsView = new ListAllClients(primaryStage);
        Scene listAllClientsScene = new Scene(listAllClientsView.asParent(), 1300, 1000);
        
        listAllTrainersView = new ListAllTrainers(primaryStage);
        Scene listAllTrainersScene = new Scene(listAllTrainersView.asParent(), 1300, 1000);
        
        listAllTypesView = new ListAllTypes(primaryStage);
        Scene listAllTypesScene = new Scene(listAllTypesView.asParent(), 1300, 1000);

        
        // setting components' fonts
        title.setFont(new Font("Calibri Bold", 50));
        lblBookingId.setFont(new Font("Calibri Bold", 25));
        lblClientId.setFont(new Font("Calibri Bold", 25));
        lblTrainerId.setFont(new Font("Calibri Bold", 25));
        lblDate.setFont(new Font("Calibri Bold", 25));
        msg.setFont(new Font("Calibri Bold", 30));
        msg.setFill(Color.RED);
        btnBookingId.setFont(new Font("Calibri", 20));
        btnClientId.setFont(new Font("Calibri", 20));
        btnTrainerId.setFont(new Font("Calibri", 20));
        btnDate.setFont(new Font("Calibri", 20));
        btnListBookings.setFont(new Font("Calibri Bold", 25));
        btnListClients.setFont(new Font("Calibri Bold", 25));
        btnListTrainers.setFont(new Font("Calibri Bold", 25));
        btnListTypes.setFont(new Font("Calibri Bold", 25));
        btnAddBooking.setFont(new Font("Calibri Bold", 25));
        
        // defining textfields' size and prompt text
         txtBookingId.setPrefSize(200, 30);
         txtBookingId.setPromptText("Enter booking's id");
         txtClientId.setPrefSize(200, 30);
         txtClientId.setPromptText("Enter client's id");
         txtTrainerId.setPrefSize(200, 30);
         txtTrainerId.setPromptText("Enter trainer's Id");
         txtDate.setPrefSize(200, 30);
         txtDate.setPromptText("YYYY-MM-DD");
        
        // defining components' position in the grid
        GridPane.setConstraints(lblBookingId, 0, 1);
        GridPane.setConstraints(lblClientId, 0, 2);
        GridPane.setConstraints(lblTrainerId, 0, 3);
        GridPane.setConstraints(lblDate, 0, 4);
        GridPane.setConstraints(txtBookingId, 1, 1);
        GridPane.setConstraints(txtClientId, 1, 2);
        GridPane.setConstraints(txtTrainerId, 1, 3);
        GridPane.setConstraints(txtDate, 1, 4);
        GridPane.setConstraints(btnBookingId, 2, 1, 3, 1);
        GridPane.setConstraints(btnClientId, 2, 2, 3, 1);
        GridPane.setConstraints(btnTrainerId, 2, 3, 3, 1);
        GridPane.setConstraints(btnDate, 2, 4, 3, 1);
        GridPane.setConstraints(msg, 0, 6, 8, 1);
        GridPane.setConstraints(btnListBookings, 1, 10, 3, 1);
        GridPane.setConstraints(btnListClients, 1, 12, 3, 1);
        GridPane.setConstraints(btnListTrainers, 1, 14, 3, 1);
        GridPane.setConstraints(btnListTypes, 1,16, 3, 1);
        GridPane.setConstraints(btnAddBooking, 5, 10, 5, 1);
        
        
        // action upon pressing the button Search By Booking Id
        btnBookingId.setOnAction(event -> 
        {         
            // clear any previous error message
            msg.setText("");
            
            // creating a list of type Booking, which will contain the list of bookings if connection with servers is successful
            String[] booking = new String[10];
            
            // if user did not enter a trainer id, display error message
            if (txtBookingId.getText().isEmpty())
            {
                msg.setText("Enter Booking Id!");
            }
            else
            {
                try
                {
                    // Establish connection with the server
                    Socket socket = new Socket(host, port); 

                    OutputStream toServer = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(toServer);

                    InputStream fromServer = socket.getInputStream();
                    Scanner scan = new Scanner(fromServer);

                    // send request information to the server
                    writer.println("LISTID");
                    writer.println(txtBookingId.getText());
                    writer.flush();

                    // obtain the list of bookings from the server
                     if(scan.hasNextLine())
                    {
                        String result = scan.nextLine();

                        if (!result.isEmpty())
                        {
                            if (result.equals("No records found."))
                            {
                                msg.setText(result);
                            }
                            else
                            {
                                booking = result.split(";");
                            }                        
                        }
                    }

                    // closing connection with server
                    socket.close();

                    // if the server returned something, load next page
                    if (booking[0] != null)
                    {
                        // passing the results to the next page to be loaded
                        OneBooking.addText(booking);

                        // loading the next page to display results
                        primaryStage.setScene(oneBookingScene);
                        primaryStage.setTitle("Booking Search Result - Booking Id");
                        primaryStage.show();
                    }
                   
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    msg.setText("Some connection error occurred or could not load new page.");
                }
            }
        });
        
        
        // action upon pressing the button Search By Client Id
        btnClientId.setOnAction(event -> 
        {   
            // clear any previous error message
            msg.setText("");
            
            // creating a list of type Booking, which will contain the list of bookings if connection with servers is successful
            List<Booking> allBookings = new ArrayList<>();
            
            // if user did not enter a trainer id, display error message
            if (txtClientId.getText().isEmpty())
            {
                msg.setText("Enter Client Id!");
            }
            else
            {
                try
                {
                    // Establish connection with the server
                    Socket socket = new Socket(host, port); 

                    OutputStream toServer = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(toServer);

                    InputStream fromServer = socket.getInputStream();
                    Scanner scan = new Scanner(fromServer);

                    // send request information to the server
                    writer.println("LISTCLIENT");
                    writer.println(txtClientId.getText());
                    writer.flush();

                    // obtain the list of bookings from the server
                    while(scan.hasNextLine())
                    {
                        String result = scan.nextLine();

                        if (!result.isEmpty())
                        {
                            if (result.equals("No records found."))
                            {
                                msg.setText(result);
                            }
                            else
                            {
                                String[] arrayList = result.split(";");

                                // each booking that the server sends will be stored in an object "booking" of type Booking
                                Booking booking = new Booking();

                                booking.setId(Integer.parseInt(arrayList[0]));
                                booking.setClientId(Integer.parseInt(arrayList[1]));
                                booking.setTrainerId(Integer.parseInt(arrayList[2]));
                                booking.setStaffId(Integer.parseInt(arrayList[3]));
                                booking.setTypeId(arrayList[4]);
                                booking.setDate(arrayList[5]);
                                booking.setStartTime(arrayList[6]);
                                booking.setFinishTime(arrayList[7]);

                                // each booking is added to the ArrayList
                                allBookings.add(booking);

                           }                        
                        }
                    }

                    // closing connection with server
                    socket.close();

                    // if the server returned something, load next page
                    if (!allBookings.isEmpty())
                    {
                        // passing the results to the next page to be loaded
                        ListAllBookings.setBookingsList(allBookings);

                        // loading the next page to display results
                        primaryStage.setScene(listAllBookingsScene);
                        primaryStage.setTitle("Booking Search Result - Client Id");
                        primaryStage.show();
                    }                        
                   
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    msg.setText("Some connection error occurred or could not load new page.");
                } 
            }                
        });
        
        
         // action upon pressing the button Search By Trainer Id
        btnTrainerId.setOnAction(event -> 
        {   
            // clear any previous error message
            msg.setText("");
            
            // creating a list of type Booking, which will contain the list of bookings if connection with servers is successful
            List<Booking> allBookings = new ArrayList<>();
            
            // if user did not enter a trainer id, display error message
            if (txtTrainerId.getText().isEmpty())
            {
                msg.setText("Enter Trainer Id!");
            }
            else
            {
                try
                {
                    // Establish connection with the server
                    Socket socket = new Socket(host, port); 

                    OutputStream toServer = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(toServer);

                    InputStream fromServer = socket.getInputStream();
                    Scanner scan = new Scanner(fromServer);

                    // send request information to the server
                    writer.println("LISTPT");
                    writer.println(txtTrainerId.getText());
                    writer.flush();

                    // obtain the list of bookings from the server
                     while(scan.hasNextLine())
                    {
                        String result = scan.nextLine();

                        if (!result.isEmpty())
                        {
                            if (result.equals("No records found."))
                            {
                                msg.setText(result);
                            }
                            else
                            {
                                String[] arrayList = result.split(";");

                                // each booking that the server sends will be stored in an object "booking" of type Booking
                                Booking booking = new Booking();

                                booking.setId(Integer.parseInt(arrayList[0]));
                                booking.setClientId(Integer.parseInt(arrayList[1]));
                                booking.setTrainerId(Integer.parseInt(arrayList[2]));
                                booking.setStaffId(Integer.parseInt(arrayList[3]));
                                booking.setTypeId(arrayList[4]);
                                booking.setDate(arrayList[5]);
                                booking.setStartTime(arrayList[6]);
                                booking.setFinishTime(arrayList[7]);

                                // each booking is added to the ArrayList
                                allBookings.add(booking);

                            }                        
                        }
                    }

                    // closing connection with server
                    socket.close();

                    // if the server returned something, load next page
                    if (!allBookings.isEmpty())
                    {
                        // passing the results to the next page to be loaded
                        ListAllBookings.setBookingsList(allBookings);

                        // loading the next page to display results
                        primaryStage.setScene(listAllBookingsScene);
                        primaryStage.setTitle("Booking Search Result - Trainer Id");
                        primaryStage.show();
                    }                   
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    msg.setText("Some connection error occurred or could not load new page.");
                } 
            }                
        });
        
        
         // action upon pressing the button Search By Booking Date
        btnDate.setOnAction(event -> 
        {   
            // clear any previous error message
            msg.setText("");
            
            // creating a list of type Booking, which will contain the list of bookings if connection with servers is successful
            List<Booking> allBookings = new ArrayList<>();
            
            // if user did not enter a trainer id, display error message
            if (txtDate.getText().isEmpty())
            {
                msg.setText("Enter a Date!");
            }
            else
            {
                try
                {
                    // Establish connection with the server
                    Socket socket = new Socket(host, port); 

                    OutputStream toServer = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(toServer);

                    InputStream fromServer = socket.getInputStream();
                    Scanner scan = new Scanner(fromServer);

                    // send request information to the server
                    writer.println("LISTDAY");
                    writer.println(txtDate.getText());
                    writer.flush();

                    // obtain the list of bookings from the server
                     while(scan.hasNextLine())
                    {
                        String result = scan.nextLine();

                        if (!result.isEmpty())
                        {
                            if (result.equals("No records found.  Note date format should be: YYYY-MM-DD"))
                            {
                                msg.setText(result);
                            }
                            else
                            {
                                String[] arrayList = result.split(";");

                                // each booking that the server sends will be stored in an object "booking" of type Booking
                                Booking booking = new Booking();

                                booking.setId(Integer.parseInt(arrayList[0]));
                                booking.setClientId(Integer.parseInt(arrayList[1]));
                                booking.setTrainerId(Integer.parseInt(arrayList[2]));
                                booking.setStaffId(Integer.parseInt(arrayList[3]));
                                booking.setTypeId(arrayList[4]);
                                booking.setDate(arrayList[5]);
                                booking.setStartTime(arrayList[6]);
                                booking.setFinishTime(arrayList[7]);

                                // each booking is added to the ArrayList
                                allBookings.add(booking);
                            }                        
                        }
                    }

                    // closing connection with server
                    socket.close();

                    // if the server returned something, load next page
                    if (!allBookings.isEmpty())
                    {
                        // passing the results to the next page to be loaded
                        ListAllBookings.setBookingsList(allBookings);

                        // loading the next page to display results
                        primaryStage.setScene(listAllBookingsScene);
                        primaryStage.setTitle("Booking Search Result - Date");
                        primaryStage.show();
                    }                   
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    msg.setText("Some connection error occurred or could not load new page.");
                } 
            }                
        });
        
        
        // action upon pressing the button List All Bookings
        btnListBookings.setOnAction(event -> 
        {            
            // clear any previous error message
            msg.setText("");
            
            // creating a list of type Booking, which will contain the list of bookings if connection with servers is successful
            List<Booking> allBookings = new ArrayList<>();
            
            try
            {
                // Establish connection with the server
                Socket socket = new Socket(host, port);

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);

                // send request information to the server
                writer.println("LISTALLBOOKINGS");
                writer.flush();

                // obtain the list of bookings from the server
                while(scan.hasNextLine())
                {
                    String result = scan.nextLine();
                    
                    if (!result.isEmpty())
                    {
                        if (result.equals("No records found."))
                        {
                            msg.setText(result);
                        }
                        else
                        {
                            String[] arrayList = result.split(";");

                            // each booking that the server sends will be stored in an object "booking" of type Booking
                            Booking booking = new Booking();

                            booking.setId(Integer.parseInt(arrayList[0]));
                            booking.setClientId(Integer.parseInt(arrayList[1]));
                            booking.setTrainerId(Integer.parseInt(arrayList[2]));
                            booking.setStaffId(Integer.parseInt(arrayList[3]));
                            booking.setTypeId(arrayList[4]);
                            booking.setDate(arrayList[5]);
                            booking.setStartTime(arrayList[6]);
                            booking.setFinishTime(arrayList[7]);

                            // each booking is added to the ArrayList
                            allBookings.add(booking);

                        }                        
                    }
                }
                
                // closing connection with server
                socket.close();
                
                // if the server returned something, load next page
                if (!allBookings.isEmpty())
                {
                    // passing the results to the next page to be loaded
                    ListAllBookings.setBookingsList(allBookings);

                    // loading the next page to display results
                    primaryStage.setScene(listAllBookingsScene);
                    primaryStage.setTitle("All Bookings");
                    primaryStage.show();
                }               
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                msg.setText("Some connection error occurred or could not load new page.");
            }               
        });
        
        
        // action upon pressing the button List All Clients
        btnListClients.setOnAction(event -> 
        {            
            // clear any previous error message
            msg.setText("");
            
            // creating a list of type Client, which will contain the list of clients if connection with servers is successful
            List<Client> allClients = new ArrayList<>();
            
            try
            {
                // Establish connection with the server
                Socket socket = new Socket(host, port);

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);

                // send request information to the server
                writer.println("LISTALLCLIENTS");
                writer.flush();

                // obtain the list of clients from the server
                while(scan.hasNextLine())
                {
                    String result = scan.nextLine();
                    
                    if (!result.isEmpty())
                    {
                        if (result.equals("No records found."))
                        {
                            msg.setText(result);
                        }
                        else
                        {
                            String[] arrayList = result.split(";");

                            // storing each client that the server sends
                            Client client = new Client();

                            client.setId(Integer.parseInt(arrayList[0]));
                            client.setName(arrayList[1]);
                            client.setSurname(arrayList[2]);
                            client.setDate(arrayList[3]);
                            client.setGender(arrayList[4].charAt(0));
                            client.setContactNo(arrayList[5]);
                            client.setEmail(arrayList[6]);
                            client.setAddress(arrayList[7]);
                            client.setCity(arrayList[8]);
                            client.setCountry(arrayList[9]);                            
                            client.setEmergencyContact(arrayList[10]);

                            // each client is added to the ArrayList
                            allClients.add(client);

                        }                        
                    }
                }
                
                // closing connection with server
                socket.close();
                
                // if the server returned something, load next page
                if (!allClients.isEmpty())
                {
                    // passing the results to the next page to be loaded
                    ListAllClients.setClientsList(allClients);

                    // loading the next page to display results
                    primaryStage.setScene(listAllClientsScene);
                    primaryStage.setTitle("All Clients");
                    primaryStage.show();
                }               
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                msg.setText("Some connection error occurred or could not load new page.");
            }               
        });
        
        
        // action upon pressing the button List All Trainers
        btnListTrainers.setOnAction(event -> 
        {            
            // clear any previous error message
            msg.setText("");
            
            // creating a list of type Trainer, which will contain the list of trainers if connection with servers is successful
            List<Trainer> allTrainers = new ArrayList<>();
            
            try
            {
                // Establish connection with the server
                Socket socket = new Socket(host, port);

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);

                // send request information to the server
                writer.println("LISTALLTRAINERS");
                writer.flush();

                // obtain the list of trainers from the server
                while(scan.hasNextLine())
                {
                    String result = scan.nextLine();
                    
                    if (!result.isEmpty())
                    {
                        if (result.equals("No records found."))
                        {
                            msg.setText(result);
                        }
                        else
                        {
                            String[] arrayList = result.split(";");

                            // storing each trainer object 
                            Trainer trainer = new Trainer();

                            trainer.setId(Integer.parseInt(arrayList[0]));
                            trainer.setName(arrayList[1]);
                            trainer.setSurname(arrayList[2]);
                            trainer.setDate(arrayList[3]);
                            trainer.setGender(arrayList[4].charAt(0));
                            trainer.setContactNo(arrayList[5]);
                            trainer.setEmail(arrayList[6]);

                            // each trainer is added to the ArrayList
                            allTrainers.add(trainer);

                        }                        
                    }
                }
                
                // closing connection with server
                socket.close();
                
                // if the server returned something, load next page
                if (!allTrainers.isEmpty())
                {
                    // passing the results to the next page to be loaded
                    ListAllTrainers.setTrainersList(allTrainers);

                    // loading the next page to display results
                    primaryStage.setScene(listAllTrainersScene);
                    primaryStage.setTitle("All Trainers");
                    primaryStage.show();
                }               
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                msg.setText("Some connection error occurred or could not load new page.");
            }               
        });
        
        
        // action upon pressing the button List All Types of Sessions
        btnListTypes.setOnAction(event -> 
        {            
            // clear any previous error message
            msg.setText("");
            
            // creating a list of type Type, which will contain the list of types if connection with servers is successful
            List<Type> allTypes = new ArrayList<>();
            
            try
            {
                // Establish connection with the server
                Socket socket = new Socket(host, port);

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);

                // send request information to the server
                writer.println("LISTALLTYPES");
                writer.flush();

                // obtain the list of types from the server
                while(scan.hasNextLine())
                {
                    String result = scan.nextLine();
                    
                    if (!result.isEmpty())
                    {
                        if (result.equals("No records found."))
                        {
                            msg.setText(result);
                        }
                        else
                        {
                            String[] arrayList = result.split(";");

                            // storing each type object
                            Type type = new Type();

                            type.setId(arrayList[0]);
                            type.setDescription(arrayList[1]);

                            // each type is added to the ArrayList
                            allTypes.add(type);

                        }                        
                    }
                }
                
                // closing connection with server
                socket.close();
                
                // if the server returned something, load next page
                if (!allTypes.isEmpty())
                {
                    // passing the results to the next page to be loaded
                    ListAllTypes.setTypesList(allTypes);

                    // loading the next page to display results
                    primaryStage.setScene(listAllTypesScene);
                    primaryStage.setTitle("All Types");
                    primaryStage.show();
                }               
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                msg.setText("Some connection error occurred or could not load new page.");
            }               
        });
        
        
        
        // action upon pressing the button List All Bookings
        btnAddBooking.setOnAction(event -> 
        {        
            // clear any previous error message
            msg.setText("");
            
            String result = "";
            List<String> typesList = new ArrayList<>();
            
            try
            {
                // Establish connection with the server
                Socket socket = new Socket(host, port);

                OutputStream toServer = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(toServer);

                InputStream fromServer = socket.getInputStream();
                Scanner scan = new Scanner(fromServer);

                // send request information to the server
                writer.println("ALLTYPES");
                writer.flush();

                // obtain the list of bookings from the server
                if(scan.hasNextLine())
                {
                    result = scan.nextLine();
                    String[] types = result.split(";");
                    
                    // populating the list to be passed to the AddBooking class, to display the combobox with list of types
                    for(int i = 0; i < types.length; i++)
                    {
                        typesList.add(types[i]);
                    }
                }
                // if no result was returned
                else
                {
                     msg.setText("Could not retreive list of Types of Sessions.");
                }
                
                // closing connection with server
                socket.close();
                
                // if the server returned a valid list of types of sessions
                if (!result.equals("Could not retreive list of Types of Sessions.") && !msg.getText().equals("Could not retreive list of Types of Sessions."))
                {
                    // passing the results to the next page to be loaded
                    AddBooking.setTypes(typesList);

                     // loading the next page to display results
                    primaryStage.setScene(addBookingScene);
                    primaryStage.setTitle("Add a Booking");
                    primaryStage.show();
                }               
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                msg.setText("Some connection error occurred or could not load new page.");
            }               
        });
        
        
        pane.setAlignment(Pos.TOP_LEFT);

        // setting the padding and the vertical + horizontal gaps between columns
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(15);
        pane.setHgap(15);

        // adding all the components in the grid - including the column , row where to add the component
        pane.add(title, 0, 0);
        pane.getChildren().addAll(lblBookingId, lblClientId, lblTrainerId, lblDate, txtBookingId, txtClientId, txtTrainerId, txtDate, btnBookingId, btnClientId, btnTrainerId, btnDate, 
                btnListBookings,btnListClients, btnListTrainers, btnListTypes, btnAddBooking, msg);
    }
    
}
