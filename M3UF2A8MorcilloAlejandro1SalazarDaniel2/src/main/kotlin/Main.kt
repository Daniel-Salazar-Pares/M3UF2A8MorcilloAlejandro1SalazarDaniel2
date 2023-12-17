fun main() {
    var sortir = false
    println("Benvingut a ${tetrisTitol()}!")
    var taulell = definirTaulell()
    imprimirFigures()
    do {
        menu()
        val opcio = demanarEnterCondicionat(0, 3)
        when (opcio) {
            0 -> sortir = true

            1 -> {
                println("Mida del taulell cambiada exitosament!")
                taulell = definirTaulell()
            }

            2 -> {
                println("El taulell es el següent:")
                mostrarTaulell(taulell)
            }

            3 -> sortir = menuJugar(taulell)
        }

    } while (!sortir)
    println("Gracies per jugar a ${tetrisTitol()}, fins avant.")
}