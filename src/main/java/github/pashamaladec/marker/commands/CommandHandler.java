package github.pashamaladec.marker.commands;

import github.pashamaladec.marker.config.Config;
import org.bukkit.command.CommandExecutor;

import java.util.logging.Logger;

public abstract class CommandHandler implements CommandExecutor
{
	protected final Logger logger;
	protected final Config config;
	
	public CommandHandler(Config config, Logger logger)
	{
		this.logger = logger;
		this.config = config;
	}
}
