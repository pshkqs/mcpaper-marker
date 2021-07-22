package github.pashamaladec.marker.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config
{
	private Logger logger = null;
	
	private final File file;
	private FileConfiguration configuration;
	
	public Config(JavaPlugin self, String name, Logger logger)
	{
		file = new File(self.getDataFolder().getParent(), name);
		this.logger = logger;
	}
	
	public FileConfiguration getConfig()
	{
		if (configuration == null)
		{
			throw new NullPointerException();
		}
		
		return configuration;
	}
	
	public boolean create()
	{
		try
		{
			var success = file.createNewFile();
			load();
			
			return success;
			
		} catch (IOException e)
		{
			logger.log(Level.SEVERE, file.getName() + " couldn't create, " + file.getAbsolutePath() + ", " + e.getMessage());
		}
		
		return false;
	}
	
	public boolean load()
	{
		if (file.isFile() == false)
		{
			logger.log(Level.SEVERE, file.getAbsolutePath() + " is not a file");
			return false;
		}
		
		configuration = YamlConfiguration.loadConfiguration(file);
		logger.log(Level.INFO, "Loaded config " + file.getName());
		
		return true;
	}
	
	public void save()
	{
		try
		{
			configuration.save(file);
		} catch (IOException e)
		{
			logger.log(Level.SEVERE, file.getName() + " couldn't save");
		}
		
		logger.log(Level.INFO, "Saved config " + file.getName());
	}
}
