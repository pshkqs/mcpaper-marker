package github.pashamaladec.marker.config;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MarkData implements ConfigurationSerializable
{
	public String author;
	public String uuid;
	public String name;
	public String enviroment;
	public String world;
	public String created;
	public int x;
	public int y;
	public int z;
	
	public MarkData(String author, String uuid, String created, String name, String enviroment, String world, int x, int y, int z)
	{
		this.author = author;
		this.uuid = uuid;
		this.created = created;
		this.name = name;
		this.enviroment = enviroment;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public MarkData()
	{
	
	}
	
	@Override
	public @NotNull Map<String, Object> serialize()
	{
		var serialized = new HashMap<String, Object>();
		serialized.put("author", author);
		serialized.put("uuid", uuid);
		serialized.put("created", created);
		serialized.put("name", name);
		serialized.put("enviroment", enviroment);
		serialized.put("world", world);
		serialized.put("x", x);
		serialized.put("y", y);
		serialized.put("z", z);
		
		return serialized;
	}
	
	public static MarkData deserialize(Map<?, ?> serialized)
	{
		return new MarkData(
				serialized.get("author").toString(),
				serialized.get("uuid").toString(),
				serialized.get("created").toString(),
				serialized.get("name").toString(),
				serialized.get("enviroment").toString(),
				serialized.get("world").toString(),
				NumberConversions.toInt(serialized.get("x")),
				NumberConversions.toInt(serialized.get("y")),
				NumberConversions.toInt(serialized.get("z"))
				);
	}
}
