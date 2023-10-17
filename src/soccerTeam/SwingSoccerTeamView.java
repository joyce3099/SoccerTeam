package soccerTeam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;


/**
 * The SwingSoccerTeamView class implements the SoccerTeamView interface and represents the graphical user interface
 * for interacting with the soccer team management system using Swing components.
 */
public class SwingSoccerTeamView extends JFrame implements SoccerTeamView{

    private JButton addButton, removeButton, createTeamButton,showAllPlayersButton, showStartingLineupButton;

    private JTextArea addRemoveOutputArea;
    private JTextArea allPlayersOutputArea;
    private JTextArea startingLineupOutputArea;
    private JTextField firstNameField, lastNameField;

    private JComboBox<Position> preferredPositionComboBox;
    private JComboBox<Integer>  skillLevelComboBox;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<Integer> monthComboBox;
    private JComboBox<Integer> dayComboBox;


    /**
     * Creates a new instance of SwingSoccerTeamView with the specified caption.
     * @param caption The caption to be displayed on the frame.
     */
    public SwingSoccerTeamView(String caption) {
        super(caption);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1800, 1500);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel outputPanel = new JPanel(new GridLayout(3, 1));

        addRemoveOutputArea = new JTextArea(10, 10);
        addRemoveOutputArea.setEditable(false);
        JScrollPane addRemoveScrollPane = new JScrollPane(addRemoveOutputArea);
        outputPanel.add(addRemoveScrollPane);

        allPlayersOutputArea = new JTextArea(10, 10);
        allPlayersOutputArea.setEditable(false);
        JScrollPane allPlayersScrollPane = new JScrollPane(allPlayersOutputArea);
        outputPanel.add(allPlayersScrollPane);

        startingLineupOutputArea = new JTextArea(10, 10);
        startingLineupOutputArea.setEditable(false);
        JScrollPane startingLineupScrollPane = new JScrollPane(startingLineupOutputArea);
        outputPanel.add(startingLineupScrollPane);

        JPanel addRemovePanel = createTitledPanel("Add or Remove Player",addRemoveScrollPane);
        JPanel allPlayersPanel = createTitledPanel("Current Team", allPlayersScrollPane);
        JPanel startingLineupPanel = createTitledPanel("Starting Lineup", startingLineupScrollPane);
        outputPanel.add(addRemovePanel);
        outputPanel.add(allPlayersPanel);
        outputPanel.add(startingLineupPanel);

        JPanel inputPanel = new JPanel(new GridLayout(6, 1));

        JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        firstNamePanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField(20);
        firstNamePanel.add(firstNameField);
        inputPanel.add(firstNamePanel);

        JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lastNamePanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(20);
        lastNamePanel.add(lastNameField);
        inputPanel.add(lastNamePanel);

        JPanel birthDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        birthDatePanel.add(new JLabel("Birth Date (yyyy-mm-dd):"));
        Integer[] years = new Integer[LocalDate.now().getYear() - 1989];
        for (int i = 0; i < years.length; i++) {
            years[i] = 1990 + i;
        }
        yearComboBox = new JComboBox<>(years);

        Integer[] months = new Integer[12];
        for (int i = 0; i < months.length; i++) {
            months[i] = i + 1;
        }
        monthComboBox = new JComboBox<>(months);

        Integer[] days = new Integer[31];
        for (int i = 0; i < days.length; i++) {
            days[i] = i + 1;
        }
        dayComboBox = new JComboBox<>(days);

        birthDatePanel.add(yearComboBox);
        birthDatePanel.add(new JLabel("-"));
        birthDatePanel.add(monthComboBox);
        birthDatePanel.add(new JLabel("-"));
        birthDatePanel.add(dayComboBox);
        inputPanel.add(birthDatePanel);

        JPanel preferredPositionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        preferredPositionPanel.add(new JLabel("Preferred Position:"));
        preferredPositionComboBox = new JComboBox<>(Position.values());
        preferredPositionPanel.add(preferredPositionComboBox);
        inputPanel.add(preferredPositionPanel);

        JPanel skillLevelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        skillLevelPanel.add(new JLabel("Skill Level:"));
        Integer[] skillLevels = { 1, 2, 3, 4, 5 }; // 1 to 5 as options
        skillLevelComboBox = new JComboBox<>(skillLevels);
        skillLevelPanel.add(skillLevelComboBox);
        inputPanel.add(skillLevelPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addButton = new JButton("Add Player");
        buttonPanel.add(addButton);
        removeButton = new JButton("Remove Player");
        buttonPanel.add(removeButton);

        createTeamButton = new JButton("Create Team");
        buttonPanel.add(createTeamButton);

        showAllPlayersButton = new JButton("Show All Players");
        buttonPanel.add(showAllPlayersButton);

        showStartingLineupButton = new JButton("Show Start Lineup");
        buttonPanel.add(showStartingLineupButton);

        inputPanel.add(buttonPanel);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setResizable(false);
    }

    /**
     * Creates a titled panel with a title label and a scroll pane.
     *
     * @param title The title to be displayed on the panel.
     * @param scrollPane The scroll pane containing the content to be displayed.
     * @return A JPanel with the specified title and content.
     */
    private JPanel createTitledPanel(String title, JScrollPane scrollPane) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void updateAddRemoveOutput(String message) {
        addRemoveOutputArea.append(message + "\n");
    }

    @Override
    public void updateAllPlayersOutput(String message) {
        allPlayersOutputArea.append(message + "\n");
    }

    @Override
    public void updateStartingLineupOutput(String message) {
        startingLineupOutputArea.append(message + "\n");
    }

    @Override
    public void clearInputFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        yearComboBox.setSelectedIndex(0); // Reset to the first item
        monthComboBox.setSelectedIndex(0); // Reset to the first item
        dayComboBox.setSelectedIndex(0);
        preferredPositionComboBox.setSelectedIndex(0); // Reset to the first item
        skillLevelComboBox.setSelectedIndex(0); // Reset to the first item
    }

    @Override
    public String getFirstName() {
        return firstNameField.getText();
    }

    @Override
    public String getLastName() {
        return lastNameField.getText();
    }

    @Override
    public LocalDate getBirthDate() {
        int selectedYear = (Integer) yearComboBox.getSelectedItem();
        int selectedMonth = (Integer) monthComboBox.getSelectedItem();
        int selectedDay = (Integer) dayComboBox.getSelectedItem();
        return LocalDate.of(selectedYear, selectedMonth, selectedDay);
    }

    @Override
    public Position getPreferredPosition() {
        return (Position) preferredPositionComboBox.getSelectedItem();
    }

    @Override
    public int getSkillLevel() {
        return (Integer) skillLevelComboBox.getSelectedItem();
    }

    @Override
    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    @Override
    public void addRemoveButtonListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    @Override
    public void addCreateTeamButtonListener(ActionListener listener) {
        createTeamButton.addActionListener(listener);
    }

    @Override
    public void addShowAllPlayersButtonListener(ActionListener listener) {
        showAllPlayersButton.addActionListener(listener);
    }

    @Override
    public void addShowStartingLineupButtonListener(ActionListener listener) {
        showStartingLineupButton.addActionListener(listener);
    }
}

