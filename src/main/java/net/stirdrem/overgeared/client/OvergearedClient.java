package net.stirdrem.overgeared.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.stirdrem.overgeared.block.entity.AbstractSmithingAnvilBlockEntity;
import net.stirdrem.overgeared.block.entity.ModBlockEntities;
import net.stirdrem.overgeared.block.entity.renderer.SmithingAnvilBlockEntityRenderer;
import net.stirdrem.overgeared.screen.AlloySmelterScreen;
import net.stirdrem.overgeared.screen.BlueprintWorkbenchScreen;
import net.stirdrem.overgeared.screen.CastFurnaceScreen;
import net.stirdrem.overgeared.screen.FletchingStationScreen;
import net.stirdrem.overgeared.screen.ModMenuTypes;
import net.stirdrem.overgeared.screen.NetherAlloySmelterScreen;
import net.stirdrem.overgeared.screen.RockKnappingScreen;
import net.stirdrem.overgeared.screen.SteelSmithingAnvilScreen;
import net.stirdrem.overgeared.screen.StoneSmithingAnvilScreen;
import net.stirdrem.overgeared.screen.TierASmithingAnvilScreen;
import net.stirdrem.overgeared.screen.TierBSmithingAnvilScreen;

public class OvergearedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientModMessages.register();
        AnvilMinigameEvents.register();
        AnvilMinigameOverlay.register();
        PopupOverlay.register();
        OvergearedTooltipEvents.register();

        HandledScreens.register(ModMenuTypes.STEEL_SMITHING_ANVIL_MENU, SteelSmithingAnvilScreen::new);
        HandledScreens.register(ModMenuTypes.TIER_A_SMITHING_ANVIL_MENU, TierASmithingAnvilScreen::new);
        HandledScreens.register(ModMenuTypes.TIER_B_SMITHING_ANVIL_MENU, TierBSmithingAnvilScreen::new);
        HandledScreens.register(ModMenuTypes.STONE_SMITHING_ANVIL_MENU, StoneSmithingAnvilScreen::new);
        HandledScreens.register(ModMenuTypes.ALLOY_SMELTER_MENU, AlloySmelterScreen::new);
        HandledScreens.register(ModMenuTypes.NETHER_ALLOY_SMELTER_MENU, NetherAlloySmelterScreen::new);
        HandledScreens.register(ModMenuTypes.CAST_FURNACE, CastFurnaceScreen::new);
        HandledScreens.register(ModMenuTypes.ROCK_KNAPPING_MENU, RockKnappingScreen::new);
        HandledScreens.register(ModMenuTypes.BLUEPRINT_WORKBENCH_MENU, BlueprintWorkbenchScreen::new);
        HandledScreens.register(ModMenuTypes.FLETCHING_STATION_MENU, FletchingStationScreen::new);

        registerAnvilRenderer(ModBlockEntities.STEEL_SMITHING_ANVIL_BE);
        registerAnvilRenderer(ModBlockEntities.TIER_A_SMITHING_ANVIL_BE);
        registerAnvilRenderer(ModBlockEntities.TIER_B_SMITHING_ANVIL_BE);
        registerAnvilRenderer(ModBlockEntities.STONE_SMITHING_ANVIL_BE);
    }

    /**
     * All four anvil tiers share one renderer targeting the abstract base type. Generics are
     * invariant, so BlockEntityRenderer<AbstractSmithingAnvilBlockEntity> can't be handed
     * directly to register(BlockEntityType<E>, BlockEntityRendererFactory<? super E>) for a
     * concrete E - the cast is safe since the renderer only ever touches the abstract type's API.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <E extends AbstractSmithingAnvilBlockEntity> void registerAnvilRenderer(BlockEntityType<E> type) {
        BlockEntityRendererFactory<E> factory = (BlockEntityRendererFactory) (BlockEntityRendererFactory<AbstractSmithingAnvilBlockEntity>) SmithingAnvilBlockEntityRenderer::new;
        BlockEntityRendererRegistry.register(type, factory);
    }
}
