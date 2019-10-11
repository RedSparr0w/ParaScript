package ParaScript.ui;

import ParaScript.data.variables.Trees;
import ParaScript.data.Variables;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    private final ButtonGroup woodcutOptionButtonGroup = new ButtonGroup();
    private JPanel contentPane;
    private JComboBox location = new JComboBox();
    private JComboBox treeSelect = new JComboBox();
    private JRadioButton bank = new JRadioButton("Bank");
    private JRadioButton drop = new JRadioButton("Drop");
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JCheckBox birdsNest = new JCheckBox();

    public UI() {
        setTitle("AIO Woodcutter");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 280);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 395, 220);
        contentPane.add(tabbedPane);

        JPanel fletchPanel = new JPanel();
        tabbedPane.addTab("Woodcutting", null, fletchPanel, null);
        fletchPanel.setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(20, 20, 73, 20);
        fletchPanel.add(lblUsername);
        username.setBounds(20, 40, 150, 20);
        fletchPanel.add(username);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(20, 60, 73, 20);
        fletchPanel.add(lblPassword);
        password.setBounds(20, 80, 150, 20);
        fletchPanel.add(password);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(200, 20, 73, 20);
        fletchPanel.add(lblLocation);

        /*
        location.setModel(
                new DefaultComboBoxModel(Methods.locationToStringArray()));
        location.setBounds(200, 40, 150, 20);
        fletchPanel.add(location);
        */

        JLabel lblTree = new JLabel("Tree");
        lblTree.setBounds(200, 60, 73, 20);
        fletchPanel.add(lblTree);

        treeSelect.setModel(new DefaultComboBoxModel(Trees.toStringArray()));
        treeSelect.setBounds(200, 80, 150, 20);
        fletchPanel.add(treeSelect);

        JLabel lblMethod = new JLabel("Method");
        lblMethod.setBounds(20, 120, 73, 20);
        fletchPanel.add(lblMethod);

        /*
        woodcutOptionButtonGroup.add(bank);
        bank.setSelected(true);
        bank.setBounds(20, 140, 80, 20);
        fletchPanel.add(bank);

        woodcutOptionButtonGroup.add(drop);
        drop.setBounds(20, 160, 80, 20);
        fletchPanel.add(drop);

        JLabel lblBirdsNest = new JLabel("Bird nests");
        lblBirdsNest.setBounds(200, 120, 150, 20);
        fletchPanel.add(lblBirdsNest);

        birdsNest.setBounds(195, 140, 20, 20);
        birdsNest.setSelected(true);
        fletchPanel.add(birdsNest);

        JLabel lblBirdsNestCheckBox = new JLabel("Pickup");
        lblBirdsNestCheckBox.setBounds(215, 140, 150, 20);
        fletchPanel.add(lblBirdsNestCheckBox);

        location.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for (Location loc : Location.values()) {
                    if (loc.getName().equalsIgnoreCase(location.getSelectedItem().toString())) {
                        treeSelect.setModel(
                                new DefaultComboBoxModel(Methods.treeToStringArray(loc)));
                    }
                }
            }
        });
        */

        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                /*
                for (Location loc : Location.values()) {
                    if (loc.getName().equalsIgnoreCase(location.getSelectedItem().toString())) {
                        Variables.setLocation(loc);
                    }
                }
                for (Tree selectedTree : Tree.values()) {
                    if (selectedTree.getName().equalsIgnoreCase(treeSelect.getSelectedItem().toString())) {
                        Variables.setTree(selectedTree);
                        System.out.println(selectedTree.getName());
                    }
                }
                if(!password.getText().equals("") && !username.getText().equals("")) {
                    Variables.setAccountUsername(username.getText());
                    Variables.setAccountPassword(password.getText());
                }
                if (drop.isSelected()) {
                    Variables.setDrop(true);
                }
                if (bank.isSelected()) {
                    Variables.setBanking(true);
                }
                if (birdsNest.isSelected()) {
                    Variables.setPickupBirdNests(true);
                }
                dispose();
                */
                Variables.running = !Variables.running;
                start.setText(Variables.running ? "pause" : "start");
            }
        });
        start.setBounds(0, 220, 400, 20);
        contentPane.add(start);
    }
}