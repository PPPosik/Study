fun chapter823() {
    val expr = Mul(Plus(Const(1), Const(2)), Const(3))

    println(expr) // Mul(op1=Plus(op1=Const(num=1), op2=Const(num=2)), op2=Const(num=3))
    println(expr.eval()) // 9
}

sealed class Expr

data class Const(val num: Int) : Expr()
data class Neg(val operand: Expr) : Expr()
data class Plus(val op1: Expr, val op2: Expr) : Expr()
data class Mul(val op1: Expr, val op2: Expr) : Expr()

fun Expr.eval(): Int = when (this) {
    is Const -> num
    is Neg -> -operand.eval()
    is Plus -> op1.eval() + op2.eval()
    is Mul -> op1.eval() * op2.eval()
    else -> throw Exception()
}