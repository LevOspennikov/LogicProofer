/**
 * Created by ospen on 7/10/2017.
 */
internal class Implication(l: Expression, r: Expression) : BinaryOp(l, r) {
    override fun toString(): String {
        return "(" + left.toString() + "->" + right.toString() + ")"
    }
}