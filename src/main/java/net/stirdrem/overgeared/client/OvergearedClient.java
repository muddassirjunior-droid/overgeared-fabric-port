package net.stirdrem.overgeared.client;

import net.fabricmc.api.ClientModInitializer;

public class OvergearedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientModMessages.register();
        AnvilMinigameEvents.register();
    }
}
