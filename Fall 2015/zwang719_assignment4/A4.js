/* 
Assignment 4
Name: Zilong Wang 
Course: COMP 2511   
*/

var dieValue = []; //store the value for counting the result
var diceList = []; //the dice value from sever
var $playerName1 = "Player1"; //with default player name
var $playerName2 = "Player2";
var score = [0, 0]; //p1 and p2 score
var round = 0; //round 2 is end of game
var chance = 0; //chance can reroll, cap is 1
var NUMBER_OF_SLOTS = 5; //number of die display on screen
var $largestScore = 0; //if user foget check the box, using largest by default

$(document).ready(function () {
    newGame();
    $("#play").on("click", playListener);
    $(".f1, .f2, .f3, .f4, .f5").on("click", toggleDiceFaces); //lock dice faces
    $("#roll, #reroll").on("click", rollDiceListener);
    $("#send").on("click", submitScoreListener);
    $("#newGame").on("click", newGame);
});

/*
start a new game
*/
function newGame() {
    resetEveryting();
    $("#newGame").hide();
    $(".playArea").hide();
    $("#score1").hide();
    $("#score2").hide();
    $("#notice>p").html("Please enter the name<br/> and load the game");
    $(".reRoll").hide();
    $("footer").css("margin-top", "20px");
}

/*
reset every number and user info to default
*/
function resetEveryting() {
    round = 0;
    score = [0, 0];
    change = 0;
    $largestScore = 0;
    $("input[name = Player1]").val(""); //clear input boxes
    $("input[name = Player2]").val("");
    $playerName1 = "Player1"; //set player names to default
    $playerName2 = "Player2";
    $("#pt1, #pt2").html(0); //clear points
    $("#play").removeAttr("disabled"); //enable play button
    //$(":radio").attr("disabled", true);
}

/*
after player hit "play" button, show the game area and their name, point
*/
function playListener(e) {
    e.preventDefault();
    setUpUsers();
    $("#play").attr("disabled", true);
    $("#score1").show();
    $("#score2").show();
    $(".playArea").show();
    $(".rollTheDice").show();
    $(".dice").show();
    $(".die").addClass("bigDie");
    userNotice();
}

/*
after user enter their names, update the names
if they enter empty, use default one
*/
function setUpUsers() {
    $tempName1 = $("input[name = Player1]").val();
    if ($tempName1 != "") {
        $playerName1 = $tempName1;
    }
    $tempName2 = $("input[name = Player2]").val();
    if ($tempName2 != "") {
        $playerName2 = $tempName2;
    }
    $("#p1").html($playerName1);
    $("#p2").html($playerName2);
}

/*
Update the box showing which player's turn
*/
function userNotice() {
    if (round == 0) {
        $("#notice>p").html($playerName1 + " <br/> it is your move");
    } else if (round == 1) {
        $("#notice>p").html($playerName2 + " <br/> it is your move");
    }
}

/*
get random value from the sever and store them into list
present the dice face corresponding to the 5 random values
*/
function playTurn() {
    clearUnlockedDie();
    $.post("http://ins.mtroyal.ca/~nkhemka/2511/process.php")
        .done(function (data) {
            diceList = $.parseJSON(data);
            showDieFace();
            unlockRadio(); //unlock the check box depending on the current die faces
        });
}

/*
this function is the remove the class for unlock the dice faces
User can reroll them later
*/
function clearUnlockedDie() {
    if (chance != 0) {
        for (var i = 0; i < NUMBER_OF_SLOTS; i++) {
            if (!isLockedDice(i)) {
                $(".f" + (i + 1)).removeClass("face" + diceList.Roll[i].value);
                $(".f" + (i + 1)).removeClass("animation");
            }
        }
    }
}

/*
after user hit the "roll the dice" button, five dices rolling
and show them reroll or submit page
*/
function rollDiceListener(e) {
    e.preventDefault();
    playTurn(); //get the dice rolled and show faces
    cleanCheckBox(); //before new selection, reset old one
    chance++; //count how chance to re roll
    $(".rollTheDice").hide();
    $(".reRoll").show();
    if (chance == 2) {
        $(".reRoll>#reroll").attr("disabled", true);
        $(".reRoll>#reroll").css("border-color", "#ff0000");
        chance = 0;
    }
    $(".reRoll>p").addClass("reRollOrScore");
    $(".reRoll>button").addClass("decorateButton");

}

/*
after user hit submit score, the socre will be updated
and game goes to next player round
*/
function submitScoreListener() {
    result();
    round++; //end last round, start next round
    chance = 0;
    userNotice(); //update next player info 
    cleanToggle();
    cleanDieFaces();
    $(".die").css("visibility", "visible"); //show 3d dices
    $(".rollTheDice").show(); //show roll all die button 
    enableReroll();
    if (round == 2) {
        endGame(); //2 player rounds end, game end
    }
}

