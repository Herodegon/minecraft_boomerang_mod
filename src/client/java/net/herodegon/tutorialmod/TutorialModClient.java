package net.herodegon.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.herodegon.tutorialmod.entity.ModEntities;
import net.herodegon.tutorialmod.entity.client.BoomerangProjectileModel;
import net.herodegon.tutorialmod.entity.client.BoomerangProjectileRenderer;

public class TutorialModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		EntityModelLayerRegistry.registerModelLayer(BoomerangProjectileModel.BOOMERANG_ENTITY, BoomerangProjectileModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.BOOMERANG_ENTITY, BoomerangProjectileRenderer::new);
	}
}