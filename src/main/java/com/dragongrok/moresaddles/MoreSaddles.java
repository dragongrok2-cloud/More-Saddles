package com.dragongrok.moresaddles;

import com.dragongrok.moresaddles.compat.DragonSurvivalCompat;
import com.dragongrok.moresaddles.item.DragonSaddleItem;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
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
 * Adds additional saddle variants that interact with dragon form.
 */
@Mod(MoreSaddles.MODID)
public class MoreSaddles {

    public static final String MODID = "moresaddles";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredItem<Item> DRAGON_SADDLE_BASIC = ITEMS.register(
            "dragon_saddle_basic",
            () -> new DragonSaddleItem(DragonSaddleItem.SaddleTier.BASIC, new Item.Properties().stacksTo(1))
    );

    public static final DeferredItem<Item> DRAGON_SADDLE_FANCY = ITEMS.register(
            "dragon_saddle_fancy",
            () -> new DragonSaddleItem(DragonSaddleItem.SaddleTier.FANCY, new Item.Properties().stacksTo(1))
    );

    public static final DeferredItem<Item> DRAGON_SADDLE_ROYAL = ITEMS.register(
            "dragon_saddle_royal",
            () -> new DragonSaddleItem(DragonSaddleItem.SaddleTier.ROYAL, new Item.Properties().stacksTo(1))
    );

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
        modEventBus.addListener(this::commonSetup);

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        LOGGER.info("More Saddles loaded!");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(DragonSurvivalCompat::logStatus);
        LOGGER.info("More Saddles common setup complete.");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // Custom tab already contains the items
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        if (DragonSurvivalCompat.isDragonSurvivalLoaded()) {
            LOGGER.info("Server starting with Dragon Survival + More Saddles.");
        }
    }
}
