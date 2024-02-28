const inputFactorial = document.getElementById("inputFactorial");
const lbFactorialOutput = document.getElementById("lbFactorialOutput");
const formFactorial = document.getElementById("formFactorial");

function factorialSubmitClick(event){
    if(inputFactorial.value === ""){
        inputFactorial.insertAdjacentElement('beforebegin', emptyError);
        return;
    }
    lbFactorialOutput.textContent = getFactorial(inputFactorial.value);
    formFactorial.removeChild(emptyError);
    
}

formFactorial.addEventListener('submit', factorialSubmitClick);