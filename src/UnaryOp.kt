/**
 * Created by ospen on 7/10/2017.
 */
internal abstract class UnaryOp(var exp: Expression) : Expression {

    override fun equals(e: Expression): Boolean {
        if (e === this) return true
        when (e) {
            is UnaryOp -> return exp.equals(e.exp)
        }
        return false;
    }

}
