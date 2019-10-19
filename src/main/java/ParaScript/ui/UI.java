package ParaScript.ui;

import ParaScript.data.variables.*;
import ParaScript.data.Variables;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Skill;
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

    // Woodcutting
    private JComboBox treeSelect = new JComboBox();
    private JComboBox woodcuttingMethod = new JComboBox();
    private JComboBox location = new JComboBox();
    private JCheckBox birdsNest = new JCheckBox();

    // Mining
    private JComboBox oreSelect = new JComboBox();
    private JComboBox miningMethod = new JComboBox();

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
                Skill.WOODCUTTING.getName(),
                Skill.MINING.getName(),
                "Bank Runner",
        }));
        skillSelect.setBounds(20, 40, 150, 20);
        skillSelect.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for (Skill skill : Skill.values()) {
                    if (skill.getName().equalsIgnoreCase(skillSelect.getSelectedItem().toString())) {
                        Variables.skill_to_train = skill;
                        return;
                    }
                    Variables.skill_to_train = null;
                }
            }
        });
        settingsPanel.add(skillSelect);

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
        lblTree.setBounds(20, 20, 150, 20);
        woodcuttingPanel.add(lblTree);
        treeSelect.setModel(new DefaultComboBoxModel(Trees.toStringArray()));
        treeSelect.setBounds(20, 40, 150, 20);
        treeSelect.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for (Trees tree : Trees.values()) {
                    if (tree.getName().equalsIgnoreCase(treeSelect.getSelectedItem().toString())) {
                        Variables.woodcutting_tree_selected = tree;
                    }
                }
            }
        });
        woodcuttingPanel.add(treeSelect);

        // What should we do with our logs
        JLabel lblWoodcuttingMethod = new JLabel("Method");
        lblWoodcuttingMethod.setForeground(Color_WhiteSmoke);
        lblWoodcuttingMethod.setBounds(20, 60, 150, 20);
        woodcuttingPanel.add(lblWoodcuttingMethod);
        woodcuttingMethod.setModel(new DefaultComboBoxModel(new String[]{
                "Fletch",
                "Bank",
                "Drop",
        }));
        woodcuttingMethod.setBounds(20, 80, 150, 20);
        woodcuttingMethod.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Variables.woodcutting_method = woodcuttingMethod.getSelectedItem().toString();
            }
        });
        woodcuttingPanel.add(woodcuttingMethod);

        /*
        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(200, 20, 73, 20);
        woodcuttingPanel.add(lblLocation);

        location.setModel(new DefaultComboBoxModel(Methods.locationToStringArray()));
        location.setBounds(200, 40, 150, 20);
        woodcuttingPanel.add(location);


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
         * Mining Panel
         */

        JPanel miningPanel = new JPanel();
        miningPanel.setForeground(Color_WhiteSmoke);
        miningPanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Mining", null, miningPanel, null);
        miningPanel.setLayout(null);

        // Select which ore to mine
        JLabel lblOre = new JLabel("Ore");
        lblOre.setForeground(Color_WhiteSmoke);
        lblOre.setBounds(20, 20, 73, 20);
        miningPanel.add(lblOre);
        oreSelect.setModel(new DefaultComboBoxModel(Ores.toStringArray()));
        oreSelect.setBounds(20, 40, 150, 20);
        oreSelect.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for (Ores ore : Ores.values()) {
                    if (ore.getName().equalsIgnoreCase(oreSelect.getSelectedItem().toString())) {
                        Variables.mining_ore_selected = ore;
                    }
                }
            }
        });
        miningPanel.add(oreSelect);

        // What should we do with our ores
        JLabel lblMiningMethod = new JLabel("Method");
        lblMiningMethod.setForeground(Color_WhiteSmoke);
        lblMiningMethod.setBounds(20, 60, 150, 20);
        miningPanel.add(lblMiningMethod);
        miningMethod.setModel(new DefaultComboBoxModel(new String[]{
                "Bank",
                "Drop",
        }));
        miningMethod.setBounds(20, 80, 150, 20);
        miningMethod.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Variables.mining_method = miningMethod.getSelectedItem().toString();
            }
        });
        miningPanel.add(miningMethod);

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