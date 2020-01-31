package application;

public class Person 
{
    public int id;
    public String name;
    public String surname;

    // constructor
    public Person() 
    {
        this.id = 0;
        this.name = "no name";
        this.surname = "no surname";
    }

    // setters
    public void setId(int _id)
    {
        this.id = _id;
    }

    public void setName(String _name)
    {
        this.name = _name;
    }

    public void setSurname(String _surname)
    {
        this.surname = _surname;
    }

    // getters
    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getSurname()
    {
        return this.surname;
    }
}
