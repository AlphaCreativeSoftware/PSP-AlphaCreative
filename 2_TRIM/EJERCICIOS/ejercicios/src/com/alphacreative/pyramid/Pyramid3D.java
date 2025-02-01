package com.alphacreative.pyramid;
public class Pyramid3D {
    private static final int WIDTH = 40;  // Ancho de la pantalla
    private static final int HEIGHT = 20; // Alto de la pantalla
    private static final double[][] vertices = {
        {0, 1, 0},   // Vértice superior
        {-1, -1, -1}, // Base izquierda trasera
        {1, -1, -1},  // Base derecha trasera
        {1, -1, 1},   // Base derecha frontal
        {-1, -1, 1}   // Base izquierda frontal
    };

    private static final int[][] caras = {
        {0, 1, 2}, // Caras triangulares
        {0, 2, 3},
        {0, 3, 4},
        {0, 4, 1},
        {1, 2, 3, 4} // Base cuadrada
    };
    public static void main(String[] args) throws InterruptedException {
        double angulo = 0;
        while (true) {
            double[][] rotados = new double[vertices.length][3];

            // Rotación de los puntos alrededor del eje Y
            for (int i = 0; i < vertices.length; i++) {
                double x = vertices[i][0];
                double z = vertices[i][2];

                rotados[i][0] = x * Math.cos(angulo) - z * Math.sin(angulo);
                rotados[i][1] = vertices[i][1]; // Y no cambia
                rotados[i][2] = x * Math.sin(angulo) + z * Math.cos(angulo);
            }

            // Proyectar y dibujar en consola
            char[][] buffer = new char[HEIGHT][WIDTH];
            for (int i = 0; i < HEIGHT; i++)
                for (int j = 0; j < WIDTH; j++)
                    buffer[i][j] = ' ';

            for (int[] cara : caras) {
                for (int i = 0; i < cara.length; i++) {
                    int j = (i + 1) % cara.length;
                    proyectarYConectar(buffer, rotados[cara[i]], rotados[cara[j]]);
                }
            }

            // Imprimir el frame
            System.out.print("\033[H\033[2J"); // Limpia pantalla en Unix/macOS/Linux
            System.out.flush();
            for (char[] row : buffer) {
                System.out.println(row);
            }

            Thread.sleep(100); // Pausa para animación
            angulo += 0.1; // Incrementa el ángulo para rotar
        }
    }

    private static void proyectarYConectar(char[][] buffer, double[] p1, double[] p2) {
        int fov = 10; // Factor de profundidad
        int x1 = (int) (WIDTH / 2 + p1[0] * fov / (p1[2] + 3));
        int y1 = (int) (HEIGHT / 2 - p1[1] * fov / (p1[2] + 3));
        int x2 = (int) (WIDTH / 2 + p2[0] * fov / (p2[2] + 3));
        int y2 = (int) (HEIGHT / 2 - p2[1] * fov / (p2[2] + 3));

        dibujarLinea(buffer, x1, y1, x2, y2);
    }

    private static void dibujarLinea(char[][] buffer, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1, sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            if (x1 >= 0 && x1 < WIDTH && y1 >= 0 && y1 < HEIGHT)
                buffer[y1][x1] = '*';

            if (x1 == x2 && y1 == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }
}
