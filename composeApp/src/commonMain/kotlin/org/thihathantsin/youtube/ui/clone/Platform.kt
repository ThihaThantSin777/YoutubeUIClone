package org.thihathantsin.youtube.ui.clone

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform