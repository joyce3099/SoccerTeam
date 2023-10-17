package soccerTeam;
import java.time.LocalDate;
import java.time.Period;

/**
 * The `playerImpl` class represents a concrete implementation of the `player` interface, representing a player in a sports team.
 * It stores the player's first name, last name, date of birth, preferred position, actual assigned position, skill level, and jersey number.
 */
public class playerImpl implements player{

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Position preferredPosition;
    private Position actualPosition;
    private int skillLevel;
    private int jerseyNumber;

    /**
     * Constructs a new `playerImpl` object with the provided player details.
     *
     * @param firstName       The first name of the player.
     * @param lastName        The last name of the player.
     * @param dateOfBirth     The date of birth of the player.
     * @param preferredPosition The preferred position of the player.
     * @param skillLevel      The skill level of the player.
     */
    public playerImpl(String firstName,String lastName,LocalDate dateOfBirth,Position preferredPosition,int skillLevel)throws IllegalArgumentException{

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.preferredPosition = preferredPosition;
        if (skillLevel < 1 || skillLevel > 5){
            throw new IllegalArgumentException("skill level should be between 1 and 5");
        }
        this.skillLevel = skillLevel;
        this.actualPosition = null;
        this.jerseyNumber = 0;

//        if (this.getAge() > 10){
//            throw new IllegalArgumentException("Player's age should be under 10");
//        }
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }


    @Override
    public int getJerseyNumber() {
        return this.jerseyNumber;
    }


    @Override
    public int getAge(){
        LocalDate currentDate = LocalDate.now();
        int years = currentDate.getYear() - dateOfBirth.getYear();
        if (currentDate.getMonthValue() < dateOfBirth.getMonthValue() ||
                (currentDate.getMonthValue() == dateOfBirth.getMonthValue() &&
                        currentDate.getDayOfMonth() < dateOfBirth.getDayOfMonth())) {
            years--;
        }
        return years;
    }

    public int getSkillLevel(){
        return this.skillLevel;
    }

    @Override
    public Position getPreferredPosition() {
        return this.preferredPosition;
    }

    @Override
    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
    @Override
    public void setActualPosition(Position position) {
        this.actualPosition = position;
    }

    @Override
    public Position getActualPosition() {
        return this.actualPosition;
    }


}