/*
show dice face depending on the random number
locked dice will not be updated
*/
function showDieFace() {
    var singleDiceFace;
    $(".die").css("visibility", "hidden");
    for (var i = 0; i < diceList.Roll.length; i++) {
        if (!isLockedDice(i)) {
            singleDiceFace = diceList.Roll[i].value;
            $(".f" + (i + 1)).addClass("face" + singleDiceFace);
            $(".f" + (i + 1)).addClass("animation");
            dieValue[i] = singleDiceFace; //update the dice face
        }
    }
}

/*
unlock the radio box depending on the values they get
all combaination at most have change in 2 indexes in diceCount array
*/
function unlockRadio() {
    var diceCount = [0, 0, 0, 0, 0, 0]; //count kinds of values
    var temp = 0;
    $largestScore = 0; //if user reroll, it should be set back to 0
    for (var i = 0; i < dieValue.length; i++) {
        diceCount[dieValue[i] - 1]++;
    }
    for (var i = 0; i < diceCount.length; i++) {
        if (diceCount[i] > 1) { //1 cannot be counted
            if ($largestScore == 0) { //find the first one
                $largestScore += diceCount[i];
            } else {
                temp += diceCount[i]; //new var to get number, for compare
                if ($largestScore != temp) { //avoid 2 * 2 = 4
                    $largestScore *= temp; //full house will be 6, 5 kinds is 5
                }
            }
        }
    }
    switch ($largestScore) { // no break before case 2, 
    case 5: //because, case 5 has 4,3,2! case 4 has 3,2, so on...
        $("#5k>input").removeAttr("disabled");
        $("#5k").css("color", "#cf49e0");
    case 4:
        $("#4k>input").removeAttr("disabled");
        $("#4k").css("color", "#cf49e0");
    case 3:
        $("#3k>input").removeAttr("disabled");
        $("#3k").css("color", "#cf49e0");
    case 2:
        $("#2k>input").removeAttr("disabled");
        $("#2k").css("color", "#cf49e0");
        break;
    case 6:
        $("#2k>input, #3k>input, #6k>input").removeAttr("disabled");
        $("#2k, #3k, #6k").css("color", "#cf49e0");
        break;
    }
}

/*
get value from the radio that user chose
calculate the score for player and update them on the screen
2 kinds: 20
3 kinds: 30
4 kinds: 40
5 kinds: 50
Full house: 75
*/
function result() {
    if ($(".checkBox input").is(":checked")) {
        $largestScore = $(".checkBox input:checked").val();
    }
    if (round == 0) {
        if ($largestScore != 6) {
            $("#pt1").html($largestScore *= 10);
            score[0] = $largestScore;
        } else {
            $("#pt1").html(75);
            score[0] = 75;
        }
    } else {
        if ($largestScore != 6) {
            $("#pt2").html($largestScore *= 10);
            score[1] = $largestScore;
        } else {
            $("#pt2").html(75);
            score[1] = 75;
        }
    }
}

/*
when round is 2 end game
*/
function endGame() {
    $(".dice").hide();
    $(".rollTheDice").hide();
    findWinner();
    $("#newGame").show();
    $("#newGame").addClass("newGame");
}

/*
caculating the score, and find which one is the winner
and update the notice area, showing who won
*/
function findWinner() {
    if (score[0] > score[1]) {
        $("#notice>p").html("Good luck!<br/>" + $playerName1 + " win!");
    } else if (score[0] == score[1]) {
        $("#notice>p").html("Game ended!<br/>Draw!");
    } else {
        $("#notice>p").html("Good luck!<br/>" + $playerName2 + " win!");
    }
}

/*
if 3D die are hidden
lock the dices that user click on 
unlock the dices that being locked, when user click it again
*/
function toggleDiceFaces() {
    if ($(".die").is(":hidden")) {
        if ($(this).attr("title") != "locked") {
            $(this).css("opacity", 0.2);
            $(this).attr("title", "locked");
        } else {
            $(this).css("opacity", 1);
            $(this).removeAttr("title");
        }
    }
}

/*
this function is to set all dice area to be default style
*/
function cleanToggle() {
    $(".f1, .f2, .f3, .f4, .f5").css("opacity", 1);
    $(".f1, .f2, .f3, .f4, .f5").removeAttr("title");
}

/*
clean the dice faces, for next player round
*/
function cleanDieFaces() {
    for (var i = 0; i < NUMBER_OF_SLOTS; i++) {
        $(".f" + (i + 1)).removeClass("face" + dieValue[i]);
        $(".f" + (i + 1)).removeClass("animation");
    }
}

/*
to check if the dice face is locked
@param <index: index of dice>
*/
function isLockedDice(index) {
    return $(".f" + (index + 1)).attr("title") == "locked";
}

/*
unlock disabled re roll button
*/
function enableReroll() {
    $(".reRoll").hide();
    $(".reRoll>#reroll").removeAttr("disabled");
    $(".reRoll>#reroll").css("border-color", "#000000");
}

/*
reset check box to be grey and disable all
*/
function cleanCheckBox() {
    $(".checkBox>label").css("color", "#a8a8a8");
    $(":radio").prop("checked", false); //reset all radio box close
    $(":radio").attr("disabled", true); //reset all radio box close
}