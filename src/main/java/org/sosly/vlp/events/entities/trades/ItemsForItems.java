package org.sosly.vlp.events.entities.trades;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;

public class ItemsForItems implements VillagerTrades.ItemListing {
    private final Item get;
    private final Item give;
    private final int cost;
    private final int maxUses;
    private final int value;
    private final int villagerXp;
    private final float priceMultiplier;

    public ItemsForItems(ItemLike get, int cost, ItemLike give, int value, int maxTrades, int exp) {
        this.get = get.asItem();
        this.give = give.asItem();
        this.cost = cost;
        this.maxUses = maxTrades;
        this.value = value;
        this.villagerXp = exp;
        this.priceMultiplier = 0.05F;
    }

    public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
        ItemStack itemstack = new ItemStack(this.get, this.cost);
        ItemStack emeralds = new ItemStack(this.give, this.value);
        return new MerchantOffer(itemstack, emeralds, this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}
