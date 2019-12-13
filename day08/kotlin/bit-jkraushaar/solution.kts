#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File

// tag::firstStar[]
val encodedImage = File("./input.txt").readText().toCharArray().toList();

val layers = encodedImage.chunked(25 * 6)

val layerWithLeastZeros = layers.withIndex().map { (index, layer) -> index to layer.count { it == '0' } }.minBy { it.second }
val theLayer = layers[layerWithLeastZeros!!.first]

val oneDigits = theLayer.count { it == '1' }
val twoDigits = theLayer.count { it == '2' }

println("First star: ${oneDigits * twoDigits}")
// end::firstStar[]

// tag::secondStar[]
fun mergeLayers(frontLayer: List<Char>, backLayer: List<Char>) = frontLayer.zip(backLayer)
    .map { (frontPixel, backPixel) -> if (frontPixel == '2') backPixel else frontPixel }

val reducedLayer = layers.reduce { front, back -> mergeLayers(front, back) }

val rows = reducedLayer.chunked(25)
for (row in rows) {
    row.map { pixel -> if (pixel == '1') '*' else ' ' }.forEach { print(it)}
    println()
}
// end::secondStar[]