package net.herodegon.tutorialmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BoomerangItem extends Item implements ProjectileItem 
{
    private static final float RANGE = 10.0f;
    private static final float SPEED = 1.0f;
    private Vec3d useDir = Vec3d.ZERO;

    public BoomerangItem(Item.Settings settings) {
        super(settings);
    }

    // @Override
    // public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) 
    // {
    //     if (user instanceof PlayerEntity player)
    //     {
    //         Vec3d stopDir = player.getRotationVector().normalize();
    //         double angle = Math.acos(useDir.dotProduct(stopDir));
    //         // Prevent throwing boomerang if the angle is too large
    //         // This is to balance the size of the throwing arc
    //         if (world instanceof ServerWorld serverWorld)
    //         {
    //             if (Math.abs(angle) > Math.PI)
    //             {
    //                 return false;
    //             }
    //         }
    //     }


    //     return true;
    // }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) 
    {
        ItemStack itemStack = user.getStackInHand(hand);
        useDir = user.getRotationVector().normalize();
        if (world instanceof ServerWorld serverWorld) {
			ProjectileEntity.spawnWithVelocity(SnowballEntity::new, serverWorld, itemStack, user, 0.0F, SPEED, 1.0F);
		}
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return ActionResult.SUCCESS;
    }

    @Override
	public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
		return new SnowballEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
	}
}
