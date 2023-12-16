val bloc = '█'
val espai = ' '
val arrayFigures = arrayOf(Figures.FIGURA_CUB, Figures.FIGURA_L_RECTA, Figures.FIGURA_L_ESTIRADA, Figures.FIGURA_4_ENLINIA)
enum class ColorANSI(val codi: String) {
    RESET("\u001B[0m"),
    VERMELL("\u001B[31m"),
    VERD("\u001B[32m"),
    GROC("\u001B[33m"),
    BLAU("\u001B[34m"),
    TARONJA("\u001B[38;5;208m"),
    MORAT("\u001B[38;5;165m");
}

enum class Figures(val figura: Array<Array<String>>) {
    FIGURA_4_ENLINIA(arrayOf(
        arrayOf("${ColorANSI.BLAU.codi}$bloc${ColorANSI.RESET.codi}"),
        arrayOf("${ColorANSI.BLAU.codi}$bloc${ColorANSI.RESET.codi}"),
        arrayOf("${ColorANSI.BLAU.codi}$bloc${ColorANSI.RESET.codi}"),
        arrayOf("${ColorANSI.BLAU.codi}$bloc${ColorANSI.RESET.codi}")
    )),

    FIGURA_L_ESTIRADA(arrayOf(
        arrayOf("${ColorANSI.TARONJA.codi}$bloc${ColorANSI.RESET.codi}", "$espai", "$espai"),
        arrayOf("${ColorANSI.TARONJA.codi}$bloc${ColorANSI.RESET.codi}", "${ColorANSI.TARONJA.codi}$bloc${ColorANSI.RESET.codi}", "${ColorANSI.TARONJA.codi}$bloc${ColorANSI.RESET.codi}")
    )),

    FIGURA_L_RECTA(arrayOf(
        arrayOf("$espai", "${ColorANSI.VERD.codi}$bloc${ColorANSI.RESET.codi}"),
        arrayOf("$espai", "${ColorANSI.VERD.codi}$bloc${ColorANSI.RESET.codi}"),
        arrayOf("$espai", "${ColorANSI.VERD.codi}$bloc${ColorANSI.RESET.codi}"),
        arrayOf("${ColorANSI.VERD.codi}$bloc${ColorANSI.RESET.codi}", "${ColorANSI.VERD.codi}$bloc${ColorANSI.RESET.codi}")
    )),

    FIGURA_CUB(arrayOf(
        arrayOf("${ColorANSI.VERMELL.codi}$bloc${ColorANSI.RESET.codi}", "${ColorANSI.VERMELL.codi}$bloc${ColorANSI.RESET.codi}"),
        arrayOf("${ColorANSI.VERMELL.codi}$bloc${ColorANSI.RESET.codi}", "${ColorANSI.VERMELL.codi}$bloc${ColorANSI.RESET.codi}")
    ))
}
