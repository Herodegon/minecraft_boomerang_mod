package net.herodegon.boomerangmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.herodegon.boomerangmod.entity.ModEntities;
import net.herodegon.boomerangmod.entity.client.BoomerangProjectileModel;
import net.herodegon.boomerangmod.entity.client.BoomerangProjectileRenderer;

public class BoomerangModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		EntityModelLayerRegistry.registerModelLayer(BoomerangProjectileModel.BOOMERANG_ENTITY, BoomerangProjectileModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.BOOMERANG_ENTITY, BoomerangProjectileRenderer::new);
	}
}