package application;

public class Type 
{
    private String id;
    private String description;

    // constructor
    public Type()
    {
        this.id = "no id";
        this.description = "no description";
    }

    // constructor overload
    public Type(String _id, String _description)
    {
        this.setId(_id);
        this.setDescription(_description);
    }

    // setters
    public void setId(String _id)
    {
        this.id = _id;
    }

    public void setDescription (String _description)
    {
        this.description = _description;
    }

    // getters
    public String getId()
    {
        return this.id;
    }

    public String getDescription()
    {
        return this.description;
    }    

    // method to convert an object Client into a String
    public String toString()
    {
        return id + ";"  + description;    
    }
}
