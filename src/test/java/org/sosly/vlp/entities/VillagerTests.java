package org.sosly.vlp.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.AfterBatch;
import net.minecraft.gametest.framework.BeforeBatch;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.sosly.vlp.Config;
import org.sosly.vlp.VillagerLiteracyProgram;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@PrefixGameTestTemplate(false)
@GameTestHolder(VillagerLiteracyProgram.MOD_ID)
public class VillagerTests {
    @BeforeBatch(batch = "villager")
    public static void beforeEntityBatch(Level level) {
        Config.levelUpTime = 5;
    }

    @AfterBatch(batch = "villager")
    public static void afterEntityBatch(Level level) {
        Config.levelUpTime = 24000;
    }

    @GameTest(template = "test_3x3", batch = "villager", requiredSuccesses = 4, attempts = 5)
    public static void levelUpTest(final @NotNull GameTestHelper test) throws InterruptedException {
        Level level = test.getLevel();
        Villager villager = EntityType.VILLAGER.create(level);
        if (villager == null) {
            test.fail("Failed to create villager");
            return;
        }
        BlockPos fletchingPos = test.absolutePos(new BlockPos(2, 2, 2));
        BlockPos villagerPos = test.absolutePos(new BlockPos(2, 3, 2));
        level.setBlock(fletchingPos, ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:fletching_table")).defaultBlockState(), 3);
        level.addFreshEntity(villager);
        villager.setPos(villagerPos.getCenter());

        AtomicReference<MerchantOffers> initialOffers = new AtomicReference<>(new MerchantOffers());
        AtomicBoolean ready = new AtomicBoolean(false);
        test.onEachTick(() -> {
            if (!ready.get() && villager.getVillagerData().getProfession() != VillagerProfession.NONE) {
                test.assertTrue(villager.getVillagerData().getLevel() == 1, "Villager leveled before initialization");
                test.assertTrue(villager.getOffers().size() == 2, "Villager has too many offers before initialization");
                MerchantOffers offers = villager.getOffers();
                offers.forEach(initialOffers.get()::add);
                ready.set(true);
            }
        });

        test.succeedWhen(() -> {
            test.assertTrue(ready.get(), "Villager did not initialize");
            test.assertTrue(villager.getVillagerData().getLevel() > 1, "Villager did not level up");
            test.assertTrue(villager.getOffers() != initialOffers.get(), "Villager did not refresh offers");
        });
    }
}
