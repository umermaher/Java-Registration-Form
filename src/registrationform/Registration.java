package registrationform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class Registration extends JFrame{
    private JPanel panel1,panel2,panel3,panel4,panel5,gPanel;
    private JLabel title,subTitle;
    private JLabel name,password,email,gender,city,country,phoneNo;
    private JTextField nameField,emailField,cityField,phoneNoField;
    private JComboBox countryBox;
    private String[] countries={"Afghanistan","Pakistan","India","Iran","USA","Saudi","UAE"};
    private String selectedGender;
    private JPasswordField passwordField;
    private JRadioButton maleBtn,femaleBtn;
    private ButtonGroup group;
    private JCheckBox acceptBtn;
     JButton regBtn,clearBtn;
    private final ImageIcon frameIcon;
    private ImageIcon icon;
     InfoDialog info;
    public boolean test=false,verify=false;
    ConnectWithDB db;
     
    public Registration(String s){
        super(s);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,500);
        this.setLayout(new BorderLayout());
        frameIcon=new ImageIcon("studentIcon.png"); //frame logo
        
        this.setIconImage(frameIcon.getImage());
        this.addPanels();        //add and set panels to the frame which contain other components
        this.addTitleAndIcon();         //add and set title and icon to the window/fame.
        this.addLabelsAndTextFields();  //add labels and textField to the window/fame.
        this.addButtons();              //add Buttons to the frame
        this.onclickButtons();           //add addActionListener(Anonymous class)
       
        this.setVisible(true);
    }
    
    public void addPanels(){
        panel1=new JPanel();
        panel2=new JPanel();
        panel3=new JPanel();
        panel4=new JPanel();
        panel5=new JPanel();
        gPanel=new JPanel();
        panel1.setPreferredSize(new Dimension(400,120));
        panel2.setPreferredSize(new Dimension(210,280));
        panel3.setPreferredSize(new Dimension(400,100));
        panel4.setPreferredSize(new Dimension(80,500));
        panel5.setPreferredSize(new Dimension(70,500));
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new GridLayout(7,2,10,10));
        gPanel.setLayout(new FlowLayout(FlowLayout.LEADING,8,0));
        panel3.setLayout(new FlowLayout(FlowLayout.LEADING,90,8));
        add(panel1,BorderLayout.NORTH);
        add(panel2,BorderLayout.CENTER);
        add(panel3,BorderLayout.SOUTH);
        add(panel4,BorderLayout.EAST);
        add(panel5,BorderLayout.WEST);
    }
    
    public void addTitleAndIcon(){
       // icon=new ImageIcon("F:\\SW 2nd SEM\\OOP Theory\\gui assignment\\studentIcon.png");
        icon=new ImageIcon("studentIcon.png");
        title=new JLabel("Admit Slip");
        subTitle=new JLabel("  Registration");
        title.setPreferredSize(new Dimension(260,50));
        title.setText("Admit Slip");
        title.setFont(new Font("MV Bli",Font.ITALIC,45));
        title.setIcon(icon);
        subTitle.setPreferredSize(new Dimension(200,40));
        subTitle.setFont(new Font("MV Bli",Font.PLAIN,30));
        panel1.add(title);
        panel1.add(subTitle);
    }
    
    public void addLabelsAndTextFields(){
        name=new JLabel(" Name: ");
        nameField=new JTextField();
        password=new JLabel(" Password: ");
        passwordField=new JPasswordField();
        email=new JLabel(" email: ");
        emailField=new JTextField();
        gender=new JLabel(" Gender: ");
        city=new JLabel(" City: ");
        cityField=new JTextField();
        country=new JLabel(" Country: ");
        countryBox=new JComboBox(countries);
        phoneNo=new JLabel(" Phone no: ");
        phoneNoField=new JTextField();
        setLabelsAndTextFields();
        panel2.add(name);
        panel2.add(nameField);
        panel2.add(password);
        panel2.add(passwordField);
        panel2.add(email);
        panel2.add(emailField);
        panel2.add(gender);
        panel2.add(gPanel);
        panel2.add(city);
        panel2.add(cityField);
        panel2.add(country);
        panel2.add(countryBox);
        panel2.add(phoneNo);
        panel2.add(phoneNoField);
    }
    
    public void setLabelsAndTextFields(){
        name.setFont(new Font("MV Bli",Font.PLAIN,20));
        nameField.setPreferredSize(new Dimension(100,20));
        password.setFont(new Font("MV Bli",Font.PLAIN,20));
        passwordField.setPreferredSize(new Dimension(100,20));
        email.setFont(new Font("MV Bli",Font.PLAIN,20));
        emailField.setPreferredSize(new Dimension(100,20));
        gender.setFont(new Font("MV Bli",Font.ROMAN_BASELINE,20));
        countryBox.setSelectedIndex(1);
        city.setFont(new Font("MV Bli",Font.PLAIN,20));
        cityField.setPreferredSize(new Dimension(100,20));
        country.setFont(new Font("MV Bli",Font.PLAIN,20));
        countryBox.setPreferredSize(new Dimension(100,20));
        phoneNo.setFont(new Font("MV Bli",Font.PLAIN,20));
        phoneNoField.setPreferredSize(new Dimension(100,20));
    }
    
    public void addButtons(){
        group=new ButtonGroup();
        maleBtn=new JRadioButton("male");
        femaleBtn=new JRadioButton("female");
        acceptBtn=new JCheckBox("Accept terms and conditions");
        regBtn=new JButton("register");
        clearBtn=new JButton("clear");
        setButtons();
        group.add(maleBtn);
        group.add(femaleBtn);
        gPanel.add(maleBtn);
        gPanel.add(femaleBtn);
        panel3.add(acceptBtn);
        panel3.add(regBtn);
        panel3.add(clearBtn);
    }
    public void setButtons(){
        maleBtn.setFocusable(false);
        femaleBtn.setFocusable(false);
        acceptBtn.setFocusable(false);
        regBtn.setFocusable(false);
        clearBtn.setFocusable(false);
        regBtn.setBackground(Color.GRAY);
        clearBtn.setBackground(Color.GRAY);
    }
    
    public void onclickButtons(){
        regBtn.addActionListener(new ActionListener(){
            Connection con;
            public void actionPerformed(ActionEvent e){
                boolean checkInput=checkingInput(true); //return false if any of the textfield/info box is empty  
                boolean checkEmail=checkingEmail(true); //check whether the email input from the user is correct or not
                boolean checkPhoneNo=checkingPhoneNo(true);  ////check whether the phone number input from the user is correct or not
                
                //int count=0;
                if(checkInput && checkEmail && checkPhoneNo){
                    verify=true;
                    info=new InfoDialog();
                    info.setName(nameField.getText());
                    info.setEmail(emailField.getText());
                    info.setPassword(passwordField.getText());
                    info.setgender(selectedGender);
                    info.setcountry(countryBox.getSelectedItem().toString());
                    info.setCity(cityField.getText());
                    info.setPhoneNo(phoneNoField.getText());
                    showDialog();
                    db=new ConnectWithDB(info);
                    db.createConnection();
                }
            }
        });
        //clear all the fields
        clearBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                nameField.setText("");
                passwordField.setText("");
                emailField.setText("");
                cityField.setText("");
                phoneNoField.setText("");
                group.clearSelection();
                test=false;
            }
        });
        
        maleBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                test=true;
                selectedGender="Male";
            }
        });
        femaleBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                test=true;
                selectedGender="Female";
            }
        });
    }
    
    public boolean checkingInput(boolean checkInput){
                if(nameField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter name", "Information required", JOptionPane.WARNING_MESSAGE);
                    checkInput=false;
                }
                if(passwordField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter password", "Information required", JOptionPane.WARNING_MESSAGE);
                    checkInput=false;
                }
                if(emailField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter email", "Information required", JOptionPane.WARNING_MESSAGE);
                    checkInput=false;
                }
                if(!(test)){   
                    JOptionPane.showMessageDialog(null, "Select gender", "Error", JOptionPane.WARNING_MESSAGE);
                    checkInput=false;
                }
                if(cityField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter city", "Information required", JOptionPane.WARNING_MESSAGE);
                    checkInput=false;
                }
                if(phoneNoField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter Phone number", "Information required", JOptionPane.WARNING_MESSAGE);
                    checkInput=false; 
                }
                if(!(acceptBtn.isSelected())){
                    JOptionPane.showMessageDialog(null, "Accepting Conditions are mandatory", "Accept conditions", JOptionPane.WARNING_MESSAGE);
                    checkInput=false;
                }
                return checkInput;
    }
    public boolean checkingEmail(boolean checkEmail){
        if(!(emailField.getText().isEmpty())){
            int countAtTheRate=0,countDot=0;  
            String traverseEmail=emailField.getText();
            for(int i=0;i<traverseEmail.length();i++){
                if(traverseEmail.charAt(i)=='@')
                        countAtTheRate++;
                if(traverseEmail.charAt(i)=='.')
                    countDot++;
            }
            
            if(countAtTheRate!=1 && countDot!=1){
                JOptionPane.showMessageDialog(null, "Wrong email", "Error", JOptionPane.ERROR_MESSAGE);
                checkEmail=false;
            }
        }
        return checkEmail;
    }
    public boolean checkingPhoneNo(boolean checkPhoneNo){
            if(!(phoneNoField.getText().isEmpty())){
                try{
                    long l=Long.parseLong(phoneNoField.getText());
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Wrong Number", "Error", JOptionPane.ERROR_MESSAGE);
                    checkPhoneNo=false;
            }
        }
        return checkPhoneNo;
    }
    public void showDialog(){
        JOptionPane.showMessageDialog(null, "Name: "+info.getName()+"\nEmail: "+info.getEmail()+
        "\nGender: "+info.getGender()+"\nCountry: "+info.getCountry()+"\nCity: "+info.getCity()+
        "\nPhone no: "+info.getPhoneNo(), "Student Info", JOptionPane.INFORMATION_MESSAGE);
    }
}


