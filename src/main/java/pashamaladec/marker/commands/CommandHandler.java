package github.pashamaladec.marker.commands;

import github.pashamaladec.marker.config.Config;
import org.bukkit.command.CommandExecutor;

public abstract class CommandHandler implements CommandExecutor
{
	protected final Config config;
	
	public CommandHandler(Config config)
	{
		this.config = config;
	}
}
