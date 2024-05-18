package org.sosly.vlp.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;
import net.minecraftforge.registries.ForgeRegistries;
import org.sosly.vlp.Config;
import org.sosly.vlp.VillagerLiteracyProgram;

@PrefixGameTestTemplate(false)
@GameTestHolder(VillagerLiteracyProgram.MOD_ID)
public class VillagerTests {
    @GameTest(template = "test_5x5", batch = "entity", setupTicks = 1)
    public static void levelUpTest(final GameTestHelper test) throws InterruptedException {
        Level level = test.getLevel();

        Config.levelUpTime = 20;
        EntityType<?> villagerEntityType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation("minecraft:villager"));
        if (villagerEntityType == null) {
            test.fail("Failed to get villager entity type");
            return;
        }
        Villager villager = (Villager) villagerEntityType.create(level);
        if (villager == null) {
            test.fail("Failed to create villager");
            return;
        }
        BlockPos fletchingPos = test.absolutePos(new BlockPos(2, 2, 2));
        BlockPos villagerPos = test.absolutePos(new BlockPos(3, 2, 3));
        level.setBlock(fletchingPos, ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:fletching_table")).defaultBlockState(), 3);
        level.addFreshEntity(villager);
        villager.setVillagerData(new VillagerData(VillagerType.PLAINS, VillagerProfession.FLETCHER, 1));
        villager.setPos(villagerPos.getCenter());
        test.succeedWhen(() -> test.assertTrue(villager.getVillagerData().getLevel() == 2, "Villager did not level up"));
    }
}
