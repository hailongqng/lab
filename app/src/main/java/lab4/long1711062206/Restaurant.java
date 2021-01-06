package lab4.long1711062206;

public class Restaurant {
    private String name="";
    private String address="";
    private String type="";
    public Restaurant(){

    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return (name);
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getAddress()
    {
        return (address);
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getType()
    {
        return (type);
    }
    public String toString(){
        return (getName());
    }

}