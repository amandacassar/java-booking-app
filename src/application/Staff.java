package application;

public class Staff extends Person
{
    private String password;
    private boolean logged;

    // constructor
    public Staff()
    {
        this.password = "";
        this.logged = false;
    }

    // constructor overload
    public Staff(int _id, String _name, String _surname, String _password, boolean _logged)
    {
        this.setId(_id);
        this.setName(_name);
        this.setSurname(_surname);
        this.setPass(_password);
        this.setLogged(_logged);
    }
    
    // setters
    public void setPass(String _password)
    {
        this.password = _password;
    }
    
    public void setLogged(boolean _logged)
    {
        this.logged = _logged;
    }
    
    @Override
    public void setId(int _id)
    {
        super.setId(_id);
    }
    
    @Override
    public void setName(String _name)
    {
        super.setName(_name);
    }
    
    @Override
    public void setSurname(String _surname)
    {
        super.setSurname(_surname);
    }

    
    // getters
    public String getPass()
    {
        return this.password;
    }
    
    public boolean getLogged()
    {
        return this.logged;
    }

    @Override
    public int getId()
    {
        return super.getId();
    }

    @Override
    public String getName()
    {
        return super.getName();
    }

    @Override
    public String getSurname()
    {
        return super.getSurname();
    }
}
