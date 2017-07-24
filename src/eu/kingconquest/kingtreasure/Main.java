package eu.kingconquest.kingtreasure;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import eu.kingconquest.kingtreasure.command.HomeCommand;
import eu.kingconquest.kingtreasure.extension.EconAPI;
import eu.kingconquest.kingtreasure.extension.Hooks;
import eu.kingconquest.kingtreasure.extension.Vault;
import eu.kingconquest.kingtreasure.listener.ChestGuiListener;
import eu.kingconquest.kingtreasure.storage.YmlStorage;
import eu.kingconquest.kingtreasure.util.Cach;
import eu.kingconquest.kingtreasure.util.Message;
import eu.kingconquest.kingtreasure.util.MessageType;

/**
 * Kingconquest Conquest Plugin
 * 
 * @author Thomas Lundqvist
 */
public class Main extends JavaPlugin implements Listener{
	private static Main instance;

	/**
	 * Plugin Startup
	 * 
	 * @return void
	 */
	@Override
	public void onEnable(){
		instance = this;
		YmlStorage.load();

		new EconAPI();
		new Vault();

		new Message(MessageType.CONSOLE, "&6|==============={Prefix}==============|");
		new Message(MessageType.CONSOLE, "&6|&2 Version: " + getDescription().getVersion());
		new Message(MessageType.CONSOLE, "&6|&2 Hooks:");
		Hooks.output();
		new Message(MessageType.CONSOLE, "&6|&2 Configs:");
		YmlStorage.output();
		new Message(MessageType.CONSOLE, "&6|=======================================|");
		////////////////////////////////////////////////////////
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
				new Runnable(){
					@Override
					public void run(){

					}
				}, 0, 0);
		////////////////////////////////////////////////////////
		setListeners();

	}

	private void setListeners(){
		this.getServer().getPluginManager().registerEvents(new ChestGuiListener(), this);
	}

	/**
	 * On Command /c /kc, kingc, conquest, kingconquest
	 * 
	 * @return boolean
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		this.getCommand("kingconquest").setExecutor(new HomeCommand());
		return true;
	}

	/**
	 * Get Instance of Plugin
	 * 
	 * @return Plugin Instance
	 */
	public static final Main getInstance(){
		return instance;
	}

	/**
	 * Plugin Shutdown
	 * 
	 * @return void
	 */
	@Override
	public void onDisable(){
		new Message(null, MessageType.CONSOLE, "&6|==============={Prefix}==============|");
		new Message(null, MessageType.CONSOLE, "&6|&2 Configs:");
		YmlStorage.remove();
		YmlStorage.save();
		new Message(null, MessageType.CONSOLE, "&6|=======================================|");

		YmlStorage.clear();
		Cach.nullify();

		getServer().getServicesManager().unregisterAll(this);
		Bukkit.getScheduler().cancelTasks(this);
	}
}
