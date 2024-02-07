import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

enum FRAMES{
    MAIN,
    REGISTER,
    VIEW
}



class FormInput extends JPanel {
    public FormInput(String labelString,Component comp){
        super(new GridLayout(1,2));
        RegistrationForm.center(this,new JLabel(labelString));
        this.add(comp);
    }
}


class Register extends JFrame {
    public Register(){
        super("Register Frame");
        JPanel formPanel = new JPanel(new GridLayout(6,1));

        RegistrationForm.center(formPanel, new FormInput("Student Id", new JTextField()));

        JPanel genderPanel = new JPanel(new GridLayout(2,1));
        ButtonGroup genderGroup = new ButtonGroup();

        JRadioButton maleRadioButton = new JRadioButton("Male");
        genderGroup.add(maleRadioButton);

        JRadioButton femaleRadioButton = new JRadioButton("Female");
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

        RegistrationForm.center(formPanel, new FormInput("Student Name", new JTextField()));
        RegistrationForm.center(formPanel, new FormInput("Gender", genderPanel));
        RegistrationForm.center(formPanel, new FormInput("Courses", coursePanel));
        RegistrationForm.center(formPanel, new FormInput("Department", departmentComboBox));
        RegistrationForm.center(formPanel, new JButton("Submit"));
    
        this.add(formPanel,BorderLayout.NORTH);
    }

}



public class RegistrationForm{
    public static void main(String[] args) {
        
        JFrame mainFrame = new JFrame("Main Frame");
        JFrame registerFrame = new Register();
        JFrame viewFrame = new JFrame("View Frame");
        mainFrame.setSize(1000, 1000);
        registerFrame.setSize(1000, 1000);
        viewFrame.setSize(1000, 1000);

        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e->{
            registerFrame.setVisible(true);
            mainFrame.setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("View"));
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