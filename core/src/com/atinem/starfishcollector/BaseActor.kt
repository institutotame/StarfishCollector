package com.atinem.starfishcollector

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.*
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Array as ArrayGDX

open class BaseActor(x: Float, y: Float, stage: Stage) : Group(){
    var animation: Animation<TextureRegion>? = null
        set(value) {
            field = value
            field?.let {
                val textureRegion = it.getKeyFrame(0f)
                val width = textureRegion.regionWidth.toFloat()
                val height = textureRegion.regionHeight.toFloat()
                setSize(width,height)
                setOrigin(width/2, height/2)
                boundaryPolygon ?: setBoundaryRectangle()
            }
        }
    private var elapsedTime: Float = 0f
    var isAnimationPaused: Boolean = false

    private val velocityVec: Vector2 = Vector2(0f,0f)
    private val accelerationVec: Vector2 = Vector2(0f,0f)
    var acceleration: Float = 0f

    var maxSpeed: Float = 1000f
    var deceleration: Float = 0f





    private var boundaryPolygon: Polygon? = null


    init {
        setPosition(x,y)
        stage.addActor(this)
    }

    override fun act(delta: Float) {
        super.act(delta)
        if(!isAnimationPaused) elapsedTime += delta
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.color = color
        animation?.let {
            if(isVisible){
                batch?.draw(it.getKeyFrame(elapsedTime),x,y,originX,originY,width,height,scaleX,scaleY,rotation)
            }
        }
        super.draw(batch, parentAlpha)
    }

    fun loadAnimationFromFiles(fileName: Array<String>, frameDuration: Float, loop: Boolean): Animation<TextureRegion>{
        val textureList: ArrayGDX<TextureRegion> = ArrayGDX()

        for(i in 0 until fileName.size){
            val texture = Texture(fileName[i])
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
            textureList.add(TextureRegion(texture))
        }

        val anim: Animation<TextureRegion> = Animation(frameDuration,textureList)
        if(loop) anim.playMode = Animation.PlayMode.LOOP
        else anim.playMode = Animation.PlayMode.NORMAL

        if(animation==null) animation = anim


        return anim
    }

    fun loadAnimationFromSheet(
            fileName: String,
            rows: Int,
            cols: Int,
            frameDuration: Float,
            loop: Boolean
    ): Animation<TextureRegion> {
        val filePath = fileName
        val texture = Texture(Gdx.files.internal(filePath),true)
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        val frameWidth = texture.width / cols
        val frameHeight = texture.height / rows

        val temp : Array<Array<TextureRegion>> = TextureRegion.split(texture, frameWidth, frameHeight)

        val textureArray: ArrayGDX<TextureRegion> = ArrayGDX()

        for(r in 0 until rows)
            for(c in 0 until cols)
                textureArray.add(temp[r][c])

        val anim = Animation<TextureRegion>(frameDuration,textureArray)

        if(loop)
            anim.playMode = Animation.PlayMode.LOOP
        else
            anim.playMode = Animation.PlayMode.NORMAL

        if(animation == null) animation = anim

        return anim
    }

    fun loadTexture(fileName: String): Animation<TextureRegion>{
        val fileNames: Array<String> = Array(1){""}
        fileNames[0] = fileName
        return loadAnimationFromFiles(fileNames, 1f, true)
    }

    fun isAnimationFinished(): Boolean = animation?.isAnimationFinished(elapsedTime) ?: false

    fun setSpeed(speed: Float){
        if(velocityVec.len()== 0f)
            velocityVec.set(speed,0f)
        else{
            velocityVec.setLength(speed)
        }
    }

    fun getSpeed(): Float = velocityVec.len()

    fun setMotionAngle(angle: Float) = velocityVec.setAngle(angle)

    fun getMotionAngle(): Float = velocityVec.angle()

    fun isMoving(): Boolean = getSpeed() > 0f

    fun accelerateAtAngle(angle: Float) = accelerationVec.add(Vector2(acceleration,0f).setAngle(angle))

    fun accelerateForward() = accelerateAtAngle(rotation)

    fun applyPhysics(delta: Float){
        // Apply acceleration
        velocityVec.add(accelerationVec.x * delta, accelerationVec.y * delta)

        var speed = getSpeed()

        // Decrease speed (decelerate) when not accelerating
        if(accelerationVec.len() == 0f)
            speed -= deceleration * delta

        // Keep speed within set bounds
        speed = MathUtils.clamp(speed, 0f, maxSpeed)

        setSpeed(speed)

        moveBy(velocityVec.x * delta, velocityVec.y * delta)

        accelerationVec.set(0f,0f)
    }

