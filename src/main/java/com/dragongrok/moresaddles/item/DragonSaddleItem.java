package com.dragongrok.moresaddles.item;

import com.dragongrok.moresaddles.compat.DragonSurvivalCompat;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Custom saddle item that reacts to Dragon Survival state.
 */
public class DragonSaddleItem extends Item {

    private final SaddleTier tier;

    public enum SaddleTier {
        BASIC("basic"),
        FANCY("fancy"),
        ROYAL("royal");

        public final String id;

        SaddleTier(String id) {
            this.id = id;
        }
    }

    public DragonSaddleItem(SaddleTier tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

    public SaddleTier getTier() {
        return tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (DragonSurvivalCompat.isDragonSurvivalLoaded()) {
                if (DragonSurvivalCompat.isDragon(player)) {
                    player.displayClientMessage(
                            Component.translatable("message.moresaddles.equipped." + tier.id),
                            true
                    );
                    // Future: apply real visual / ability effect when DS exposes a stable skin/armor API
                } else {
                    player.displayClientMessage(
                            Component.translatable("message.moresaddles.need_dragon"),
                            true
                    );
                }
            } else {
                player.displayClientMessage(
                        Component.translatable("message.moresaddles.no_ds"),
                        true
                );
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.moresaddles." + tier.id));
        if (DragonSurvivalCompat.isDragonSurvivalLoaded()) {
            tooltip.add(Component.translatable("tooltip.moresaddles.ds_linked"));
        } else {
            tooltip.add(Component.translatable("tooltip.moresaddles.ds_missing"));
        }
    }
}
