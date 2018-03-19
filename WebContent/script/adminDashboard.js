function displayOrders(){
		
	var orderTable = document.getElementById("orderTable");
		
	$.ajax({
		context: this,
		url: 'getOrders',
		type: 'get',
		cache: false,
		success: function(data){
			$(orderTable).append(data);
			console.log("displayOrders complete!");
			console.log(data);
		},
		error:function(){
			console.log("something is wrong on displayOrders");
		}
	});
}

function displayAdminList(){
		
	var adminTable = document.getElementById("adminTable");
		
	$.ajax({
		context: this,
		url: 'getAdminList',
		type: 'get',
		cache: false,
		success: function(data){
			$(adminTable).append(data);
			console.log("displayAdminList complete!");
			console.log("adminList is"  + data);
		},
		error:function(){
			console.log("something is wrong on displayAdminList");
		}
	});
}

function displayInventory(){
	
	var inventoryTable = document.getElementById("inventoryTable");
		
	$.ajax({
		context: this,
		url: 'getInventory',
		type: 'get',
		cache: false,
		success: function(data){
			$(inventoryTable).append(data);
			console.log(data);
			console.log("displayInventory complete!");
		},
		error:function(){
			console.log("something is wrong on displayInventory");
		}
	});
}

$("document").ready(function() {
	displayOrders();
	displayAdminList();
	displayInventory();
});