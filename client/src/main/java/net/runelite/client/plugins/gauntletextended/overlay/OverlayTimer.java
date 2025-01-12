/*
 * Copyright (c) 2020, dutta64 <https://github.com/dutta64>
 * Copyright (c) 2018, Seth <http://github.com/sethtroll>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.runelite.client.plugins.gauntletextended.overlay;

import meteor.Main;
import meteor.ui.overlay.OverlayLayer;
import meteor.ui.overlay.OverlayPosition;
import meteor.ui.overlay.OverlayPriority;
import meteor.ui.overlay.PanelComponent;
import meteor.ui.table.TableAlignment;
import meteor.ui.table.TableComponent;
import meteor.ui.table.TableElement;
import meteor.ui.table.TableRow;
import meteor.ui.table.TitleComponent;
import net.runelite.api.ChatMessageType;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import meteor.chat.QueuedMessage;
import net.runelite.client.plugins.gauntletextended.GauntletExtendedConfig;
import net.runelite.client.plugins.gauntletextended.GauntletExtendedPlugin;
import org.jetbrains.annotations.NotNull;
import org.rationalityfrontline.kevent.KEvent;

import java.awt.*;
import java.time.Instant;
import java.util.Arrays;

public class OverlayTimer extends Overlay
{
	private final GauntletExtendedConfig config;
	private final ChatMessageManager chatMessageManager = Main.INSTANCE.getChatMessageManager();

	private final PanelComponent panelComponent;
	private final TableComponent tableComponent;

	private final TableRow rowPrepTime;
	private final TableRow rowTotalTime;

	private long timeGauntletStart;
	private long timeHunllefStart;

	public OverlayTimer(final GauntletExtendedPlugin plugin, final GauntletExtendedConfig config)
	{
		super(plugin);

		this.config = config;

		this.panelComponent = new PanelComponent();
		this.tableComponent = new TableComponent();

		panelComponent.getChildren().add(TitleComponent.Companion.builder().text("Gauntlet Timer"));
		panelComponent.getChildren().add(tableComponent);

		tableComponent.setColumnAlignments(TableAlignment.LEFT, TableAlignment.RIGHT);

		this.rowPrepTime = TableRow.Companion.builder()
			.elements(Arrays.asList(
				TableElement.Companion.builder()
					.content("Prep Time:"),
					TableElement.Companion.builder()
					.content("")));

		this.rowTotalTime = TableRow.Companion.builder()
			.elements(Arrays.asList(
					TableElement.Companion.builder()
					.content("Total Time:"),
					TableElement.Companion.builder()
					.content("")));

		this.timeGauntletStart = -1;
		this.timeHunllefStart = -1;

		setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
		setPriority(OverlayPriority.HIGH);
		determineLayer();
	}

	@Override
	public Dimension render(final Graphics2D graphics2D)
	{
		if (!config.timerOverlay() || timeGauntletStart == -1)
		{
			return null;
		}

		final TableRow tableRow = timeHunllefStart == -1 ? rowPrepTime : rowTotalTime;

		tableRow.getElements()
			.get(1)
			.setContent(calculateElapsedTime(Instant.now().getEpochSecond(), timeGauntletStart));

		return panelComponent.render(graphics2D);
	}

	public void determineLayer()
	{
		setLayer(OverlayLayer.UNDER_WIDGETS);
	}

	public void reset()
	{
		timeGauntletStart = -1;
		timeHunllefStart = -1;
		rowPrepTime.getElements().get(1).setContent("");
		rowTotalTime.getElements().get(1).setContent("");
		tableComponent.getRows().clear();
	}

	public void setGauntletStart()
	{
		timeGauntletStart = Instant.now().getEpochSecond();
		rowPrepTime.setRowColor(Color.WHITE);
		tableComponent.setRows(rowPrepTime);
	}

	public void setHunllefStart()
	{
		timeHunllefStart = Instant.now().getEpochSecond();
		rowPrepTime.setRowColor(Color.LIGHT_GRAY);
		tableComponent.setRows(rowPrepTime);
		tableComponent.addRows(rowTotalTime);
	}

	public void onPlayerDeath()
	{
		if (!config.timerChatMessage())
		{
			return;
		}

		printTime();
		reset();
	}

	private void printTime()
	{
		if (timeGauntletStart == -1 || timeHunllefStart == -1)
		{
			return;
		}

		final long current = Instant.now().getEpochSecond();

		final String elapsedPrepTime = calculateElapsedTime(timeHunllefStart, timeGauntletStart);
		final String elapsedBossTime = calculateElapsedTime(current, timeHunllefStart);
		final String elapsedTotalTime = calculateElapsedTime(current, timeGauntletStart);

		final ChatMessageBuilder chatMessageBuilder = new ChatMessageBuilder()
			.append(ChatColorType.NORMAL)
			.append("Preparation time: ")
			.append(ChatColorType.HIGHLIGHT)
			.append(elapsedPrepTime)
			.append(ChatColorType.NORMAL)
			.append(". Hunllef kill time: ")
			.append(ChatColorType.HIGHLIGHT)
			.append(elapsedBossTime)
			.append(ChatColorType.NORMAL)
			.append(". Total time: ")
			.append(ChatColorType.HIGHLIGHT)
			.append(elapsedTotalTime + ".");

		chatMessageManager.queue(QueuedMessage.Companion.builder()
			.type(ChatMessageType.CONSOLE)
			.runeLiteFormattedMessage(chatMessageBuilder.build()));
	}

	private static String calculateElapsedTime(final long end, final long start)
	{
		final long elapsed = end - start;

		final long minutes = elapsed % 3600 / 60;
		final long seconds = elapsed % 60;

		return String.format("%01d:%02d", minutes, seconds);
	}

	@NotNull
	@Override
	public KEvent getKEVENT_INSTANCE() {
		return Main.INSTANCE.getEventBus();
	}

	@NotNull
	@Override
	public String getSUBSCRIBER_TAG() {
		return "overlayTimer";
	}
}
