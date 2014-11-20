	function write_to_excel() 
	{
		
		alert("Demande au prof, import d'excel, fichier texte ????");
	str="";

	var mytable = document.getElementsByTagName("table")[0];
	var row_Count = mytable.rows.length;
	var col_Count = mytable.getElementsByTagName("tr")[0].getElementsByTagName("td").length;    

	if (window.ActiveXObject) {
	    try {
	var ExcelApp = new ActiveXObject("Excel.Application");
	var ExcelSheet = new ActiveXObject("Excel.Sheet");
	ExcelSheet.Application.Visible = true;

	    for(var i=0; i < row_Count ; i++) 
	    {   
	        for(var j=0; j < col_Count; j++) 
	        {           
	            str= mytable.getElementsByTagName("tr")[i].getElementsByTagName("td")[j].innerHTML;
	            ExcelSheet.ActiveSheet.Cells(i+1,j+1).Value = str;
	        }
	    }
	    
	// Save the sheet.
    ExcelSheet.SaveAs("C:\\TEST.XLS");
	// Close Excel with the Quit method on the Application object.
	ExcelSheet.Application.Quit();
	    }
	    catch (e) {
	        alert (e.message);
	    }
	}
	else {
	    alert ("Your browser does not support this example.");
	}
	}
	