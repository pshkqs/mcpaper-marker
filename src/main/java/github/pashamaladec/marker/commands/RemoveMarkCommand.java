package github.pashamaladec.marker.commands;

import github.pashamaladec.marker.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class RemoveMarkCommand extends CommandHandler
{
	public RemoveMarkCommand(Config config, Logger logger)
	{
		super(config, logger);
	}
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player player)
		{
			if (args.length != 1)
			{
				player.sendMessage("Нужно ввести только ID");
				return false;
			}
			var id = args[0];
			
			if (config.getConfig().get(id) == null)
			{
				player.sendMessage(id + " - неверный");
				return true;
			}
			
			config.getConfig().set(id, null);
			
			config.save();
			player.sendMessage(id + " - удалено");
			return true;
		}
		return false;
	}
}

