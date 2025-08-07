package net.herodegon.boomerangmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.herodegon.boomerangmod.BoomerangMod;
import net.herodegon.boomerangmod.entity.custom.BoomerangProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BoomerangProjectileEntity> BOOMERANG_ENTITY = register("boomerang_entity", 
        FabricEntityTypeBuilder.<BoomerangProjectileEntity>create(SpawnGroup.MISC, BoomerangProjectileEntity::new)
            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
    );
    
    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> type) {
        RegistryKey<EntityType<?>> entityKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BoomerangMod.MOD_ID, id)); 

        EntityType<T> entity = type.build();

        Registry.register(Registries.ENTITY_TYPE, entityKey, entity);
        return entity;
	}

    public static void initialize() 
    {
        BoomerangMod.LOGGER.info("Registering Mod Entities for " + BoomerangMod.MOD_ID);
    }
}
