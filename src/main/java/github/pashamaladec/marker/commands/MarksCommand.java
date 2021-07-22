package github.pashamaladec.marker.commands;

import github.pashamaladec.marker.config.Config;
import github.pashamaladec.marker.config.MarkData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.logging.Logger;

public class MarksCommand extends CommandHandler
{
	public MarksCommand(Config config, Logger logger)
	{
		super(config, logger);
	}
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player player)
		{
			var keys = config.getConfig().getKeys(false);
			player.sendMessage("");
			player.sendMessage("");
			
			var playerWorld = player.getWorld().getName();
			
			for (var key : keys)
			{
				if (config.getConfig().get(key) instanceof MarkData mark)
				{
					if (mark.world.equals(playerWorld))
					{
						player.sendMessage(ChatColor.GRAY + key + ": " + ChatColor.AQUA + mark.name
								+ ChatColor.GRAY + " x: "
								+ ChatColor.WHITE + mark.x
								+ ChatColor.GRAY + " y: "
								+ ChatColor.WHITE + mark.y
								+ ChatColor.GRAY + " z: "
								+ ChatColor.WHITE + mark.z
								+ ChatColor.GRAY + " в мире "
								+ ChatColor.WHITE + mark.enviroment.toLowerCase(Locale.ROOT));
						player.sendMessage(ChatColor.GRAY + "       создал " + mark.author + " в " + mark.created);
						player.sendMessage("");
					}
				}
				else
				{
					player.sendMessage(key + " - ошибка, хызы какая");
				}
			}
			
			return true;
		}
		
		return false;
	}
}
