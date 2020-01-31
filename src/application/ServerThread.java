package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ServerThread implements Runnable
{
    // declaring the socket to be able to connect to clients
    Socket socket;

    public ServerThread(Socket _socket) 
    {
        this.socket = _socket;
    }

    @Override
    public void run() 
    {
        String bookingId = "";
        String clientId = "";
        String trainerId = "";
        String staffId = "";
        String typeId = "";
        String date = "";
        String startTime = "";
        String finishTime = "";
        int bId = 0;
        int cId = 0;
        int tId = 0;
        int sId = 0;
        Booking booking = new Booking();
        List<Booking> bookingsList = new ArrayList<>();        
        
                
        try 
        {
            OutputStream toClient = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(toClient);

            // Create an input stream from the socket
            InputStream fromClient = socket.getInputStream();
            Scanner scan = new Scanner(fromClient);

            // Read input from client and creat corresponding variables
            String clientRequest = scan.nextLine();

            // actioning the client's requests
            switch (clientRequest) 
            {
                 // client requested to search a booking by its id
                case "LISTID" :
                    // getting also the trainerId from the client
                    bookingId = scan.nextLine();
                    
                    // converting trainerId from String to Integer - since it is stored as integer in the database
                    bId = Integer.parseInt(bookingId);
                    
                    // creating a list of type Booking which will store the bookings that will be obtained from the database
                    String oneBooking = Controller.listByBookingId(bId);
                    
                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (oneBooking.isEmpty()) 
                    {
                        writer.println("No records found.");
                        writer.flush();
                    } 
                    // if the Controller returned bookings from the database
                    else 
                    {
                        // sending the result to the client
                        writer.println(oneBooking);
                        writer.flush();
                    }
                    
                    break;
                    
                    
                // client requested the list of bookings for a specific personal trainer
                case "LISTCLIENT" :
                    // getting also the trainerId from the client
                    clientId = scan.nextLine();
                    
                    // converting trainerId from String to Integer - since it is stored as integer in the database
                    cId = Integer.parseInt(clientId);
                    
                    // creating a list of type Booking which will store the bookings that will be obtained from the database
                    bookingsList = Controller.listByClientId(cId);
                    
                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (bookingsList.isEmpty()) 
                    {
                        writer.println("No records found.");
                        writer.flush();
                    } 
                    // if the Controller returned bookings from the database
                    else 
                    {
                        for (int i = 0; i < bookingsList.size(); i++) 
                        {
                            Booking clientBooking = bookingsList.get(i);

                            // each booking object inside the ArrayList allBookings is being converted into one string - to pass to the client one string for each booking
                            String result = clientBooking.toString();
                            
                            // collecting the strings to be sent to the client
                            writer.println(result);
                        }
                        // send string to the client
                        writer.flush();
                    }

                    break;
                    
                    
                 // client requested the list of bookings for a specific personal trainer
                case "LISTPT" :
                    // getting also the trainerId from the client
                    trainerId = scan.nextLine();
                    
                    // converting trainerId from String to Integer - since it is stored as integer in the database
                    tId = Integer.parseInt(trainerId);
                    
                    // creating a list of type Booking which will store the bookings that will be obtained from the database
                    bookingsList = Controller.listByTrainerId(tId);
                    
                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (bookingsList.isEmpty()) 
                    {
                        writer.println("No records found.");
                        writer.flush();
                    } 
                    // if the Controller returned bookings from the database
                    else 
                    {
                        for (int i = 0; i < bookingsList.size(); i++) 
                        {
                            Booking trainerBooking = bookingsList.get(i);

                            // each booking object inside the ArrayList allBookings is being converted into one string - to pass to the client one string for each booking
                            String result = trainerBooking.toString();
                            
                            // collecting the strings to be sent to the client
                            writer.println(result);
                        }
                        // send string to the client
                        writer.flush();
                    }

                    break;
                    
                    
                // client requested the list of bookings for a specific personal trainer
                case "LISTDAY" :
                    // getting also the trainerId from the client
                    date = scan.nextLine();
                    
                    // creating a list of type Booking which will store the bookings that will be obtained from the database
                    bookingsList = Controller.listByDate(date);
                    
                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (bookingsList.isEmpty()) 
                    {
                        writer.println("No records found.  Note date format should be: YYYY-MM-DD");
                        writer.flush();
                    } 
                    // if the Controller returned bookings from the database
                    else 
                    {
                        for (int i = 0; i < bookingsList.size(); i++) 
                        {
                            booking = bookingsList.get(i);

                            // each booking object inside the ArrayList allBookings is being converted into one string - to pass to the client one string for each booking
                            String result = booking.toString();
                            
                            // collecting the strings to be sent to the client
                            writer.println(result);
                        }
                        // send string to the client
                        writer.flush();
                    }

                    break;
                    
                    
                 
                // client requested the list of all the bookings
                case "LISTALLBOOKINGS":

                    // creating a list of type Booking which will store the bookings that will be obtained from the database
                    bookingsList = Controller.listAllBookings();

                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (bookingsList.isEmpty()) 
                    {
                        writer.println("No records found.");
                        writer.flush();
                    }
                    // if the Controller returned bookings from the database
                    else 
                    {
                        for (int i = 0; i < bookingsList.size(); i++) 
                        {
                            booking = bookingsList.get(i);

                            // each booking object inside the ArrayList allBookings is being converted into one string - to pass to the client one string for each booking
                            String result = booking.toString();
                            
                            // collecting the strings to be sent to the client
                            writer.println(result);
                        }
                        // send string to the client
                        writer.flush();
                    }

                    break;
                    
                    
                    // client requested the list of all the clients
                case "LISTALLCLIENTS":

                    // creating a list of type Client which will store the clients that will be obtained from the database
                    List<Client> clientsList = Controller.listAllClients();

                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (clientsList.isEmpty()) 
                    {
                        writer.println("No records found.");
                        writer.flush();
                    }
                    // if the Controller returned clients from the database
                    else 
                    {
                        for (int i = 0; i < clientsList.size(); i++) 
                        {
                            Client client = clientsList.get(i);

                            // each booking object inside the ArrayList allClients is being converted into one string - to pass to the client one string for each client
                            String result = client.toString();
                            
                            // collecting the strings to be sent to the client
                            writer.println(result);
                        }
                        // send string to the client
                        writer.flush();
                    }

                    break;
                    
                    
                    // client requested the list of all the trainers
                case "LISTALLTRAINERS":

                    // creating a list of type Trainer which will store the trainers that will be obtained from the database
                    List<Trainer> trainersList = Controller.listAllTrainers();

                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (trainersList.isEmpty()) 
                    {
                        writer.println("No records found.");
                        writer.flush();
                    }
                    // if the Controller returned bookings from the database
                    else 
                    {
                        for (int i = 0; i < trainersList.size(); i++) 
                        {
                            Trainer trainer = trainersList.get(i);

                            // each booking object inside the ArrayList allTrainers is being converted into one string - to pass to the client one string for each trainer
                            String result = trainer.toString();
                            
                            // collecting the strings to be sent to the client
                            writer.println(result);
                        }
                        // send string to the client
                        writer.flush();
                    }

                    break;
                    
                    
                    // client requested the list of all the types of sessions
                case "LISTALLTYPES":

                    // creating a list of type Type which will store the types that will be obtained from the database
                    List<Type> typesList = Controller.listAllTypes();

                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (typesList.isEmpty()) 
                    {
                        writer.println("No records found.");
                        writer.flush();
                    }
                    // if the Controller returned types from the database
                    else 
                    {
                        for (int i = 0; i < typesList.size(); i++) 
                        {
                            Type type = typesList.get(i);

                            // each booking object inside the ArrayList allTypes is being converted into one string - to pass to the client one string for each type
                            String result = type.toString();
                            
                            // collecting the strings to be sent to the client
                            writer.println(result);
                        }
                        // send string to the client
                        writer.flush();
                    }

                    break;

                    
                // client requested to add a new booking
                case "ADDBOOKING":
                    // getting the values of the textfields
                    clientId = scan.nextLine();
                    trainerId = scan.nextLine();
                    staffId = scan.nextLine();
                    typeId = scan.nextLine();
                    date = scan.nextLine();
                    startTime = scan.nextLine();
                    finishTime = scan.nextLine();
                    
                    // obtaining the result from the controller
                    String result = Controller.addBooking(clientId, trainerId, staffId, typeId, date, startTime, finishTime);

                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (result.isEmpty()) 
                    {
                        writer.println("Some error occurred.");
                        writer.flush();
                    }
                    // if the Controller returned bookings from the database
                    else 
                    {
                        writer.println(result);
                        writer.flush();
                    }

                    break;
                    
                    
                // client requested to update part of an existing booking
                case "UPDATEBOOKING":
                    // getting the values of the textfields
                    bookingId  = scan.nextLine();
                    trainerId = scan.nextLine();
                    date = scan.nextLine();
                    startTime = scan.nextLine();
                    finishTime = scan.nextLine();
                    
                    // obtaining the result from the controller
                    String updateResult = Controller.updateBooking(bookingId, trainerId, date, startTime, finishTime);

                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (updateResult.isEmpty()) 
                    {
                        writer.println("Some error occurred.");
                        writer.flush();
                    }
                    // if the Controller returned bookings from the database
                    else 
                    {
                        writer.println(updateResult);
                        writer.flush();
                    }

                    break;
                    
                    
                // client requested to delete a booking
                case "DELETEBOOKING":
                    // getting the values of the textfields
                    bookingId  = scan.nextLine();
                    
                    // obtaining the result from the controller
                    String deleteResult = Controller.deleteBooking(bookingId);

                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (deleteResult.isEmpty()) 
                    {
                        writer.println("Some error occurred.");
                        writer.flush();
                    }
                    // if the Controller returned bookings from the database
                    else 
                    {
                        writer.println(deleteResult);
                        writer.flush();
                    }

                    break;
                    
                    
                    
                    
                // client requested the list of bookings for a specific personal trainer
                case "ALLTYPES" :
                    
                    // creating a list of type Booking which will store the bookings that will be obtained from the database
                    String typesString = Controller.allTypes();
                    
                    // if no results are returned from the Controller (from the database) - pass an error message to the client
                    if (typesString.isEmpty()) 
                    {
                        writer.println("Could not retreive list of Types of Sessions.");
                        writer.flush();
                    } 
                    // if the Controller returned bookings from the database
                    else 
                    {
                        // sending the result to the client
                        writer.println(typesString);
                        writer.flush();
                    }

                    break;
                    
            }

            socket.close();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}
