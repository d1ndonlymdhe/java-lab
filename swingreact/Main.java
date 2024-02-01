import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import react.Reactive;

public class Main {
    public static void main(String[] args) {

        Reactive<JPanel, ArrayList<String>> RJpanel = new Reactive<>(new JPanel(), new ArrayList<>(),
                (comp, children) -> {
                    comp.setLayout(new GridLayout((int) Math.ceil(children.size() / 2.0), 2));
                    for (int i = 0; i < children.size(); i++) {
                        comp.add(new JLabel(children.get(i)));
                    }
                },
                (comp) -> {
                    comp.removeAll();
                }
        );
        JFrame frame = new JFrame("Hello");
        frame.setSize(1000, 1000);

        ArrayList<String> nums = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            nums.add(String.valueOf(i));
        }

        RJpanel.setHook(nums);
        
        frame.add(RJpanel.comp, BorderLayout.NORTH);

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(e -> {
            nums.add(String.valueOf(Integer.parseInt(nums.get(nums.size() - 1)) + 1));
            RJpanel.setHook(nums);
        });
        frame.add(addBtn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
