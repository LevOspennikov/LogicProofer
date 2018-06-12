import java.io.File
import java.io.InputStream

/**
 * Created by ospen on 8/7/2017.
 */

fun main(args: Array<String>) {
    try {
        val bufferedReader = File("test.in").bufferedReader()
        var lineList = mutableListOf<String>();
        val out = File("test.out").bufferedWriter();

        bufferedReader.useLines { lines -> lines.forEach { lineList.add(it) } }

        var line: String
        line = lineList[0];
        lineList = lineList.subList(1, lineList.size);
        val proofer = Proofer(line)
        lineList.forEach { proofer.addExpression(it) }
        val res = proofer.annotate()
        out.use {
            it.write(proofer.head + "\r\n");
            for (i in 1..res.size) {
                it.write("(" + i + ") " + proofer.getExpression(i) + " (" + res.get(i - 1) + ")\r\n")
            }
        }
    } catch (e: Error) {
        print(e);
    }
}

