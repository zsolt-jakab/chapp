var stompClient = null;
var myName = null;
var message = null;

function login() {
	window.location.replace("/login");
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

function logout() {
	ajaxSetUp();
	$.post("/logout", function() {
		disconnect();
	})
}

function ajaxSetUp() {
	$.ajaxSetup({
	beforeSend : function(xhr, settings) {
	  if (settings.type == 'POST' || settings.type == 'PUT'
	      || settings.type == 'DELETE') {
	    if (!(/^http:.*/.test(settings.url) || /^https:.*/
	        .test(settings.url))) {
	      xhr.setRequestHeader("X-XSRF-TOKEN", Cookies.get('XSRF-TOKEN'));
	    }
	  }
	}
	});
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

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
    $( "#logout" ).click(function() { logout(); });
    $( "#sendMessage" ).click(function() { sendMessage(); });
    $("#conversation").hide();
    $("#messageToSend").hide();
});