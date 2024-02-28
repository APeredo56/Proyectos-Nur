function calcularAprobados(notas) {
    let aprobados = [];
    let notaFinal = 0;
    for (estudiante of notas) {
        notaFinal = 0;
        notaFinal += estudiante["Practicos"]*10/100;
        notaFinal += estudiante["Parcial 1"]*10/100;
        notaFinal += estudiante["Parcial 2"]*15/100;
        notaFinal += estudiante["Proyecto Final"]*40/100;
        notaFinal += estudiante["Examen Final"]*25/100;
        if (notaFinal >= 51) {
            //aprobados.push([estudiante["nombre"], notaFinal]);
            
            aprobados.push({
                "Nombre":estudiante["nombre"], 
                "Nota Final": notaFinal.toFixed(2)});
        }
    }
    return aprobados;
}

const notas = [{
        "nombre": "Kellie Shaw",
        "Practicos": 60,
        "Parcial 1": 20,
        "Parcial 2": 45,
        "Proyecto Final": 40,
        "Examen Final": 60
    }, {
        "nombre": "Gary Brock",
        "Practicos": 76,
        "Parcial 1": 34,
        "Parcial 2": 44,
        "Proyecto Final": 67,
        "Examen Final": 27
    }, {
        "nombre": "Brittany Krueger",
        "Practicos": 88,
        "Parcial 1": 24,
        "Parcial 2": 77,
        "Proyecto Final": 71,
        "Examen Final": 26
    }, {
        "nombre": "Denise Hicks",
        "Practicos": 38,
        "Parcial 1": 93,
        "Parcial 2": 15,
        "Proyecto Final": 34,
        "Examen Final": 26
    }, {
        "nombre": "Shannon Schmitt",
        "Practicos": 93,
        "Parcial 1": 54,
        "Parcial 2": 44,
        "Proyecto Final": 51,
        "Examen Final": 28
    }, {
        "nombre": "Cassandra Evans",
        "Practicos": 69,
        "Parcial 1": 45,
        "Parcial 2": 69,
        "Proyecto Final": 11,
        "Examen Final": 5
    }, {
        "nombre": "Holly Padilla",
        "Practicos": 52,
        "Parcial 1": 13,
        "Parcial 2": 100,
        "Proyecto Final": 69,
        "Examen Final": 76
    }, {
        "nombre": "Michele Davis",
        "Practicos": 100,
        "Parcial 1": 11,
        "Parcial 2": 34,
        "Proyecto Final": 11,
        "Examen Final": 5
    }, {
        "nombre": "Raymond Farrell",
        "Practicos": 1,
        "Parcial 1": 27,
        "Parcial 2": 71,
        "Proyecto Final": 26,
        "Examen Final": 40
    }, {
        "nombre": "Corey Wolf",
        "Practicos": 55,
        "Parcial 1": 42,
        "Parcial 2": 42,
        "Proyecto Final": 21,
        "Examen Final": 2
    }
]

const aprobados = calcularAprobados(notas);

console.table(aprobados); 