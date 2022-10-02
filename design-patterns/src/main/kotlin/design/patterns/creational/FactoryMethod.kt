package design.patterns.creational

import design.patterns.creational.Country.*
import design.patterns.creational.Currency.*

fun main() {
    println(currency(Spain).print())
    println(currency(UnitedStates).print())
    println(currency(UK).print())
}

enum class Country {
    UnitedStates, Spain, UK, Greece
}

fun currency(country: Country): Currency = when (country) {
    Spain, Greece -> Euro
    UnitedStates -> Dollar
    UK -> Pound
}

sealed class Currency(private val symbol: String) {
    object Euro: Currency("€")
    object Dollar: Currency("$")
    object Pound: Currency("£")

    fun print() = "Currency symbol: $symbol"
}

