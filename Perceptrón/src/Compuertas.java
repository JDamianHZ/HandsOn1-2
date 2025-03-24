public class Compuertas {
    public int AND[][];
    public int OR[][];
    public int ResultadoAND[];
    public int ResultadoOR[];

    // Constructor para inicializar los arreglos
    public Compuertas() {
        AND = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        OR = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        ResultadoAND = new int[]{0, 0, 0, 1};
        ResultadoOR = new int[]{0, 1, 1, 1};

    }

    public static void mostrarCompuesrtas() {
        Compuertas compuertas = new Compuertas();
        System.out.println("Compuerta AND");
        for (int i = 0; i < compuertas.AND.length; i++) {
            System.out.println(compuertas.AND[i][0] + " AND " + compuertas.AND[i][1] + " = " + compuertas.ResultadoAND[i]);
        }
        System.out.println("Compuerta OR");
        for (int i = 0; i < compuertas.OR.length; i++) {
            System.out.println(compuertas.OR[i][0] + " OR " + compuertas.OR[i][1] + " = " + compuertas.ResultadoOR[i]);
        }
    }

    public int[][] RetornoMatrizAND() {
        return  AND;
    }
    public int[][] RetornoMatrizOR() {
        return  OR;
    }
    public int[] RetornoResultadoAND() {
        return  ResultadoAND;
    }
    public int[] RetornoResultadoOR() {
        return  ResultadoOR;
    }



}



