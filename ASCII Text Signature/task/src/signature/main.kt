package signature

import java.io.File
import java.util.Scanner

fun readFont(fileName: String): MutableMap<Char, Array<String>> {
    val res = mutableMapOf<Char, Array<String>>()

    val reader = Scanner(File(fileName))

    val height = reader.nextInt()
    val numChars = reader.nextInt()

    repeat(numChars) {
        val key = reader.next().first()
        val width = reader.nextInt()
        reader.nextLine()
        val view = Array(height) { "" }

        for (i in 0 until height) view[i] = reader.nextLine()

        res[key] = view
    }

    return res
}

fun applyFont(str: String, font: MutableMap<Char, Array<String>>, space: String): Array<String> {
    val numRows = font.entries.first().value.size
    val res = Array(numRows) { "" }

    for (c in str)
        for (i in 0 until numRows) {
            val row = font[c]?.get(i)
            res[i] = res[i] + if (row != null) font[c]?.get(i) else space
        }

    return res
}

fun extend(src: Array<String>, add: Int, sub: Int): Array<String> {
    val res = Array(src.size) { "" }
    for (i in src.indices) res[i] = "88  ${" ".repeat(add)}${src[i]}${" ".repeat(add + sub)}  88"
    return res
}

fun main() {
    val scanner = Scanner(System.`in`)

    val bigFont = readFont("c:\\users\\ir\\downloads\\roman.txt")
    val smallFont = readFont("c:\\users\\ir\\downloads\\medium.txt")


//    val name = "Ian One"
//    val status = "VIP"
//    val name = "A b"
//    val status = "long participant"

    print("Enter name and surname: ")
    val name = scanner.nextLine()
    print("Enter person's status: ")
    val status = scanner.nextLine()

    var bigName = applyFont(name, bigFont, "          ")
    val bigNameLength = bigName[0].length

    var bigStatus = applyFont(status, smallFont, "     ")
    val bigStatusLength = bigStatus[0].length

    if (bigNameLength > bigStatusLength) {
        val add = (bigNameLength - bigStatusLength) / 2
        val sub = bigNameLength - (add * 2 + bigStatusLength)
        bigName = extend(bigName, 0, 0)
        bigStatus = extend(bigStatus, add, sub)
    } else {
        val add = (bigStatusLength - bigNameLength) / 2
        val sub = bigStatusLength - (add * 2 + bigNameLength)
        bigName = extend(bigName, add, sub)
        bigStatus = extend(bigStatus, 0, 0)
    }

    val width = bigName[0].length
    println("8".repeat(width))
    for (s in bigName) println(s)
    for (s in bigStatus) println(s)
    println("8".repeat(width))
}
