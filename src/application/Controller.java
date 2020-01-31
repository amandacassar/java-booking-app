package application;

import java.sql.*;
import java.util.*;

public class Controller 
{
    // DB connection details
    private static final String URL = "jdbc:mysql://localhost:3306/ptBookings";
    private static final String NAME = "dbuser";
    private static final String PASS = "dbpass";
    

    
    // method to search for a booking by the booking id
    public static String listByBookingId(int id)
    {
        String result = "";
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "SELECT * FROM Booking WHERE id =?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            // if booking id is found, load the next page
            if (rs.next())
            {
                  String[] booking = new String[10];

                  int bId = rs.getInt("id");
                  int cId = rs.getInt("clientId");
                  int tId = rs.getInt("trainerId");
                  int sId = rs.getInt("staffId");
                  booking[0] = Integer.toString(bId);
                  booking[1] = Integer.toString(cId);
                  booking[3] = Integer.toString(tId);
                  booking[5] = Integer.toString(sId);
                  booking[6] = rs.getString("typeId");                              
                  booking[7] = rs.getString("date");
                  booking[8] = rs.getString("startTime");
                  booking[9] = rs.getString("finishTime");

                  // obtainting client name
                  try
                  {
                        String queryClient = "SELECT * FROM Client WHERE id =?;";
                        PreparedStatement stClient = con.prepareStatement(queryClient);
                        stClient.setInt(1, cId);
                        ResultSet rsClient = stClient.executeQuery();

                        if (rsClient.next())
                        {
                            booking[2] = rsClient.getString("name");
                        }
                  }
                  catch (Exception ex)
                  {
                      ex.printStackTrace();
                  }

                  // obtaining trainer name
                  try
                  {
                        String queryTrainer = "SELECT * FROM Trainer WHERE id =?;";
                        PreparedStatement stTrainer = con.prepareStatement(queryTrainer);
                        stTrainer.setInt(1, tId);
                        ResultSet rsTrainer = stTrainer.executeQuery();

                        if (rsTrainer.next())
                        {
                            booking[4] = rsTrainer.getString("name");
                        }
                  }
                  catch (Exception ex)
                  {
                      ex.printStackTrace();
                  }
                  
                  result = booking[0] + ";" + booking[1] + ";" + booking[2] + ";" + booking[3] + ";" + booking[4] + ";" + booking[5] + ";" + booking[6] + ";" + booking[7] + ";" + booking[8] + ";" + booking[9]; 
            }
            
            st.close();
            con.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  

        return result;
    }
    
    

    // method to search for a booking by a client id
    public static List<Booking>listByClientId(int cId)
    {
        // list of type Booking which will contain all the bookings obtainted from the databas
        List<Booking> allBookingsList = new ArrayList<>();

        try
        {
            // connecting to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "SELECT * FROM Booking WHERE clientId = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, cId);
            ResultSet rs = st.executeQuery();

            try
            {
                // get the bookings from the database, and add each booking to the ArrayList "allBookings"
                while (rs.next())
                {               
                    int bookingId = rs.getInt("id");
                    int clientId = rs.getInt("clientId");
                    int trainerId = rs.getInt("trainerId");
                    int staffId = rs.getInt("staffId");
                    String typeId = rs.getString("typeId"); 
                    String date = rs.getString("date");
                    String start = rs.getString("startTime");
                    String finish = rs.getString("finishTime");

                    Booking booking = new Booking(bookingId, clientId, trainerId, staffId, typeId, date, start, finish);

                    allBookingsList.add(booking);
                }   
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                st.close();
                con.close();
            }        

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        } 

        return allBookingsList;
    }
    
    

