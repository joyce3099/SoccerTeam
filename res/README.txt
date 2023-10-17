
1.About/Overview
The program is a U10 Soccer Team management system. I follow the MVC architecture guidelines to build the program and use Java Swing to build a GUI.
This project can help the coach to manage the U10 Soccer Team.

2.List of features
adding players to the team
removing players from the team
creating a team
displaying team members
displaying starting lineup members

3.How To Run
double click on the jar file

4.How to Use the Program
1.adding players to the team: the coach should input the basic information of the player to add the player into the system as new team members. Then click on the add player button.If the player is added successfully, there will be a notice to user.
2. removing players from the team:the coach should input the basic information of the player to remove players from the system. Then click on the remove player button.If the player is removed successfully, there will be a notice to user.
3. creating a team: click on the create team button. If the team size is not over 10, an
error message will pop out. If the team is created successfully, there will be a notice to user.
4.displaying team members:click on the display all players button. All players that have been added to the team will be shown in the display box.
5.displaying starting lineup members：click on the display all players button. All players in the starting lineup will be shown in the display box.

5.Design/Model Changes
I delete the code in reconstructTeamSize() method when the teamSize > 20, the system will remove people with the lowest skill levels. This is because I made the change that the system cannot add more players if the team size is over 20.
I set every player's ActualPosition to NULL in assignPosition() method. This makes sure that the showStartingLineupButton can work successfully every time.
I changed the removePlayer() method's return type from void to boolean so that I can check if the player is removed successfully in the controller to give user correct feedback information.

6.Assumptions
When the user wants to add a player over 10 years old,an error message will appear.
When the user wants to add a player if the team size is over 20,an error message will appear.
When the user enters invalid player information and then clicks the "Remove Player" button, an error message will appear.
If the number of players is less than 10 and the user clicks the "Create Team" button, an error message will be displayed.
If the number of players is 0 and the user clicks the "Show All Players" button, an error message will be displayed.
If the number of players is less than 10 and the user clicks the "Show Starting Lineup" button, an error message will appear.

7.Limitations
I assume that user inputs for names are always valid, and the program will not throw an error if the user enters a blank name.

8.Citations
[1] https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html (accessed Aug. 14, 2023). 
