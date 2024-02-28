Proceso Pregunta8
	Definir nestudiantes Como Entero;
	Definir notamin Como Entero;
	Definir notas Como Entero;
	// a. La cantidad de Estudiantes reprobados 
	// b. La cantidad de Estudiantes aprobados. 
	// c. El promedio del curso. 
	// d. El porcentaje de estudiantes con notas mayores o iguales a 78.
	Definir aprobados Como Entero;
	Definir reprobados Como Entero;
	Definir promedio Como Real;
	Definir porcentaje Como Real;
	Definir contador Como Entero;
	Definir contador2 Como Entero;
	Escribir '>>>>> Sistema de notas <<<<<';
	Escribir 'Ingresar la cantidad de alumnos: ';
	Leer nestudiantes;
	Escribir 'Escribir la nota minima para aprobar: ';
	Leer notamin;
	contador <- 1;
	porcentaje <- 0;
	reprobados <- 0;
	aprobados <- 0;
	promedio <- 0;
	Mientras contador<=nestudiantes Hacer
		Escribir 'Ingrese la nota del alumno ',contador,' de ',nestudiantes;
		Leer notas;
		Si notas<notamin Entonces
			reprobados <- reprobados+1;
		SiNo
			aprobados <- aprobados+1;
		FinSi
		promedio <- promedio+notas;
		contador <- contador+1;
		Si notas>=78 Entonces
			porcentaje <- porcentaje+1;
		FinSi
	FinMientras
	Escribir 'Numero de estudiantes aprobados: ',aprobados;
	Escribir 'Numero de estudiantes reprobados: ',reprobados;
	Escribir 'Promedio del curso: ',promedio/nestudiantes;
	Escribir 'porcentaje de notas mayores a 78: ',(porcentaje/nestudiantes)*100,'%';
FinProceso
