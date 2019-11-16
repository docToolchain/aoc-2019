#!/usr/bin/env node

const fs = require('fs')

const content = fs.readFileSync('./input.txt', 'utf8')

console.log(`Hello  ${content}`)
