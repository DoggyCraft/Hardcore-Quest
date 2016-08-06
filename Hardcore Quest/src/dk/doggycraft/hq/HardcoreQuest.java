package dk.doggycraft.hq;
//this is the package name

//this is the imported libraries
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

//this is the main class of the plugin.
public class HardcoreQuest extends JavaPlugin{

	
	//this gets triggered once when the server starts
	public void onEnabled(){
		//this sends the message "Lets get going WO" when the server starts
		Bukkit.getServer().getLogger().info(ChatColor.DARK_AQUA + "Lets get going WO");
		//this defines pm to be getServer.getPluginManager(). it is not necesary but if you have lots of classes its nice to just use pm instead of getServer.getPluginManager()
		PluginManager pm = getServer().getPluginManager();
		//this initialises the other class named Quest
		pm.registerEvents(new Quest(), this);
	}
	
	
	//this gets triggered once when the server closes
	public void onDisabled(){
		//this sends the message "Oh no, dont leave me..."  when the server stops
		Bukkit.getServer().getLogger().info(ChatColor.BLUE + "Oh no, dont leave me...");
		
		
	}
	//this is the the thing that gets triggered when someone types a command.
	//sender is the person thats sends the command and cmd is the command name
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//this checks if the command typed is equal with "HardcoreQuest" or "HQ"
		if (cmd.getName().equalsIgnoreCase("HardcoreQuest") || cmd.getName().equalsIgnoreCase("HQ")) {
			//if it is. then this will send the player this message
		    sender.sendMessage(ChatColor.YELLOW + "---------------Hardcore Quest V0.0.1---------------");
		    sender.sendMessage(ChatColor.AQUA + "By The Doggycraft Team");
		    sender.sendMessage("");
		    sender.sendMessage(ChatColor.AQUA + "This Plugin is absolutely useless right now");
			return true;
			
		}

		//if someone types a command this will check if its the specific command /quest and the equalsIgnoreCase tells the plugin that it dosnt matter if it is written in caps or not
		if (cmd.getName().equalsIgnoreCase("Quest")) {
			//this checks if the person that sends the command is a player or the console. if it is the console it returns the messgage "Do A Barrel Roll"
			if (!(sender instanceof Player)) {
				sender.sendMessage("Do A Barrel Roll");
		//		return true;
			}
			
			//the Player p is set to (Player) sender. this is unnecesary for now but if you want to do more complicated things with the player than to send them a message the p variable
			//will be a good thing to use
			Player p = (Player) sender;
			//sends the player that uses the command /quest a colorfull message
		    sender.sendMessage(ChatColor.YELLOW + "This is a" + ChatColor.BLUE + " colored Message");
			return true;
		}
	
		
		return false; 
		
		
	}	
		
	}

