package dev.hoot.api;

import meteor.config.legacy.Config;
import meteor.config.legacy.ConfigGroup;
import meteor.config.legacy.ConfigItem;
import meteor.config.legacy.ConfigSection;

@ConfigGroup("interaction")
public interface InteractionConfig extends Config
{
	@ConfigSection(
			name = "Interaction manager",
			description = "Interaction settings",
			position = 0
	)
	String interactionManager = "Interaction manager";

	@ConfigItem(
			keyName = "interactMethod",
			name = "Interact method",
			description = "Interaction method",
			section = interactionManager,
			position = 1
	)
	default InteractMethod interactMethod()
	{
		return InteractMethod.PACKETS;
	}

	@ConfigItem(
			keyName = "naturalMouse",
			name = "Natural mouse",
			description = "Uses the 'natural mouse' algorithm to move and click",
			section = interactionManager,
			position = 2
	)
	default boolean naturalMouse()
	{
		return false;
	}

//	@ConfigItem(
//			keyName = "mouseOffScreen",
//			name = "Mouse off screen when idle",
//			description = "Move the mouse off screen if idle",
//			section = interactionManager,
//			position = 3
//	)
//	default boolean mouseOffScreen()
//	{
//		return false;
//	}

	@ConfigItem(
			keyName = "mouseBehavior",
			name = "Mouse behavior",
			description = "Type of clicks to send to the server",
			section = interactionManager,
			position = 4
	)
	default MouseBehavior mouseBehavior()
	{
		return MouseBehavior.CLICKBOXES;
	}

	@ConfigItem(
			keyName = "drawMouse",
			name = "Draw mouse events",
			description = "Draws the sent mouse events on screen",
			section = interactionManager,
			position = 5
	)
	default boolean drawMouse()
	{
		return false;
	}
}
