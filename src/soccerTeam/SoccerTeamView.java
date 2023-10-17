package soccerTeam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * The SoccerTeamView interface defines the methods that a soccer team view should implement
 * to interact with the user interface and display information related to the soccer team.
 */
public interface SoccerTeamView {

    /**
     * Updates the output area related to player addition and removal actions.
     *
     * @param message The message to be displayed in the output area.
     */
    void updateAddRemoveOutput(String message);

    /**
     * Updates the output area displaying information about all players in the soccer team.
     *
     * @param message The message to be displayed in the output area.
     */
    void updateAllPlayersOutput(String message);

    /**
     * Updates the output area displaying the starting lineup of the soccer team.
     *
     * @param message The message to be displayed in the output area.
     */
    void updateStartingLineupOutput(String message);

    /**
     * Clears the input fields in the user interface after performing an action.
     */
    void clearInputFields();

    /**
     * Retrieves the first name of the player.
     * @return The first name of the player.
     */
    String getFirstName();

    /**
     * Retrieves the last name of the player.
     * @return The last name of the player.
     */
    String getLastName();


    /**
     * Retrieves the birth date of the player.
     * @return The birth date of the player.
     */
    LocalDate getBirthDate();

    /**
     * Retrieves the preferred position of the player.
     * @return The preferred position of the player.
     */
    Position getPreferredPosition();

    /**
     * Retrieves the skill level of the player.
     * @return The skill level of the player.
     */
    int getSkillLevel();

    /**
     * Adds an ActionListener to the "Add" button.
     * @param listener The ActionListener to be added.
     */
    void addAddButtonListener(ActionListener listener);

    /**
     * Adds an ActionListener to the "Remove" button.
     * @param listener The ActionListener to be added.
     */
    void addRemoveButtonListener(ActionListener listener);

    /**
     * Adds an ActionListener to the "Create Team" button.
     * @param listener The ActionListener to be added.
     */
    void addCreateTeamButtonListener(ActionListener listener);

    /**
     * Adds an ActionListener to the "Show All Players" button.
     * @param listener The ActionListener to be added.
     */
    void addShowAllPlayersButtonListener(ActionListener listener);


    /**
     * Adds an ActionListener to the "Show Starting Lineup" button.
     * @param listener The ActionListener to be added.
     */
    void addShowStartingLineupButtonListener(ActionListener listener);
}
