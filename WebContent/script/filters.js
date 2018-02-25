// https://stackoverflow.com/questions/35011034/jquery-filtering-with-multiple-checkboxes
var format = {};
var $priceCheckboxes = $(".priceCheckbox");
var $formatCheckboxes = $("formatCheckbox");

$priceCheckboxes.on('change', function(){
	var prices = {};
	$priceCheckboxes.filter( ':checked' ).each( function() {	
		if(!prices.hasOwnProperty(this.id)){
			prices[this.id] = [];
		}
		prices[this.id].push(this.data-low);
	});
	console.log(prices);
});

$(document).on("change", "#under500", function(){
	if(this.checked){
		console.log("Under 500PHP is checked!");
	}
	else{
		console.log("Under 500PHP is unchecked!");
	}
});

$(document).on("change", "#fiveHundredTo1k", function(){
	if(this.checked){
		console.log("500 to 1000 is checked!");
	}
	else{
		console.log("500 to 1000 is unchecked!");
	}
});

$(document).on("change", "#over1000", function(){
	if(this.checked){
		console.log("Over 1000 is checked!");
	}
	else{
		console.log("Over 1000 is unchecked!");
	} 
});

$(document).on("change", "#paperback", function(){
	if(this.checked){
		console.log("paperback is checked!");
	}
	else{
		console.log("paperback is unchecked!");
	} 	
});

$(document).on("change", "#hardbound", function(){
	if(this.checked){
		console.log("hardbound is checked!");
	}
	else{
		console.log("hardbound is unchecked!");
	} 	
});