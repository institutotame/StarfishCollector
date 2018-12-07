package com.atinem.starfishcollector

class StarfishGame : BaseGame() {
    override fun create() {
        super.create()
        setActiveScreen(MenuScreen())
    }
    
    companion object {
        fun setActiveScreen(screen: BaseScreen) = BaseGame.setActiveScreen(screen)
    }
}