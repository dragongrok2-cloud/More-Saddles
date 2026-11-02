package com.dragongrok.moresaddles.compat;

import com.dragongrok.moresaddles.MoreSaddles;
import com.dragongrok.moresaddles.item.DragonSaddleItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;

/**
 * Soft integration with Curios API.
 * Slot type and item tags are registered via datapack.
 * This class only logs status and provides helpers.
 */
public final class CuriosCompat {

    public static final String CURIOS_MODID = "curios";
    public static final String SLOT_ID = "dragon_saddle";

    private static final boolean LOADED = ModList.get().isLoaded(CURIOS_MODID);

    private CuriosCompat() {}

    public static boolean isCuriosLoaded() {
        return LOADED;
    }

    public static void logStatus() {
        if (LOADED) {
            MoreSaddles.LOGGER.info("Curios detected — slot '{}' is available for dragon saddles.", SLOT_ID);
        } else {
            MoreSaddles.LOGGER.info("Curios not found — saddles work as normal items only.");
        }
    }

    /**
     * Returns true if the stack is one of our dragon saddle items.
     */
    public static boolean isDragonSaddle(ItemStack stack) {
        return stack != null && !stack.isEmpty() && stack.getItem() instanceof DragonSaddleItem;
    }
}
