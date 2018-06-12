/**
 * Created by ospen on 7/10/2017.
 */
class Variable(private val name: String) : Expression {

    override fun toString(): String {
        return name
    }

    override fun equals(e: Expression): Boolean {
        if (e === this) return true
        when (e) {
            is Variable -> return name == e.name;
        }
        return false;
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false;
        if (other::class == Variable::class) {
            return equals(other as Expression)
        }
        return false
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}