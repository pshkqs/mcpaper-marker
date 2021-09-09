package github.pashamaladec.marker;

import github.pashamaladec.marker.commands.CommandHandler;
import github.pashamaladec.marker.commands.removemark.RemoveMark;
import github.pashamaladec.marker.commands.setmark.SetMark;
import github.pashamaladec.marker.commands.showmarks.ShowMarks;
import github.pashamaladec.marker.config.Config;
import github.pashamaladec.marker.config.Mark;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Marker extends JavaPlugin
{
	static
	{
		ConfigurationSerialization.registerClass(Mark.class);
	}
	
	private static Logger logger;
	private static final Random random = new Random();
	
	@Override
	public void onEnable()
	{
		logger = getLogger();
		var config = new Config(getDataFolder().getParent() + "\\player-marks.yml");
		
		if(config.exists() == false)
			config.create();
		
		config.load();
		
		registerCommand("setmark", new SetMark(config));
		registerCommand("removemark", new RemoveMark(config));
		registerCommand("showmarks", new ShowMarks(config));
	}
	
	private void registerCommand(String command, CommandHandler handler)
	{
		var pluginCommand = getCommand(command);
		
		if(pluginCommand == null)
		{
			log(Level.SEVERE, command + " is not found");
			return;
		}
		
		pluginCommand.setExecutor(handler);
	}
	
	public static void log(Level level, String message)
	{
		logger.log(level, message);
	}
	
	public static String formatInt(int value)
	{
		if (value < 10)
			return "0" + value;
		
		return "" + value;
	}
	
	public static String timeToString(LocalDateTime time)
	{
		return formatInt(time.getHour()) + ":"
				+ formatInt(time.getMinute()) + ":"
				+ formatInt(time.getSecond()) + " - "
				+ formatInt(time.getDayOfMonth()) + "/"
				+ formatInt(time.getMonthValue()) + "/"
				+ formatInt(time.getYear());
	}
	
	public static String randomHash(int start, int stop, int length)
	{
		return random.ints(start, stop + 1).limit(length).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
}
