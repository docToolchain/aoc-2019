package com.github.corneil.aoc2019.day8

import java.io.File

typealias ImageLayer = List<Int>

data class Image(val width: Int, val height: Int, val layers: List<ImageLayer>)

fun readImage(width: Int, height: Int, input: String): Image {
    val layersCount = input.length / (width * height)
    val layers = (0 until layersCount).map { l ->
        input.substring(l * width * height, (l + 1) * width * height).map {
            "$it".toInt()
        }
    }
    return Image(width, height, layers)
}

fun printImage(image: Image) {
    for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            val offset = x + (y * image.width)
            val pixel = (0 until image.layers.size).map { image.layers[it][offset] }.find { it != 2 }
            print(if (pixel == 1) "*" else " ")
        }
        println()
    }
    println()
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val image = readImage(25, 6, File(fileName).readText().trim())
    println("Layers=${image.layers.size}")
    val minZeros = image.layers.minBy { it.count { it == 0 } }
    requireNotNull(minZeros) { "Expected to find a layer with 0" }
    val result = minZeros.count { it == 1 } * minZeros.count { it == 2 }
    println("Result = $result")
    printImage(image)
}
