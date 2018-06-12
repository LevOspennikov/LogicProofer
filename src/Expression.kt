/**
 * Created by ospen on 7/10/2017.
 */
interface Expression {

    override fun toString(): String

    fun equals(e: Expression): Boolean
}