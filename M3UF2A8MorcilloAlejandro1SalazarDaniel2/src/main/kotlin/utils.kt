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
 * Autor: Alejandro Morcillo
 *
 * Funció que defineix el taulell
 *
 * @return Taulell del tamany demanat
 */
fun definirTaulell() : MutableList<MutableList<String>> {
    var tamany = tamanyTaulell()
    var width = tamany.first
    var heigth = tamany.second
    return MutableList(heigth) { MutableList(width) { "$espai" } }
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

/**
 * Autor: Alejandro Morcillo i Daniel Salazar
 *
 * Funció del menu de joc
 * @param taulell el taulell on es juga
 */
fun menuJugar(taulell: MutableList<MutableList<String>>){
    println(
        "Les comandes del prorgama son les següents:\n" +
                "d/D -> Moure figura cap a la dreta\n" +
                "e/E -> Moure figura cap a l'esquerra\n" +
                "t/T -> Tirar la peça cap abaix\n" +
                "r/R -> Rendir-se"
    )
    var entrada = ""
    do {
        val random = numAleatori()
        var infoPosicioEntrada = moureFigura(entrada, taulell, random)
        var posicioColumna = infoPosicioEntrada.first
        entrada = infoPosicioEntrada.second
        tirarPeçaAbaix(random, taulell, posicioColumna)
        netejarColumnesPlenes(taulell, espai)
    } while (entrada.uppercase() != "R")
}

/**
 * Autor: Daniel Salazar
 * Data: 2023-12-16
 *
 * Funció que mostra el taulell de Tetris amb les figures actuals.
 *
 * @param taulell Representació del taulell amb les figures
 */
fun mostrarTaulell(taulell: MutableList<MutableList<String>>) {
    val height = taulell.size
    val width = if (height > 0) taulell[0].size else 0

    for (fila in 0 until height) {
        print('|')
        for (columna in 0 until width) {
            print(taulell[fila][columna])
        }
        print('|')
        println()
    }

    print(' ')
    repeat(width) {
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
 * Autor: Daniel Salazar i Alejandro Morcillo
 * Data: 2023-12-16
 *
 * Funció que retorna un número aleatori entre 0 i 3.
 *
 * @return Número aleatori
 */
fun numAleatori(): Int {
    return Random.nextInt(0, 4)
}

/**
 * Autor: Daniel Salazar i Alejandro Morcillo
 *
 * Funció que imprimeix la figura escollida a la part d'adalt del taulell
 *
 * @param figura Figura declarada
 * @param taulell Taulell de joc
 * @param posicioColumna Meitat del taulell
 */
fun imprimirFiguraATaulell(figura: Array<Array<String>>, taulell: MutableList<MutableList<String>>, posicioColumna: Int) {
    val height = taulell.size
    val width = if (height > 0) taulell[0].size else 0
    for (fila in 0 until 4) {
        for (columna in -1..width) {
            if (columna == -1 || columna == width) {
                print(' ')
            } else if (fila < figura.size && columna - posicioColumna >= 0 && columna - posicioColumna < figura[fila].size && figura[fila][columna - posicioColumna] != espai.toString()) {
                print(figura[fila][columna - posicioColumna])
            } else print('▼')
        }
        println()
    }
    mostrarTaulell(taulell)
}

/**
 * Autor: Alejandro Morcillo i Daniel Salazar
 *
 * Funció que posiciona el objecte al punt més baix posible del taulell, i en cas que no pugui, perd el joc
 * @param random És la posició que defineix el objecte
 * @param taulell Taulell de joc
 * @param posicioColumna Posició de la columna
 */
fun tirarPeçaAbaix(
    random: Int,
    taulell: MutableList<MutableList<String>>,
    posicioColumna: Int
) {
    var posicioFila = 0
    var colisio = false
    val figura = arrayFigures[random].figura

    while (posicioFila + figura.size < taulell.size && !colisio) {
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

    val color = when (random) {
        0 -> ColorANSI.VERMELL
        1 -> ColorANSI.VERD
        2 -> ColorANSI.TARONJA
        3 -> ColorANSI.BLAU
        else -> null
    }

    color?.let {
        for (i in figura.indices) {
            for (j in figura[i].indices) {
                val fila = posicioFila + i
                val columna = posicioColumna + j

                if (fila in 0 until taulell.size && columna in 0 until taulell[0].size && figura[i][j] != espai.toString()) {
                    taulell[fila][columna] = "${it.codi}$bloc${ColorANSI.RESET.codi}"
                }
            }
        }
    }
}




fun imprimirFigures() {
    imprimirFigura(Figures.FIGURA_CUB.figura)
    println()
    imprimirFigura(Figures.FIGURA_L_RECTA.figura)
    println()
    imprimirFigura(Figures.FIGURA_4_ENLINIA.figura)
    println()
    imprimirFigura(Figures.FIGURA_L_ESTIRADA.figura)
}


fun tetrisTitol() : String {
    return "${ColorANSI.VERMELL.codi}T${ColorANSI.TARONJA.codi}E${ColorANSI.GROC.codi}T${ColorANSI.VERD.codi}R${ColorANSI.BLAU.codi}I${ColorANSI.MORAT.codi}S${ColorANSI.RESET.codi}"
}

/**
 * Autor: Alejandro Morcillo
 *
 * Funció que s'ocupa de moure l'objecte
 *
 * @param entrada És la comanda inicial
 * @param taulell Taulell de joc
 * @param random És la posició que defineix el objecte
 *
 * @return Un Pair qué conté la posició final i la comanda final
 */
fun moureFigura(entrada : String, taulell: MutableList<MutableList<String>>, random : Int) : Pair<Int, String> {
    var posicioColumna = taulell[0].size / 2
    var comanda = entrada
    do {
        imprimirFiguraATaulell(arrayFigures[random].figura, taulell, posicioColumna)
        comanda = scan.next().uppercase()
        when (comanda) {
            "D" -> posicioColumna++
            "E" -> posicioColumna--
        }
        if (posicioColumna + arrayFigures[random].figura[0].size-1 == taulell[0].size) {
            posicioColumna--
        } else if (posicioColumna == -1) {
            posicioColumna++
        }
    } while (comanda != "T" && comanda != "R")
    return Pair(posicioColumna, comanda)
}


/**
 * Autor: Alejandro Morcillo i Daniel Salazar
 *
 * Funció que s'ocupa de netejar si una linia està plena d'objectes
 *
 * @param taulell Taulell de joc
 */

fun netejarColumnesPlenes(taulell: MutableList<MutableList<String>>, espai: Char) {
    for (columna in 0 until taulell.size) {
        if (" " !in taulell[columna]) {
            taulell.removeAt(columna)
            taulell.add(0, MutableList(taulell[0].size) { "$espai" })
        }
    }
}
