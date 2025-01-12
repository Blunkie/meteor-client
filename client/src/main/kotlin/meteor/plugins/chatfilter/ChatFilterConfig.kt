/*
 * Copyright (c) 2018, Magic fTail
 * Copyright (c) 2019, osrs-music-map <osrs-music-map@users.noreply.github.com>
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
package meteor.plugins.chatfilter

import meteor.config.legacy.*

@ConfigGroup("chatfilter")
interface ChatFilterConfig : Config {
    @ConfigItem(
        keyName = "filteredWords",
        name = "Filtered Words",
        description = "List of filtered words, separated by commas",
        textField = true,
        position = 1,
        section = "Filter Lists"
    )
    fun filteredWords(): String? {
        return ""
    }

    @ConfigItem(
        keyName = "filteredRegex",
        name = "Filtered Regex",
        description = "List of regular expressions to filter, one per line",
        textField = true,
        position = 2,
        section = "Filter Lists"
    )
    fun filteredRegex(): String? {
        return ""
    }

    @ConfigItem(
        keyName = "filteredNames",
        name = "Filtered Names",
        description = "List of filtered names, one per line. Accepts regular expressions",
        textField = true,
        position = 3,
        section = "Filter Lists"
    )
    fun filteredNames(): String? {
        return ""
    }

    @ConfigItem(
        keyName = "filterType",
        name = "Filter type",
        description = "Configures how the messages are filtered",
        position = 4
    )
    fun filterType(): ChatFilterType? {
        return ChatFilterType.CENSOR_WORDS
    }

    @ConfigItem(
        keyName = "filterFriends",
        name = "Filter Friends",
        description = "Filter your friends' messages",
        position = 5
    )
    fun filterFriends(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "filterClan",
        name = "Filter Friends Chat Members",
        description = "Filter your friends chat members' messages",
        position = 6
    )
    fun filterFriendsChat(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "filterClanChat",
        name = "Filter clan Chat Members",
        description = "Filter your clan chat members' messages",
        position = 7
    )
    fun filterClanChat(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "filterGameChat",
        name = "Filter Game Chat",
        description = "Filter your game chat messages",
        position = 9
    )
    fun filterGameChat(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "collapseGameChat",
        name = "Collapse Game Chat",
        description = "Collapse duplicate game chat messages into a single line",
        position = 10
    )
    fun collapseGameChat(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "collapsePlayerChat",
        name = "Collapse Player Chat",
        description = "Collapse duplicate player chat messages into a single line",
        position = 11
    )
    fun collapsePlayerChat(): Boolean {
        return false
    }

    @Range(textInput = true)
    @ConfigItem(
        keyName = "maxRepeatedPublicChats",
        name = "Repeat filter",
        description = "Block player chat message if repeated this many times. 0 is off",
        position = 12
    )
    fun maxRepeatedPublicChats(): Int {
        return 0
    }


    @ConfigSection(
        name = "Filter Lists",
        description = "Custom Word, Regex, and Username filter lists",
        position = 0,
        closedByDefault = true
    )
    val FilterLists: String

}