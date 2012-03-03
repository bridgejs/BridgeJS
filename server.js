var express = require('express');
var app = express.createServer();

app.get('/', function(req, res){
	res.sendfile(__dirname + '/index.html');
});

app.get('/server.js', function(req, res){
	res.sendfile(__dirname + '/server.js');
});

app.get('/rapid.js', function(req, res){
	res.sendfile(__dirname + '/js/rapid.js');
});

app.get('/demos/gameNative.html', function(req, res){
	res.sendfile(__dirname + '/demos/gameNative.html');
});

app.get('/demos/gamePhoneGap.html', function(req, res){
	res.sendfile(__dirname + '/demos/gamePhoneGap.html');
});

app.listen(80);
