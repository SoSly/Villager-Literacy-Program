package org.sosly.vlp.events.entities;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.sosly.vlp.Config;
import org.sosly.vlp.VillagerLiteracyProgram;
import org.sosly.vlp.api.capability.IVillagerStatus;
import org.sosly.vlp.api.entity.IVillager;
import org.sosly.vlp.capabilities.entities.VillagerStatus;
import org.sosly.vlp.capabilities.entities.VillagerStatusProvider;

@Mod.EventBusSubscriber(modid = VillagerLiteracyProgram.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VillagerEventHandler {
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<?> event) {
        if (event.getObject() instanceof Villager) {
            event.addCapability(IVillagerStatus.CAPABILITY, new VillagerStatusProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        if (player == null) {
            return;
        }

        if (!(event.getTarget() instanceof Villager villager)) {
            return;
        }

        // since we have a villager, let's stop the usual interact from happening
        event.setCanceled(true);
        // todo: open villager inventory gui
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

        IVillagerStatus capability = villager.getCapability(VillagerStatusProvider.STATUS)
                .orElse(new VillagerStatus());

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
}
