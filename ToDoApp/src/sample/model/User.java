package sample.model;

public class User
{
    private String lastName;
    private String username;
    private String password;
    private String location;
    private String gender;

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    private String firstName;

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getLocation()
    {
        return location;
    }

    public String getGender()
    {
        return gender;
    }

    public User()
    {
    }

    public User(String firstName, String lastName, String username, String password, String location, String gender)
    {
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.location = location;
        this.gender = gender;
        this.firstName = firstName;
    }
}
