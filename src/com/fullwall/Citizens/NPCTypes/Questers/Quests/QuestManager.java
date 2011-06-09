package com.fullwall.Citizens.NPCTypes.Questers.Quests;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.fullwall.Citizens.NPCTypes.Questers.PlayerProfile;
import com.fullwall.Citizens.NPCTypes.Questers.Quest;

public class QuestManager {
	public enum QuestType {
		/**
		 * Place blocks
		 */
		BUILD,
		/**
		 * Collect item(s)/blocks(s)
		 */
		COLLECT,
		/**
		 * Deliver item(s) to an NPC
		 */
		DELIVERY,
		/**
		 * Break blocks
		 */
		DESTROY_BLOCK,
		/**
		 * Kill mobs
		 */
		HUNT,
		/**
		 * Travel a distance
		 */
		MOVE_DISTANCE,
		/**
		 * Travel to a location
		 */
		MOVE_LOCATION,
		/**
		 * Kill players
		 */
		PLAYER_COMBAT;

		public static QuestType getType(String string) {
			return QuestType.valueOf(string);
		}
	}

	private static HashMap<String, PlayerProfile> cachedProfiles = new HashMap<String, PlayerProfile>();
	private static HashMap<String, Quest> quests = new HashMap<String, Quest>();

	public static void load(Player player) {
		PlayerProfile profile = new PlayerProfile(player.getName());
		cachedProfiles.put(player.getName(), profile);
	}

	public static void unload(Player player) {
		getProfile(player.getName()).save();
		cachedProfiles.put(player.getName(), null);
	}

	public static void incrementQuest(Player player, Event event) {
		getProfile(player.getName()).getProgress().updateProgress(event);
	}

	public static boolean hasQuest(Player player) {
		return getProfile(player.getName()).hasQuest();
	}

	private static PlayerProfile getProfile(String name) {
		return cachedProfiles.get(name);
	}

	public static Quest getQuest(String questName) {
		return quests.get(questName);
	}
}