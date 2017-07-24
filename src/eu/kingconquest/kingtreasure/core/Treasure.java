package eu.kingconquest.kingtreasure.core;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Treasure{
	private Location location;

	public Treasure(Location location){
		this.location = location;
		spawn(location);
	}

	private void setInventory(Inventory inv){
		Random rn = new Random();
		Material[] material = Material.values();
		for (int x = 0; x < 5; x++){
			int i = rn.nextInt(Material.values().length) + 1;
			inv.addItem(new ItemStack(material[i], rn.nextInt(5) + 1));
		}
	}

	private void spawn(Location location){
		Block block = location.getBlock();
		location.getBlock().setType(Material.CHEST);
		Chest chest = (Chest) block.getState();
		Inventory inv = chest.getInventory();
		setInventory(inv);
	}

	public Location getLocation(){
		return this.location;
	}
}
