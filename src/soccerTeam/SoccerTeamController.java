package soccerTeam;

import java.awt.event.ActionEvent;

/**
 * The SoccerTeamController interface defines the methods that a soccer team controller should implement
 * in order to manage players and team-related actions.
 */
public interface SoccerTeamController {

    /**
     * Handles the addition of a player to the soccer team.
     * @param e The ActionEvent triggered by the "Add Player" button.
     */
    void handleAddPlayer(ActionEvent e);

    /**
     * Handles the removal of a player from the soccer team.
     * @param e The ActionEvent triggered by the "Remove Player" button.
     */
    void handleRemovePlayer(ActionEvent e);

    /**
     * Handles the creation of a new soccer team.
     * @param e The ActionEvent triggered by the "Create Team" button.
     */
    void handleCreateTeam(ActionEvent e);

    /**
     * Handles the action to display all players in the soccer team.
     * @param e The ActionEvent triggered by the "Show All Players" button.
     */
    void handleShowAllPlayers(ActionEvent e);

    /**
     * Handles the action to display the starting lineup of the soccer team.
     * @param e The ActionEvent triggered by the "Show Starting Lineup" button.
     */
    void handleShowStartingLineup(ActionEvent e);
}
