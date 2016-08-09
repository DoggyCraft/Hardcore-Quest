package dk.doggycraft.hq;

import org.bukkit.entity.Player;

public class QuestManager
{
	private HardcoreQuest plugin;

	public QuestManager(HardcoreQuest p)
	{
		this.plugin = p;
	}

	// Load all defined quests from quests.yml configuration file
	public void load()
	{
		quests.yml questlist = new quests.yml();
	}

	// Generate a new quest for the player and return a handle to it
	public int generateQuest(Player player, int difficulty)
	{
		int questIdentifier = 1;
		
		return questIdentifier;
	}

	// Get the next challenge in a players current quest 
	// A challenge is a collection of text to display in the chat:
	// 1 - A description of the environment (You see a house, There is a horse here, A knife is lying on the ground)
	// 2 - 4 possible choices (actions like "Get the knife")
	public String[] getNextChallengeInQuest(Player player, int questIdentifier)
	{
		return new String[4];
	}	
}