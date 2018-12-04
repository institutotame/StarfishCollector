package com.atinem.starfishcollector

class StarfishGame : GameBase() {
    override fun create() {
        setActiveScreen(MenuScreen())
    }
    
    companion object {
        fun setActiveScreen(screen: BaseScreen) = GameBase.setActiveScreen(screen)
    }
}