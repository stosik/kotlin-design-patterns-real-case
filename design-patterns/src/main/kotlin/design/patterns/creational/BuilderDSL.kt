package design.patterns.creational

import design.patterns.creational.Payment.Companion.payment
import design.patterns.creational.PaymentType.FAST
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

fun main() {
    val fastTodayPayment = payment {
        from = BankAccount("GB29NWBK60161331926819")
        to = BankAccount("IE29AIBK93115212345678")
        amount = Money(BigDecimal.valueOf(10L), "euro")
        type = FAST
        date = LocalDate.now().atStartOfDay()
    }

    println(fastTodayPayment)
}

data class BankAccount(val iban: String)
data class Money(val amount: BigDecimal, val currency: String) {
    companion object {
        fun zero() = Money(BigDecimal.ZERO, "euro")
    }
}
enum class PaymentType { STANDARD, FAST }

class Payment(
    private val from: BankAccount,
    private val to: BankAccount,
    private val amount: Money,
    private val type: PaymentType,
    private val date: LocalDateTime
) {
    private constructor(builder: Builder) : this(builder.from, builder.to, builder.amount, builder.type, builder.date)

    companion object {
        inline fun payment(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        lateinit var from: BankAccount
        lateinit var to: BankAccount
        var amount: Money = Money.zero()
        var type = PaymentType.STANDARD
        lateinit var date: LocalDateTime

        fun build() = Payment(this)
    }

    override fun toString(): String {
        return "Payment(from=$from, to=$to, amount=$amount, type=$type, date=$date)"
    }
}