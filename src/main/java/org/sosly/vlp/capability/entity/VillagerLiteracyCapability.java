package org.sosly.vlp.capability.entity;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.sosly.vlp.Config;
import org.sosly.vlp.VillagerLiteracyProgram;
import org.sosly.vlp.api.capability.IVillagerLiteracyCapability;
import org.sosly.vlp.api.entity.IVillager;

@Mod.EventBusSubscriber(modid = VillagerLiteracyProgram.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VillagerLiteracyCapability implements IVillagerLiteracyCapability {
    private long lastLevelUpTick = 0;

    @Override
    public long getLastLevelUpTick() {
        return lastLevelUpTick;
    }

    @Override
    public void setLastLevelUpTick(long time) {
        lastLevelUpTick = time;
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<?> event) {
        if (event.getObject() instanceof Villager) {
            event.addCapability(IVillagerLiteracyCapability.KEY, new VillagerLiteracyProvider());
        }
    }

    @SubscribeEvent
    public static void onVillagerTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Villager villager)) {
            return;
        }

        Level level = villager.level();
        if (level.isClientSide()) {
            return;
        }

        IVillagerLiteracyCapability capability = villager.getCapability(VillagerLiteracyProvider.VILLAGER_LITERACY)
                .orElse(null);
        if (capability == null) {
            return;
        }

        long lastLevelUpTick = capability.getLastLevelUpTick();
        long currentTick = level.getGameTime();
        if (lastLevelUpTick == 0) {
            lastLevelUpTick = currentTick;
            capability.setLastLevelUpTick(lastLevelUpTick);
            return;
        }

        if (currentTick - lastLevelUpTick < Config.levelUpTime) {
            return;
        }

        VillagerData data = villager.getVillagerData();
        if (data.getProfession() == VillagerProfession.NONE) {
            return;
        }

        if (data.getLevel() >= VillagerData.MAX_VILLAGER_LEVEL) {
            return;
        }

        villager.setVillagerData(data.setLevel(data.getLevel() + 1));
        ((IVillager)villager).vlp$UpdateTrades();
        capability.setLastLevelUpTick(currentTick);
    }

    @SubscribeEvent
    public static void onVillagerJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Villager villager)) {
            return;
        }

        IVillagerLiteracyCapability capability = villager.getCapability(VillagerLiteracyProvider.VILLAGER_LITERACY)
                .orElse(null);
        if (capability == null) {
            return;
        }
    }
}
