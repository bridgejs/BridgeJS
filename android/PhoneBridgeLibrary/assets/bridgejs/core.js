var ___androidExists = true;

function disableAnnoyingBlueHighlightOnClick(){
	var sheet = document.createElement('style')
	sheet.innerHTML = "div { -webkit-tap-highlight-color: rgba(0, 0, 0, 0);	}";
	 document.getElementsByTagName("head")[0].appendChild(sheet);
}
disableAnnoyingBlueHighlightOnClick();

console.log("core loaded");