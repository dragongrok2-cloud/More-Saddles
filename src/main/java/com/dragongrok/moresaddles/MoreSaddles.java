package com.dragongrok.moresaddles;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

/**
 * More Saddles — addon for Dragon Survival.
 * Adds additional saddle variants for dragons.
 */
@Mod(MoreSaddles.MODID)
public class MoreSaddles {

    public static final String MODID = "moresaddles";
    public static final Logger LOGGER = LogUtils.getLogger();

    // Deferred Registers
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // === Example saddle items (skeleton) ===
    // TODO: Replace with real Dragon Survival saddle items / integration

    public static final DeferredItem<Item> DRAGON_SADDLE_BASIC = ITEMS.registerSimpleItem(
            "dragon_saddle_basic",
            new Item.Properties().stacksTo(1)
    );

    public static final DeferredItem<Item> DRAGON_SADDLE_FANCY = ITEMS.registerSimpleItem(
            "dragon_saddle_fancy",
            new Item.Properties().stacksTo(1)
    );

    public static final DeferredItem<Item> DRAGON_SADDLE_ROYAL = ITEMS.registerSimpleItem(
            "dragon_saddle_royal",
            new Item.Properties().stacksTo(1)
    );

    // Creative tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MORE_SADDLES_TAB =
            CREATIVE_MODE_TABS.register("more_saddles_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.moresaddles"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> DRAGON_SADDLE_BASIC.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(DRAGON_SADDLE_BASIC.get());
                        output.accept(DRAGON_SADDLE_FANCY.get());
                        output.accept(DRAGON_SADDLE_ROYAL.get());
                    })
                    .build());

    public MoreSaddles(IEventBus modEventBus, ModContainer modContainer) {
        // Common setup
        modEventBus.addListener(this::commonSetup);

        // Register deferred registers
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        // Game events
        NeoForge.EVENT_BUS.register(this);

        // Creative tabs event (optional extra)
        modEventBus.addListener(this::addCreative);

        LOGGER.info("More Saddles loaded! Ready for Dragon Survival integration.");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("More Saddles common setup complete.");
        // TODO: Register compatibility with Dragon Survival here
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // Items are already added via the custom tab
    }

    @net.neoforged.bus.api.SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("More Saddles says hello on server start!");
    }
}
