function getGreater(num1, num2, num3){
    if(num1 > num2 && num1 > num3){
        return num1
    } else if (num2 > num1 && num2 > num3){
        return num2
    } else return num3
}

function getNaturalNumbers(amount){
    let output = ""
    let separador = ""
    for(let i = 1; i <= amount; i++){
        output += separador + i*2
        separador = ", "
    }
    return output
}

function fibonacii(limit){
    let contador = 0;
    let num1 = 0;
    let num2 = 1;
    let output = "1";
    while(contador < limit){
        output += ", " + num1 + num2;
        let holder = num1
        num1 = num2
        num2 = holder + num2;
        contador ++;
    } 
    return output
}

function getPrimeNumbers(amount){
    let output = ""
    let contador = 0
    let numActual = 2
    while (contador < amount){
        let primo = true
        for(let i = 2; i < numActual; i++){
            if(numActual%i === 0){
                primo = false
                break
            }
        }
        if(primo){
            output += numActual + ", "
            contador++
        }
        numActual++
    }
    return output
}

function getFactorial(number){
    if(number === 1){
        return 1
    }
    return number * getFactorial(number - 1)
}