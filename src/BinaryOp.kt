/**
 * Created by ospen on 7/10/2017.
 */
abstract internal class BinaryOp(var left: Expression, var right: Expression) : Expression {

    override fun equals(e: Expression): Boolean {
        if (e === this) return true
        if (this::class != e::class) {
            return false;
        }
        when (e) {
            is BinaryOp -> return left.equals(e.left) && right.equals(e.right); // TODO:add name equality
        }
        return false;
    }

}