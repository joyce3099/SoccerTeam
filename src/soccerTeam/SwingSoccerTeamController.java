package soccerTeam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.Period;

/**
 * The SwingSoccerTeamController class implements the SoccerTeamController interface and serves as the controller
 * component for the soccer team management system using Swing components.
 */
public class SwingSoccerTeamController implements SoccerTeamController{

    private teamModel model;
    private SwingSoccerTeamView view;

    /**
     * Creates a new instance of SwingSoccerTeamController with the provided model and view.
     *
     * @param model The data model representing the soccer team.
     * @param view The graphical user interface view for the soccer team.
     */
    public SwingSoccerTeamController(teamModel model, SwingSoccerTeamView view) {
        this.model = model;
        this.view = view;

        //sets up listeners for different button events,corresponding methods
        // in controller will be triggered to handle the button actions
        this.view.addAddButtonListener(e -> handleAddPlayer(e));
        this.view.addRemoveButtonListener(e -> handleRemovePlayer(e));
        this.view.addCreateTeamButtonListener(e -> handleCreateTeam(e));
        this.view.addShowAllPlayersButtonListener(e -> handleShowAllPlayers(e));
        this.view.addShowStartingLineupButtonListener(e -> handleShowStartingLineup(e));
    }


    @Override
    public void handleAddPlayer(ActionEvent e) {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        LocalDate birthDate = view.getBirthDate();
        Position preferredPosition = view.getPreferredPosition();
        int skillLevel = view.getSkillLevel();

        LocalDate currentDate = LocalDate.now();
        int years = currentDate.getYear() - birthDate.getYear();
        if (currentDate.getMonthValue() < birthDate.getMonthValue() ||
                (currentDate.getMonthValue() == birthDate.getMonthValue() &&
                        currentDate.getDayOfMonth() < birthDate.getDayOfMonth())) {
            years--;
        }
        int age = years;

        try {
            if (age >= 10) {
                JOptionPane.showMessageDialog(view, "Player's age should be under 10", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (model.getTeamSize() >= 20) {
                JOptionPane.showMessageDialog(view, "The team is full and cannot add players.Please remove a player first.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                model.addPlayer(firstName, lastName, birthDate, preferredPosition, skillLevel);
                view.updateAddRemoveOutput("Player added: Name:" + firstName + " " + lastName + ", " + "birthDate:" + birthDate + ", " + "preferredPosition:" + preferredPosition + ", " + "skillLevel:" + skillLevel);
            }
        } catch (IllegalStateException ex) {
            view.updateAddRemoveOutput(ex.getMessage());
        }

            view.clearInputFields();
        }

    @Override
    public void handleRemovePlayer(ActionEvent e) {

        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        LocalDate birthDate = view.getBirthDate();
        Position preferredPosition = view.getPreferredPosition();
        int skillLevel = view.getSkillLevel();

        boolean playerRemoved = model.removePlayer(firstName, lastName, birthDate, preferredPosition, skillLevel);

        if (playerRemoved) {
            view.updateAddRemoveOutput("Player removed: " + firstName + " " + lastName + "\n");
        } else {
            JOptionPane.showMessageDialog(view, "The player does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
        view.clearInputFields();
    }

    @Override
    public void handleCreateTeam(ActionEvent e){
        try {
            if (model.getTeamSize() < 10) {
                JOptionPane.showMessageDialog(view, "The team should have at least 10 players and can't be created now", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                model.reconstructTeamSize();
                JOptionPane.showMessageDialog(view, "Team created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IllegalStateException ex) {
            view.updateAddRemoveOutput(ex.getMessage());
        }
    }

    @Override
    public void handleShowAllPlayers(ActionEvent e) {
        if (model.getTeamSize() == 0) {
            JOptionPane.showMessageDialog(view, "The team is empty now.","Error", JOptionPane.ERROR_MESSAGE);
        }  else  {
            String message;

            if (model.getTeamSize() < 10) {
                message = "The team is less than 10 players and cannot be created now.\n";
            } else {
                message = "";
            }

            String allPlayersList = model.getAllPlayersList();
            view.updateAllPlayersOutput("-----------------------------------\n");
            view.updateAllPlayersOutput("The list of all players in the team:\n");
            view.updateAllPlayersOutput(allPlayersList);

            if (!message.isEmpty()) {
                JOptionPane.showMessageDialog(view, message, "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void handleShowStartingLineup(ActionEvent e) {

        if (model.getTeamSize() < 10) {
            JOptionPane.showMessageDialog(view, "The team should have at least 10 players so lineup team can't be created now","Error", JOptionPane.ERROR_MESSAGE);
        } else {
            model.selectStartingLineup();
            model.assignPosition();
            String startingLineup = model.getStartingLineupList();

            view.updateStartingLineupOutput("-----------------------------------");
            view.updateStartingLineupOutput("The list of all players in the start lineup:");
            view.updateStartingLineupOutput(startingLineup);
        }
    }
}
