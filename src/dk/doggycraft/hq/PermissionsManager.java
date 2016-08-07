package dk.doggycraft.hq;

import org.bukkit.entity.Player;

public class PermissionsManager
{
	private HardcoreQuest plugin;

	public PermissionsManager(HardcoreQuest p)
	{
		this.plugin = p;
	}

	public void load()
	{
		// Detect what permission plugin is used on this server and setup accordingly
	}

	public boolean hasPermission(Player player, String node)
	{
		return false;
	}
}