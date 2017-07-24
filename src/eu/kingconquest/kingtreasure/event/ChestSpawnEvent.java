package eu.kingconquest.kingtreasure.event;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChestSpawnEvent extends Event{
	private static final HandlerList	handlers	= new HandlerList();
	private Location					location;

	public ChestSpawnEvent(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	@Override
	public HandlerList getHandlers(){
		return handlers;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}
}
