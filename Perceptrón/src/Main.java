import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Compuertas compuertas = new Compuertas();

        int[][] entradasAND = compuertas.RetornoMatrizAND();
        int[] resultadosAND = compuertas.RetornoResultadoAND();

        int[][] entradasOR = compuertas.RetornoMatrizOR();
        int[] resultadosOR = compuertas.RetornoResultadoOR();

        Perceptron perceptron = new Perceptron();

        int opcion; // Variable para almacenar la opción del usuario

        do {
            // Mostrar el menú
            System.out.println("\nSeleccione la compuerta que desea entrenar:");
            System.out.println("\nNo es posible entrenar la compuerta AND sin el sesgo ya que no es linealmente separable.");
            System.out.println("AND no converge con el perceptrón simple.");
            System.out.println("1. AND");
            System.out.println("2. OR");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

      
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                   perceptron.entrenarSS(entradasAND, resultadosAND);

                   
                   
                   
                    break;

                case 2:
                    perceptron.entrenar(entradasOR, resultadosOR);
                    System.out.println("\nPredicción de la compuerta OR:");
                    for (int i = 0; i < entradasOR.length; i++) {
                        int prediccion = perceptron.predecir(entradasOR[i]);
                        System.out.println(entradasOR[i][0] + " OR " + entradasOR[i][1] + " = " + prediccion);
                    }
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 3);

        scanner.close(); // Cerrar el Scanner
    }
}
