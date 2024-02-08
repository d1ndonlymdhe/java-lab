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
import java.util.List;

import java.awt.FlowLayout;
class FormInput extends JPanel {
    public FormInput(String labelString, Component comp) {
        super(new GridLayout(1, 2));
        this.add(new JLabel(labelString));
        this.add(comp);
    }
}

class Student {
    public String id;
    public String name;
    public String gender;
    public List<String> courses;
    public String department;

    public Student(String id, String name, String gender, List<String> courses, String department) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.courses = courses;
        this.department = department;
    }
}

class Register extends JFrame {
    public Register(JFrame prevFrame, ArrayList<Student> students) {
        super("Register Frame");
        JPanel container = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 1));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.setVisible(false);
            prevFrame.setVisible(true);
        });
        topPanel.add(backButton);

        JPanel genderPanel = new JPanel(new GridLayout(2, 1));
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
        submitBtn.addActionListener(e -> {

            String name = studentInput.getText();
            String id = idInput.getText();
            String gender = genderGroup.getSelection().getActionCommand();
            ArrayList<String> courses = new ArrayList<>();
            String department = (String) departmentComboBox.getSelectedItem();

            if (physicsCheckBox.isSelected()) {
                courses.add("Physics");
            }
            if (dsaCheckBox.isSelected()) {
                courses.add("DSA");
            }
            if (javaCheckBox.isSelected()) {
                courses.add("Java");
            }
            // print all info
            System.out.println("Name: " + name);
            System.out.println("Id: " + id);
            System.out.println("Gender " + gender);
            System.out.println("Courses: " + courses);
            System.out.println("Department: " + department);

            students.add(new Student(id, name, gender, courses, department));

        });

        RegistrationForm.center(formPanel, submitBtn);
        this.add(topPanel, BorderLayout.NORTH);
        container.add(formPanel,BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
    }

}

class View extends JFrame {
    private int studentIdx;
    private Student student;

    public View(JFrame prevFrame, List<Student> students) {
        super("View Frame");
        studentIdx = 0;
        student = null;

        Reactive<JPanel, Student> studentRenderer = new Reactive<JPanel, Student>(new JPanel(), null, (comp, hook) -> {
            if (student != null) {
                comp.setLayout(new GridLayout(5, 1, 10, 20));
                comp.add(new JLabel("Name: " + student.name));
                comp.add(new JLabel("Id: " + student.id));
                comp.add(new JLabel("Gender: " + student.gender));
                comp.add(new JLabel("Courses: " + student.courses));
                comp.add(new JLabel("Department: " + student.department));
            } else {
                comp.add(new JLabel("No Students"));
            }
        }, JPanel::removeAll);

        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        Reactive<JLabel, String> countLabel = new Reactive<JLabel, String>(new JLabel(), "", JLabel::setText, comp -> {
        });

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            if (students.size() > 0) {
                student = students.get(studentIdx);
                studentRenderer.setHook(student);
                countLabel.setHook("Student " + (studentIdx + 1) + " of " + students.size());
            } else {
                studentRenderer.setHook(null);
                countLabel.setHook("Student " + 0 + " of " + students.size());
            }
        });

        JButton prevButton = new JButton("Prev");
        prevButton.addActionListener(e -> {
            if (studentIdx > 0) {
                studentIdx--;
                student = students.get(studentIdx);
                studentRenderer.setHook(student);
                countLabel.setHook("Student " + (studentIdx + 1) + " of " + students.size());
            }
        });

        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(e -> {
            if (studentIdx < students.size() - 1) {
                studentIdx++;
                student = students.get(studentIdx);
                studentRenderer.setHook(student);
                countLabel.setHook("Student " + (studentIdx + 1) + " of " + students.size());
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            if (students.size() > 0) {
                students.remove(studentIdx);
                if (studentIdx > 0) {
                    studentIdx--;
                    student = students.get(studentIdx);
                    studentRenderer.setHook(student);
                    countLabel.setHook("Student " + (studentIdx + 1) + " of " + students.size());
                } else if (studentIdx == 0) {
                    student = students.get(studentIdx);
                    studentRenderer.setHook(student);
                    countLabel.setHook("Student " + (studentIdx + 1) + " of " + students.size());
                } else {
                    student = null;
                    studentRenderer.setHook(null);
                    countLabel.setHook("Student " + 0 + " of " + students.size());
                }
            }
        });

        controlsPanel.add(refreshButton);
        controlsPanel.add(prevButton);
        controlsPanel.add(countLabel.comp);
        controlsPanel.add(nextBtn);
        controlsPanel.add(deleteButton);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.setVisible(false);
            prevFrame.setVisible(true);
        });
        JPanel backContainerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backContainerPanel.add(backButton);
        topPanel.add(backContainerPanel);
        topPanel.add(controlsPanel);
        this.add(topPanel, BorderLayout.NORTH);
        RegistrationForm.center(this, studentRenderer.comp, BorderLayout.CENTER);
    }
}

public class RegistrationForm {
    public static void main(String[] args){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("1", "ABCD", "Male", List.of("Physics","Chemistry"), "Science"));
        students.add(new Student("2", "QWER", "FEMALE", List.of("Physics","Chemistry"), "Software"));
        students.add(new Student("3", "IOUIOU", "Male", List.of("Physics"), "Science"));

        JFrame mainFrame = new JFrame("Main Frame");
        JFrame registerFrame = new Register(mainFrame, students);
        JFrame viewFrame = new View(mainFrame, students);
        mainFrame.setSize(1000, 1000);
        registerFrame.setSize(1000, 1000);
        viewFrame.setSize(1000, 1000);

        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> {
            registerFrame.setVisible(true);
            mainFrame.setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        JButton viewBtn = new JButton("View");
        viewBtn.addActionListener(e -> {
            viewFrame.setVisible(true);
            mainFrame.setVisible(false);
        });

        buttonPanel.add(viewBtn);
        buttonPanel.add(registerBtn);
        center(mainFrame, buttonPanel);
        center(mainFrame, new JLabel("Student Info"), BorderLayout.NORTH);
        mainFrame.setVisible(true);
    }

    static void center(Container parent, Component child) {
        JPanel centerer = new JPanel(new GridBagLayout());
        centerer.add(child, new GridBagConstraints());
        parent.add(centerer);
    }

    static void center(Container parent, Component child, String Layout) {
        JPanel centerer = new JPanel(new GridBagLayout());
        centerer.add(child, new GridBagConstraints());
        parent.add(centerer, Layout);
    }

}