    // method to search for a booking by a trainer id
    public static List<Booking>listByTrainerId(int ptId)
    {
        // list of type Booking which will contain all the bookings obtainted from the databas
        List<Booking> allBookingsList = new ArrayList<>();

        try
        {
            // connecting to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "SELECT * FROM Booking WHERE trainerId = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, ptId);
            ResultSet rs = st.executeQuery();

            try
            {
                // get the bookings from the database, and add each booking to the ArrayList "allBookings"
                while (rs.next())
                {               
                    int bookingId = rs.getInt("id");
                    int clientId = rs.getInt("clientId");
                    int trainerId = rs.getInt("trainerId");
                    int staffId = rs.getInt("staffId");
                    String typeId = rs.getString("typeId"); 
                    String date = rs.getString("date");
                    String start = rs.getString("startTime");
                    String finish = rs.getString("finishTime");

                    Booking booking = new Booking(bookingId, clientId, trainerId, staffId, typeId, date, start, finish);

                    allBookingsList.add(booking);
                }   
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                st.close();
                con.close();
            }        

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        } 
        
        return allBookingsList;
    }
    
    

    // method to search for bookings for a specific day
    public static List<Booking> listByDate(String _date)
    {
        // list of type Booking which will contain all the bookings obtainted from the databas
        List<Booking> allBookingsList = new ArrayList<>();

        try
        {
            // connecting to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "SELECT * FROM Booking WHERE date = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, _date);
            ResultSet rs = st.executeQuery();

            try
            {
                // get the bookings from the database, and add each booking to the ArrayList "allBookings"
                while (rs.next())
                {               
                    int bookingId = rs.getInt("id");
                    int clientId = rs.getInt("clientId");
                    int trainerId = rs.getInt("trainerId");
                    int staffId = rs.getInt("staffId");
                    String typeId = rs.getString("typeId"); 
                    String date = rs.getString("date");
                    String start = rs.getString("startTime");
                    String finish = rs.getString("finishTime");

                    Booking booking = new Booking(bookingId, clientId, trainerId, staffId, typeId, date, start, finish);

                    allBookingsList.add(booking);
                }   
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                st.close();
                con.close();
            }        

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        } 

        return allBookingsList;
    }
    
    

    // method to list all bookings
    public static List<Booking> listAllBookings()
    {
        // list of type Booking which will contain all the bookings obtainted from the database
        List<Booking> allBookingsList = new ArrayList<>();

        try
        {
            // connecting to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "SELECT * FROM Booking;";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            try
            {
                // get the bookings from the database, and add each booking to the ArrayList "allBookings"
                while (rs.next())
                {               
                    int bookingId = rs.getInt("id");
                    int clientId = rs.getInt("clientId");
                    int trainerId = rs.getInt("trainerId");
                    int staffId = rs.getInt("staffId");
                    String typeId = rs.getString("typeId"); 
                    String date = rs.getString("date");
                    String start = rs.getString("startTime");
                    String finish = rs.getString("finishTime");

                    Booking booking = new Booking(bookingId, clientId, trainerId, staffId, typeId, date, start, finish);

                    allBookingsList.add(booking);
                }   
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                st.close();
                con.close();
            }        

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        } 

        return allBookingsList;
    }


    
    // method to list all clients 
    public static List<Client> listAllClients()
    {
        // list of type Client which will contain all the clients obtainted from the database
        List<Client> allClientsList = new ArrayList<>();

        try
        {
            // connecting to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "SELECT * FROM Client;";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            try
            {
                // get the clients from the database, and add each client to the ArrayList "allClients"
                while (rs.next())
                {               
                    int id = rs.getInt("id");
                    String name = rs.getString("name"); 
                    String surname = rs.getString("surname");
                    String dob = rs.getString("dob");
                    char gender = (rs.getString("gender").charAt(0));
                    String contact = rs.getString("contactNo"); 
                    String email = rs.getString("email"); 
                    String address = rs.getString("address"); 
                    String city = rs.getString("city"); 
                    String country = rs.getString("country"); 
                    String emergency = rs.getString("emergencyContact");

                    Client client = new Client(id, name, surname, dob, gender, contact, email, address, city, country, emergency);

                    allClientsList.add(client);
                }   
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                st.close();
                con.close();
            }        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        } 
        
        return allClientsList;
    }
     

