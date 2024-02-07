import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import react.Reactive;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.util.ArrayList;

import java.awt.FlowLayout;
import java.sql.*;



class FormInput extends JPanel {
    public FormInput(String labelString,Component comp){
        super(new GridLayout(1,2));
        RegistrationForm.center(this,new JLabel(labelString));
        this.add(comp);
    }
}


class Student{
    public String id;
    public String name;
    public String gender;
    public String courses;
    public String department;
    public Student(String id, String name, String gender, String courses, String department) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.courses = courses;
        this.department = department;
    }
}





class Register extends JFrame {
    public Register(Connection conn,JFrame prevFrame){
        super("Register Frame");
        JPanel formPanel = new JPanel(new GridLayout(6,1));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e->{
            this.setVisible(false);
            prevFrame.setVisible(true);
        });
        topPanel.add(backButton);

        JPanel genderPanel = new JPanel(new GridLayout(2,1));
        ButtonGroup genderGroup = new ButtonGroup();

        JRadioButton maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setActionCommand("Male");
        genderGroup.add(maleRadioButton);

        JRadioButton femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setActionCommand("Female");
        genderGroup.add(femaleRadioButton);

        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

        JPanel coursePanel = new JPanel(new GridLayout(3, 1));

        JCheckBox physicsCheckBox = new JCheckBox("Physics");
        JCheckBox dsaCheckBox = new JCheckBox("Economics");
        JCheckBox javaCheckBox = new JCheckBox("Java");

        coursePanel.add(physicsCheckBox);
        coursePanel.add(dsaCheckBox);
        coursePanel.add(javaCheckBox);


        JComboBox<String> departmentComboBox = new JComboBox<String>();

        departmentComboBox.addItem("Computer Science");
        departmentComboBox.addItem("Phyics");
        departmentComboBox.addItem("Ecconomics");


        JTextField idInput = new JTextField();
        RegistrationForm.center(formPanel, new FormInput("Student Id", idInput));
        JTextField studentInput = new JTextField();
        RegistrationForm.center(formPanel, new FormInput("Student Name", studentInput));
        RegistrationForm.center(formPanel, new FormInput("Gender", genderPanel));
        RegistrationForm.center(formPanel, new FormInput("Courses", coursePanel));
        RegistrationForm.center(formPanel, new FormInput("Department", departmentComboBox));
        
        JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e->{
           
            String name = studentInput.getText();
            String id = idInput.getText();
            String gender = genderGroup.getSelection().getActionCommand();
            String courses = "";
            String department = (String) departmentComboBox.getSelectedItem();

            if(physicsCheckBox.isSelected()){
                courses+="Physics,";
            }
            if(dsaCheckBox.isSelected()){
                courses+="DSA,";
            }
            if(javaCheckBox.isSelected()){
                courses+="Java,";
            }
            //print all info
            System.out.println("Name: "+name);
            System.out.println("Id: "+id);
            System.out.println("Gender "+gender);
            System.out.println("Courses: "+courses);
            System.out.println("Department: "+department);

            try{
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (id, name, gender, courses, department) VALUES (?,?,?,?,?)");
                stmt.setString(1, id);
                stmt.setString(2, name);
                stmt.setString(3, gender);
                stmt.setString(4, courses);
                stmt.setString(5, department);
                stmt.execute();
            }catch(SQLException error){
                error.printStackTrace();
            }

        });
        
        RegistrationForm.center(formPanel, submitBtn);
        this.add(topPanel,BorderLayout.NORTH);
        this.add(formPanel,BorderLayout.CENTER);
    }

}

class View extends JFrame {
    private ArrayList<Student> students;
    private int studentIdx;
    private Student student;
    public View(Connection conn,JFrame prevFrame){
        super("View Frame");
        studentIdx = 0;
        students = new ArrayList<>();

        student = null;
        Reactive<JLabel,String> countLabel = new Reactive<JLabel,String>(new JLabel(), "", (comp,hook)->{
            comp.setText(hook);
        }, comp->{
            comp.setText("");
        });
        Reactive<JPanel,Student> studentRenderer = new Reactive<JPanel,Student>(new JPanel(), students.size()>0?students.get(0):null, (comp,hook)->{
            if(student!= null){
                comp.setLayout(new GridLayout(5,1,10,20));
                comp.add(new JLabel("Name: "+student.name));
                comp.add(new JLabel("Id: "+student.id));
                comp.add(new JLabel("Gender: "+student.gender));
                comp.add(new JLabel("Courses: "+student.courses));
                comp.add(new JLabel("Department: "+student.department)); 
            }else{
                comp.add(new JLabel("No Students"));
            }
        }, (comp)->{
            comp.removeAll();
        });
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String courses = rs.getString("courses");
                String department = rs.getString("department");
                students.add(new Student(id, name, gender, courses, department));
            }
            if(students.size()>0){
                student = students.get(0);
                studentRenderer.setHook(student);
                countLabel.setHook("Student "+(1)+" of "+students.size());
            }
        }catch(SQLException error){
            error.printStackTrace();
        }


        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JButton prevButton = new JButton("Prev");
        prevButton.addActionListener(e->{
            if(studentIdx>0){
                studentIdx--;
                student = students.get(studentIdx);
                studentRenderer.setHook(student);
                countLabel.setHook("Student "+(studentIdx+1)+" of "+students.size());
            }
        });
        controlsPanel.add(prevButton);
        
        controlsPanel.add(countLabel.comp);

        

        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(e->{
            if(studentIdx<students.size()-1){
                studentIdx++;
                student = students.get(studentIdx);
                studentRenderer.setHook(student);
                countLabel.setHook("Student "+(studentIdx+1)+" of "+students.size());
            }
        });
        controlsPanel.add(nextBtn);

        JPanel topPanel = new JPanel(new GridLayout(2,1));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e->{
            this.setVisible(false);
            prevFrame.setVisible(true);
        });
        JPanel backContainerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backContainerPanel.add(backButton);
        topPanel.add(backContainerPanel);
        topPanel.add(controlsPanel);
        this.add(topPanel,BorderLayout.NORTH);
        RegistrationForm.center(this, studentRenderer.comp, BorderLayout.CENTER);
    }
}



public class RegistrationForm{
    public static void main(String[] args) throws SQLException{

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch(ClassNotFoundException error){
            error.printStackTrace();
        }

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/registration","mdhe","hojlund");
        JFrame mainFrame = new JFrame("Main Frame");
        JFrame registerFrame = new Register(conn,mainFrame);
        JFrame viewFrame = new View(conn,mainFrame);
        mainFrame.setSize(1000, 1000);
        registerFrame.setSize(1000, 1000);
        viewFrame.setSize(1000, 1000);

        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e->{
            registerFrame.setVisible(true);
            mainFrame.setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        JButton viewBtn = new JButton("View");
        viewBtn.addActionListener(e->{
            viewFrame.setVisible(true);
            mainFrame.setVisible(false);
        });

        buttonPanel.add(viewBtn);
        buttonPanel.add(registerBtn);
        center(mainFrame, buttonPanel);
        center(mainFrame, new JLabel("Student Info") ,BorderLayout.NORTH);
        mainFrame.setVisible(true);
    }

    static void center(Container parent,Component child){
        JPanel centerer = new JPanel(new GridBagLayout());
        centerer.add(child,new GridBagConstraints());
        parent.add(centerer);
    }

    static void center(Container parent,Component child,String Layout){
        JPanel centerer = new JPanel(new GridBagLayout());
        centerer.add(child,new GridBagConstraints());
        parent.add(centerer,Layout);
    }

    
}