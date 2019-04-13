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

package ro.luca1152.gravitybox.components.game

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.utils.Pool.Poolable
import ro.luca1152.gravitybox.components.ComponentResolver
import ro.luca1152.gravitybox.utils.kotlin.createComponent

/** Indicates that a map object is a rotating one. */
class RotatingObjectComponent : Component, Poolable {
    companion object : ComponentResolver<RotatingObjectComponent>(RotatingObjectComponent::class.java) {
        const val SPEED = .4f
    }

    var speed = SPEED

    override fun reset() {
        speed = SPEED
    }
}

val Entity.rotatingObject: RotatingObjectComponent
    get() = RotatingObjectComponent[this]

fun Entity.rotatingObject() =
    add(createComponent<RotatingObjectComponent>())!!