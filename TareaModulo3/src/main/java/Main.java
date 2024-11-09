import java.util.ArrayList;
import java.util.Scanner;

class Libro {

private final String titulo;
    private final String autor;
    private final int anioPublicacion;
    private final String genero;
    private boolean disponible;

    // Constructor para inicializar los atributos
    public Libro(String titulo, String autor, int anioPublicacion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.disponible = true; // Inicialmente el libro está disponible
    }

    // Getters y Setters para los atributos
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Método para mostrar información del libro
    @Override
    public String toString() {
        return "Título: " + titulo + "\nAutor: " + autor + "\nAño de Publicación: " + anioPublicacion + "\nGénero: " + genero + "\nDisponible: " + (disponible ? "Sí" : "No") + "\n";
    }
}

class Biblioteca {
    private final ArrayList<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    // Método para agregar un nuevo libro
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("El libro ha sido agregado correctamente.");
    }

    // Método para buscar libros por título o autor
    public void buscarLibro(String criterio) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(criterio) || libro.getAutor().equalsIgnoreCase(criterio)) {
                System.out.println(libro.toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros con ese criterio.");
        }
    }

    // Método para prestar un libro
    public void prestarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                if (libro.isDisponible()) {
                    libro.setDisponible(false);
                    System.out.println("El libro ha sido prestado.");
                } else {
                    System.out.println("El libro ya está prestado.");
                }
                return;
            }
        }
        System.out.println("No se encontró el libro con ese título.");
    }

    // Método para devolver un libro
    public void devolverLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                if (!libro.isDisponible()) {
                    libro.setDisponible(true);
                    System.out.println("El libro ha sido devuelto.");
                } else {
                    System.out.println("El libro no está prestado.");
                }
                return;
            }
        }
        System.out.println("No se encontró el libro con ese título.");
    }

    // Método para mostrar información de todos los libros disponibles
    public void mostrarLibrosDisponibles() {
        boolean hayLibrosDisponibles = false;
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                System.out.println(libro.toString());
                hayLibrosDisponibles = true;
            }
        }
        if (!hayLibrosDisponibles) {
            System.out.println("No hay libros disponibles.");
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Biblioteca Virtual");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar libro por título o autor");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese el autor del libro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese el año de publicación: ");
                    int anio = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el género del libro: ");
                    String genero = scanner.nextLine();
                    Libro libro = new Libro(titulo, autor, anio, genero);
                    biblioteca.agregarLibro(libro);
                }
                case 2 -> {
                    System.out.print("Ingrese el título o autor del libro a buscar: ");
                    String criterio = scanner.nextLine();
                    biblioteca.buscarLibro(criterio);
                }
                case 3 -> {
                    System.out.print("Ingrese el título del libro a prestar: ");
                    String tituloPrestar = scanner.nextLine();
                    biblioteca.prestarLibro(tituloPrestar);
                }
                case 4 -> {
                    System.out.print("Ingrese el título del libro a devolver: ");
                    String tituloDevolver = scanner.nextLine();
                    biblioteca.devolverLibro(tituloDevolver);
                }
                case 5 -> biblioteca.mostrarLibrosDisponibles();
                case 6 -> System.out.println("Gracias por usar la biblioteca virtual.");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 6);
    }
}
