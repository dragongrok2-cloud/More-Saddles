package com.dragongrok.moresaddles.compat;

import com.dragongrok.moresaddles.MoreSaddles;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.ModList;

import java.lang.reflect.Method;

/**
 * Soft compatibility layer for Dragon Survival.
 *
 * Dragon Survival (by.dragonsurvivalteam) lets the player *become* a dragon.
 * There is no public stable API for classic "saddles", so we use soft checks
 * and reflection to detect whether a player is currently a dragon.
 *
 * This keeps More Saddles loadable even when Dragon Survival is absent.
 */
public final class DragonSurvivalCompat {

    public static final String DS_MODID = "dragonsurvival";

    private static final boolean LOADED = ModList.get().isLoaded(DS_MODID);

    // Cached reflection handles (filled on first use)
    private static boolean resolved = false;
    private static Method isDragonMethod; // Player -> boolean

    private DragonSurvivalCompat() {}

    public static boolean isDragonSurvivalLoaded() {
        return LOADED;
    }

    /**
     * Returns true if the given player is currently in dragon form.
     * Safe to call even when Dragon Survival is not installed (returns false).
     */
    public static boolean isDragon(Player player) {
        if (!LOADED || player == null) {
            return false;
        }
        resolve();
        if (isDragonMethod == null) {
            return false;
        }
        try {
            Object result = isDragonMethod.invoke(null, player);
            return result instanceof Boolean b && b;
        } catch (Throwable t) {
            MoreSaddles.LOGGER.debug("DragonSurvivalCompat.isDragon failed: {}", t.toString());
            return false;
        }
    }

    private static void resolve() {
        if (resolved) return;
        resolved = true;

        // Try several known helper locations used across DS versions.
        // Order: most recent / common first.
        String[] candidates = {
                "by.dragonsurvivalteam.dragonsurvival.util.DragonUtils",
                "by.dragonsurvivalteam.dragonsurvival.common.capability.DragonStateProvider",
                "by.dragonsurvivalteam.dragonsurvival.common.handlers.DragonHandler"
        };

        String[] methodNames = { "isDragon", "isDragonOrPassenger", "getHandler" };

        for (String className : candidates) {
            try {
                Class<?> clazz = Class.forName(className);
                for (String methodName : methodNames) {
                    try {
                        Method m = clazz.getMethod(methodName, Player.class);
                        if (m.getReturnType() == boolean.class || m.getReturnType() == Boolean.class) {
                            m.setAccessible(true);
                            isDragonMethod = m;
                            MoreSaddles.LOGGER.info("Dragon Survival integration: using {}.{}", className, methodName);
                            return;
                        }
                    } catch (NoSuchMethodException ignored) {
                    }
                }
            } catch (ClassNotFoundException ignored) {
            }
        }

        MoreSaddles.LOGGER.warn(
                "Dragon Survival is present, but no known isDragon helper was found. " +
                "Saddle items will still work as regular items."
        );
    }

    public static void logStatus() {
        if (LOADED) {
            MoreSaddles.LOGGER.info("Dragon Survival detected — soft integration active.");
        } else {
            MoreSaddles.LOGGER.info("Dragon Survival not found — More Saddles runs in standalone mode.");
        }
    }
}
