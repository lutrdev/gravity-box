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

package ro.luca1152.gravitybox.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Pool.Poolable
import ro.luca1152.gravitybox.components.utils.ComponentResolver

/**
 * Used to detect when the player is in the finish point.
 * Box2D collisions can't be used because the player doesn't collide with the finish point.
 */
class CollisionBoxComponent : Component, Poolable {
    // Initialized with an empty rectangle to avoid nullable type
    val box = Rectangle()

    var size = 0f

    /** Initializes the component. */
    fun set(size: Float) {
        this.size = size
        box.setSize(size)
    }

    /** Resets the component for reuse. */
    override fun reset() {
        box.set(0f, 0f, 0f, 0f)
    }

    companion object : ComponentResolver<CollisionBoxComponent>(CollisionBoxComponent::class.java)
}

val Entity.collisionBox: CollisionBoxComponent
    get() = CollisionBoxComponent[this]