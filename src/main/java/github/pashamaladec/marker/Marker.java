package github.pashamaladec.marker;

import github.pashamaladec.marker.commands.MarkCommand;
import github.pashamaladec.marker.commands.MarksCommand;
import github.pashamaladec.marker.commands.RemoveMarkCommand;
import github.pashamaladec.marker.config.Config;
import github.pashamaladec.marker.config.MarkData;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class Marker extends JavaPlugin implements Listener
{
	private final Logger logger = getLogger();
	
	private final Config config = new Config(this, "MarkerConfig.yml", logger);
	
	@Override
	public void onEnable()
	{
		if (config.load() == false)
		{
			config.create();
		}
		
		getCommand("mark").setExecutor(new MarkCommand(config, logger));
		getCommand("marks").setExecutor(new MarksCommand(config, logger));
		getCommand("rmark").setExecutor(new RemoveMarkCommand(config, logger));
	}
	
	@Override
	public void onDisable()
	{
		// Plugin shutdown logic
	}
	
	static
	{
		ConfigurationSerialization.registerClass(MarkData.class);
	}
}
