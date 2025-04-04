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
                System.out.println("salida (sigmoide): " + activacion);
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


  


    public void entrenarSS(int[][] entradas, int[] resultadosEsperados) {
        int epoca = 0;
        double errorTotal;
    
        // Inicializar 3 pesos (pesos[0] actuará como sesgo implícito)
        pesos = new double[3]; 
        pesos[0] = random.nextDouble() * 2 - 1; // w0 
        pesos[1] = random.nextDouble() * 2 - 1; // w1 
        pesos[2] = random.nextDouble() * 2 - 1; // w2 
    
        do {
            errorTotal = 0;
            epoca++;
            System.out.println("\n========================");
            System.out.println("Época " + epoca);
            System.out.println("========================");
    
            for (int j = 0; j < entradas.length; j++) {
                System.out.println("\nIteración " + (j + 1) + ":");
                System.out.println("Entrada: [" + entradas[j][0] + ", " + entradas[j][1] + "], Resultado esperado: " + resultadosEsperados[j]);
    
                double entradaConBias = 1.0; // Esto se multiplicará por pesos[0]
                double x1 = entradas[j][0];  // Se multiplicará por pesos[1]
                double x2 = entradas[j][1];  // Se multiplicará por pesos[2]
    
                // suma ponderada
                double sumaPonderada = pesos[0] * entradaConBias + pesos[1] * x1 + pesos[2] * x2;
                System.out.println("Suma Ponderada (con sesgo implícito): " + sumaPonderada);
    
                // Función escalón (clasificación binaria)
                int salida = (sumaPonderada >= 0) ? 1 : 0;
                System.out.println("Salida (Escalón): " + salida);
    
                // Calcular el error
                double error = resultadosEsperados[j] - salida;
                System.out.println("Error: " + error);
                errorTotal += Math.abs(error);
    
                // Actualizar los 3 pesos 
                double peso0Antes = pesos[0];
                double peso1Antes = pesos[1];
                double peso2Antes = pesos[2];
    
                pesos[0] += tasaAprendizaje * error * entradaConBias; // Actualizar w0 
                pesos[1] += tasaAprendizaje * error * x1;            // Actualizar w1
                pesos[2] += tasaAprendizaje * error * x2;            // Actualizar w2
    
                System.out.println("Pesos antes de actualizar: [" + peso0Antes + ", " + peso1Antes + ", " + peso2Antes + "]");
                System.out.println("Pesos actualizados: [" + pesos[0] + ", " + pesos[1] + ", " + pesos[2] + "]");
            }
    
            System.out.println("Error total en esta época: " + errorTotal);

        } while (errorTotal > errorUmbral);
    
        System.out.println("\n¡Entrenamiento completado en " + epoca + " épocas!");
        System.out.println("Pesos finales: [w0 (sesgo implícito) = " + pesos[0] + ", w1 = " + pesos[1] + ", w2 = " + pesos[2] + "]");
    }
    

    public int funcionEscalon(double sumaPonderada) {
        return sumaPonderada >= 0 ? 1 : 0;
    }

    public void entrenarSS2(int[][] entradas, int[] resultadosEsperados) {
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

            
            if(epoca>= 1000){
                System.out.println("Se ha alcanzado el límite de épocas, se detiene el entrenamiento."+ epoca);
                break;
            }


        } while (errorTotal > errorUmbral); // Repetir mientras haya errores

        
    }



}
