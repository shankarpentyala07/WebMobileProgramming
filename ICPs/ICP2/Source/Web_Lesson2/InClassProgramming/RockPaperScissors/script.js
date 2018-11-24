function rps() {

    var userInput = $('#userInput').val();
    $('#finalResult').hide();
    if(userInput == null || userInput == ''){
        alert("Input text cannot be Null/Empty and it should be one of Rock/Scissors/Paper")
    }else {
        <!-- Getting Random Choice -->
        var randomChoice = Math.random();
        console.log(randomChoice);
        // Based on the Random Choice hardcoding systems selection
        if (randomChoice < 0.34) {
            randomChoice = "rock";
        } else if (randomChoice >= 0.34 && randomChoice <= 0.67) {
            randomChoice = "paper";
        } else {
            randomChoice = "scissors";
        }
    }
    // Comparing now between user input and system choice
    var userInputLower = userInput.toLowerCase();
    $("#finalResult").html("Your Choice : "+userInputLower.toUpperCase()+ "<br/> System Choice : "+randomChoice.toUpperCase()
        +"<br/>");

    // Tie
    if(userInputLower == randomChoice){
        $("#finalResult").append("<strong>It's a Tie..</strong>");
    }else {
        // Rock, Scissors Scenario
        if (userInputLower == "rock") {
            if (randomChoice == "scissors") {
                $("#finalResult").append("<strong>You WIN !!!</strong>");
            } else {
                $("#finalResult").append("<strong>You Lose, Try Again</strong>");
            }
        }
        // Paper, Rock Scenario
        if (userInputLower == "paper") {
            if (randomChoice == "rock") {
                $("#finalResult").append("<strong>You WIN !!!</strong>");
            } else {
                $("#finalResult").append("<strong>You Lose, Try Again</strong>");
            }
        }
        // Scissors, Paper scenario
        if (userInputLower == "scissors") {
            if (randomChoice == "paper") {
                $("#finalResult").append("<strong>You WIN !!!</strong>");
            } else {
                $("#finalResult").append("<strong>You Lose, Try Again</strong>");
            }
        }
    }
    $("#finalResult").show();
}


    
