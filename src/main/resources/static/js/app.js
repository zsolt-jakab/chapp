var stompClient = null;
var myName = null;
var message = null;

function setConnected(connected) {
    $("#login").prop("disabled", connected);
    $("#logout").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        $("#messageToSend").show();
    }
    else {
        $("#conversation").hide();
        $("#messageToSend").hide();
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
	$.get("/user", function(data) {
		myName = data.userAuthentication.details.name;
    });
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

function login() {
	window.location.replace("/login");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
	$.get("/isAuthenticated", function(data) {
		if(data) {
			connect();
			setName();
		}
    });
    $( "#login" ).click(function() { login(); });
    $( "#logout" ).click(function() { disconnect(); });
    $( "#sendMessage" ).click(function() { sendMessage(); });
    $("#conversation").hide();
    $("#messageToSend").hide();
});

