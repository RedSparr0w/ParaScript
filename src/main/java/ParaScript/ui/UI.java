package ParaScript.ui;

import ParaScript.data.variables.*;
import ParaScript.data.Variables;
import ParaScript.strategies.UpdateBank;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.methods.Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

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
    private JComboBox rockSelect = new JComboBox();
    private JComboBox miningMethod = new JComboBox();

    // Smithing
    private JComboBox barSelect = new JComboBox();

    // Fighting
    private JComboBox fightingNpcSelect = new JComboBox();
    private JLabel lblFightingNpcCustomID = new JLabel("Custom NPC IDs");
    private JTextField fightingNpcCustomID = new JTextField();
    private JCheckBox fightingBuryBones = new JCheckBox();
    private JCheckBox fightingLoadCannon = new JCheckBox();
    private JTextField returnPositionCoords = new JTextField();
    private JTextField fightingItemCustomID = new JTextField();
    private JTextField fightingMinimumHitpoints = new JTextField();
    private JTextField fightingFoodToEat = new JTextField();
    private JTextField fightingFoodHealsAmount = new JTextField();

    // Thieving
    private JComboBox thievingNpcSelect = new JComboBox();
    private JLabel lblThievingNpcCustomID = new JLabel("Custom NPC IDs");
    private JTextField thievingNpcCustomID = new JTextField();

    // Fishing
    private JComboBox fishingTypeSelect = new JComboBox();

    // Our colors
    private Color Color_MidnightBlue = new Color(44, 62, 80);
    private Color Color_WetAsphalt = new Color(52, 73, 94);
    private Color Color_WhiteSmoke = new Color(245, 245, 245);
    private Color Color_Emerald = new Color(46, 204, 113);
    private Color Color_Alizarin = new Color(231, 76, 60);
    private Color Color_BelizeHole = new Color(41, 128, 185);

    public UI() {
        setTitle("2006 AIO");
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
                Skill.ATTACK.getName(),
                Skill.WOODCUTTING.getName(),
                Skill.MINING.getName(),
                Skill.SMITHING.getName(),
                Skill.THIEVING.getName(),
                Skill.FISHING.getName(),
                Skill.CRAFTING.getName(),
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
                        Variables.woodcutting_tree_selected.reset();
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
        JLabel lblRock = new JLabel("Rock");
        lblRock.setForeground(Color_WhiteSmoke);
        lblRock.setBounds(20, 20, 73, 20);
        miningPanel.add(lblRock);
        rockSelect.setModel(new DefaultComboBoxModel(Rocks.toStringArray()));
        rockSelect.setBounds(20, 40, 150, 20);
        rockSelect.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for (Rocks rock : Rocks.values()) {
                    if (rock.getName().equalsIgnoreCase(rockSelect.getSelectedItem().toString())) {
                        Variables.mining_rock_selected = rock;
                        Variables.mining_rock_selected.reset();
                    }
                }
            }
        });
        miningPanel.add(rockSelect);

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
         * Smithing Panel
         */

        JPanel smithingPanel = new JPanel();
        smithingPanel.setForeground(Color_WhiteSmoke);
        smithingPanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Smithing", null, smithingPanel, null);
        smithingPanel.setLayout(null);

        // Select which ore to mine
        JLabel lblBar = new JLabel("Bar");
        lblBar.setForeground(Color_WhiteSmoke);
        lblBar.setBounds(20, 20, 73, 20);
        smithingPanel.add(lblBar);
        barSelect.setModel(new DefaultComboBoxModel(Bars.toStringArray()));
        barSelect.setBounds(20, 40, 150, 20);
        barSelect.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for (Bars bar : Bars.values()) {
                    if (bar.getName().equalsIgnoreCase(barSelect.getSelectedItem().toString())) {
                        Variables.smithing_bar_selected = bar;
                    }
                }
            }
        });
        smithingPanel.add(barSelect);

        /*
         * Fighting Panel
         */

        JPanel fightingPanel = new JPanel();
        fightingPanel.setForeground(Color_WhiteSmoke);
        fightingPanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Fighting", null, fightingPanel, null);
        fightingPanel.setLayout(null);

        // Select which npc should be our victim
        JLabel lblFightingNpc = new JLabel("NPC");
        lblFightingNpc.setForeground(Color_WhiteSmoke);
        lblFightingNpc.setBounds(20, 0, 73, 20);
        fightingPanel.add(lblFightingNpc);
        fightingNpcSelect.setModel(new DefaultComboBoxModel(FightingNpcs.toStringArray()));
        fightingNpcSelect.setBounds(20, 20, 150, 20);
        fightingNpcSelect.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for (FightingNpcs npc : FightingNpcs.values()) {
                    if (npc.getName().equalsIgnoreCase(fightingNpcSelect.getSelectedItem().toString())) {
                        Variables.fighting_npc_selected = npc;
                    }
                }
                if (Variables.fighting_npc_selected == FightingNpcs.CUSTOM) {
                    lblFightingNpcCustomID.setVisible(true);
                    fightingNpcCustomID.setVisible(true);
                } else {
                    lblFightingNpcCustomID.setVisible(false);
                    fightingNpcCustomID.setVisible(false);
                }
                UI.this.revalidate();
                UI.this.repaint();
            }
        });
        fightingPanel.add(fightingNpcSelect);

        // Custom npc id to attack
        lblFightingNpcCustomID.setForeground(Color_WhiteSmoke);
        lblFightingNpcCustomID.setBounds(200, 0, 150, 20);
        fightingPanel.add(lblFightingNpcCustomID);
        fightingNpcCustomID.setBounds(200, 20, 150, 20);
        fightingNpcCustomID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // we don't need to do anything here
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String[] sample = fightingNpcCustomID.getText().split("(,|;)\\s*");
                    int[] customIDs = new int[sample.length];

                    for (int i = 0; i < sample.length; i++)
                        customIDs[i] = Integer.parseInt(sample[i]);
                    FightingNpcs.CUSTOM.setIDs(customIDs);
                } catch (Exception ಠ_ಠ) {
                    FightingNpcs.CUSTOM.setIDs(new int[]{});
                }
            }
        });
        fightingPanel.add(fightingNpcCustomID);
        lblFightingNpcCustomID.setVisible(false);
        fightingNpcCustomID.setVisible(false);

        // Should we collect and bury our bones
        JLabel lblFightingLoadCannon = new JLabel("Load cannon");
        lblFightingLoadCannon.setForeground(Color_WhiteSmoke);
        lblFightingLoadCannon.setBounds(40, 40, 130, 20);
        fightingPanel.add(lblFightingLoadCannon);
        fightingLoadCannon.setBackground(Color_WetAsphalt);
        fightingLoadCannon.setForeground(Color_WhiteSmoke);
        fightingLoadCannon.setBounds(15, 40, 20, 20);
        fightingLoadCannon.setSelected(false);
        fightingLoadCannon.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Variables.load_cannon = fightingLoadCannon.isSelected();
            }
        });
        fightingPanel.add(fightingLoadCannon);

        // Should we collect and bury our bones
        JLabel lblFightingBuryBones = new JLabel("Collect and bury bones");
        lblFightingBuryBones.setForeground(Color_WhiteSmoke);
        lblFightingBuryBones.setBounds(40, 60, 130, 20);
        fightingPanel.add(lblFightingBuryBones);
        fightingBuryBones.setBackground(Color_WetAsphalt);
        fightingBuryBones.setForeground(Color_WhiteSmoke);
        fightingBuryBones.setBounds(15, 60, 20, 20);
        fightingBuryBones.setSelected(true);
        fightingBuryBones.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Variables.fighting_bury_bones = fightingBuryBones.isSelected();
            }
        });
        fightingPanel.add(fightingBuryBones);

        // Should we hold a position (aggressive npcs) return after collecting items
        JLabel lblReturnPosition = new JLabel("Return Position");
        lblReturnPosition.setForeground(Color_WhiteSmoke);
        lblReturnPosition.setBounds(200, 40, 150, 20);
        fightingPanel.add(lblReturnPosition);
        returnPositionCoords.setBounds(200, 60, 150, 20);
        returnPositionCoords.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // we don't need to do anything here
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String[] sample = returnPositionCoords.getText().split("(,|;)\\s*");
                    int[] coords = new int[sample.length];

                    for (int i = 0; i < sample.length; i++)
                        coords[i] = Integer.parseInt(sample[i]);
                    Variables.return_to_coords = coords;
                } catch (Exception ಠ_ಠ) {
                    Variables.return_to_coords = new int[]{};
                }
            }
        });
        fightingPanel.add(returnPositionCoords);

        // Custom items to pickup
        JLabel lblFightingItemCustomID = new JLabel("Pickup Item IDs");
        lblFightingItemCustomID.setForeground(Color_WhiteSmoke);
        lblFightingItemCustomID.setBounds(20, 80, 150, 20);
        fightingPanel.add(lblFightingItemCustomID);
        fightingItemCustomID.setBounds(20, 100, 150, 20);
        fightingItemCustomID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // we don't need to do anything here
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String[] sample = fightingItemCustomID.getText().split("(,|;)\\s*");
                    int[] customIDs = new int[sample.length];

                    for (int i = 0; i < sample.length; i++)
                        customIDs[i] = Integer.parseInt(sample[i]);
                    Variables.fighting_item_ids = customIDs;
                } catch (Exception ಠ_ಠ) {
                    Variables.fighting_item_ids = new int[]{};
                }
            }
        });
        fightingPanel.add(fightingItemCustomID);

        // Minimum hitpoints to start fighting
        JLabel lblFightingMinimumHitpoints = new JLabel("Minimum hitpoints to fight");
        lblFightingMinimumHitpoints.setForeground(Color_WhiteSmoke);
        lblFightingMinimumHitpoints.setBounds(200, 80, 150, 20);
        fightingPanel.add(lblFightingMinimumHitpoints);
        fightingMinimumHitpoints.setBounds(200, 100, 150, 20);
        fightingMinimumHitpoints.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // we don't need to do anything here
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Variables.fighting_minimum_hitpoints = Integer.parseInt(fightingMinimumHitpoints.getText());
                } catch (Exception ಠ_ಠ) {
                    Variables.fighting_minimum_hitpoints = -1;
                }
            }
        });
        fightingPanel.add(fightingMinimumHitpoints);

        // Food to eat when health is low
        JLabel lblFightingFoodToEat = new JLabel("Food ID to eat");
        lblFightingFoodToEat.setForeground(Color_WhiteSmoke);
        lblFightingFoodToEat.setBounds(20, 120, 150, 20);
        fightingPanel.add(lblFightingFoodToEat);
        fightingFoodToEat.setBounds(20, 140, 150, 20);
        fightingFoodToEat.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // we don't need to do anything here
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Variables.fighting_food_to_eat = Integer.parseInt(fightingFoodToEat.getText());
                } catch (Exception ಠ_ಠ) {
                    Variables.fighting_food_to_eat = -1;
                }
            }
        });
        fightingPanel.add(fightingFoodToEat);

        // How much the food heals for
        JLabel lblFightingFoodHealsAmount = new JLabel("Food heals amount");
        lblFightingFoodHealsAmount.setForeground(Color_WhiteSmoke);
        lblFightingFoodHealsAmount.setBounds(200, 120, 150, 20);
        fightingPanel.add(lblFightingFoodHealsAmount);
        fightingFoodHealsAmount.setBounds(200, 140, 150, 20);
        fightingFoodHealsAmount.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // we don't need to do anything here
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Variables.fighting_food_heals_amount = Integer.parseInt(fightingFoodHealsAmount.getText());
                } catch (Exception ಠ_ಠ) {
                    Variables.fighting_food_heals_amount = 20;
                }
            }
        });
        fightingPanel.add(fightingFoodHealsAmount);

        /*
         * Thieving Panel
         */

        JPanel thievingPanel = new JPanel();
        thievingPanel.setForeground(Color_WhiteSmoke);
        thievingPanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Thieving", null, thievingPanel, null);
        thievingPanel.setLayout(null);

        // Select which npc should be our victim
        JLabel lblThievingNpc = new JLabel("NPC");
        lblThievingNpc.setForeground(Color_WhiteSmoke);
        lblThievingNpc.setBounds(20, 20, 73, 20);
        thievingPanel.add(lblThievingNpc);
        thievingNpcSelect.setModel(new DefaultComboBoxModel(ThievingNpcs.toStringArray()));
        thievingNpcSelect.setBounds(20, 40, 150, 20);
        thievingNpcSelect.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for (ThievingNpcs npc : ThievingNpcs.values()) {
                    if (npc.getName().equalsIgnoreCase(thievingNpcSelect.getSelectedItem().toString())) {
                        Variables.thieving_npc_selected = npc;
                    }
                }
                if (Variables.thieving_npc_selected == ThievingNpcs.CUSTOM) {
                    lblThievingNpcCustomID.setVisible(true);
                    thievingNpcCustomID.setVisible(true);
                } else {
                    lblThievingNpcCustomID.setVisible(false);
                    thievingNpcCustomID.setVisible(false);
                }
                UI.this.revalidate();
                UI.this.repaint();
            }
        });
        thievingPanel.add(thievingNpcSelect);

        // Custom npc id to steal from
        lblThievingNpcCustomID.setForeground(Color_WhiteSmoke);
        lblThievingNpcCustomID.setBounds(200, 20, 150, 20);
        thievingPanel.add(lblThievingNpcCustomID);
        thievingNpcCustomID.setBounds(200, 40, 150, 20);
        thievingNpcCustomID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // we don't need to do anything here
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String[] sample = thievingNpcCustomID.getText().split("(,|;)\\s*");
                    int[] customIDs = new int[sample.length];

                    for (int i = 0; i < sample.length; i++)
                        customIDs[i] = Integer.parseInt(sample[i]);
                    Variables.thieving_npc_selected.setIDs(customIDs);
                } catch (Exception ಠ_ಠ) {
                    ThievingNpcs.CUSTOM.setIDs(new int[]{0});
                }
            }
        });
        thievingPanel.add(thievingNpcCustomID);
        lblThievingNpcCustomID.setVisible(false);
        thievingNpcCustomID.setVisible(false);

        /*
         * Fishing Panel
         */

        JPanel fishingPanel = new JPanel();
        fishingPanel.setForeground(Color_WhiteSmoke);
        fishingPanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Fishing", fishingPanel);
        fishingPanel.setLayout(null);

        // Select our fishing type
        JLabel lblFishingTypeSelect = new JLabel("Fishing Type");
        lblFishingTypeSelect.setForeground(Color_WhiteSmoke);
        lblFishingTypeSelect.setBounds(20, 20, 73, 20);
        fishingPanel.add(lblFishingTypeSelect);
        fishingTypeSelect.setModel(new DefaultComboBoxModel(FishingSpots.toStringArray()));
        fishingTypeSelect.setBounds(20, 40, 150, 20);
        fishingTypeSelect.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for (FishingSpots fishingSpot : FishingSpots.values()) {
                    if (fishingSpot.name().equalsIgnoreCase(fishingTypeSelect.getSelectedItem().toString())) {
                        Variables.fishing_spot_selected = fishingSpot;
                    }
                }
            }
        });
        fishingPanel.add(fishingTypeSelect);

        /*
         * Slave Panel
         *
         * DISABLED FOR NOW

        JPanel slavePanel = new JPanel();
        slavePanel.setForeground(Color_WhiteSmoke);
        slavePanel.setBackground(Color_WetAsphalt);
        tabbedPane.addTab("Bank Runner", null, slavePanel, null);
        slavePanel.setLayout(null);

        // Name of the Master account
        JLabel lblSlaveMaster = new JLabel("Slave Master");
        lblSlaveMaster.setForeground(Color_WhiteSmoke);
        lblSlaveMaster.setBounds(20, 20, 73, 20);
        slavePanel.add(lblSlaveMaster);
        JTextField slaveMaster = new JTextField();
        slaveMaster.setBounds(20, 40, 150, 20);
        slavePanel.add(slaveMaster);
        slaveMaster.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Variables.slave_master = slaveMaster.getText();
            }
        });
        */

        JButton start = new JButton("START");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(autoLogin.isSelected() && !username.getText().equals("")) {
                    Variables.setAccountUsername(username.getText());
                    Variables.setAccountPassword(password.getText());
                }
                Variables.bank_items = UpdateBank.loadBankFile();
                Variables.running = !Variables.running;
                start.setText(Variables.running ? "PAUSE" : "START");
                start.setBackground(Variables.running ? Color_Alizarin : Color_Emerald);
            }
        });
        start.setBackground(Color_Emerald);
        start.setForeground(Color_MidnightBlue);
        start.setContentAreaFilled(false);
        start.setOpaque(true);
        start.setBounds(20, 220, 360, 40);
        contentPane.add(start);
    }
}