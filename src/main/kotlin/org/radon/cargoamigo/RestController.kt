package org.radon.cargoamigo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class RestController {

    @GetMapping("/landing")
    fun helloworld(): String = "Hello World"

}