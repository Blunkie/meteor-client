/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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
package meteor.plugins.xptracker

import net.runelite.api.Client
import net.runelite.api.Varbits
import net.runelite.api.WorldType

internal enum class XpWorldType {
    NORMAL, TOURNEY, DMM {
        override fun modifier(client: Client): Int {
            return 5
        }
    },
    LEAGUE {
        override fun modifier(client: Client): Int {
            if (client.getVarbitValue(Varbits.LEAGUE_RELIC_6) != 0) {
                return 16
            }
            if (client.getVarbitValue(Varbits.LEAGUE_RELIC_4) != 0) {
                return 12
            }
            return if (client.getVarbitValue(Varbits.LEAGUE_RELIC_2) != 0) {
                8
            } else 5
        }
    };

    open fun modifier(client: Client): Int {
        return 1
    }

    companion object {
        fun of(type: WorldType?): XpWorldType {
            return when (type) {
                WorldType.NOSAVE_MODE -> TOURNEY
                WorldType.DEADMAN -> DMM
                WorldType.SEASONAL -> LEAGUE
                else -> NORMAL
            }
        }
    }
}