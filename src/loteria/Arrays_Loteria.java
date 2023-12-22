package loteria;
import java.lang.Math;
import java.util.Scanner;   //se hace la importación de la clase SCANNER para leer por teclado

public class Arrays_Loteria {

	public static void main(String[] args) {
		int vector_boleto[]= new int[50];
		int i=0;
		int j=0;
		Scanner resultado = new Scanner(System.in);  //preparación para leer por teclado
		int vector_comprobacion[]= new int[15];   //Para guardar los números acertados en un vector de números acertados
		int comprobacion=0;   //Numero que irá comprobando si se ha acertado o no y en el caso que sí, será guardado en los vectores anteriores
		int contador_intentos=0;  //Cuando los intentos lleguen a 15 el programa finaliza haya adivinado los números o no el usuario
		int mencion_intentos=1;   //Es el contador que aparecerá para el usuario, así sabrá en qué intento se encuentra en cada ciclo ejecutado
		int pivote=0;		//Pivote que recorrerá los vectores de indices y de numeros acertados
		int parada=0;       //Variable para detener la busqueda de un numero acertado y poner en su lugar una XX, si es encontrado
		int coincide=0; 	//Variable para buscar si el nuevo numero introducido ya habia sido introducido en un intento antes
		boolean encontrado=false;    //Booleano que dice si se ha encontado el numero o no
		boolean repetido=false;		 //Booleano que dice si un numero acertado es nuevamente introducido en el siguiente intento
	    int cuenta_numeros=0;      //Variable que contabiliza el numero de números que se han acertado del boleto
		String boleto="";  //STRING que se irá mostrando para ver si el usuario ha adivinado o no los números con una XX
		//La razón de que se emplee el STRING es la problemática de combinar números enteros de un vector con las "XX" que hay que añadir
		// si acierta el número o números que va metiendo por teclado
		pivote=0;		//Pivote que recorrerá los vectores de indices y de numeros acertados
		// Inicializamos el vector: vector_comprobacion[]
		for(i=0;i<15;i++)
		{
			//Se llena con numeros: -1, en todo el vector_comprobacion
			vector_comprobacion[i]=-1; 
		}
		
		// Primero se guardan los elementos en el vector de enteros
		for (i=0;i<9;i++)
		{
			// El generador de números aleatorios es de tipo DOUBLE, se pone (int) para convertir a tipo entero
			//Si ponía el numero 99 en donde generaba los números aleatorios, generaba números por encima de 100
			vector_boleto[i] = (int) (Math.floor(Math.random()*90+10));
		}
		// Segundo se muestra por pantalla los números introducidos
			System.out.println("Bienvenido a participar a la lotería online");
			System.out.println("-- A continuación se le dará el boleto --");
		    System.out.println("-- El boleto generado es el siguiente  --");
		    System.out.println("");
		for(i=0;i<9;i++)
		{
			//Se muestran los números del boleto que se sacarán por pantalla
			System.out.print(" "+vector_boleto[i]+" ");
		}
		System.out.println("");   //Espacio para diseño de interfaz de usuario
		//Tercero Pedimos al ususario que vaya introduciendo los datos para adivinar el boleto

		do{
				//Bucle repetitivo para comprobar si ha acertado, el usuario
			System.out.println("");
			System.out.println("Este es su intento: " +mencion_intentos);
			System.out.println("Introduzca un número para comprobar si ha acertado:");
			System.out.println("");
			// Recoge los números que haya acertado o no
			comprobacion= resultado.nextInt();
			if(comprobacion==0)
			{
				System.out.println("Ha decidido salir del programa");
			}
			else
			{
				for(i=0;i<9;i++)
				{
					// En el caso de que el número introducido sea uno de los pertenecientes al boleto
					if(vector_boleto[i]==comprobacion && encontrado==false)
					{		
						//Hay que tener en cuenta dos cosas para este IF dentro del FOR que le precede:
						// 1) Si el numero acertado es la primera vez que se mete, se registra y punto
						// 2) Si el usuario vuelve a meter el mismo numero QUE YA HABIA ACERTADO ANTES se procede a no 
						//    registrarlo como número nuevamente acertado, pero se pierde un intento en el proceso
						//En este bucle FOR se comprueba si el nuevo numero introducido es repetido
						for(coincide=0;coincide<15;coincide++)
						{
							if(vector_comprobacion[coincide]==comprobacion && parada==0)
							{
								System.out.println("El número acertado ya había sido introducido antes, inténtelo de nuevo");
								encontrado=false;   //Se considera como FALSE el numero introducido repetido
								parada=1;           //Se cierra el acceso a este IF
								repetido=true;
							}
						}
						//En el caso de que no sea repetido se guardará en el vector_comprobacion[] y se sigue con el programa
						if(repetido==false) 
						{
							System.out.println("Ha acertado el número " + comprobacion + " del boleto!!!");
							//Una vez que se ha comprobado que el número pertenece al boleto, se guardará en el vector_comprobacion
							vector_comprobacion[pivote]=comprobacion;
							//Se incrementa el indice para guardar el siguiente numero que se haya adivinado
							pivote=pivote+1;  //Pasa a la siguiente celda para guardar el siguiente numero
							//Se inicializa el STRING de boleto, y siempre se inicializará para cada número adivinado para actualizarlo
							boleto="";
							encontrado=true;  //En el caso de que se haya encontrado, cambie a TRUE y deja el bucle FOR
							parada=1;           //Se cierra el acceso a este IF
							cuenta_numeros=cuenta_numeros+1;  //Contabiliza los números acertados
						}		
					}
				 }
				 parada=0;   ///Se reinicia la variable para su uso posterior en la siguiente parte del programa
					if(encontrado==true)
					{
						//En el siguiente FOR no se añade el i++, debido a las siguientes razones:
						// 1) Si se adivina el primer número, el añadir i++, pasaría directamente a comprobar el tercer número, saltando por
						//    encima del segundo número a salir del SI del interior del bucle tras marcar como "XX" el adivinado
							for(i=0;i<9;)
							{
									for(j=0;j<15;j++)
									{
										// Este IF se activa para comprobar cada número del boleto con cada número del vector_comprobacion
										// Si aparece en el orden en que se guardó, el número quedará marcado con: "XX"
										if(vector_boleto[i]==vector_comprobacion[j] && parada==0)
										{
											parada=1;  //Se activa la parada cuando el número adivinado se haya encontrado, así no continua la búsqueda
											boleto=boleto+"  XX";
											i=i+1;  //Esta sentencia hace que pase al siguiente número para comprobar, por esta razon
													//en el bucle FOR se omite el i++, ya que al final se incrementaria en dos unidades
										}
									}	
								if(parada==0)
								{
									// Solo cuando el número no se haya encontrado, porque no se encuentra en la posición esperada, se seguirá
									// colocando el número del boleto correspondiente a su posición
									boleto=boleto+"  "+vector_boleto[i];
									i=i+1;  //Se incrementa, igualmente, para pasar al siguiente numero del boleto y del vector_comprobacion
								}
								parada=0;  //Para encontrar el siguiente valor
							}
						System.out.println("");
						System.out.println("El boleto queda actualizado de la siguiente manera:");
						System.out.print(boleto);  //Tras actualizar el STRING se mostrará el resultado de boleto
						System.out.println("");
					}
					if(encontrado==false)
					{
							if(contador_intentos==0 && boleto=="" && repetido==false)
							{
								System.out.println("");
								System.out.println("El número: " +comprobacion+" no existe.");
								System.out.println("El boleto queda del siguiente modo:");
									for(i=0;i<9;i++)
									{
										//Se muestran los números del boleto que se sacarán por pantalla
										System.out.print(" "+vector_boleto[i]+" ");
									}
							}
							if(contador_intentos>0 && boleto!="" && repetido==false)
							{
								System.out.println("");
								System.out.println("El número: " +comprobacion+" no existe.");
								System.out.println("El boleto queda del siguiente modo:");
								System.out.println(boleto);	
							}
							if(contador_intentos>0 && boleto!="" && repetido==true)
							{
								System.out.println("");
								System.out.println("Se contabiliza este intento también, al introducir nuevamente el número acertado");
								System.out.println("El boleto queda del siguiente modo:");
								System.out.println(boleto);	
							}
					}
					encontrado=false;   //Para repetir el siguiente ciclo
					parada=0;  //Se reinicia la variable para el siguiente ciclo de ejecución
					repetido=false; //Se reinicia la variable para el siguiente cielo de ejecución
				    contador_intentos=contador_intentos+1;  //Se contabiliza el intento actual
				    mencion_intentos=mencion_intentos+1;    //Se avanza el contador que aparecerá ante el usuario
				    //Por ultimo se gestiona el posible logro de haber acertado todos los números del boleto
				    //El modo en que se hará es contabilizar los números del vector_comprobacion[] 
				    //Si coincide con el número de cifras ofrecido en el boleto, se considera que se han acertado todos los
				    //numeros del mismo boleto
					    if(cuenta_numeros==9)
					    {
					    	System.out.println("Enhorabuena. Ha adivinado los números del boleto. Es usted millonario!");
					    	comprobacion=0;
					    }
			  } //Llave de cierre del ELSE, de la salida directa de la aplicación al introducir un 0 por teclado
	    }while(contador_intentos<15 && comprobacion!=0); //Aquí termina el bucle de comprobación de resultados
		
		//En el caso de que no logre adivinar pero que no se haya introducido un 0 porque en ese caso es salir de la aplicacion
	    if(cuenta_numeros<9 && comprobacion!=0)
	    {
	    	System.out.println("Lo siento, no ha adivinado todos los números del boleto, no logra ser millonario!");
			System.out.println("Fin del programa");
			System.out.println("");
	    }
	}
}
