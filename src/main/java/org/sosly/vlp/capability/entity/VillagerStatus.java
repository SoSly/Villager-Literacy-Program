package org.sosly.vlp.capability.entity;

import org.sosly.vlp.api.capability.IVillagerStatus;

public class VillagerStatus implements IVillagerStatus {
    private long lastLevelUpTick = 0;

    @Override
    public long getLastLevelUpTick() {
        return lastLevelUpTick;
    }

    @Override
    public void setLastLevelUpTick(long time) {
        lastLevelUpTick = time;
    }
}
