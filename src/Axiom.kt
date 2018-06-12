import java.util.HashMap

/**
 * Created by ospen on 7/11/2017.
 */
class Axiom(private val axiom: Expression) {

    constructor(str: String) : this(Parser.parse(str))

    fun isAxiom(exp: Expression): Boolean {
        val substitutes = HashMap<Variable, Expression>()
        return substituteVars(axiom, exp, substitutes)
    }

    private fun substituteVars(ax: Expression, exp: Expression, substitutes: HashMap<Variable, Expression>): Boolean {
        when {
            ax::class == Variable::class ->
                if (substitutes.containsKey(ax)) {
                    return substitutes[ax]!!.equals(exp) // cause substutes containsKey is true
                } else {
                    substitutes.put(ax as Variable, exp)
                    return true
                }
            (ax is UnaryOp && ax::class == exp::class) -> {
                val expUn = exp as UnaryOp
                return substituteVars(ax.exp, expUn.exp, substitutes)
            }
            (ax is BinaryOp && ax::class == exp::class) -> {
                val expB = exp as BinaryOp
                return substituteVars(ax.left, expB.left, substitutes) && substituteVars(ax.right, expB.right, substitutes)
            }
            else -> return false
        }
    }
}
