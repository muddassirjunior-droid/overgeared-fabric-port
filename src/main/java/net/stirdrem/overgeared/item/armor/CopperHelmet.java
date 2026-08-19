package net.stirdrem.overgeared.item.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

// TODO(client-pass): original Forge item uses IClientItemExtensions to swap in a custom
// Blockbench-authored armor model (CustomCopperHelmet) instead of vanilla's humanoid model.
// Fabric's equivalent is net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer,
// registered client-side for this item - not wired up yet.
public class CopperHelmet extends ArmorItem {
    public CopperHelmet(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }
}
