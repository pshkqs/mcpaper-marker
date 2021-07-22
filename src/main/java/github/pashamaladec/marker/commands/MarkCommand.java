package github.pashamaladec.marker.commands;

import github.pashamaladec.marker.config.Config;
import github.pashamaladec.marker.config.MarkData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarkCommand extends CommandHandler
{
	private final Random random = new Random();
	
	private String RandomHash(int start, int stop, int length)
	{
		return random.ints(start, stop + 1).limit(length).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
	
	public MarkCommand(Config config, Logger logger)
	{
		super(config, logger);
	}
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player player)
		{
			if (args.length < 1)
			{
				return false;
			}
			
			var time = LocalDateTime.now();
			
			var enviroment = player.getLocation().getWorld().getEnvironment().name();
			
			var mark = new MarkData(player.getName(), player.getUniqueId().toString(), format(time.getHour()) + ":" + format(time.getMinute()) + ":" + format(time.getSecond()) + " - " + format(time.getDayOfMonth()) + "/" + format(time.getMonthValue()) + "/" + time.getYear(), String.join(" ", args), enviroment, player.getWorld().getName(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
			
			var id = RandomHash(97, 122, 3);
			
			config.getConfig().set(id, mark);
			config.save();
			var reply = id + ": " + mark.name + " - сохранено";
			player.sendMessage(reply);
			
			logger.log(Level.INFO, id + ": " + mark.name + " - сохранено. Создал " + player.getName());
			return true;
		}
		return false;
	}
	
	private static String format(int value)
	{
		if (value < 10)
		{
			return "0" + value;
		}
		
		return "" + value;
	}
}
