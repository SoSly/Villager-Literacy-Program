package org.sosly.vlp.mixin.entity.npc;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.Level;
import org.sosly.vlp.Config;
import org.sosly.vlp.VillagerLiteracyProgram;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager {
    @Shadow public abstract VillagerData getVillagerData();
    @Shadow public abstract void setVillagerData(VillagerData data);
    @Shadow protected abstract void maybeDecayGossip();
    @Shadow protected abstract void updateTrades();

    public VillagerMixin(EntityType<? extends AbstractVillager> type, Level level) {
        super(type, level);
    }

    public void tick() {
        super.tick();
        if (this.getUnhappyCounter() > 0) {
            this.setUnhappyCounter(this.getUnhappyCounter() - 1);
        }

        this.maybeDecayGossip();

        if (this.level().isClientSide) {
            return;
        }

        VillagerData data = this.getVillagerData();
        if (data.getProfession() == VillagerProfession.NONE) {
            return;
        }

        if (data.getLevel() == 5) {
            return;
        }

        long last_level = this.getPersistentData().getLong("vlp_last_level");
        long current_time = this.level().getGameTime();
        if (last_level == 0) {
            last_level = current_time;
            this.getPersistentData().putLong("vlp_last_level", last_level);
            return;
        }
        if (current_time - last_level < Config.levelUpTime) {
            return;
        }

        this.setVillagerData(data.setLevel(data.getLevel() + 1));
        this.getPersistentData().putLong("vlp_last_level", current_time);
        this.updateTrades();
    }
}
