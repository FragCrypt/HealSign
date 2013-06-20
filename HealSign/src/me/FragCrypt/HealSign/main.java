package me.FragCrypt.HealSign;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable(){
		PluginDescriptionFile PDF = this.getDescription();
		System.out.println("[" + PDF.getName() + "]" + " Version " + PDF.getVersion() + " has now turned on!");
        getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable(){
		PluginDescriptionFile PDF = this.getDescription();
		System.out.println("[" + PDF.getName() + "]" + " Version " + PDF.getVersion() + " has now turned off!");
	}
		
	@EventHandler
    public void onSignPlace(SignChangeEvent e) {
        Player p = e.getPlayer();
        if(e.getLine(0).equalsIgnoreCase("[HealSign]")) {
        	if(e.getLine(1).isEmpty()){
        		p.sendMessage("Test");
        	}else{
                  e.setLine(0, ChatColor.BLUE + "[HealSign]");
                  e.setLine(1, ChatColor.GREEN + e.getLine(1) + " Hearts");
                  e.setLine(2, ChatColor.BLUE + "[test]");
                  e.setLine(3, ChatColor.BLUE + "[test]");
                  e.getBlock().getState().update();
        	}
        }
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
	    //Action a = e.getAction();
	    Block block = e.getClickedBlock();
	    Player p = e.getPlayer();
	    if(e.getAction() == Action.RIGHT_CLICK_BLOCK ){ //&& e.getPlayer().hasPermission("sign.use")
	    	if(block.getType().equals(Material.SIGN) || block.getType().equals(Material.SIGN_POST)) {
	    		Sign sign = (Sign)e.getClickedBlock().getState();
	    		String[] lines = sign.getLines();
	        	if(lines[0].equalsIgnoreCase("[HealSign]")){
	        		p.sendMessage(ChatColor.GREEN + "You have been healed " + lines[1]);
	        		int h = p.getHealth();
	        		String[] h2 = lines[1].split(",");
	        		int h21 = Integer.parseInt(h2[0]);
	        		int h22 = h21*2;
	        		int h3 = h + h22;
	        		p.setHealth(h3);
	        	}
	    	}
	    }
	    
	}
}
