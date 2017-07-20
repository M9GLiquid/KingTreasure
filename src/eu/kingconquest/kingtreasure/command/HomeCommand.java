package eu.kingconquest.kingtreasure.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.kingconquest.kingtreasure.gui.HomeGUI;
import eu.kingconquest.kingtreasure.storage.YmlStorage;

public class HomeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if (sender instanceof Player){
			Player player = (Player) sender;
			YmlStorage.getWorlds().forEach(uniqueID ->{
				if (player.getWorld().equals(Bukkit.getWorld(uniqueID))){
					new HomeGUI(player);
				}
			});
		}
		return true;
	}
}