package net.herodegon.boomerangmod.item.custom;

import net.herodegon.boomerangmod.entity.custom.BoomerangProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BoomerangItem extends Item 
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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) 
    {
        ItemStack itemStack = user.getStackInHand(hand);
        useDir = user.getRotationVector().normalize();
        if (!world.isClient()) {
			BoomerangProjectileEntity boomerang = new BoomerangProjectileEntity(null, user, world);
            boomerang.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, SPEED, 0.0f);
            world.spawnEntity(boomerang);
		}
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
