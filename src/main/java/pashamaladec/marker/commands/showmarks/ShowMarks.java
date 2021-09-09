package github.pashamaladec.marker.commands.showmarks;

import github.pashamaladec.marker.commands.CommandHandler;
import github.pashamaladec.marker.config.Config;
import github.pashamaladec.marker.config.Mark;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShowMarks extends CommandHandler
{
	public ShowMarks(Config config)
	{
		super(config);
	}
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player player)
		{
			var keys = config.getConfig().getKeys(false);
			player.sendMessage("");
			player.sendMessage("");
			
			var world = player.getWorld().getName();
			var location = player.getLocation();
			
			for (var key : keys)
			{
				if (config.getConfig().get(key) instanceof Mark mark)
				{
					if (mark.world.equals(world))
					{
						var distance = getDistance(
								location.getBlockX(),
								location.getBlockY(),
								location.getBlockZ(),
								mark.x,
								mark.y,
								mark.z
						);
						
						player.sendMessage(ChatColor.GRAY + key + ": " + mark.getFirstLine() + ChatColor.GRAY + ", " + distance + " м.");
						player.sendMessage(mark.getSecondLine());
						player.sendMessage("");
					}
				}
			}
			
			return true;
		}
		
		sender.sendMessage("You need to be player");
		return false;
	}
	
	private int getDistance(int x1, int y1, int z1, int x2, int y2, int z2)
	{
		return (int)Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
	}
}
