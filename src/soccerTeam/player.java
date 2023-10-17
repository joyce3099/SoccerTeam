package soccerTeam;

import java.time.LocalDate;

/**
 * The `player` interface represents a player in a sports team. It defines methods to access various properties of a player,
 * such as their name, jersey number, age, skill level, preferred position, and actual assigned position.
 */
public interface player {

    /**
     * Get the first name of the player.
     * @return The first name of the player.
     */
    String getFirstName();

    /**
     * Get the last name of the player.
     * @return The last name of the player.
     */
    String getLastName();

    /**
     * Get the date of birth of the player.
     * @return The date of birth of the player.
     */
    LocalDate getDateOfBirth();

    /**
     * Get the jersey number assigned to the player.
     * @return The jersey number of the player.
     */
    int getJerseyNumber();

    /**
     * Get the age of the player.
     * @return The age of the player.
     */
    int getAge();

    /**
     * Get the skill level of the player.
     * @return The skill level of the player.
     */
    int getSkillLevel();

    /**
     * Get the preferred position of the player.
     * @return The preferred position of the player.
     */
    Position getPreferredPosition();

    /**
     * Get the actual assigned position of the player in the team's starting lineup.
     * @return The actual assigned position of the player.
     */
    Position getActualPosition();

    /**
     * Set the jersey number for the player.
     * @param jerseyNumber The jersey number to be assigned to the player.
     */
    void setJerseyNumber(int jerseyNumber);

    /**
     * Set the actual assigned position of the player in the team's starting lineup.
     * @param position The actual assigned position of the player.
     */
    void setActualPosition(Position position);
}
