Proceso Pregunta7
	Definir nombre Como Caracter;
	Definir contador Como Entero;
	Definir largo Como Entero;
	Definir posicion Como Entero;
	Definir nombrenum Como Caracter;
	Definir auxiliar Como Caracter;
	Escribir '>>>>> Enumerador de digitos de un nombre <<<<<';
	Escribir 'Ingrese su nombre: ';
	Leer nombre;
	largo <- Longitud(nombre);
	posicion <- 0;
	nombrenum <- '';
	Para contador<-1 Hasta largo Hacer
		auxiliar <- Concatenar(subcadena(nombre,posicion,posicion),ConvertirATexto(contador));
		nombrenum <- Concatenar(nombrenum,auxiliar);
		posicion <- posicion+1;
	FinPara
	Escribir nombrenum;
FinProceso
