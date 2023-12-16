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
                menuJugar(taulell)
            }
        }

    } while (!sortir)
    println("Gracies per jugar a ${tetrisTitol()}, fins avant.")
}