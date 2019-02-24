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

package ro.luca1152.gravitybox.systems.game

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.gdx.physics.box2d.BodyDef
import ro.luca1152.gravitybox.components.*
import ro.luca1152.gravitybox.components.utils.tryGet
import ro.luca1152.gravitybox.entities.game.PlayerEntity

/** Adds Box2D bodies to every map. It is called every time the level changes. */
class MapCreationSystem(private val levelEntity: Entity) : EntitySystem() {
    private val shouldUpdateMap
        get() = levelEntity.level.forceUpdateMap || (levelEntity.newMap.levelNumber != levelEntity.level.levelNumber)

    override fun update(deltaTime: Float) {
        if (!shouldUpdateMap)
            return

        if (levelEntity.level.forceUpdateMap)
            levelEntity.level.forceUpdateMap = false

        updateMap()
    }

    private fun updateMap() {
        levelEntity.newMap.run {
            reset()
            levelNumber = levelEntity.level.levelNumber
            createBox2DBodies()
        }
    }

    private fun createBox2DBodies() {
        val mapObjects = engine.getEntitiesFor(Family.all(NewMapObjectComponent::class.java).exclude(DeletedMapObjectComponent::class.java).get())
        mapObjects.forEach {
            when {
                it.tryGet(PlatformComponent) != null -> it.body.set(it.image.imageToBox2DBody(BodyDef.BodyType.StaticBody), it)
                it.tryGet(PlayerComponent) != null -> it.body.set(
                        it.image.imageToBox2DBody(BodyDef.BodyType.DynamicBody, PlayerEntity.FRICTION, PlayerEntity.DENSITY),
                        it, PlayerEntity.FRICTION, PlayerEntity.DENSITY)
            }
        }
    }
}