package org.sosly.vlp;

import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

@PrefixGameTestTemplate(false)
@GameTestHolder(VillagerLiteracyProgram.MOD_ID)
public class VillagerLiteracyProgramTests {
    @GameTest(template = "bedrock_5x5", batch = "mod_load")
    public static void doTest(final GameTestHelper test) {
        Level level = test.getLevel();
        MinecraftServer server = level.getServer();

        test.succeedIf(() -> ModList.get().isLoaded(VillagerLiteracyProgram.MOD_ID));
    }
}
