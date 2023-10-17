package soccerTeam;

import java.time.LocalDate;
import java.util.List;

/**
 * The `team` interface represents a sports team and defines the operations that can be performed on the team, such as managing players,
 * reconstructing team size, assigning jersey numbers, selecting a starting lineup, assigning player positions, and getting player lists.
 */
public interface team {

    /**
     * Get a list of all current players in the team.
     * @return A list of all current players in the team.
     */
    List<player> getCurrentTeam();

    /**
     * Get a list of all available jersey numbers in the team.
     * @return A list of available jersey numbers in the team.
     */
    List<Integer> getAvailableJerseyNumber();

    /**
     * Get the team size.
     * @return An integer of the team size.
     */
    int getTeamSize();

    /**
     * Add a new player to the team.
     * @param firstName      The first name of the player.
     * @param lastName       The last name of the player.
     * @param dateOfBirth    The date of birth of the player.
     * @param preferredPosition The preferred position of the player.
     * @param skillLevel     The skill level of the player.
     * @throws IllegalArgumentException If the player is under 10 years old.
     */
    void addPlayer(String firstName, String lastName, LocalDate dateOfBirth, Position preferredPosition, int skillLevel);

    /**
     * Reconstruct the team size to meet the required size limit of 20 players.
     * If the team size is less than 10 players, it throws an IllegalArgumentException.
     * If the team size exceeds 20 players, the least skilled players are removed to meet the limit.
     *
     * @throws IllegalArgumentException If the team size is less than 10 players.
     */
    void reconstructTeamSize();

    /**
     * Removes a player from the team based on the provided information (first name, last name, date of birth, preferred position, and skill level).
     * If a player with matching information is found in the current team, they will be removed, and their jersey number will be recycled.
     *
     * @param firstName         The first name of the player to be removed.
     * @param lastName          The last name of the player to be removed.
     * @param dateOfBirth       The date of birth of the player to be removed.
     * @param preferredPosition The preferred position of the player to be removed.
     * @param skillLevel        The skill level of the player to be removed.
     * @return
     */
    boolean removePlayer (String firstName, String lastName, LocalDate dateOfBirth, Position preferredPosition, int skillLevel);

    /**
     * Select the starting lineup of the team based on players' skill levels.
     * The top seven players with the highest skill levels (or all players if less than seven) are chosen for the starting lineup.
     */
    void selectStartingLineup();

    /**
     * Assign positions to players in the starting lineup based on their preferred positions and priority order.
     * Players are assigned to positions in the order of Goalie -> Defenders -> Midfielders -> Forward.
     */
    void assignPosition();

    /**
     * Get a formatted string representing the list of all players in the current team, sorted alphabetically by last name.
     */
    String getAllPlayersList();

    /**
     * Get a formatted string representing the list of players in the starting lineup, sorted by position and last name.
     */
    String getStartingLineupList();
}