    // method to list all trainers
    public static List<Trainer> listAllTrainers()
    {
        // list of type Trainer which will contain all the trainers obtainted from the database
        List<Trainer> allTrainersList = new ArrayList<>();

        try
        {
            // connecting to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "SELECT * FROM Trainer;";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            try
            {
                // get the bookings from the database, and add each booking to the ArrayList "allBookings"
                while (rs.next())
                {               
                    int id = rs.getInt("id");
                    String name = rs.getString("name"); 
                    String surname = rs.getString("surname");
                    String dob = rs.getString("dob");
                    char gender = (rs.getString("gender").charAt(0));
                    String contact = rs.getString("contactNo"); 
                    String email = rs.getString("email"); 

                    Trainer trainer = new Trainer(id, name, surname, dob, gender, contact, email);

                    allTrainersList.add(trainer);
                }   
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                st.close();
                con.close();
            }      
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        } 

        return allTrainersList;
    }
     

    // method to list all types of sessions
    public static List<Type> listAllTypes()
    {
        // list of type Booking which will contain all the bookings obtainted from the databas
        List<Type> allTypesList = new ArrayList<>();

        try
        {
            // connecting to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "SELECT * FROM Type;";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            try
            {
                // get the bookings from the database, and add each booking to the ArrayList "allBookings"
                while (rs.next())
                {               
                    String id = rs.getString("id"); 
                    String description = rs.getString("description");

                    Type type = new Type(id, description);

                    allTypesList.add(type);
                }   
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                st.close();
                con.close();
            }        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        } 

        return allTypesList;
    }
     
     

