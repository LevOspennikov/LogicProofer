import java.util.ArrayList

/**
 * Created by ospen on 7/11/2017.
 */
class Proofer(val head: String) {
    private val assumptions = ArrayList<Expression>();
    private var statement: Expression? = null
    private val inference =  ArrayList<Expression>()
    private val body = ArrayList<String>()

    init {
        var left = 0
        for (i in 0..head.length - 1) {
            if (head[i] == ',') {
                assumptions.add(Parser.parse(head.substring(left, i)))
                left = i + 1
            }
            if (i >= 1 && head.substring(i - 1, i + 1) == "|-") {
                assumptions.add(Parser.parse(head.substring(left, i - 1)))
                statement = Parser.parse(head.substring(i + 1, head.length))
                break
            }
        }
    }

    fun addExpression(str: String) {
        body.add(str)
        inference.add(Parser.parse(str))
    }

    fun getExpression(index: Int): String {
        return body[index - 1]
    }

    internal fun annotate(): ArrayList<Annotations> {
        val ans = ArrayList<Annotations>(body.size)
        for (i in body.indices) {

            val currentExp = inference[i]
            for (j in assumptions.indices) {
                if (currentExp.equals(assumptions[j])) {
                    ans.add(Annotations(Annotations.ASSUM, j + 1))
                    break
                }
            }

            if (ans.size > i) continue

            for (j in i - 1 downTo 0) {
                val x = inference[j];
                when (x) {
                    is Implication -> if (x.right.equals(currentExp)) {
                        for (k in i - 1 downTo 0) {
                            if (inference[k].equals(x.left)) {
                                ans.add(Annotations(Annotations.MODUS, k + 1, j + 1))
                                break
                            }
                        }
                    }
                }
            }

            if (ans.size > i) continue

            for (j in axioms.indices) {
                if (axioms[j].isAxiom(currentExp)) {
                    ans.add(Annotations(Annotations.AXIOM, j + 1))
                    break
                }
            }

            if (ans.size == i) ans.add(Annotations(Annotations.WRONG))
        }

        return ans
    }

    companion object {

        private var axioms: ArrayList<Axiom> = ArrayList<Axiom>()

        init {
            axioms.add(Axiom("a->b->a"))
            axioms.add(Axiom("(a->b)->(a->b->c)->(a->c)"))
            axioms.add(Axiom("a->b->a&b"))
            axioms.add(Axiom("a&b->a"))
            axioms.add(Axiom("a&b->b"))
            axioms.add(Axiom("a->a|b"))
            axioms.add(Axiom("b->a|b"))
            axioms.add(Axiom("(a->c)->(b->c)->(a|b->c)"))
            axioms.add(Axiom("(a->b)->(a->!b)->!a"))
            axioms.add(Axiom("!!a->a"))
        }
    }
}