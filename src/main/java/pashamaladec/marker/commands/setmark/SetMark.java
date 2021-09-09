package github.pashamaladec.marker.commands.setmark;

import github.pashamaladec.marker.commands.CommandHandler;
import github.pashamaladec.marker.config.Config;
import github.pashamaladec.marker.config.Mark;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.logging.Level;

import static github.pashamaladec.marker.Marker.*;

public class SetMark extends CommandHandler
{
	public SetMark(Config config)
	{
		super(config);
	}
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player player)
		{
			if (args.length < 1)
				return false;
			
			var time = LocalDateTime.now();
			var enviroment = player.getLocation().getWorld().getEnvironment().name();
			
			var mark = new Mark(
					player.getName(),
					player.getUniqueId().toString(),
					timeToString(time),
					String.join(" ", args),
					enviroment,
					player.getWorld().getName(),
					player.getLocation().getBlockX(),
					player.getLocation().getBlockY(),
					player.getLocation().getBlockZ());
			
			var id = randomHash(97, 122, 3);
			
			while (config.getConfig().get(id) != null)
				id = randomHash(97, 122, 3);
			
			config.getConfig().set(id, mark);
			config.save();
			player.sendMessage(ChatColor.GRAY + id + ": " + ChatColor.AQUA + mark.name + ChatColor.WHITE + " - сохранено");
			
			log(Level.INFO, id + ": " + mark.name + " - сохранено. Создал " + player.getName());
			return true;
		}
		
		return false;
	}
}
