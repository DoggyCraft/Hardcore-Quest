package dk.doggycraft.hq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

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
		if (questsConfigFile == null)
		{
			questsConfigFile = new File(plugin.getDataFolder(), "quests.yml");
		}
		
		questsConfig = YamlConfiguration.loadConfiguration(questsConfigFile);
		
		try
		{
			questsConfig.load(new InputStreamReader(new FileInputStream(questsConfigFile), Charset.forName("UTF-8")));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (InvalidConfigurationException e)
		{
			e.printStackTrace();
		}

		if(!questsConfigFile.exists())
		{
			save();			
		}

		//questConfig = YamlConfiguration.loadConfiguration(questsConfigFile);

		if (questsConfig.getKeys(false).size() > 0)
		{
			this.plugin.log("Loaded " + questsConfig.getKeys(false).size() + " quests.");
		}
	}

	private void save()
	{
		if ((this.questsConfig == null) || (this.questsConfigFile == null))
		{
			return;
		}

		try
		{
			this.questsConfig.save(this.questsConfigFile);
		}
		catch (Exception ex)
		{
			this.plugin.log("Could not save config to " + this.questsConfigFile + ": " + ex.getMessage());
		}
	}

	public void update()
	{
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