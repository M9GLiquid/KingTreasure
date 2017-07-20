package eu.kingconquest.kingtreasure.core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.Objective;

import eu.kingconquest.kingtreasure.util.Validate;

/**
 * Capture Event for Outpost
 * 
 * @author Thomas Lundqvist
 */
public class Proximity implements Listener{
	@SuppressWarnings("unused")
	private static PluginManager pm = Bukkit.getServer().getPluginManager();

	public static void objectiveZoneProximity(Location location, Player player){
		if (!Validate.isWithinArea(player.getLocation(), location, 0, 0, 0)){
			// If the player is outside of the area
			return;
		}
		//pm.callEvent(new CaptureZoneEnterEvent(player, village));
	}

	public static void objectiveAreaProximity(Objective objective, Player player){

	}
}
