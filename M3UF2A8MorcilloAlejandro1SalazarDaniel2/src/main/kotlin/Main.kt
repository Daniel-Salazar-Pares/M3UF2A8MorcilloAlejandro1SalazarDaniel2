fun main() {
    var sortir = false
    println("Benvingut a ${tetrisTitol()}!")
    var taulell = definirTaulell()
    imprimirFigures()
    do {
        menu()
        val opcio = demanarEnterCondicionat(0, 3)
        when (opcio) {
            0 -> {
                sortir = true
            }

            1 -> {
                taulell = definirTaulell()
                println("Mida del taulell cambiada exitosament!")
            }

            2 -> {
                println("El taulell es el següent:")
                mostrarTaulell(taulell)
            }

            3 -> {
                var comanda: String
                menuComandes()
                do {
                    val random = numAleatori()
                    var posicioColumna = taulell[0].size / 2

                    do {
                        imprimirFiguraATaulell(arrayFigures[random].figura, taulell, posicioColumna)
                        comanda = scan.next()
                        when (comanda.uppercase()) {
                            "D" -> posicioColumna++
                            "E" -> posicioColumna--
                        }
                        if (posicioColumna + arrayFigures[random].figura[0].size-1 == taulell[0].size) {
                            posicioColumna--
                        } else if (posicioColumna == -1) {
                            posicioColumna++
                        }
                    } while (comanda.uppercase() != "T" && comanda.uppercase() != "R")

                    tirarPeçaAbaix(arrayFigures[random].figura, random, taulell.size, taulell, posicioColumna)
                    for (columna in 0..<taulell.size) {
                        if (" " !in taulell[columna]) {
                            taulell.removeAt(columna)
                            taulell.add(0, MutableList(taulell[0].size) { "$espai" })
                        }
                    }
                } while (comanda.uppercase() != "R")
            }
        }

    } while (!sortir)
    println("Gracies per jugar a ${tetrisTitol()}, fins avant.")
}