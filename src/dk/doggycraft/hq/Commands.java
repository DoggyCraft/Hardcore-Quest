package dk.doggycraft.hq;

import org.apache.commons.lang.ObjectUtils.Null;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dk.doggycraft.hq.HardcoreQuest;

@SuppressWarnings("unused")
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

		// Check that the command is something we want to handle at all
		if (!cmd.getName().equalsIgnoreCase("HardcoreQuest") && !cmd.getName().equalsIgnoreCase("HQ")) 
		{
			return false;
		}
					
		// If the player is null, it means the command comes from the server console
		if (player == null)
		{
			if (args.length == 1)
			{
				if(args[0].equalsIgnoreCase("reload"))
				{
					CommandReload(player);
					plugin.log(sender.getName() + " /hq reload");

					return true;
				}
			}

			return true;
		}
		
		if (player == null)
		{
			if (args.length == 1)
			{
				if(args[0].equalsIgnoreCase("help"))
				{
					CommandHelp(player);
					plugin.log(sender.getName() + " /hq help");

					return true;
				}
			}

			return true;
		}
		
		if (player == null)
		{
			if (args.length == 1)
			{
				if(args[0].equalsIgnoreCase("info"))
				{
					CommandInfo(player);
					plugin.log(sender.getName() + " /hq info");

					return true;
				}
			}

			return true;
		}


		// User has just written /hq command and nothing else 
		if (args.length == 0)
		{
			CommandHelp(player);
			plugin.log(sender.getName() + " /hq");
			return true;	
		}
		
		// User has written /hq <something> 
		if (args.length == 1)
		{
			switch(args[0].toLowerCase())
			{
				case "info" : CommandInfo(player); break;
			    case "help" : CommandHelp(player); break;
				case "reload" : CommandReload(player); break;
				case "a" : 
				case "b" : 
				case "c" : 
				case "d" : CommandAction(player, args); break;
				default : player.sendMessage(ChatColor.RED + "Invalid HardcoreQuest command");				
			}
			
			return true;
		}
		
		sender.sendMessage(ChatColor.RED + "Too many arguments!");
		return true;		
	}
	
	//Shows some info
	public void CommandInfo(Player player)
	{
		if (player.hasPermission("hq.info"))
		{
			// Show some info
			// like
			player.sendMessage(ChatColor.YELLOW + "--------------- Hardcore Quest V0.0.1 ---------------");
			player.sendMessage(ChatColor.AQUA + "By The DoggyCraft Tech Team");
			player.sendMessage("");
			player.sendMessage(ChatColor.AQUA + "This Plugin is absolutely useless right now");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "You do not have permission for this command!");
		}
	}
	
	//Shows help
	public boolean CommandHelp(Player player)
	{
		if (player.hasPermission("hq.help"))
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
		}
		else
		{
			player.sendMessage(ChatColor.RED + "You do not have permission for this command!");
		}
		
		return true;
	}	
	
	//Does things
	private void CommandAction(Player player, String[] args)
	{
		if (player.hasPermission("hq.action"))
		{			
			if (!plugin.getQuestManager().isDoingQuest(player))
			{
				player.sendMessage(ChatColor.RED + "You are not on any quest!");
				return;
			}
	
			plugin.getQuestManager().handleAction(player, args[0]);		
		}
		else
		{
			player.sendMessage(ChatColor.RED + "You do not have permission for this command!");
		}
	}
	
	//Reloads the config
	public void CommandReload(Player player)
	{
		if (player.hasPermission("hq.reload"))
		{
			plugin.reloadSettings();
			plugin.loadSettings();
			plugin.getQuestManager().load();
			
			if (player == null)
			{
				this.plugin.log(this.plugin.getDescription().getFullName() + ": Reloaded configuration.");
			}
			else
			{
				player.sendMessage(ChatColor.YELLOW + this.plugin.getDescription().getFullName() + ": " + ChatColor.WHITE + "Reloaded configuration.");
			}
		}
		else
		{
			player.sendMessage(ChatColor.RED + "You do not have permission for this command!");
		}
	}

	//Doesn't do shit
	private void CommandHQList(CommandSender sender)
	{
	}
}