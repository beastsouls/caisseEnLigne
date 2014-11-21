// javascript pour ouvrir et fermer les calques des pages
// insee - educnet. réalisation Isabelle gautier
<!--

function ouvrir(num){
nom="Layer"+num
if (document.getElementById) { //IE5 ou Netscape 6 
		document.getElementById(nom).style.visibility="visible";
}
if (document.layers) { //NS4.X seul
		document.layers[nom].visibility="visible"
}
}
function fermer(num){
nom="Layer"+num
if (document.getElementById) { //IE5 ou Netscape 6 
		document.getElementById(nom).style.visibility="hidden";
}
if (document.layers) { //NS4.X seul
		document.layers[nom].visibility="hidden"
}
}


// -->

