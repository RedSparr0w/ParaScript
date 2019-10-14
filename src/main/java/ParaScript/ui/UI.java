package ParaScript.ui;

import ParaScript.data.variables.Trees;
import ParaScript.data.Variables;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    private final ButtonGroup woodcutOptionButtonGroup = new ButtonGroup();
    private JPanel contentPane;
    // Login tab
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JCheckBox autoLogin = new JCheckBox();
    // Settings
    private JComboBox skillSelect = new JComboBox();
    private JRadioButton bank = new JRadioButton("Bank");
    private JRadioButton drop = new JRadioButton("Drop");
    // Woodcutting
    private JComboBox treeSelect = new JComboBox();
    private JComboBox location = new JComboBox();
    private JCheckBox birdsNest = new JCheckBox();
    // Our colors
    private Color Color_MidnightBlue = new Color(44, 62, 80);
    private Color Color_WetAsphalt = new Color(52, 73, 94);
    private Color Color_WhiteSmoke = new Color(245, 245, 245);
    private Color Color_Emerald = new Color(46, 204, 113);
    private Color Color_Alizarin = new Color(231, 76, 60);

    public UI() {
        setTitle("src/ParaScript");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setForeground(Color_WhiteSmoke);
        contentPane.setBackground(Color_MidnightBlue);
        setContentPane(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 395, 215);
        contentPane.add(tabbedPane);

        /*
         * Login Panel
         */
        JPanel loginPanel = new JPanel();
        loginPanel.setForeground(Color_WhiteSmoke);
        loginPanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Login", null, loginPanel, null);
        loginPanel.setLayout(null);

        // Username
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color_WhiteSmoke);
        lblUsername.setBounds(20, 20, 73, 20);
        loginPanel.add(lblUsername);
        username.setText(Game.isLoggedIn() ? Players.getMyPlayer().getName() : "");
        username.setBounds(20, 40, 150, 20);
        loginPanel.add(username);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color_WhiteSmoke);
        lblPassword.setBounds(200, 20, 73, 20);
        loginPanel.add(lblPassword);
        password.setBounds(200, 40, 150, 20);
        loginPanel.add(password);

        // Auto Login
        JLabel lblAutoLogin = new JLabel("Auto Login Enabled");
        lblAutoLogin.setForeground(Color_WhiteSmoke);
        lblAutoLogin.setBounds(40, 80, 130, 20);
        loginPanel.add(lblAutoLogin);
        autoLogin.setBackground(Color_WetAsphalt);
        autoLogin.setForeground(Color_WhiteSmoke);
        autoLogin.setBounds(15, 80, 20, 20);
        autoLogin.setSelected(true);
        loginPanel.add(autoLogin);

        /*
         * Settings Panel
         */
        JPanel settingsPanel = new JPanel();
        settingsPanel.setForeground(Color_WhiteSmoke);
        settingsPanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Settings", null, settingsPanel, null);
        settingsPanel.setLayout(null);

        // Which skill are we training
        JLabel lblSkillToTrain = new JLabel("Skill to train");
        lblSkillToTrain.setForeground(Color_WhiteSmoke);
        lblSkillToTrain.setBounds(20, 20, 73, 20);
        settingsPanel.add(lblSkillToTrain);
        skillSelect.setModel(new DefaultComboBoxModel(new String[]{
                "Woodcutting",
                "Mining",
                "Bank Runner",
        }));
        skillSelect.setBounds(20, 40, 150, 20);
        settingsPanel.add(skillSelect);
        skillSelect.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Variables.skill_to_train = skillSelect.getSelectedItem().toString();
            }
        });

        /*
         * Woodcutting Panel
         */

        JPanel woodcuttingPanel = new JPanel();
        woodcuttingPanel.setForeground(Color_WhiteSmoke);
        woodcuttingPanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Woodcutting", null, woodcuttingPanel, null);
        woodcuttingPanel.setLayout(null);

        // Select which tree to cut
        JLabel lblTree = new JLabel("Tree");
        lblTree.setForeground(Color_WhiteSmoke);
        lblTree.setBounds(20, 20, 73, 20);
        woodcuttingPanel.add(lblTree);
        treeSelect.setModel(new DefaultComboBoxModel(Trees.toStringArray()));
        treeSelect.setBounds(20, 40, 150, 20);
        woodcuttingPanel.add(treeSelect);

        /*
        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(200, 20, 73, 20);
        woodcuttingPanel.add(lblLocation);

        location.setModel(new DefaultComboBoxModel(Methods.locationToStringArray()));
        location.setBounds(200, 40, 150, 20);
        woodcuttingPanel.add(location);

        JLabel lblMethod = new JLabel("Method");
        lblMethod.setBounds(20, 120, 73, 20);
        woodcuttingPanel.add(lblMethod);

        woodcutOptionButtonGroup.add(bank);
        bank.setSelected(true);
        bank.setBounds(20, 140, 80, 20);
        woodcuttingPanel.add(bank);

        woodcutOptionButtonGroup.add(drop);
        drop.setBounds(20, 160, 80, 20);
        woodcuttingPanel.add(drop);

        JLabel lblBirdsNest = new JLabel("Bird nests");
        lblBirdsNest.setForeground(WhiteSmoke);
        lblBirdsNest.setBounds(200, 120, 150, 20);
        woodcuttingPanel.add(lblBirdsNest);

        birdsNest.setBounds(195, 140, 20, 20);
        birdsNest.setSelected(true);
        woodcuttingPanel.add(birdsNest);

        JLabel lblBirdsNestCheckBox = new JLabel("Pickup");
        lblBirdsNestCheckBox.setBounds(215, 140, 150, 20);
        woodcuttingPanel.add(lblBirdsNestCheckBox);

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

        /*
         * Slave Panel
         */
        JPanel slavePanel = new JPanel();
        slavePanel.setForeground(Color_WhiteSmoke);
        slavePanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Bank Runner", null, slavePanel, null);
        slavePanel.setLayout(null);

        // Which skill are we training
        JLabel lblSlaveMaster = new JLabel("Slave Master");
        lblSlaveMaster.setForeground(Color_WhiteSmoke);
        lblSlaveMaster.setBounds(20, 20, 73, 20);
        slavePanel.add(lblSlaveMaster);
        JTextField slaveMaster = new JTextField();
        slaveMaster.setBounds(20, 40, 150, 20);
        slavePanel.add(slaveMaster);
        slaveMaster.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Variables.slaveMaster = slaveMaster.getText();
            }
        });

        JButton start = new JButton("START");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(autoLogin.isSelected() && !username.getText().equals("")) {
                    Variables.setAccountUsername(username.getText());
                    Variables.setAccountPassword(password.getText());
                }
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
                start.setText(Variables.running ? "PAUSE" : "START");
                start.setBackground(Variables.running ? Color_Alizarin : Color_Emerald);
            }
        });
        start.setBackground(Color_Emerald);
        start.setForeground(Color_MidnightBlue);
        // these next two lines do the magic..
        start.setContentAreaFilled(false);
        start.setOpaque(true);
        start.setBounds(20, 220, 360, 40);
        contentPane.add(start);
    }
}