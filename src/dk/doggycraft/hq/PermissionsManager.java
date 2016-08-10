package dk.doggycraft.hq;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.platymuus.bukkit.permissions.Group;
import com.platymuus.bukkit.permissions.PermissionsPlugin;

import de.bananaco.bpermissions.api.ApiLayer;
import de.bananaco.bpermissions.api.WorldManager;
//import de.bananaco.bpermissions.api.util.Calculable;
//import de.bananaco.bpermissions.api.util.CalculableType;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.anjocaido.groupmanager.data.User;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;

@SuppressWarnings("unused")
public class PermissionsManager
{
	private String pluginName = "null";
	private PluginManager pluginManager = null;
	private HardcoreQuest plugin;
	private PermissionsPlugin permissionsBukkit	= null;
	private PermissionManager pex = null;
	private GroupManager groupManager = null;

	public PermissionsManager(HardcoreQuest p)
	{
		this.plugin = p;
	}

	public void load()
	{
		// Detect what permission plugin is used on this server and setup accordingly
		pluginManager = plugin.getServer().getPluginManager();
	
		if (pluginManager.getPlugin("PermissionsBukkit") != null)
		{
			plugin.log("Using PermissionsBukkit.");
			pluginName = "PermissionsBukkit";
			permissionsBukkit = ((PermissionsPlugin) pluginManager.getPlugin("PermissionsBukkit"));
		}
		else if (pluginManager.getPlugin("PermissionsEx") != null)
		{
			plugin.log("Using PermissionsEx.");
			pluginName = "PermissionsEx";
			pex = PermissionsEx.getPermissionManager();
		}
		else if (pluginManager.getPlugin("GroupManager") != null)
		{
			plugin.log("Using GroupManager");
			pluginName = "GroupManager";
			groupManager = ((GroupManager) pluginManager.getPlugin("GroupManager"));
		}
		else if (pluginManager.getPlugin("bPermissions") != null)
		{
			plugin.log("Using bPermissions.");
			pluginName = "bPermissions";
		}
		else
		{
			plugin.log("No permissions plugin detected! Defaulting to superperm");
			pluginName = "SuperPerm";
		}
	}


	public boolean hasPermission(Player player, String node)
	{
		return false;
	}
}