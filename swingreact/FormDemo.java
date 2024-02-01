
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import react.Reactive;

class Region {
    public int id;
    public String name;

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

}

class RowDetails {
    enum InputType {
        STRING,
        NUMBER,
        PASSWORD
    }

    public String name;
    public String label;
    public String value;
    public InputType type;

    public RowDetails(String name, String label, String value, InputType type) {
        this.name = name;
        this.label = label;
        this.value = value;
        this.type = type;
    }
}

class RFormRow extends Reactive<JPanel, RowDetails> {
    RowDetails details;

    public RFormRow(RowDetails details) {
        super(new JPanel(new GridLayout(1, 2)), details, (comp, hook) -> {
        }, (comp) -> {
            comp.removeAll();
        });
        setRenderable((comp, hook) -> {
            comp.add(new JLabel(hook.label));

            JTextField textField;
            switch (details.type) {
                case NUMBER:
                    textField = new JFormattedTextField(NumberFormat.getIntegerInstance());
                    break;
                case PASSWORD:
                    textField = new JPasswordField(details.value);
                    break;
                default:
                    textField = new JTextField(details.value);
                    break;
            }
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent arg0) {
                    updateValues(arg0);
                }

                @Override
                public void insertUpdate(DocumentEvent arg0) {
                    updateValues(arg0);

                }

                @Override
                public void removeUpdate(DocumentEvent arg0) {
                    updateValues(arg0);
                }

                void updateValues(DocumentEvent e) {
                    int length = e.getDocument().getLength();
                    try {
                        if (length >= 0) {
                            String text = e.getDocument().getText(0, length);
                            details.value = text;
                        }
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            comp.add(textField);
        });
        this.details = details;
    }

}

class RForm extends Reactive<JPanel, ArrayList<RFormRow>> {
    RForm(ActionListener onSubmit, ArrayList<RFormRow> rows) {
        super(new JPanel(), rows, (comp, hook) -> {
            comp.setLayout(new GridLayout(hook.size() + 1, 1));
            hook.forEach(h -> {
                comp.add(h.comp);
            });
            JButton subButton = new JButton("Submit");
            subButton.addActionListener(onSubmit);
            comp.add(subButton);
        }, (comp) -> {
            comp.removeAll();
        });
    }

}

public class FormDemo {
    public static void main(String[] args) throws SQLException {
        String username = "mdhe";
        String password = "hojlund";
        String host = "jdbc:mariadb://localhost:3306/company";
        Connection conn = DriverManager.getConnection(host, username, password);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM regions");
        ArrayList<Region> info = new ArrayList<>();
        while (rs.next()) {
            info.add(new Region(Integer.parseInt(rs.getString("REGION_ID")), rs.getString("REGION_NAME")));
        }
        Reactive<JPanel, ArrayList<Region>> innerInfoReactive = new Reactive<>(new JPanel(), info, (comp, hook) -> {
            comp.setLayout(new GridLayout(hook.size() + 1, 2));
            comp.add(new JLabel("Region id"));
            comp.add(new JLabel("Region Name"));
            hook.forEach(h -> {
                comp.add(new JLabel(String.valueOf(h.id)));
                comp.add(new JLabel(h.name));
            });
        }, (comp) -> {
            comp.removeAll();
        });

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(new JLabel("REGIONS"), BorderLayout.NORTH);
        infoPanel.add(innerInfoReactive.comp, BorderLayout.CENTER);

        JFrame frame = new JFrame("Hello");
        frame.setSize(1000, 1000);
        RFormRow row1 = new RFormRow(new RowDetails("region_id", "Region ID", "0", RowDetails.InputType.NUMBER));
        RFormRow row2 = new RFormRow(
                new RowDetails("region_name", "Region Name", "Name", RowDetails.InputType.STRING));
        ArrayList<RFormRow> rows = new ArrayList<>(Arrays.asList(row1, row2));
        RForm form = new RForm(e -> {
            rows.forEach(row -> {
                System.out.println(row.details.name + " = " + row.details.value);
            });
            try {
                String q = "INSERT INTO regions VALUES('" + rows.get(0).details.value + "','"
                        + rows.get(1).details.value + "')";
                System.out.println(q);
                st.executeQuery(q);
                ResultSet r = st.executeQuery("SELECT * FROM regions");
                info.clear();
                while (r.next()) {
                    info.add(new Region(Integer.parseInt(r.getString("REGION_ID")), r.getString("REGION_NAME")));
                }
                innerInfoReactive.setHook(info);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }, rows);
        frame.add(new JLabel("REGIONS"), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(form.comp, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.SOUTH);
        frame.add(new JPanel(), BorderLayout.WEST);
        frame.add(new JPanel(), BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
