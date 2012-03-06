
function __bindCanvasesToAndroid(){
	var elements = document.getElementsByTagName("canvas");

	for (i in elements){
		var element = elements[i];
		if (typeof element.id === "undefined"){
			continue;
		};

		var id = element.id;
		var style = element.style;

		var context = document.getElementById(id).getContext("2d");

		if (typeof context.boundToRapid === "undefined"){
			context.mWatch("fillStyle", function(idA, oldval, newval) {
				if (idA == "fillStyle"){
					__androidCanvas.gotFillStyle(newval, id);
				};
				return newval;  
			});

			context.save = function(){
				return __androidCanvas.save(id);
			};
			context.restore = function(){
				return __androidCanvas.restore(id);
			};
			context.translate = function(x,y){
				return __androidCanvas.translate(id,x,y);
			};
			context.rotate = function(rads){
				return __androidCanvas.rotate(id,rads);
			};

			context.clearRect = function(x,y,w,h){
				return __androidCanvas.clearRect(id,x,y,w,h);
			};
			context.fill = function(){
				return __androidCanvas.fill(id);
			};
			context.fillRect = function(x,y,w,h){
				return __androidCanvas.fillRect(id,x,y,w,h);
			};
			context.beginPath = function(){
				return __androidCanvas.beginPath(id);
			};
			context.rect = function(x,y,w,h){
				return __androidCanvas.rect(id,x,y,w,h);
			};
			context.closePath = function(){
				return __androidCanvas.closePath(id);
			};
			context.arc = function(x,y,rad,sa,ea,cc){
				return __androidCanvas.arc(id,x,y,rad,sa,ea,cc);
			};
			context.drawImage = function(img,x,y){
				return __androidCanvas.drawImage(id,img.src,x,y);
			};
			context.boundToRapid = true;
			offset = getCumulativeOffset(element);

			__androidCanvas.gotCanvas(id, offset.x, offset.y, element.width, element.height);
		}

	};
};

function getCumulativeOffset(obj) {
	var left, top;
	left = top = 0;
	if (obj.offsetParent) {
		do {
			left += obj.offsetLeft;
			top  += obj.offsetTop;
		} while (obj = obj.offsetParent);
	};
	return {
		x : left,
		y : top
	};
};

if (!Object.prototype.mWatch) {
	Object.prototype.mWatch = function (prop, handler) {
		var oldval = this[prop], newval = oldval,
		getter = function () {
			return newval;
		},
		setter = function (val) {
			oldval = newval;
			return newval = handler.call(this, prop, oldval, val);
		};
		if (delete this[prop]) {
			if (Object.defineProperty) {
				Object.defineProperty(this, prop, {
					get: getter,
					set: setter,
					enumerable: false,
					configurable: true
				});
			} else if (Object.prototype.__defineGetter__ && Object.prototype.__defineSetter__) {
				Object.prototype.__defineGetter__.call(this, prop, getter);
				Object.prototype.__defineSetter__.call(this, prop, setter);
			};
		};
	};
};

__bindCanvasesToAndroid();


setInterval(function(){
	__androidCanvas.redrawAll();
}, 33);

console.log("canvas2d loaded");