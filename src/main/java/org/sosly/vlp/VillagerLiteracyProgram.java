package org.sosly.vlp;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sosly.vlp.api.VLPRegistries;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VillagerLiteracyProgram.MOD_ID)
public class VillagerLiteracyProgram {
    public static final String MOD_ID = "vlp";
    public static final Logger LOGGER = LogManager.getLogger(VillagerLiteracyProgram.class);

    public VillagerLiteracyProgram() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        VLPRegistries.BLOCKS.register(modbus);
        VLPRegistries.POI_TYPES.register(modbus);
        VLPRegistries.VILLAGER_PROFESSIONS.register(modbus);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
