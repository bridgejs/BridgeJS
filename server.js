var express = require('express');
var app = express.createServer();

app.get('/', function(req, res){
	res.sendfile(__dirname + '/index.html');
});

app.get('/server.js', function(req, res){
	res.sendfile(__dirname + '/server.js');
});

app.get('/rapid.js', function(req, res){
	res.sendfile(__dirname + '/rapid.js');
});

app.get('/gameNative.html', function(req, res){
	res.sendfile(__dirname + '/gameNative.html');
});

app.get('/accelPhoneGap.html', function(req, res){
	res.sendfile(__dirname + '/accelPhoneGap.html');
});

app.listen(80);
