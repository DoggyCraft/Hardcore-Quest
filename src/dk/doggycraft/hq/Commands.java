package dk.doggycraft.hq;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dk.doggycraft.hq.HardcoreQuest;

public class Commands 
{
	private HardcoreQuest	plugin;

	Commands(HardcoreQuest p)
	{
		this.plugin = p;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	//'CommandSender sender' - who sent the command
	//'Command cmd' - the command that was executed
	//'String Label' - the command alias that was used
	//String[] args - an array of additional arguments, e.g. typing /hello abc def would put abc in args[0], and def in args[1]
	{
		Player player = null;
		
		if (sender instanceof Player)
		{
			player = (Player) sender;			
		}
		
		if (label.equalsIgnoreCase("info"))
		{
			CommandInfo(player);
		}
	
		// This checks if the command typed is equal with "HardcoreQuest" or "HQ"
		if (cmd.getName().equalsIgnoreCase("HardcoreQuest") || cmd.getName().equalsIgnoreCase("HQ")) 
		{
			//if it is. then this will send the player this message
			sender.sendMessage(ChatColor.YELLOW + "---------------Hardcore Quest V0.0.1---------------");
			sender.sendMessage(ChatColor.AQUA + "By The Doggycraft Team");
			sender.sendMessage("");
			sender.sendMessage(ChatColor.AQUA + "This Plugin is absolutely useless right now");
			return true;					
		}

		// if someone types a command this will check if its the specific
		// command /quest and the equalsIgnoreCase tells the plugin that it
		// dosn't matter if it is written in caps or not
		if (cmd.getName().equalsIgnoreCase("Quest"))
		{
			// this checks if the person that sends the command is a player or
			// the console. if it is the console it returns the messgage "Do A
			// Barrel Roll"
			if (!(sender instanceof Player))
			{
				sender.sendMessage("Do A Barrel Roll");
				 return true;
			}

			// the Player p is set to (Player) sender. this is unnecesary for
			// now but if you want to do more complicated things with the player
			// than to send them a message the p variable
			// will be a good thing to use
			Player p = (Player) sender;
			// sends the player that uses the command /quest a colorfull message
			sender.sendMessage(ChatColor.YELLOW + "This is a" + ChatColor.BLUE + " colored Message");
			// but as mentioned we redefined it to p so you can also use p so send a message 
			p.sendMessage(ChatColor.GREEN + "This is another" + ChatColor.RED + " colored Message");
			return true;
		}

		return true;
	}
	
	public void CommandInfo(Player player)
	{
		// Show some info
		// like
		player.sendMessage(ChatColor.YELLOW + "Contributers: Draco (Plugin base code & more) - Doggy (More code that needs to be specified) - Fido (Fucked up the code)");
		
	}

	public void CommandReload(Player player)
	{
		this.plugin.reloadSettings();
		
		if (player == null)
		{
			this.plugin.log(this.plugin.getDescription().getFullName() + ": Reloaded configuration.");
		}
		else
		{
			player.sendMessage(ChatColor.YELLOW + this.plugin.getDescription().getFullName() + ": " + ChatColor.WHITE + "Reloaded configuration.");
		}
	}

	public boolean CommandHelp(Player player)
	{
		if (player == null)
		{
			this.plugin.log(ChatColor.WHITE + "/hq" + ChatColor.AQUA + " - Show basic info");
		}
		else
		{
			player.sendMessage(ChatColor.YELLOW + "---------- " + this.plugin.getDescription().getFullName() + " ----------");

			//Permission handling
			if (player.hasPermission("hq.startquest"))
			{
				player.sendMessage(ChatColor.AQUA + "/hq startquest" + ChatColor.WHITE + " - Starts a quest");
			}

		}
		
		return true;
	}	
}