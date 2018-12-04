package com.atinem.starfishcollector

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor

open class TexturedActor : Actor() {
    private val textureRegion: TextureRegion = TextureRegion()
    private val rectangle: Rectangle = Rectangle()

    fun setTexture(texture: Texture){
        textureRegion.setRegion(texture)
        setSize(texture.width.toFloat(), texture.height.toFloat())
        rectangle.setSize(texture.width.toFloat(),texture.height.toFloat())
    }

    fun getRectangle(): Rectangle{
        rectangle.setPosition(x,y)
        return rectangle
    }

    fun overlaps(other: TexturedActor): Boolean = this.getRectangle().overlaps(other.getRectangle())

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        batch?.color = Color(color.r, color.g, color.b, color.a)
        if(isVisible){
            batch?.draw(textureRegion, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
        }
    }
}