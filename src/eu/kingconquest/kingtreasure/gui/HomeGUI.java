package eu.kingconquest.kingtreasure.gui;

import java.awt.Desktop;
import java.net.URI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.kingconquest.kingtreasure.Main;
import eu.kingconquest.kingtreasure.core.ChestGui;
import eu.kingconquest.kingtreasure.storage.YmlStorage;
import eu.kingconquest.kingtreasure.util.Message;
import eu.kingconquest.kingtreasure.util.MessageType;
import eu.kingconquest.kingtreasure.util.Validate;

public class HomeGUI extends ChestGui{
	private Player player;

	public HomeGUI(Player p){
		super();
		this.player = p;

		create();
	}

	@Override
	public void create(){
		createGui(player, "&6Home", 9);
		display();
	}

	@SuppressWarnings("unused")
	private int slot;

	@Override
	public void display(){
		clearSlots();
		slot = 9;
		str = "";

		//Slot 0

		//Slot 7
		aboutButton();

		//Slot 8
		closeButton();

		if (Validate.hasPerm(player, ".admin"))
			reloadButton();

	}

	private void reloadButton(){
		setItem(2, new ItemStack(Material.REDSTONE_LAMP_OFF), player ->{
			YmlStorage.clearData();
			YmlStorage.loadLanguage();
			YmlStorage.loadDefault();
			if (YmlStorage.loadLanguage() && YmlStorage.loadDefault()){
				new Message(player, MessageType.CHAT, "&7Config.yml & Language.yml &aSuccessfully reloaded!");
				return;
			}
			new Message(player, MessageType.CHAT, "&7Config.yml & Language.yml &cFailed to reload!");
		}, "&3Reload Config",
				"&7Affected Files: "
						+ "\n&3 - Language.yml"
						+ "\n&3 - Config.yml"
						+ "\n"
						+ "\n&bClick to reload!");
	}

	private void aboutButton(){
		setItem(1, new ItemStack(Material.PAPER), player ->{
			if (getClickType().equals(ClickType.DOUBLE_CLICK)){
				if (Desktop.isDesktopSupported()){
					try{
						Desktop.getDesktop().browse(new URI(Main.getInstance().getDescription().getWebsite()));
					}catch (Exception e){}
				}
			}
		}, "&6About", aboutInfo());
	}

	String str = "";

	private String aboutInfo(){

		str = "&7Plugin Name: &3" + Main.getInstance().getDescription().getName()
				+ "\n&7Version: &3" + Main.getInstance().getDescription().getVersion()
				+ "\n&7Website: &3" + Main.getInstance().getDescription().getWebsite()
				+ "\n&7Author: ";
		Main.getInstance().getDescription().getAuthors().forEach(author ->{
			str += "&3" + author + "\n";
		});
		str += "&7Plugin Description: "
				+ "\n&8" + Main.getInstance().getDescription().getDescription()
				+ "\n"
				+ "\n&bDouble-Click to open website";
		return str;
	}

}
