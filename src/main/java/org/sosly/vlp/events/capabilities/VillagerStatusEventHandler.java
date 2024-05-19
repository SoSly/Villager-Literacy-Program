package org.sosly.vlp.events.capabilities;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.sosly.vlp.VillagerLiteracyProgram;
import org.sosly.vlp.api.capability.IVillagerStatus;

@Mod.EventBusSubscriber(modid = VillagerLiteracyProgram.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VillagerStatusEventHandler {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IVillagerStatus.class);
    }
}
