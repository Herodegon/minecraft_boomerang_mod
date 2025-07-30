package net.herodegon.tutorialmod.entity;

import net.herodegon.tutorialmod.TutorialMod;
import net.herodegon.tutorialmod.entity.custom.BoomerangProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BoomerangProjectileEntity> BOOMERANG_ENTITY = register("boomerang_entity", 
        EntityType.Builder.<BoomerangProjectileEntity>create(BoomerangProjectileEntity::new, SpawnGroup.MISC)
            .dimensions(0.5F, 0.5F)
    );
    
    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        RegistryKey<EntityType<?>> entityKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TutorialMod.MOD_ID, id)); 

        EntityType<T> entity = type.build(entityKey);

        Registry.register(Registries.ENTITY_TYPE, entityKey, entity);
        return entity;
	}

    public static void initialize() 
    {
        TutorialMod.LOGGER.info("Registering Mod Entities for " + TutorialMod.MOD_ID);
    }
}
