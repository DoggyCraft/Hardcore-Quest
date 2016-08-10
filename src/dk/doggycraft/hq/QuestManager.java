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

@SuppressWarnings("unused")
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
		
	}

	private void save()
	{
		
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
	
	public void handleAction(Player player, String action)
	{
		player.sendMessage(ChatColor.DARK_RED + "* You pick up the knife and slices the plant");			
		player.sendMessage(ChatColor.DARK_PURPLE + "The plant contains explosives and blows you up in 1000 pieces!");			
		player.sendMessage(ChatColor.DARK_RED + "You are DEAD!");			
	}
}