package application;

public class Trainer extends Person
{
    public String dob;
    public char gender;
    public String contactNo;
    public String email;

    // constructor
    public Trainer()
    {
        this.dob = "no dob";
        this.gender = (char)0;
        this.contactNo = "no contact";
        this.email = "no email";       
    }

    // constructor overload
    public Trainer(int _id, String _name, String _surname, String _dob, char _gender, String _contactNo, String _email)
    {
        super.setId(_id);
        super.setName(_name);
        super.setSurname(_surname);
        this.setDate(_dob);
        this.setGender(_gender);
        this.setContactNo(_contactNo);
        this.setEmail(_email);
    }

    // setters
    public void setDate(String _dob)
    {
        this.dob = _dob;
    }

    public void setGender(char _gender)
    {
        this.gender = _gender;
    }

    public void setContactNo(String _contactNo)
    {
        this.contactNo = _contactNo;
    }

    public void setEmail(String _email)
    {
        this.email = _email;
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
    public String getDate()
    {
        return this.dob;
    }

    public char getGender()
    {
        return this.gender;
    }

    public String getContactNo()
    {
        return this.contactNo;
    }

    public String getEmail()
    {
        return this.email;
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

    // method to convert an object Client into a String
    public String toString()
    {
        return id + ";"  + name + ";" + surname + ";" + dob  + ";" + gender + ";" + contactNo + ";" + email;    
    }
}
