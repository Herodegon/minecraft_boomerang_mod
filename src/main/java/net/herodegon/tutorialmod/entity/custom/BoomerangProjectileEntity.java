package net.herodegon.tutorialmod.entity.custom;

import net.herodegon.tutorialmod.entity.ModEntities;
import net.herodegon.tutorialmod.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class BoomerangProjectileEntity extends PersistentProjectileEntity
{
    private float rotation = 0.0f;
    private static final float RANGE = 10.0f;
    private static final float DAMAGE = 3.0f;

    public BoomerangProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BoomerangProjectileEntity(World world, PlayerEntity player)
    {
        super(ModEntities.BOOMERANG_ENTITY, player, world, new ItemStack(ModItems.BOOMERANG_ITEM), null);
    }

    @Override
    public void tick()
    {
        super.tick();
        float distance = (float) this.getPos().distanceTo(this.getOwner().getPos());
        if (distance > RANGE)
        {
            setVelocity(getVelocity().multiply(-1.0, -1.0, -1.0));
        }
    }

    @Override
    protected ItemStack getDefaultItemStack()
    {
        return new ItemStack(ModItems.BOOMERANG_ITEM);
    }

    public float getRenderingRotation()
    {
        rotation += 0.5f;
        if (rotation >= 360.0f)
        {
            rotation -= 360.0f;
        }
        return rotation;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		Entity entity = entityHitResult.getEntity();
        if (this.getWorld() instanceof ServerWorld serverWorld)
        {
            entity.damage(serverWorld, this.getDamageSources().thrown(this, this.getOwner()), DAMAGE);
        }
	}
}
