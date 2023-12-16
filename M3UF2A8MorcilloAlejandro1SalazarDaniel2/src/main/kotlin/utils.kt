import java.util.*
import kotlin.random.Random

val scan = Scanner(System.`in`)

/**
 * Autor: Daniel Salazar
 * Data: 2023-12-16
 *
 * Funció que demana i retorna les dimensions desitjades pel taulell de Tetris.
 *
 * @return Pair amb l'amplada i l'altura del taulell
 */
fun tamanyTaulell(): Pair<Int, Int> {
    print("Introdueixi l'amplitud del taulell de tetris (minim 5 i maxim 50): ")
    val width = demanarEnterCondicionat(5, 50)

    print("Introdueixi l'altura del taulell de tetris (minim 5 maxim 50): ")
    val height = demanarEnterCondicionat(5, 50)

    return Pair(width, height)
}

/**
 * Autor: Daniel Salazar
 * Data: 2023-12-16
 *
 * Funció que demana a l'usuari un número enter vàlid.
 *
 * @return El número enter ingressat per l'usuari
 */
fun demanarNumeroEnter(): Int {
    do {
        if (!scan.hasNextInt()) {
            println("Introdueixi un número valid: ")
            scan.next()
        }
    } while (!scan.hasNextInt())
    val numero = scan.nextInt()
    return numero
}

/**
 * Autor: Daniel Salazar
 * Data: 2023-12-16
 *
 * Funció que demana un número enter condicionat per un rang específic.
 *
 * @param minim Valor mínim permès
 * @param maxim Valor màxim permès
 * @return Número enter ingressat per l'usuari que compleix amb les condicions
 */
fun demanarEnterCondicionat(minim: Int, maxim: Int): Int {
    var numero = 0
    do {
        if (!scan.hasNextInt()) {
            println("Introdueixi un valor valid")
            scan.next()
        } else {
            numero = scan.nextInt()
            if (numero < minim || numero > maxim) {
                println("El valor introduit ha d'estar entre $minim i $maxim")
            }
        }
    } while (numero < minim || numero > maxim)
    return numero
}


fun menu() {
    print(
        "***MENU***\n" +
                "0-Sortir\n" +
                "1-Cambiar mida taulell\n" +
                "2-Mostra taulell\n" +
                "3-Jugar\n" +
                "Introduir opcio: "
    )
}

fun menuComandes() {
    println(
        "Les comandes del prorgama son les següents:\n" +
                "d/D -> Moure figura cap a la dreta\n" +
                "e/E -> Moure figura cap a l'esquerra\n" +
                "t/T -> Tirar la peça cap abaix\n" +
                "r/R -> Rendir-se"
    )
}

/**
 * Autor: Daniel Salazar
 * Data: 2023-12-16
 *
 * Funció que mostra el taulell de Tetris amb les figures actuals.
 *
 * @param with Amplada del taulell
 * @param heigth Altura del taulell
 * @param taulell Representació del taulell amb les figures
 */
fun mostrarTaulell(with: Int, heigth: Int, taulell: MutableList<MutableList<String>>) {
    for (fila in 0 until heigth) {
        print('|')
        for (columna in 0 until with) {
            print(taulell[fila][columna])
        }
        print('|')
        println()
    }
    print(' ')
    repeat(with) {
        print('¯')
    }
    println()
}

/**
 * Autor: Daniel Salazar
 * Data: 2023-12-16
 *
 * Funció que imprimeix la representació visual d'una figura.
 *
 * @param figura Matriu que representa la figura
 */
fun imprimirFigura(figura: Array<Array<String>>) {
    for (fila in figura) {
        for (celda in fila) {
            print(celda)
        }
        println()
    }
}

/**
 * Autor: Daniel Salazar
 * Data: 2023-12-16
 *
 * Funció que retorna un número aleatori entre 0 i 3.
 *
 * @return Número aleatori
 */
fun numAleatori(): Int {
    val numeroAleatori = Random.nextInt(0, 4)
    return numeroAleatori
}

fun imprimirFiguraATaulell(
    figura: Array<Array<String>>,
    with: Int,
    heigth: Int,
    taulell: MutableList<MutableList<String>>,
    posicioColumna: Int
) {
    for (fila in 0 until 4) {
        for (columna in -1..with) {
            if (columna == -1 || columna == with) {
                print(' ')
            } else if (fila < figura.size && columna - posicioColumna >= 0 && columna - posicioColumna < figura[fila].size && figura[fila][columna - posicioColumna] != espai.toString()) {
                print(figura[fila][columna - posicioColumna])
            } else print('▼')
        }
        println()
    }
    mostrarTaulell(with, heigth, taulell)
}

fun tirarPeçaAbaix(
    figura: Array<Array<String>>,
    random: Int,
    heigth: Int,
    taulell: MutableList<MutableList<String>>,
    posicioColumna: Int
): Boolean {
    var posicioFila = 0
    var colisio = false

    while (posicioFila + figura.size < heigth && !colisio) {
        for (i in figura.indices) {
            for (j in figura[i].indices) {
                if ((figura[i][j] != espai.toString() && taulell[posicioFila + i][posicioColumna + j] != "$espai") && !colisio) {
                    posicioFila--
                    colisio = true
                }
            }
        }

        if (!colisio) {
            posicioFila++
        }
    }
    var error = false

    for (i in figura.indices) {
        for (j in figura[i].indices) {
            if (figura[i][j] != espai.toString()) {
                when (random) {
                    0 -> taulell[posicioFila + i][posicioColumna + j] = "${ColorANSI.VERMELL.codi}$bloc${ColorANSI.RESET.codi}"
                    1 -> taulell[posicioFila + i][posicioColumna + j] = "${ColorANSI.VERD.codi}$bloc${ColorANSI.RESET.codi}"
                    2 -> taulell[posicioFila + i][posicioColumna + j] = "${ColorANSI.TARONJA.codi}$bloc${ColorANSI.RESET.codi}"
                    3 -> taulell[posicioFila + i][posicioColumna + j] = "${ColorANSI.BLAU.codi}$bloc${ColorANSI.RESET.codi}"
                }
            }

        }
    }
    return error
}


