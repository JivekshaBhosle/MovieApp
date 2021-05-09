package com.movieapp.domain.model

data class MovieSection(

    var id: String = String(),
    var title: String = String(),
    var href: String = String(),
    var type: String = String(),
    var name: String = String(),
    var templated: Boolean = false,
    var active: Boolean = false

)
