const inputPrimeNumbers = document.getElementById("inputPrimeNumbers");
const lbPrimeNumbersOutput = document.getElementById("lbPrimeNumbersOutput");
const formPrimeNumbers = document.getElementById("formPrimeNumbers")

function primeNumberSubmitClick(event){
    if (inputPrimeNumbers.value === ""){
        inputPrimeNumbers.insertAdjacentElement("beforebegin", emptyError);
        return;
    }
    lbPrimeNumbersOutput.textContent = getPrimeNumbers(inputPrimeNumbers.value); 
    formPrimeNumbers.removeChild(errorSpan[0]);
}

formPrimeNumbers.addEventListener('submit', primeNumberSubmitClick)