import java.util.Random;

public class Perceptron {
    public double[] pesos;
    public double sesgo;
    public double tasaAprendizaje = 0.1;
    public double errorUmbral = 0.5; 

    Random random = new Random();

    // Constructor del Perceptrón
    public Perceptron() {
        pesos = new double[]{random.nextDouble()* 2 - 1, random.nextDouble()* 2 - 1};
        sesgo = random.nextDouble();
    }

    // Función sigmoide
    public double sigmoide(double x) {
        return 1 / (1 + Math.exp(-x));
    }

 

    // Método de entrenamiento
    public void entrenar(int[][] entradas, int[] resultadosEsperados) {
        int epoca = 0;
        double errorTotal;

        do {
            errorTotal = 0;
            epoca++;
            System.out.println("\n========================");
            System.out.println("Época " + epoca);
            System.out.println("========================");

            for (int j = 0; j < entradas.length; j++) {
                System.out.println("\nIteración " + (j + 1) + ":");
                System.out.println("Entrada: [" + entradas[j][0] + ", " + entradas[j][1] + "], Resultado esperado: " + resultadosEsperados[j]);

                // Calcular la suma ponderada
                double sumaPonderada = pesos[0] * entradas[j][0] + pesos[1] * entradas[j][1] + sesgo;
                System.out.println("Suma Ponderada: " + sumaPonderada);

                // Aplicar la función sigmoide y redondear
                double activacion = sigmoide(sumaPonderada);
                int salidaRedondeada = (activacion >= 0.5) ? 1 : 0; // Redondear a 0 o 1
                System.out.println("Activación (redondeada): " + salidaRedondeada);

                // Calcular el error
                double error = resultadosEsperados[j] - salidaRedondeada;
                System.out.println("Error: " + error);
                
                errorTotal += Math.abs(error);

                // Guardar los pesos antes de actualizar
                double peso0Antes = pesos[0];
                double peso1Antes = pesos[1];
                double sesgoAntes = sesgo;

                // Actualizar pesos y sesgo
                pesos[0] += tasaAprendizaje * error * entradas[j][0];
                pesos[1] += tasaAprendizaje * error * entradas[j][1];
                sesgo += tasaAprendizaje * error;

                // Imprimir valores actualizados
                System.out.println("Pesos antes de actualizar: [" + peso0Antes + ", " + peso1Antes + "]");
                System.out.println("Pesos actualizados: [" + pesos[0] + ", " + pesos[1] + "]");
                System.out.println("Sesgo antes: " + sesgoAntes + ", Sesgo actualizado: " + sesgo);
            }
            System.out.println();
            System.out.println("Error total en esta época: " + errorTotal);

        } while (errorTotal > errorUmbral); // Repetir mientras haya errores

        System.out.println("\n¡¡Entrenamiento finalizado en " + epoca + " épocas!!");
    }

    // Método para predecir
    public int predecir(int[] entrada) {
        
        double sumaPonderada = pesos[0] * entrada[0] + pesos[1] * entrada[1] + sesgo;
        double activacion = sigmoide(sumaPonderada);
        return (activacion >= 0.5) ? 1 : 0;
    }


    public int funcionEscalon(double sumaPonderada) {
        return sumaPonderada >= 0 ? 1 : 0;
    }


    public void entrenarSS(int[][] entradas, int[] resultadosEsperados) {
        int epoca = 0;
        double errorTotal;

        do {
            errorTotal = 0;
            epoca++;
            System.out.println("\n========================");
            System.out.println("Época " + epoca);
            System.out.println("========================");

            for (int j = 0; j < entradas.length; j++) {
                System.out.println("\nIteración " + (j + 1) + ":");
                System.out.println("Entrada: [" + entradas[j][0] + ", " + entradas[j][1] + "], Resultado esperado: " + resultadosEsperados[j]);

                // Calcular la suma ponderada
                double sumaPonderada = pesos[0] * entradas[j][0] + pesos[1] * entradas[j][1];
                System.out.println("Suma Ponderada: " + sumaPonderada);

                
                int escalon = funcionEscalon(sumaPonderada);
                double salidaRedondeada = sigmoide(escalon);
                System.out.println("Activación (redondeada): " + salidaRedondeada);

                // Calcular el error
                double error = resultadosEsperados[j] - salidaRedondeada;
                System.out.println("Error: " + error);
                
                errorTotal += Math.abs(error);

                // Guardar los pesos antes de actualizar
                double peso0Antes = pesos[0];
                double peso1Antes = pesos[1];
                

                // Actualizar pesos y sesgo
                pesos[0] += tasaAprendizaje * error * entradas[j][0];
                pesos[1] += tasaAprendizaje * error * entradas[j][1];
               

                // Imprimir valores actualizados
                System.out.println("Pesos antes de actualizar: [" + peso0Antes + ", " + peso1Antes + "]");
                System.out.println("Pesos actualizados: [" + pesos[0] + ", " + pesos[1] + "]");
              
            }
            System.out.println();
            System.out.println("Error total en esta época: " + errorTotal);

        } while (errorTotal > errorUmbral); // Repetir mientras haya errores

        System.out.println("\n¡¡Entrenamiento finalizado en " + epoca + " épocas!!");
    }
    



}
