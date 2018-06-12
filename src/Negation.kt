/**
 * Created by ospen on 7/10/2017.
 */
internal class Negation(e: Expression) : UnaryOp(e) {
    override fun toString(): String {
        return "!" + "(" + exp.toString() + ")"
    }
}
