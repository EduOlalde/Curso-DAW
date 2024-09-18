import java.util.Scanner;
/**
 * Clase OrdenarBuscarArray que genera, por defecto, 1000 números enteros aleatorios en un rango de 
 * entre 1 y 5.000.000, los ordena de mayor a menor, y luego permite buscar la posición un número
 * introducido por consola entre todos los generados, devolviendo su posición en la lista.
 * @author Eduardo Olalde
 */
public class OrdenarBuscarArray {

	
/**
 * 	Método main que ejecuta todas las funciones de la clase principal:
 *	Generación de números y rellenado de array, ordenado, petición de número por consola y 
 *	búsqueda del número dentro del array.
 */
public static void main(String[] args) {
		
		
		//Declaramos e inicializamos las variables necesarias
	
		//Una variable auxiliar que luego utilizo como buffer para intercambiar
		//números de posición en el array a la hora de ordenarlo.
		//Esta varaiable también será usada más adelante para recoger el número introducido por teclado
		//que se va a buscar en la array una vez ordenada.
		
		int auxiliar = 0;
		
		//Creo 3 variables que permiten personalizar la generación de números aleatorios.
		//Por defecto tienen los valores asociados a la tarea, pero se pueden modificar libremente.
		//Por ejemplo un valor pequeño, como 1000, para selectorRango, hará que se generen multitud de números repetidos,
		//lo que permite probar las funciones asociadas a la repetición del resto del programa.
		//La tercera variable define el tamaño de los arrays, y por lo tanto la cantidad de número generados.
		
		int selectorRango = 5000000;
		int selectorInicio = 1;
		int tamanoArray = 1000;
		
		//Dos variables enteras que se usarán como contadores. El primero contendrá la cantidad de repeticiones del número
		//para saber cuántas veces se ha repetido.
		//El segundo contador se usará para finalizar el bucle de búsqueda de números en el array original sin
		//ordenar cuando se hayan encontrado todas las copias.
		
		int contadorCopias = 0;
		int contadorAuxiliar = 0;
		
		//Declaro cuatro variables que se usarán en el algoritmo de búsqueda del número en el array ordenado.
		
		int limiteSuperior = 0;
		int limiteInferior = 0;
		int posicion = 0;
		int posAnt = 0;
		
		//Variable tipo boolean que se usará para comprobar si el número elegido por el usuario
		//ha sido encontrado
		
		boolean numeroEncontrado = false;
		
		
		//Creación del objeto Scanner para recogida de datos por teclado
		
		Scanner teclado = new Scanner(System.in);

		
		
		//Declaro y construyo el array que va a contener los números.
		//Es un array de tipo entero (int) y una dimensión de un tamaño = tamanoArray
		
		int[] arrayAOrdenar = new int[tamanoArray];
		
		//Inicializo el array a un valor neutro mediante un bucle for que pase por todas
		//sus celdas
		
		for(int i=0; i<=arrayAOrdenar.length-1; i++)
		{
			arrayAOrdenar[i] = 0;
		}
		
		//Declaro, construyo e inicializo un array secundario para guardar la posición
		//original de los números antes de ser ordenados.
		
		int[] arrayOriginal = new int[tamanoArray];
		for(int i=0; i<=arrayOriginal.length-1; i++)
		{
			arrayOriginal[i] = 0;
		}
		
		
		//Muestro un mensaje indicando lo que va a ocurrir durante la ejecución del programa
		System.out.println("Se van a generar " + tamanoArray + " números aleatorios comprendidos entre " + selectorInicio + " y " 
				+ selectorRango + ", luego serán ordenados de mayor a menor. "
				+ "\nPodrás escoger un número para comprobar su posición entre los " + tamanoArray + " generados, contando desde "
						+ "el 1 para la primera posición hasta el " + tamanoArray + " para la ultima."
				+ "\n\nA continuación se mostrarán todos los números aleatorios en el orden tal y como se generaron, "
				+ "agrupados en líneas de 10."
				+ "\nPulsa enter para continuar. ");
		
		//Hago que el usuario deba pulsar enter para conttinuar la ejecucción
		teclado.nextLine();
		System.out.println("-----------------------------------------------------------------------------------------");
		
		//Ahora procedo a generar los 1000 números aleatorios que llenarán el array
		//Genero números aleatorios con el método Math.random, y cojo su parte entera
		//con (int).
		//Uso un bucle for que pase por todas las posiciones del array, y en cada
		//posición introduzco un número aleatorio entero.
		
		
		for(int i=0; i<=arrayAOrdenar.length-1; i++)
		{
			//Establezco el rango y el punto de inicio operando sobre el número devuelto por
			//el método Math. Multiplico por el número que define el rango, y sumo el número
			//que define el número mínimo.
			//Por defecto las variables están establecidas para un rango de 5.000.000 y número mínimo 1.
			arrayAOrdenar[i] = (int)(Math.random()*selectorRango+selectorInicio);
		}
		
		//Guardo la posición original de los números antes de ordenarlos igualando cada 
		//celda del array secundario a la que va a ser ordenada
		for(int i=0; i<=arrayOriginal.length-1; i++)
		{
			arrayOriginal[i] = arrayAOrdenar[i];
		}
		
		
		//Muestro por teclado los números generados, así se puede escoger uno cualquier para su búsqueda
		//Al ser 1000 una cantidad de números tan grande, creo un bucle para mostrarlos por teclado agrupados
		//en líneas de 10 números cada una.
		
		//Primero un mensaje indicándolo
		System.out.println("Estos son los números que han sido generados:");
		
		//Un primer bucle de 100 iteraciones (1000-1)/10 que genera el salto de línea
		for(int i=0; i<=(arrayAOrdenar.length-1)/10; i++)
		{
			System.out.println();
			//Y dentro un segundo bucle de 10 iteraciones que muestra 10 números separados por guiones
			//en la misma línea
			
			for(int j=0; j<=9; j++)
			{
				//Muestro por consola los números separados por guiones
				//Para conseguir la celda correcta de cada línea (que equivale a los 10 números 
				//de las decenas del número i de cada iteración de i), multiplico i por 10 y sumo j
				//en cada muestreo de número.
				System.out.print(" - " + arrayAOrdenar[i*10+j]);
				
			}
			
		}

		
		//Aquí procedo a ordenar el array.
		//La idea es comparar cada celda con el resto de números contenidos
		//en las siguientes celdas del array.
		//Para ello creo un primer bucle, cuya variable de iteración uso para definir
		//cuál es el índice en el array del número que va a ser comparado.
		//Ese número se compara con los números del resto de celdas de índices siguientes,
		//y si el número que hay contenido en la celda actual es menor
		//que el siguiente, se intercambian los números en esas dos celdas.
		//Esto asegura, una vez terminado cada bucle "i", que en esa celda está
		//el mayor número de todos los comparados.
		//Además, cada bucle asegura que en las celdas anteriores (de índice menor) a la i
		//los números contenidos son mayores que los de la celda actual, ordenando el array.
		//Esto también supone que no es necesario comprobar la última posición (la 999),
		//pues necesariamente contendrá el número más pequeño generado una vez comprobada
		//la 998. Por lo tanto finalizo el bucle al llegar a 998 (i < array.lenght-1).
		
		for(int i=0; i<arrayAOrdenar.length-1; i++)
		{
			//Segundo bucle en el que el número en la celda i es comparado con
			//el resto de números del array.
			//Para cada celda, sólo interesa comparar su número con los números
			//de las celdas siguientes, así que el bucle debe empezar en la posición de celda
			//siguiente a la del número que se está comparando actualmente,
			//por lo tanto el bucle empieza en j = i+1
			for(int j=i+1; j<=arrayAOrdenar.length-1; j++)
			{
				//Se compara cada número, y si el número que hay
				//en la celda i es menor que el de la celda j, se intercambian.
				//Esto se consigue guardando el número de la celda i en una variable auxiliar,
				//seguidamente se guarda el número de la celda j en la celda i,
				//y se guarda el número de la variable auxiliar en la celda j (número
				//que anteriormente estaba en la celda i).
				
				if(arrayAOrdenar[i] < arrayAOrdenar[j])
				{
					auxiliar = arrayAOrdenar[i];
					arrayAOrdenar[i] = arrayAOrdenar[j];
					arrayAOrdenar[j] = auxiliar;
				}
			}
		}
		
		//Vuelvo a mostrar los números, una vez han sido ordenados.
		//Uso el mismo método para agruparlos en líneas de 10 que en la anterior muestra.
		System.out.println("\n\n-----------------------------------------------------------------------------------------");	
		System.out.println("Seguidamente se mostrarán los números, agrupados de igual manera que los anteriores, "
				+ "pero ya ordenados de mayor a menor."
				+ "\nPulsa enter para continuar.");
		teclado.nextLine();
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("\n\nEstos son todos los números una vez ordenados:");
		for(int i=0; i<=(arrayAOrdenar.length-1)/10; i++)
		{
			System.out.println();
			
			
			for(int j=0; j<=9; j++)
			{
				
				System.out.print(" - " + arrayAOrdenar[i*10+j]);
				
			}
			
		}
		
		//Introduzco un syso de separación en consola
		System.out.println("\n--------------------------------------------------------------------------");
		
		//Muestro el mensaje que indica al usuario que tiene que introducir un número para su búsqueda
		System.out.print("Escoge un número e introdúcelo pulsando enter para saber en qué posición se encuentra, de 1 a 1000: ");
		
		//Lo recojo mediante el Scanner y almaceno en la variable auxiliar, que puedo reutilizar
		//porque su valor anterior es irrelevante.
		auxiliar = teclado.nextInt();
	
		
		//Procedo a buscar el número introducido por el usuario dentro del array ordenado. Se me ocurre
		//que en un array ordenado se puede crear un algoritmo muy eficiente a base de crear iteraciones que
		//comprueben el número en la posición media de la sección del array que se esté evaluando, empezando
		//con el array completo, ver si es mayor o menor que el número elegido y descartar la mitad en la que
		//no puede estar el número. Repitiendo esta operación se descarta en cada iteración la mitad de los números,
		//hasta que se encuentra el número o nos quedamos sin celdas (lo que significa que el número no
		//está contenido en el array).
		 		
		//El procedimiento consistirá en un bucle do/while, pues es necesario que se ejecute al menos una vez
		//(también podría utilizarse un bucle while asegurando que posicion y posAnt tengan valores distintos
		//antes de comenzar el bucle), en el que la posición evaluada será la mitad de la suma del límite 
		//inferior más el superior ((limiteInferior + limiteSuperior)/2), comenzando con el array entero, 
		//que en este caso supone que el límite inferior es la posición 0 y el superior la posición 999.
		//Igualo las variables a esos valores, y generalizo usando el método .lentgh
		
		limiteSuperior = arrayAOrdenar.length-1;
		limiteInferior = 0;
		
		//A partir de ahí se encuentra la posición a evaluar (la primera será (999+0)/2 = 499), y se comparara el
		//número en esa celda con el número elegido.
		
		//Para evitar la posibilidad de un bucle infinito, utilizo una segunda variable que se iguale
		//al último índice que se ha evaluado. Si se evalúa dos veces la misma celda (posAnt = posicion),
		//significa que el número no está en el array. Eso finaliza el bucle.

		
		do
		{
			//Establezco los nuevos valores para las variables de posición
			posAnt = posicion;
			posicion = (limiteSuperior + limiteInferior)/2;
						
			//Si los números son iguales, se ha encontrado el número. Cambia el valor de numeroEncontrado,
			//y esto hace finalizar el bucle
			if(arrayAOrdenar[posicion] == auxiliar)
			{
				numeroEncontrado = true;
			}
			
			//Si el número en la celda evaluada es mayor que el elegido, teniendo en cuenta que el array está 
			//ordenado de mayor a menor, significa que el número, de estar en el array, estará en la mitad con 
			//celdas de índice mayor que el actual. Por lo tanto, se iguala el límite inferior al índice
			//de la celda siguiente a la celda actual, y se repide el bucle.
			else if(arrayAOrdenar[posicion] > auxiliar)
			{
				limiteInferior = posicion + 1;
			}
			
			//La posibilidad restante es que el número de la celda actual sea menor que el buscado, por lo que
			//se iguala el límite superior a la celda anterior a la actual.
			else
			{
				limiteSuperior = posicion - 1;
			}					
		}
		while(numeroEncontrado == false && posAnt != posicion);
		
		//Una vez acabado el bucle, compruebo si se ha encontrado el número, y en caso contrario
		//se muestra el mensaje en consecuencia. 
		
		if(numeroEncontrado == false)
		{
			System.out.println("\nEl número elegido no está entre los generados.");
		}
		//Si el número fue encontrado, se ejecutará el else
		else
		{
			//Para encontrar todas las copias del número, si es que está repetido, primero buscaré la primera vez que
			//aparece. Para ello, creo un bucle que compara cada número con el número de la posición anterior.
			//El bucle es de tipo do/while porque necesito que se ejecute al menos una vez para asegurar que el número
			//en el índice "posicion" es la primera copia del número.
			//Tengo que tener en cuenta el caso de que se escoja el número más grande. Esto supone que, si el número
			//es único, se encontrará en la celda 0 (o si está repetido puede haberse encontrado en la 0), por lo que 
			//el bucle, en su primera iteración, trataría de evaluar la posición -1, que no existe.
			//Para evitarlo, sólo lo ejecuto en caso de que posición sea mayor que 0. Si posición es 0, ya hemos
			//encontrado la primera copia posible y no hace falta ejecutar el bucle para encontrarla
			if(posicion > 0)
			{			
				do
				{	
					//Utilizo de nuevo la variable posAnt para gestionar el fin del bucle, como explicaré más tarde
					posAnt = posicion;
					//Creo una condición que hace que si el número de la celda actual y la celda inmediatamente
					//anterior son iguales, posición actual pase a ser posicion - 1. Esto hará que posición se vaya
					//desplazando hasta el momento en el que los números de las dos celdas sean distintos, momento
					//en el que habremos encontrado la primera copia del número, y tendremos el valor definitivo
					//de la variable posición, el índice de la celda donde aparece por primera vez el número
					if(arrayAOrdenar[posicion] == arrayAOrdenar[posicion-1])
					{
						
						posicion = posicion-1;
					}
				}
				
				//Para gestionar el bucle, utilizo posAnt para que, en caso de que la celda evaluada en la anterior
				//iteración del bucle sea la misma que la actual, termine el bucle, pues esto sólo ocurrirá cuando 
				//"posicion" no haya cambiado, o lo que es lo mismo, ya hayamos encontrado la primera copia del número.
				while(posAnt != posicion);
			}
			
			//Con la celda definitiva ya encontrada, me interesa saber el número de copias que existen del número,
			//por lo que crearé un bucle que utilice un contador que dará el número de copias. Utilizaré el contador
			//para gestionar los mensajes por consola.
	
			//El bucle será un bucle for que empiece en i = 0  y compara los números de los índices "posicion+i" con el
			//número elegido que está en el índice "posicion", y finalizará cuando los números dejen de ser iguales. 
			//Cuando son iguales, se suma uno al contador. Como el bucle empieza en i = 0, la primera iteración compara
			//la celda en la que está la primera copia consigo misma, correctamente sumando 1 al contador, que está
			//inicializado a 0.
			//En el caso en que el número introducido por el usuario sea el menor generado, este bucle intentaría evaluar el
			//índice 1000, que no existe. Para evitarlo, declaro como primera condición que posicion+i deba ser menor que la
			//longitud del array (1000) para que el bucle pueda ejecutarse. De no cumplirse esa primera condición, el bucle 
			//finalizará sin evaluar la segunda condición, evitando una excepción.
			//El contador seguirá dando el dato correcto, pues una vez llegado a la ultima celda, sabemos que hemos 
			//encontrado todas las copias del número
			for(int i=0;  (posicion+i)<arrayAOrdenar.length && arrayAOrdenar[posicion] == arrayAOrdenar[posicion+i]; i++)
			{
				if(arrayAOrdenar[posicion] == arrayAOrdenar[posicion+i])
				{
					contadorCopias++;
				}			
			}
			
			//Con todos los datos encontrados crearé la estuctura para mostrar los mensajes. Contemplo las distintas
			//posibilidades de repetición. Para ello, utilizo un switch basado en la variable contador.
			//Tendré en cuenta que no voy a usar el número que define la celda del array para describir la posición
			//del número al usuario, puesto que la primera posición del array es la 0, y creo que es más intuitivo
			//describir la posición de los números empezando por el 1 en lugar del 0.
			//Por ello, siempre que se muestre la posición de un número por consola, se sumará 1, para que así el rango 
			//de las posiciones mostradas al usuario vaya del 1 al total de números generados, en lugar de desde el 0
			//al total de los números - 1
			switch (contadorCopias)
			{
			//Para el caso contador == 1, se mostrará el mensaje en singular con la posición del número
			case 1:
				System.out.print("\n\nEl " + auxiliar + " se encuentra en la posición " + (posicion + 1) + ".");
				break;
						
			//En caso de que el contador sea mayor de 1, se ejecutará el default con un mensaje en plural sin salto de línea
			//con la primera posición
			default:
				System.out.print("\nEl " + auxiliar + " está repetido y aparece en las posiciones: " + (posicion + 1));
				
				//Y un bucle for encargado de mostrar mensajes con las siguientes posiciones. El bucle comienza en 2
				//porque ya he mostrado la primera posicion, y su última iteración ocurre cuando i = contador
				for(int i = 2; i<=contadorCopias; i++)
				{
					System.out.print(", " + (posicion + i));
				}
				//Tras el bucle muestro el punto final del mensaje con un salto de línea
				System.out.print(".");
			}
			//Introduzco un salto de línea
			System.out.println();
				
			//Para mostrar las celdas anterior y posterior, analizo los 3 casos posibles:
			//que el número sea el primero, el último, u otro cualquiera.
			
			//Dado que los casos de un switch no pueden contener variableso, en pos de la generalización 
			//(la posibilidad de que los arrays sean de un tamaño distinto a 1000), no puedo usar un switch con los casos
			//0, 999 y default. Usaré en su lugar una estructura if/else if.
			//Hay que tener en cuenta un caso particular, que es que el número sea el menor, pero esté repetido.
			//Si no está repetido, posicion será igual a la última celda del array (array.lenght-1) pero si estuviera repetido,
			//el valor de posicion será un número menor al índice de la última celda. En cualquier caso, el número menor 
			//siempre estará en la celda igual a la longitud del array menos el valor del contador de repeticiones.
			//Dicho de otra forma, para el número menor, siempre es cierto que posicion + contador = array.length, esté repetido o no 
			//(en el caso por defecto, si no está repetido, posicion = 999 y contador = 1, para una repeticion, 
			//posicion = 998 y contador = 2, etc.)
			
			//Evalúo las condiciones y muestro los mensajes adecuados por consola
			
			//Creo el if con la condición que evalúa si el número es el menor de los generados
			if(posicion + contadorCopias == arrayAOrdenar.length)
			{
				//Muestro el número de la posición anterior, que siempre estará en la celda posicion - 1, por lo que se
				//mostrará por consola la posición posicion - 1 + 1, o lo que es lo mismo, posicion
				System.out.println("El número anterior se encuentra en la posición " + (posicion)
						+ " y es el " + arrayAOrdenar[posicion - 1] + ".");
				
				//E indico con un mensaje que no existe posición siguiente	
				System.out.println("El elegido es el número menor de los generados"
						+ ", por lo tanto no hay número siguiente.");
			}
			//El siguiente es el caso en que el número sea el mayor, caso en el que la varibale posicion siempre será
			//igual a 0
			else if(posicion == 0)
			{
				//Muestro el número de la posición siguiente
				//En cualquiera de los casos, el número no repetido siguiente estará en la celda posicion + contador
				//por lo tanto la posición mostrada por consola será posicion + contador + 1
				System.out.println("El número siguiente se encuentra en la posición " + (posicion + contadorCopias + 1)
						+ " y es el " + arrayAOrdenar[posicion + contadorCopias] + ".");
				
				//E indico con un mensaje que no existe posición anterior
				System.out.println("El elegido es el número mayor de los generados,"
						+ " por lo tanto no hay número anterior.");
			}
			//El caso restante es que el número sea otro cualquiera.
			else
			{
				//Muestro el número de la posición anterior, igual que en el caso del menor número
				System.out.println("El número anterior se encuentra en la posición " + (posicion)
						+ " y es el " + arrayAOrdenar[posicion - 1] + ".");
				
				//Muestro el número de la posición posterior, igual que en el caso del mayor
				System.out.println("El número siguiente se encuentra en la posición " + (posicion + contadorCopias + 1)
						+ " y es el " + arrayAOrdenar[posicion + contadorCopias] + ".");
			}
			
	
			//La funcionalidad pedida en la tarea ya ha acabado, pero además, en caso de que se haya encontrado el 
			//número, daré su posición también en el array tal y como estaba antes de ser ordenado. 
			
			System.out.println("\n------------------------------------------------------------------------------------");
			
			//En este caso no se me ocurre un algoritmo eficiente para encontrar el número, puesto que el array está 
			//desordenado. En su lugar, simplemente creo un bucle for que recorra el array celda a celda hasta encontrar
			//el número.
			//Además, quiero que el bucle termine cuando haya encontrado todas las copias del número si es que está
			//repetido, para lo que uso una variable llamada contadorAuxiliar, inicializada a 0, que aumentará en 1 cada 
			//vez que se encuentre una copia del número, y que finalizará el bucle una vez sea igual a contador.
			
			for(int i=0; contadorAuxiliar < contadorCopias; i++)
			{
				//El número se habrá encontrado cuando el número elegido (contenido en auxiliar), sea igual que el 
				//número contenido en la celda de la iteración actual
				if(auxiliar == arrayOriginal[i])
				{
					//Sumo uno al contador que acabará el bucle una vez se haya encontrado el número en todas sus posiciones
					contadorAuxiliar++;
					
					//En este caso, para variar, no voy a repetir la esctructura anterior de mostrar la posición del número y 
					//las celdas anterior y siguiente, sino que sólo voy a mostrar todas las posiciones del número, haciéndolo
					//en una sola línea.
							
					//Si el contador original sólo llegó a valer 1, el número se encontró sólo una vez, así que muestro el 
					//mensaje en singular, de nuevo siempre sumando 1 al mostrar la posición por consola para desplazar el rango
					//de 0-999 a 1-1000
					if(contadorCopias == 1)
					{
						System.out.print("Antes de que se ordenaran los números, el " + auxiliar + " se encontraba en la posición " + (i+1));
					}
					//En caso contrario muestro mensaje en plurar
					else
					{
						//Un primer mensaje para la primera posición
						if(contadorAuxiliar == 1)
						{
							System.out.print("Antes de que se ordenaran los números, el " + auxiliar + 
									" se encontraba en las posiciones: " + (i+1));
						}
						else
						{
							//Y la muestra del resto de posiciones
							System.out.print(", " + (i+1));
						}
					}
			
				}
				
			}
			//Último syso con el punto final de cualquiera de los mensajes una vez acabado el bucle
			System.out.println(".");
			
				
		}
			
	
		//Cierro el objeto Scanner de recogida de datos por teclado
		teclado.close();
		
	}

}
