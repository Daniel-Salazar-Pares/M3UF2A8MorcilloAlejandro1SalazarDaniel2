fun main() {
    var sortir = false
    println("Benvingut a ${ColorANSI.VERMELL.codi}T${ColorANSI.TARONJA.codi}E${ColorANSI.GROC.codi}T${ColorANSI.VERD.codi}R${ColorANSI.BLAU.codi}I${ColorANSI.MORAT.codi}S${ColorANSI.RESET.codi}!")
    var tamany = tamanyTaulell()
    var with = tamany.first
    var heigth = tamany.second
    var taulell = MutableList(heigth) { MutableList(with) { "$espai" } }
    imprimirFigura(Figures.FIGURA_CUB.figura)
    println()
    imprimirFigura(Figures.FIGURA_L_RECTA.figura)
    println()
    imprimirFigura(Figures.FIGURA_4_ENLINIA.figura)
    println()
    imprimirFigura(Figures.FIGURA_L_ESTIRADA.figura)
    do {
        menu()
        val opcio = demanarEnterCondicionat(0, 3)
        when (opcio) {
            0 -> {
                sortir = true
            }

            1 -> {
                tamany = tamanyTaulell()
                with = tamany.first
                heigth = tamany.second
                taulell = MutableList(heigth) { MutableList(with) { "$espai" } }
                println("Mida del taulell cambiada exitosament!")
            }

            2 -> {
                println("El taulell es el següent:")
                mostrarTaulell(with, heigth, taulell)
            }

            3 -> {
                val arrayFigures = arrayOf(Figures.FIGURA_CUB, Figures.FIGURA_L_RECTA, Figures.FIGURA_L_ESTIRADA, Figures.FIGURA_4_ENLINIA)
                var comanda: String
                menuComandes()
                do {
                    val random = numAleatori()
                    var posicioColumna = with / 2

                    do {
                        imprimirFiguraATaulell(arrayFigures[random].figura, with, heigth, taulell, posicioColumna)
                        comanda = scan.next()


                        when (comanda.uppercase()) {
                            "D" -> posicioColumna++
                            "A" -> posicioColumna--
                        }
                        if (posicioColumna + arrayFigures[random].figura[0].size-1 == with) {
                            posicioColumna--
                        } else if (posicioColumna == -1) {
                            posicioColumna++
                        }
                    } while (comanda.uppercase() != "T" && comanda.uppercase() != "R")

                    tirarPeçaAbaix(arrayFigures[random].figura, random, heigth, taulell, posicioColumna)
                    for (columna in 0 .. taulell.size - 1) {
                        if (" " !in taulell[columna]) {
                            taulell.removeAt(columna)
                            taulell.add(0, MutableList(with) { "$espai" })
                        }
                    }
                } while (comanda.uppercase() != "R")
            }
        }

    } while (!sortir)
    println("Gracies per jugar a ${ColorANSI.VERMELL.codi}T${ColorANSI.TARONJA.codi}E${ColorANSI.GROC.codi}T${ColorANSI.VERD.codi}R${ColorANSI.BLAU.codi}I${ColorANSI.MORAT.codi}S${ColorANSI.RESET.codi}, fins avant.")
}