package de.bigbull.vibranium.init.custom.entity;

import de.bigbull.vibranium.init.EntitiesInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TypesInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SWBoatEntity extends Boat {

    private static final EntityDataAccessor<String> WOOD_TYPE = SynchedEntityData.defineId(SWBoatEntity.class, EntityDataSerializers.STRING);

    public SWBoatEntity(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public SWBoatEntity(Level level, double x, double y, double z, String woodType) {
        this(EntitiesInit.SOULWOOD_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.entityData.set(WOOD_TYPE, woodType);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(WOOD_TYPE, TypesInit.SOULWOOD_WOODTYPE.name());
    }

    @Override
    public Item getDropItem() {
        return ItemInit.SOULWOOD_BOAT.get();
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Type", this.getWoodType());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setWoodType(tag.getString("Type"));
    }

    public String getWoodType() {
        return this.entityData.get(WOOD_TYPE);
    }

    public void setWoodType(String woodType) {
        this.entityData.set(WOOD_TYPE, woodType);
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(this.getDropItem());
    }
}