    // method to add a new booking
    public static String addBooking(String _clientId, String _trainerId, String _staffId, String _typeId, String _date, String _startTime, String _finishTime) throws Exception
    {
        String result = "";
        
        String typeId = _typeId.toUpperCase();
        
        Boolean clash = false;
	Boolean typeCheck = false;
        int cId;
        int tId;
        int sId;
        List <String> dateList = new ArrayList<>();
        List <String> startList = new ArrayList<>();
        List <String> finishList = new ArrayList<>();
	List <String> typeList = new ArrayList<>();
        
        // parsing the ID Strings to integer    
        try
        {
            cId = Integer.parseInt(_clientId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            cId = 0;
        }
        try
        {
            tId = Integer.parseInt(_trainerId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            tId = 0;
        }       
        try
        {
            sId = Integer.parseInt(_staffId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            sId = 0;
        }
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);
            
            // checking that there are no other commitments for this trainer at the same time
            // getting all the other commitments for this trainer
            String checkT = "SELECT * FROM Booking WHERE trainerId =?;";
            PreparedStatement p = con.prepareStatement(checkT);
            p.setInt(1, tId);
            ResultSet r = p.executeQuery();

            while (r.next())
            {
                dateList.add(r.getString("date"));
                startList.add(r.getString("startTime"));
                finishList.add(r.getString("finishTime"));
            }
            
	    String start = _startTime + ":00";
	    String finish = _finishTime + ":00";

            for (int i = 0; i < dateList.size(); i++)
            {
                if (_date.equals(dateList.get(i)) && (start.equals(startList.get(i)) || finish.equals(finishList.get(i))))
                {
                    result = "The trainer is not available at this date and time.  Try a different date and/or time.";
                    clash = true;
                }
            }

	    // checking that the selected trainer offers the selected type of session
	    String checkType = "SELECT * FROM Trainer_Type WHERE trainerId =?;";
	    PreparedStatement pr = con.prepareStatement(checkType);
	    pr.setInt(1, tId);
	    ResultSet res = pr.executeQuery();

	    while (res.next())
	    {
		typeList.add(res.getString("typeId"));
	    }
	    for (int i = 0; i < typeList.size(); i++)
	    {
		if (typeId.equals(typeList.get(i)))
		{
		    typeCheck = true;
	        }
	    }
			
            		
            if (!clash && typeCheck)
            {
                // getting the next id
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery("SELECT id FROM Booking ORDER BY id DESC;");
                rs.next();
                int id = rs.getInt("id");
                id += 1; 

                // inserting the new booking
                String query = "INSERT INTO Booking VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement st = con.prepareStatement(query);
                st.setInt(1, id);
                st.setInt(2, cId);
                st.setInt(3, tId);
                st.setInt(4, sId);
                st.setString(5, typeId);
                st.setString(6, _date);
                st.setString(7, _startTime);
                st.setString(8, _finishTime);
                int qty = st.executeUpdate();

                if (qty == 1)
                {
                    result = qty + " record was affected.  The Booking Id is: " + id;
                }
                else
                {
                    result = qty + "records were affected.  Please contact administrator to check.";
                }
                st.close();
                con.close();
            }
	    else if (!typeCheck)
	    {
		result = "This Trainer does not offer this type of session.";
	    }	
        }
        catch (Exception ex)
        {
           ex.printStackTrace();
           result = "Some SQL error occurred.  Hint - check that Date format should be: YYYY-MM-DD.";
        }
        
        return result;
    }    

     
     // method to update a booking
     public static String updateBooking(String _bookingId, String _trainerId, String _newDate, String _newStart, String _newFinish) throws Exception
    {                
        String result = "";
        
        Boolean clash = false;    
        int bId;
        int tId;
        List <String> dateList = new ArrayList<>();
        List <String> startList = new ArrayList<>();
        List <String> finishList = new ArrayList<>();
        
        // parsing the ID Strings to integer    
        try
        {
            bId = Integer.parseInt(_bookingId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            bId = 0;
        }
        try
        {
            tId = Integer.parseInt(_trainerId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            tId = 0;
        }        
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            // checking that there are no other commitments for this trainer at the same time
            // getting all the other commitments for this trainer
            String checkT = "SELECT * FROM Booking WHERE trainerId =?;";
            PreparedStatement p = con.prepareStatement(checkT);
            p.setInt(1, tId);
            ResultSet r = p.executeQuery();

            while (r.next())
            {
                dateList.add(r.getString("date"));
                startList.add(r.getString("startTime"));
                finishList.add(r.getString("finishTime"));
            }

            for (int i = 0; i < dateList.size(); i++)
            {
                if (_newDate.equals(dateList.get(i)) && (_newStart.equals(startList.get(i)) || _newFinish.equals(finishList.get(i))))
                {
                    result = "The trainer is not available at this date and time.  Try a different date and/or time.";
                    clash = true;
                }
            }
            
            if (!clash)
            {
                try
                {
                    String query = "UPDATE Booking SET date =?, startTime =?, finishTime =? WHERE id =?;";
                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1, _newDate);
                    st.setString(2, _newStart);
                    st.setString(3, _newFinish);
                    st.setInt(4, bId);
                    int qty = st.executeUpdate();

                    result = qty + " record was affected.  Perform search again to confirm new data.";
                    st.close();
                    con.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    result = "Some SQL error occurred.";
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
             result = "Some connection error occurred.";
        }
        
        return result;
    }
    
     
     
     // method to delete a booking
     public static String deleteBooking(String _bookingId) throws Exception
    {                 
        String result = "";
        
        int bId;
        
        // parsing the ID String to integer    
        try
        {
            bId = Integer.parseInt(_bookingId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            bId = 0;
        }    
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "DELETE FROM Booking WHERE id =?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, bId);
            int qty = st.executeUpdate();

            result = qty + " record was deleted.";
            st.close();
            con.close();
        }
        catch(Exception ex)
        {
             ex.printStackTrace();
             result = "Some SQL error occurred.";
        }
        
        return result;
    }    
     
    
     
     // method to obtain all the current types Id's - to populate the combo box when inserting a new booking or a new trainer_type
    public static String allTypes() throws Exception
    {
        String typesString = "";
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, NAME, PASS);

            String query = "SELECT * FROM Type;";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            try
            {
                while (rs.next())
                {           
                    typesString += rs.getString("id") + ";";
                }       
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }              
            st.close();
            con.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        
        return typesString;
        
    }
    
    
}
