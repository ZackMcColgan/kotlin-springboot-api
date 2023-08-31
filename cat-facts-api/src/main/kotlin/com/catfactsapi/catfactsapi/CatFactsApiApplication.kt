package com.catfactsapi.catfactsapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class CatFactsApiApplication

fun main(args: Array<String>) {
	runApplication<CatFactsApiApplication>(*args)
}
