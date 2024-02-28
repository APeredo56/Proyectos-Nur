Proceso Pregunta6
	// 1, 3, 7, 9, 13, 15, 20, 22, 28, 30, 37
	Definir num Como Entero;
	Definir generador Como Entero;
	Definir generador2 Como Entero;
	Definir contador Como Entero;
	Definir limite Como Entero;
	Escribir '>>>>> Serie numerica <<<<<';
	Escribir 'Ingrese el numero de elementos que desea ver de la serie: ';
	Leer limite;
	num <- 1;
	generador <- 2;
	generador2 <- 4;
	Escribir num,', ' Sin Saltar;
	Para contador<-1 Hasta 5 Hacer
		num <- num+generador;
		Escribir num,', ' Sin Saltar;
		num <- num+generador2;
		Escribir num,', ' Sin Saltar;
	FinPara
	Si limite>6 Entonces
		Para contador<-1 Hasta limite-5 Hacer
			generador2 <- generador2+1;
			num <- num+generador2;
			Escribir num,', ' Sin Saltar;
			num <- num+generador;
			Escribir num,', ' Sin Saltar;
		FinPara
	FinSi
	Escribir '';
FinProceso
