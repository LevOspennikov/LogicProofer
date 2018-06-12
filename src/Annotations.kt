/**
 * Created by ospen on 7/11/2017.
 */
class Annotations @JvmOverloads constructor(var annot: String, var index1: Int = 0, var index2: Int = 0) {

    override fun toString(): String {
        if (annot == Annotations.MODUS)
            return annot + index1 + ", " + index2
        else if (annot == Annotations.AXIOM)
            return annot + index1
        else if (annot == Annotations.ASSUM)
            return annot + index1
        else
            return annot
    }

    companion object {
        val MODUS = "M. P. "
        val WRONG = "Не доказано"
        val AXIOM = "Сх. акс "
        val ASSUM = "Предп. "
    }
}
