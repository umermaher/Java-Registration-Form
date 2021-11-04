package registrationform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InfoDialog extends JFrame{
    private String name,password,email,gender,country,city,phoneNo;
    public void setName(String name){
        this.name=name;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public void setgender(String gender){
        this.gender=gender;
    }
    
    public void setcountry(String country){
        this.country=country;
    }
    
    public void setCity(String city){
        this.city=city;
    }
    
    public void setPhoneNo(String phoneNo){
        this.phoneNo=phoneNo;
    }
    
    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getGender(){
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    
}
