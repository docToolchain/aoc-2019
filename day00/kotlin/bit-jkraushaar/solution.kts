#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File

val greeting = File("./input.txt").readText()

println("Hello $greeting")