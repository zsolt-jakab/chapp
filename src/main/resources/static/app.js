var stompClient = null;
var myName = "Anonym";
var message = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        $("#messageToSend").show();
        $("#setName").hide();
    }
    else {
        $("#conversation").hide();
        $("#messageToSend").hide();
        $("#setName").show();
    }
    $("#allMessages").html("");
}

function connect() {
    var socket = new SockJS('/chat-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/conversation', function (message) {
            showMessage(JSON.parse(message.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function setName() {
    myName = $("#name").val();
}

function sendMessage() {
    message = $("#message").val();
    stompClient.send("/app/messages", {}, JSON.stringify({'name': myName, 'value': message}));
}

function showMessage(message) {
    var words = message.split(':');
    var firstWord = words[0];
    if (firstWord != myName) { buildMessageRow(message); }
    else { buildMessageRow ("<b>" + message + "</b>"); }
}

function buildMessageRow(message) {
    $("#allMessages").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { setName(); connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#sendMessage" ).click(function() { sendMessage(); });
    $("#conversation").hide();
    $("#messageToSend").hide();
});

