var globalBookID;

function displayOrders(){
		
	var orderTable = document.getElementById("orderTable");
		
	$.ajax({
		context: this,
		url: 'getOrders',
		type: 'get',
		cache: false,
		success: function(data){
			$(orderTable).append(data);
			//console.log("displayOrders complete!");
		},
		error:function(){
			//console.log("something is wrong on displayOrders");
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
			//console.log("displayAdminList complete!");
		},
		error:function(){
			//console.log("something is wrong on displayAdminList");
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
			//console.log("displayInventory complete!");
		},
		error:function(){
			//console.log("something is wrong on displayInventory");
		}
	});
}

function getPublisherName(publisherID){
	//console.log("I am at getPublisherName");
	//console.log("Publisher ID is " + publisherID);
	
	var publisher;
	
	$.ajax({
		context: this,
		url: 'getPublisherName',
		data:{'publisherID' : publisherID},
		type: 'get',
		cache: false,
		async: false,
		success: function(publisherName){
			//console.log("getPublisherName complete!");
			//console.log("Publisher name in ajax: " + publisherName);
			publisher = publisherName;
		},
		error:function(){
			//console.log("something is wrong on getBookDetails");
		}
	});
	//console.log("Publiser variable contains: " + publisher);
	return publisher;
}

function getAuthorName(authorID){
	//console.log("I am at getAuthorName");
	//console.log("Author ID is " + authorID);
	var author;
	$.ajax({
		context: this,
		url: 'getAuthorName',
		data:{'authorID' : authorID},
		type: 'get',
		cache: false,
		async: false,
		success: function(authorName){
			//console.log("getAuthorName complete!");
			//console.log("Author name in ajax: " + authorName);
			author = authorName;
		},
		error:function(){
			//console.log("something is wrong on getAuthorName");
		}
	});
	//console.log("author variable contains: " + author);
	return author;
}

function updateTextFields(bookJSON){
	//console.log("I am at updateTextFields");
	var authorname = getAuthorName(bookJSON.authorID);
	var publisherName = getPublisherName(bookJSON.publisherID);
	
	//console.log("Author name: " + authorname);
	//console.log("Publisher name " + publisherName);
	
	$("#titleField").val(bookJSON.title);
	$("#authorField").val(authorname);
	$("#isbnField").val(bookJSON.isbn);
	$("#publishedField").val(bookJSON.publishedDate);
	$("#publisherField").val(publisherName);
	$("#titleField").val(bookJSON.title);
	$("#priceField").val(bookJSON.price);
	$("#stockField").val(bookJSON.stock);
	$("#genreList > option").each(function() {
		//console.log("this.value = " + this.value);
    	//console.log("bookJSON.genre = " + bookJSON.genre);
	    if(bookJSON.genre == this.value){
	    	$("#genreList").val(this.value).trigger("change");
	    }
	});
	//console.log("Format " + bookJSON.format);
	if(bookJSON.format == "Paperback"){
		$("#paperbackRadioEdit").prop("checked", true);
	}
	else {
		$("#hardboundRadioEdit").prop("checked", true);
	}
}
function getBookDetails(bookID){
	//console.log("I am at getBookDetails");
	$.ajax({
		context: this,
		url: 'getBookDetails',
		data:{'bookID' : bookID},
		type: 'get',
		cache: false,
		success: function(bookJSON){
			//console.log("getBookDetails complete!");
			updateTextFields(bookJSON);
		},
		error:function(){
			//console.log("something is wrong on getBookDetails");
		}
	});
}

function editBook(bookID){
	//console.log("I am on editBook!");
	var format = $("input[name='edit-format']:checked").val();
	//console.log("format is" + format);
	
	$.ajax({
		context: this,
		url: 'editBook',
		data:{'bookID' : bookID,
			  'title': $("#titleField").val(),
			  'authorName': $("#authorField").val(),
			  'isbn': $("#isbnField").val(),
			  'publisherName' : $("#publisherField").val(),
			  'datePublished': $("#publishedField").val(),
			  'genre': $("#genreList").val(),
			  'price': $("#priceField").val(),
			  'stock': $("#stockField").val(),
			  'format': $("input[name='edit-format']:checked").val(),
			  },
		type: 'post',
		cache: false,
		success: function(data){
			//console.log("edit book complete!");
			//console.log(data);
		},
		error:function(){
			//console.log("something is wrong on editBook");
		}
	});
}

function addBook(){
	//console.log("I am at addbook");
	
	$.ajax({
		context: this,
		url: 'addBook',
		data:{'title': $("#addbook-title").val(),
			  'authorName': $("#addbook-author").val(),
			  'isbn': $("#addbook-isbn").val(),
			  'publisherName' : $("#addbook-publisher").val(),
			  'datePublished': $("#addbook-published").val(),
			  'genre': $("#addbook-genre").val(),
			  'price': $("#addbook-price").val(),
			  'stock': $("#addbook-qty").val(),
			  'format': $("input[name='addbook-format']:checked").val(),
			  },
		type: 'post',
		cache: false,
		success: function(data){
			//console.log("add book complete!");
			//console.log(data);
		},
		error:function(){
			//console.log("something is wrong on editBook");
		}
	});
}

function getOrderDetails(orderID){
	var orderDetailsTable = document.getElementById("orderdetails-ordertable");
	
	$.ajax({
		context: this,
		url: 'getOrderDetails',
		data:{'orderID' : orderID},
		type: 'get',
		cache: false,
		success: function(orderDetails){
			//console.log("getOrderDetails complete!");
			//console.log(orderDetails);
			$(orderDetailsTable).append(orderDetails);
		},
		error:function(){
			//console.log("something is wrong on getBookDetails");
		}
	});
}

function getOrderSummary(orderID){
	var orderSummaryDiv = document.getElementById("ordersummary-div");
	
	$.ajax({
		context: this,
		url: 'getOrderSummary',
		data:{'orderID' : orderID},
		type: 'get',
		cache: false,
		success: function(orderSummary){
			//console.log("getOrderSummary complete!");
			//console.log(orderSummary);
			$(orderSummaryDiv).append(orderSummary);
		},
		error:function(){
			//console.log("something is wrong on getOrderSummary");
		}
	});	
}

$("document").ready(function() {
	displayOrders();
	displayAdminList();
	displayInventory();
	
	$(document).on("click", ".view-orderdetails-btn", function(){
		//console.log("view order details clicked!");
		
		var orderID = $(this).attr("data-orderid");
		//console.log("order ID: " + orderID);
		getOrderSummary(orderID);
		getOrderDetails(orderID);
	});
	$(document).on("click", ".edit-book-btn",function() {
		//console.log("Edit book clicked!");
		
		globalBookID = $(this).attr("data-bookId");
		//console.log("book ID of selected book is: " + globalBookID);
		getBookDetails(globalBookID);
	});
	
	$(document).on("click", "#btn-addbook", function(){
		//console.log("Add book clicked!");
		addBook();
	});
	
	$(document).on("click", "#btn-edit-okay",function() {
		//console.log("Edit book confirmed!");
		editBook(globalBookID);
		$("#viewbook-div").modal('toggle');
	});
	
	$("#details-modal").on("hidden.bs.modal", function () {
		$("#ordersummary-div").empty();
		$("#orderdetails-ordertable").empty();
	    //console.log("modal closed!");
	});

});