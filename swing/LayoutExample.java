import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;

class MyMenuItem {
    private Component menuItem;

    public Component getMenuItem() {
        return menuItem;
    }

    private boolean isSeperator;

    public boolean isSeperator() {
        return isSeperator;
    }

    private MyMenuItem(String value) {
        this.menuItem = new JMenuItem(value);
        this.isSeperator = false;
    }

    private MyMenuItem(JMenu subMenu) {
        this.menuItem = subMenu;
        this.isSeperator = false;
    }

    private MyMenuItem() {
        this.menuItem = null;
        isSeperator = true;
    }

    static MyMenuItem createSeperator(){
        return new MyMenuItem();
    }
    static MyMenuItem createMenuItem(String value){
        return new MyMenuItem(value);
    }
    static MyMenuItem createSubMenu(MyMenu subMenu){
        return new MyMenuItem(subMenu.getMenu());
    }

}

class MyMenu {
    private JMenu menu;

    public MyMenu(String title) {
        menu = new JMenu(title);
    }

    public JMenu getMenu() {
        return menu;
    }

    public MyMenu addMenuItem(MyMenuItem menuItem) {
        if (menuItem.isSeperator()) {
            menu.addSeparator();
        } else {
            menu.add(menuItem.getMenuItem());
        }
        return this;
    }
}

class MyMenuBar {
    private JMenuBar menuBar;

    public MyMenuBar() {
        menuBar = new JMenuBar();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public MyMenuBar addMenu(MyMenu menu) {
        menuBar.add(menu.getMenu());
        return this;
    }
}

class LayoutExample {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Layout Example");
        mainFrame.setSize(1000, 1000);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new Button("Home"));
        topPanel.add(new Button("About"));

        JPanel centerPanel = new JPanel(new GridLayout(3, 2));

        String[] formLabels = { "Name", "Roll", "Address" };
        for (int i = 0; i < 3; i++) {
            centerPanel.add(new JLabel(formLabels[i]));
            centerPanel.add(new TextField());
        }
        
        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(new Button("Left Panel"), BorderLayout.WEST);
        mainFrame.add(centerPanel);

        MyMenuBar menuBar = new MyMenuBar();
        menuBar
        .addMenu(
            new MyMenu("File")
                .addMenuItem(MyMenuItem.createMenuItem("Save"))
                .addMenuItem(MyMenuItem.createMenuItem("Save As"))
                .addMenuItem(MyMenuItem.createSeperator())
                .addMenuItem(MyMenuItem.createMenuItem("Exit"))
        )
        .addMenu(
            new MyMenu("Edit")
                .addMenuItem(MyMenuItem.createSubMenu(
                    new MyMenu("Format")
                        .addMenuItem(MyMenuItem.createMenuItem("Bold"))
                        .addMenuItem(MyMenuItem.createMenuItem("Italics"))
                        .addMenuItem(MyMenuItem.createMenuItem("Underline"))
                ))
        );
        mainFrame.setJMenuBar(menuBar.getMenuBar());
        // mainFrame.setJMenuBar(createMenuBar(new ArrayList<>(
        //         List.of(
        //                 createMenu("File", new ArrayList<>(List.of(
        //                         createMenuItem("Save"),
        //                         createMenuItem("Save As"),
        //                         createMenuSeperator(),
        //                         createMenuItem("Exit")))),
        //                 createMenu("Edit", new ArrayList<>(List.of(
        //                         createNestedMenuItem(
        //                                 createMenu("Format", new ArrayList<>(List.of(
        //                                         createMenuItem("Bold"),
        //                                         createMenuItem("Italics"),
        //                                         createMenuItem("Underline"))))))))))));
        mainFrame.setVisible(true);
    }

    // static MyMenuItem createMenuItem(String value) {
    //     return new MyMenuItem(value);
    // }

    // static MyMenuItem createMenuSeperator() {
    //     return new MyMenuItem();
    // }

    // static MyMenuItem createNestedMenuItem(JMenu subMenu) {
    //     return new MyMenuItem(subMenu);
    // }

    // static JMenu createMenu(String menuName, ArrayList<MyMenuItem> menuItems) {
    //     JMenu menu = new JMenu(menuName);
    //     for (int i = 0; i < menuItems.size(); i++) {
    //         if (menuItems.get(i).isSeperator()) {
    //             menu.addSeparator();
    //         } else {
    //             menu.add(menuItems.get(i).getMenuItem());
    //         }
    //     }
    //     return menu;
    // }

    // static JMenuBar createMenuBar(ArrayList<JMenu> menus) {
    //     JMenuBar menuBar = new JMenuBar();
    //     for (int i = 0; i < menus.size(); i++) {
    //         menuBar.add(menus.get(i));
    //     }
    //     return menuBar;
    // }
}