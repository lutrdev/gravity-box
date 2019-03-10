/*
 * This file is part of Gravity Box.
 *
 * Gravity Box is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Gravity Box is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Gravity Box.  If not, see <https://www.gnu.org/licenses/>.
 */

package ro.luca1152.gravitybox.events

import com.badlogic.ashley.signals.Listener
import com.badlogic.ashley.signals.Signal
import java.util.*

class EventQueue : Listener<GameEvent> {
    private val eventQueue = PriorityQueue<GameEvent>()
    private var events: Array<GameEvent?> = arrayOfNulls(25)

    fun getEvents(): Array<GameEvent?> {
        for (i in 0 until events.size)
            events[i] = null
        for (i in 0 until eventQueue.size)
            events[i] = eventQueue.elementAt(i)
        eventQueue.clear()
        return events
    }

    override fun receive(signal: Signal<GameEvent>?, event: GameEvent) {
        eventQueue.add(event)
    }
}