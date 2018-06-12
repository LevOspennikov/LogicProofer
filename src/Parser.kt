/**
 * Created by ospen on 7/9/2017.
 */
object Parser {
    private var needParse: String = ""
    private var curr: Int = 0
    

    fun parse(str: String): Expression {
        needParse = str.filter {!Character.isWhitespace(it)}
        curr = 0
        return Impl()
    }

    private fun Impl(): Expression {
        var next = Disj()
        val res: Expression
        if (needParse.length - curr > 2 && needParse.substring(curr, curr + 2) == "->") {
            curr += 2
            res = Impl()
            next = Implication(next, res)
        }
        return next
    }

    private fun Disj(): Expression {
        var next = Conj()
        var res: Expression
        while (needParse.length - curr > 1 && needParse[curr] == '|') {
            curr++
            res = Conj()
            next = Disjunction(next, res)
        }
        return next
    }

    private fun Conj(): Expression {
        var next = Neg()
        var res: Expression
        while (needParse.length - curr > 1 && needParse[curr] == '&') {
            curr++
            res = Neg()
            next = Conjunction(next, res)
        }
        return next
    }

    private fun Neg(): Expression {
        var next: Expression
        if (needParse.length - curr > 1 && needParse[curr] == '!') {
            curr++
            next = Neg()
            next = Negation(next)
        } else
            next = Bracket()
        return next
    }

    private fun Bracket(): Expression {
        if (needParse.length - curr > 0) {
            val firstCh = needParse[curr]
            if (firstCh == '(') {
                curr++
                val res = Impl()
                curr++
                return res
            }
        }
        return Var()
    }

    private fun Var(): Expression {
        var length = 1
        while (curr + length < needParse.length && (Character.isAlphabetic(needParse[curr + length].toInt()) || Character.isDigit(needParse[curr + length]))) {
            length++
        }
        val res = Variable(needParse.substring(curr, curr + length))
        curr += length
        return res
    }
}