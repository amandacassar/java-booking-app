package application;

public class Booking 
{
    private int id;
    private int clientId;
    private int trainerId;
    private String date;                   
    private String startTime;
    private String finishTime;
    private String typeId;
    private int staffId;

    // constructor
    public Booking()
    {
        this.id = 0;
        this.clientId = 0;
        this.trainerId = 0;
        this.date = "no date";                
        this.startTime = "no time";
        this.finishTime = "no time";
        this.typeId = "no type";
        this.staffId = 0;
    }

    // constructor overload
    public Booking(int _id, int _clientId, int _trainerId,  int _staffId, String _typeId, String _date, String _startTime, String _finishTime)
    {
        this.setId(_id);
        this.setClientId(_clientId);
        this.setTrainerId(_trainerId);
        this.setDate(_date);
        this.setStartTime(_startTime);
        this.setFinishTime(_finishTime);
        this.setTypeId(_typeId);
        this.setStaffId(_staffId);
    }

    // setters
    public void setId(int _id)
    {
        this.id = _id;
    }

    public void setClientId(int _clientId)
    {
        this.clientId = _clientId;
    }

    public void setTrainerId(int _trainerId)
    {
        this.trainerId = _trainerId;
    }

    public void setDate(String _date)
    {
        this.date = _date;
    }

    public void setStartTime(String _startTime)
    {
        this.startTime = _startTime;
    }

    public void setFinishTime(String _finishTime)
    {
        this.finishTime = _finishTime;
    }

    public void setTypeId(String _typeId)
    {
        this.typeId = _typeId;
    }

    public void setStaffId(int _staffId)
    {
        this.staffId = _staffId;
    }

    // getters
    public int getId()
    {
        return this.id;
    }

    public int getClientId()
    {
        return this.clientId;
    }

    public int getTrainerId()
    {
        return this.trainerId;
    }

    public String getDate()
    {
        return this.date;
    }

    public String getStartTime()
    {
        return this.startTime;
    }

    public String getFinishTime()
    {
        return this.finishTime;
    }

    public String getTypeId()
    {
        return this.typeId;
    }

    public int getStaffId()
    {
        return this.staffId;
    }
    
    
    // method to convert an object Booking into a String - used in the server to send data to the client
    public String toString()
    {
        return id + ";"  + clientId + ";" + trainerId + ";" + staffId  + ";" + typeId + ";" + date + ";" + startTime + ";" + finishTime;    
    }
    
}
