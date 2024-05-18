package org.sosly.vlp.entities.professions;


import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.sosly.vlp.VillagerLiteracyProgram;

@PrefixGameTestTemplate(false)
@GameTestHolder(VillagerLiteracyProgram.MOD_ID)
public class FletcherTests extends AbstractVillagerProfessionTests {

    @GameTest(template = "test_3x3", batch = BATCH, timeoutTicks = 200)
    public static void fletcherTest(final @NotNull GameTestHelper test) throws InterruptedException {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:fletching_table"));
        VillagerProfession profession = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("minecraft:fletcher"));

        test.assertTrue(block != null, "Fletching table block not found"); assert block != null;
        test.assertTrue(profession != null, "Fletcher profession not found"); assert profession != null;
        testLevelUpForProfession(test, block, profession);
    }
}
