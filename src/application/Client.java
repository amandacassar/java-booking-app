package application;

public class Client extends Trainer
{
    private String address;
    private String city;
    private String country;
    private String emergencyContact;
    
    //constructor
    public Client()
    {   
        this.address = "no address";
        this.city = "no city";
        this.country = "no ocountry";
        this.emergencyContact = "no emergency contact";

    }

    // constructor overload
    public Client(int _id, String _name, String _surname, String _dob, char _gender, String _contactNo, String _email, String _address, String _city, String _country, String _emergencyContact)
    {
        this.setId(_id);
        this.setName(_name);
        this.setSurname(_surname);
        this.setDate(_dob);
        this.setGender(_gender);
        this.setContactNo(_contactNo);
        this.setEmail(_email);
        this.setAddress(_address);
        this.setCity(_city);
        this.setCountry(_country);
        this.setEmergencyContact(_emergencyContact);
    }
        

    // setters
    public void setAddress(String _address)
    {
        this.address = _address;
    }

    public void setCity(String _city)
    {
        this.city = _city;
    }

    public void setCountry(String _country)
    {
        this.country = _country;
    }

    public void setEmergencyContact(String _emergencyContact)
    {
        this.emergencyContact = _emergencyContact;
    }

    @Override
    public void setDate(String _date)
    {
        super.setDate(_date);
    }
    
    @Override
    public void setEmail(String _email)
    {
        super.setEmail(_email);
    }
    
    @Override
    public void setSurname(String _surname)
    {
        super.setSurname(_surname);
    }
    
    @Override
    public void setName(String _name)
    {
        super.setName(_name);
    }
    
    @Override
    public void setId(int _id)
    {
        super.setId(_id);
    }
    
    @Override
    public void setGender (char _gender)
    {
        super.setGender(_gender);
    }
    
    @Override
    public void setContactNo(String _contactNo)
    {
        super.setContactNo(_contactNo);
    }
    

    // getters
    public String getAddress()
    {
        return this.address;
    }

    public String getCity()
    {
        return this.city;
    }

    public String getCountry()
    {
        return this.country;
    }

    public String getEmergencyContact()
    {
        return this.emergencyContact;
    }

    @Override
    public String getDate()
    {
        return super.getDate();
    }

    @Override
    public char getGender()
    {
        return super.getGender();
    }

    @Override
    public String getContactNo()
    {
        return super.getContactNo();
    }

    @Override
    public String getEmail()
    {
        return super.getEmail();
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
        return id + ";"  + name + ";" + surname + ";" + dob  + ";" + gender + ";" + contactNo + ";" + email + ";" + address + ";" + city + ";" + country + ";" + emergencyContact;    
    }

}
