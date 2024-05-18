package org.sosly.vlp.events.entities;

import net.minecraft.world.item.Items;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.sosly.vlp.VillagerLiteracyProgram;
import org.sosly.vlp.entities.VLPVillagerProfessions;
import org.sosly.vlp.events.entities.trades.ItemsForItems;

@Mod.EventBusSubscriber(modid = VillagerLiteracyProgram.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VillagerTradeEventHandler {
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        if (event.getType().equals(VLPVillagerProfessions.METALWORKER.get())) {
            addMetalworkerTrades(event);
        }
    }

    private static void addMetalworkerTrades(VillagerTradesEvent event) {
        event.getTrades().get(1).add(new ItemsForItems(Items.COAL, 16, Items.EMERALD, 1, 2, 1));
        event.getTrades().get(1).add(new ItemsForItems(Items.RAW_COPPER, 1, Items.EMERALD, 1, 2, 1));
        event.getTrades().get(1).add(new ItemsForItems(Items.RAW_COPPER_BLOCK, 1, Items.EMERALD, 7, 2, 1));
        event.getTrades().get(1).add(new ItemsForItems(Items.EMERALD, 2, Items.COPPER_INGOT, 1, 2, 1));
        event.getTrades().get(2).add(new ItemsForItems(Items.RAW_IRON, 1, Items.EMERALD, 2, 2, 1));
        event.getTrades().get(2).add(new ItemsForItems(Items.RAW_IRON_BLOCK, 1, Items.EMERALD, 14, 2, 1));
        event.getTrades().get(2).add(new ItemsForItems(Items.EMERALD, 4, Items.IRON_INGOT, 1, 2, 1));
        event.getTrades().get(3).add(new ItemsForItems(Items.RAW_GOLD, 1, Items.EMERALD, 3, 2, 1));
        event.getTrades().get(3).add(new ItemsForItems(Items.RAW_GOLD_BLOCK, 1, Items.EMERALD, 21, 2, 1));
        event.getTrades().get(3).add(new ItemsForItems(Items.EMERALD, 6, Items.GOLD_INGOT, 1, 2, 1));
    }
}
