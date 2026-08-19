package net.stirdrem.overgeared;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.stirdrem.overgeared.config.ClientConfig;
import net.stirdrem.overgeared.config.ServerConfig;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Overgeared implements ModInitializer {
    public static final String MOD_ID = "overgeared";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Nullable
    private static MinecraftServer server;

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    /**
     * Fabric equivalent of Forge's ServerLifecycleHooks.getCurrentServer() - used sparingly,
     * only where a recipe/handler doesn't already have a World reference to work from.
     */
    @Nullable
    public static MinecraftServer getServer() {
        return server;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Overgeared: initializing Fabric port");

        // Must run before any other registration: several classes (BlueprintQuality,
        // ModToolTiers, item constructors) read config values in static initializers,
        // which run the first time those classes are touched.
        ServerConfig.loadConfig(FabricLoader.getInstance().getConfigDir().resolve("overgeared-server.json"));
        ClientConfig.loadConfig(ClientConfig.CLIENT_CONFIG, FabricLoader.getInstance().getConfigDir().resolve("overgeared-client.json"));

        ServerLifecycleEvents.SERVER_STARTED.register(s -> server = s);
        ServerLifecycleEvents.SERVER_STOPPED.register(s -> server = null);
    }
}
