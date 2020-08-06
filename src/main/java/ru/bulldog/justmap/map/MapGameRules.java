package ru.bulldog.justmap.map;

import java.util.HashMap;
import java.util.Map;

import ru.bulldog.justmap.JustMap;
import ru.bulldog.justmap.mixins.BooleanRuleAccessor;
import ru.bulldog.justmap.mixins.GameRulesAccessor;
import ru.bulldog.justmap.util.DataUtil;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.RuleKey;

public class MapGameRules {

	public final static GameRules.RuleKey<GameRules.BooleanRule> ALLOW_CAVES_MAP = register("allowCavesMap", false);
	public final static GameRules.RuleKey<GameRules.BooleanRule> ALLOW_ENTITY_RADAR = register("allowEntityRadar", false);
	public final static GameRules.RuleKey<GameRules.BooleanRule> ALLOW_PLAYER_RADAR = register("allowPlayerRadar", false);
	public final static GameRules.RuleKey<GameRules.BooleanRule> ALLOW_CREATURE_RADAR = register("allowCreatureRadar", false);
	public final static GameRules.RuleKey<GameRules.BooleanRule> ALLOW_HOSTILE_RADAR = register("allowHostileRadar", false);
	public final static GameRules.RuleKey<GameRules.BooleanRule> ALLOW_SLIME_CHUNKS = register("allowSlimeChunks", false);
	public final static GameRules.RuleKey<GameRules.BooleanRule> ALLOW_TELEPORTATION = register("allowWaypointsJump", false);
	
	private MapGameRules() {}
	
	public static void init() {
		JustMap.LOGGER.info("Map gamerules loaded.");
	}

	private static GameRules.RuleKey<GameRules.BooleanRule> register(String name, boolean defaultValue) {
		return GameRulesAccessor.callRegister(name, BooleanRuleAccessor.callCreate(defaultValue));
	}
	
	private static Map<String, RuleKey<GameRules.BooleanRule>> codes;
	
	static {
		codes = new HashMap<>();
		
		codes.put("§a", ALLOW_CAVES_MAP);
		codes.put("§b", ALLOW_ENTITY_RADAR);
		codes.put("§c", ALLOW_PLAYER_RADAR);
		codes.put("§d", ALLOW_CREATURE_RADAR);
		codes.put("§e", ALLOW_HOSTILE_RADAR);
		codes.put("§s", ALLOW_SLIME_CHUNKS);
		codes.put("§t", ALLOW_TELEPORTATION);
	}
	
	/*
	 *	§0§0: prefix
	 *  §f§f: suffix
	 *  
	 *	§a: cave mapping
	 *	§b: entities radar (all)
	 *	§c: entities radar (player)
	 *	§d: entities radar (animal)
	 *	§e: entities radar (hostile)
	 *	§s: slime chunks
	 *
	 *  §1: enable
	 *  §0: disable
	 */	
	@Environment(EnvType.CLIENT)
	public static void parseCommand(String command) {
		GameRules gameRules = DataUtil.getWorld().getGameRules();
		codes.forEach((key, rule) -> {
			if (command.contains(key)) {
				int valPos = command.indexOf(key) + 2;
				boolean value = command.substring(valPos, valPos + 2).equals("§1");
				gameRules.get(rule).set(value, null);
				JustMap.LOGGER.info(String.format("Map rule %s switched to: %s.", rule, value));
			}
		});
	}
	
	@Environment(EnvType.CLIENT)
	public static boolean isAllowed(GameRules.RuleKey<GameRules.BooleanRule> rule) {
		MinecraftClient minecraft = DataUtil.getMinecraft();		
		boolean allow = true;
		if (minecraft.isIntegratedServerRunning()) {
			allow = minecraft.getServer().getGameRules().getBoolean(rule);
		} else if (!minecraft.isInSingleplayer()) {
			if (minecraft.world == null) return false;
			allow = minecraft.world.getGameRules().getBoolean(rule);
		}
		
		return allow;
	}
}