    fun setBoundaryRectangle(){
        val vertices: FloatArray = floatArrayOf(0f,0f,width,0f,width,height,0f,height)
        boundaryPolygon = Polygon(vertices)
    }

    fun setBoundaryPolygon(numSides: Int){

        val vertices: FloatArray = FloatArray(2*numSides)
        for(i in 0 until numSides){
            val angle = i * 6.28f / numSides
            // x coordinate
            vertices[2*i] = width / 2 * MathUtils.cos(angle) + width / 2
            // y coordinate
            vertices[2*i+1] = height / 2 * MathUtils.sin(angle) + height / 2
        }

        boundaryPolygon = Polygon(vertices)
    }

    fun getBoundaryPolygon(): Polygon? {
        boundaryPolygon?.setPosition(x,y)
        boundaryPolygon?.setOrigin(originX, originY)
        boundaryPolygon?.rotation = rotation
        boundaryPolygon?.setScale(scaleX,scaleY)
        return boundaryPolygon
    }

    fun overlaps(other: BaseActor): Boolean {
        val poly1: Polygon? = this.getBoundaryPolygon()
        val poly2: Polygon? = other.getBoundaryPolygon()

        poly1?.let { letPoly1 ->
            poly2?.let { letPoly2 ->
                if(!letPoly1.boundingRectangle.overlaps(letPoly2.boundingRectangle))
                    return false

                return Intersector.overlapConvexPolygons(poly1, poly2)
            }
        } ?: return false
    }

    fun centerAtPosition(x: Float, y: Float){
        setPosition(x - width / 2, y - height / 2)
    }

    fun centerAtActor(other: BaseActor){
        centerAtPosition(other.x + other.width / 2, other.y + other.height / 2)
    }

    fun setOpacity(opacity: Float) {
        color.a = opacity
    }

    fun preventOverlap(other: BaseActor): Vector2? {
        val poly1 = this.getBoundaryPolygon()
        val poly2 = other.getBoundaryPolygon()

        if (poly1 != null && poly2 != null) {
            if (!poly1.boundingRectangle.overlaps(poly2.boundingRectangle))
                return null

            val mtv = Intersector.MinimumTranslationVector()
            val polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv)

            if (!polygonOverlap)
                return null

            this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth)

            return mtv.normal
        }
        return null
    }

    fun boundToWorld(){
        if(x < 0f)
            x = 0f
        if(x + width > worldBounds.width)
            x = worldBounds.width - width
        if(y < 0f)
            y = 0f
        if(y + height > worldBounds.height)
            y = worldBounds.height - height

    }

    fun alignCamera(){
        val cam = stage.camera

        cam.position.set(x + originX, y + originY, 0f)

        cam.position.x = MathUtils.clamp(
                cam.position.x,
                cam.viewportWidth / 2,
                worldBounds.width - cam.viewportWidth / 2)

        cam.position.y = MathUtils.clamp(
                cam.position.y,
                cam.viewportHeight / 2,
                worldBounds.height - cam.viewportHeight / 2)

        cam.update()
    }

    fun wrapAroundWorld(){
        if(x + width < 0f)
            x = worldBounds.width
        if(x > worldBounds.width)
            x = -width
        if(y + height < 0)
            y = worldBounds.height
        if(y > worldBounds.height)
            y = -height
    }

    companion object {

        private const val PACKAGENAME: String = "com.atinem.starfishcollector"

        private lateinit var worldBounds: Rectangle

        fun getList(stage: Stage, className: String): MutableList<BaseActor> {
            val list = mutableListOf<BaseActor>()
            try{
                val theClass = Class.forName("$PACKAGENAME.$className")
                for(actor in stage.actors){
                    if(theClass.isInstance(actor))
                        list.add(actor as BaseActor)
                }
            } catch (error: ClassNotFoundException){
                error.printStackTrace()
            }
            return list
        }

        fun count(stage: Stage, className: String): Int{
            return getList(stage, className).size
        }

        fun setWorldBounds(width: Float, height: Float){
            worldBounds = Rectangle(0f,0f,width,height)
        }

        fun setWorldBounds(actor: BaseActor) = setWorldBounds(actor.width,actor.height)

    }
}