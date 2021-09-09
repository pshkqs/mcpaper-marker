package github.pashamaladec.marker.commands.removemark;

import github.pashamaladec.marker.commands.CommandHandler;
import github.pashamaladec.marker.config.Config;
import github.pashamaladec.marker.config.Mark;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

import static github.pashamaladec.marker.Marker.log;

public class RemoveMark extends CommandHandler
{
	public RemoveMark(Config config)
	{
		super(config);
	}
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
	{
		if (sender instanceof Player player)
		{
			if (args.length != 1)
				return false;
			
			var id = args[0];
			
			if (config.getConfig().get(id) instanceof Mark mark)
			{
				config.getConfig().set(id, null);
				player.sendMessage("Метка " + id + " - " + ChatColor.AQUA + mark.name + ChatColor.WHITE + " удалена.");
				config.save();
				log(Level.FINEST, player.getName() + " (" + player.getUniqueId() + ") removed mark " + mark.name);
				return true;
			}
			
			player.sendMessage("Метка с id " + ChatColor.AQUA + id + ChatColor.WHITE + " не существует");
			return true;
		}
		
		return false;
	}
}

