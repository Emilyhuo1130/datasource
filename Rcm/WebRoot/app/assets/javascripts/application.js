// This is a manifest file that'll be compiled into application.js, which will include all the files
// listed below.
//
// Any JavaScript/Coffee file within this directory, lib/assets/javascripts, vendor/assets/javascripts,
// or vendor/assets/javascripts of plugins, if any, can be referenced here using a relative path.
//
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// the compiled file.
//
// WARNING: THE FIRST BLANK LINE MARKS THE END OF WHAT'S TO BE PROCESSED, ANY BLANK LINE SHOULD
// GO AFTER THE REQUIRES BELOW.
//
//= require jquery
//= require jquery_ujs
//= require_tree .

TABLE_ROW_COUNT = 22;
TABLE_ROW_HEIGHT = "26px";


function HighlightNav(nav_id) {
  //alert('HighlightNav');
  var nav = document.getElementById(nav_id);
  if (nav != null)
    nav.style.color = '#cba300';
}

// private
function CheckboxSelect(current, group) {
  var i;
  var control_checkbox_all = document.getElementById(group[0]);

  if (current == group[0]) {
    if (control_checkbox_all != null) {
      // get all-checkbox status
      var all_status = control_checkbox_all.checked;
      for (i = 1; i < group.length; i++) {
        var sub_control_checkbox_id = group[i];
        var sub_control_checkbox = document.getElementById(sub_control_checkbox_id);
        if (sub_control_checkbox != null) {
          // set the control to all-status
          sub_control_checkbox.checked = all_status;
        }
      }
    }
  }
  else {
    var control_checkbox = document.getElementById(current);
    if (!control_checkbox.checked) {
      control_checkbox_all.checked = false;
    }
    else {
      // check if all other checkboxes checked?
      var count_checked = 0;
      for (i = 1; i < group.length; i++) {
        var checkbox = document.getElementById(group[i]);
        if (checkbox.checked) {
          ++count_checked;
        }
      }

      if (count_checked == group.length - 1) {
        control_checkbox_all.checked = true;
      }
    }
  }
}

// call this function to bind a group of checkbox
function CheckBoxGroupInitialize(checkbox_group) {
  if (checkbox_group.length >= 2) {
    checkbox_group.forEach(
      function (item) {
        var control_checkbox = document.getElementById(item);
        if (control_checkbox != null)
        {
          control_checkbox.onclick = CheckboxSelect.bind(this, item, checkbox_group);
        }
        else
        {
          return;
        }
      }
    );
  }
}

function TableCheckBoxInit(table_id)
{
  var table = document.getElementById(table_id);

  var table_body = table.tBodies[0];

  var group = ["select_all"];
  var i, row, cell;

  for (i = 0; row = table_body.rows[i]; i++) {
    //iterate through cells
    //cells would be accessed using the "cell" variable assigned in the for loop

    cell = row.cells[1];

    if ( (cell.childNodes.length > 0)
        && (cell.childNodes[0].nodeName == 'INPUT')
        &&  (cell.childNodes[0].type == "checkbox")
        && (cell.childNodes[0].disabled == false) )
    {
      group.push(cell.childNodes[0].id);
    }
  }
  //alert(group);
  CheckBoxGroupInitialize(group);
  var control_checkbox_all = document.getElementById("select_all");
  control_checkbox_all.checked = false;

}


// dynamically adjust row height for the table
function TableInitial(table_id, col_count, max_row_count) {
  if (max_row_count == undefined)
  {
    max_row_count = TABLE_ROW_COUNT;
  }

  if (col_count == undefined)
  {
    col_count = 14;
  }
  var table = document.getElementById(table_id);

  var row_count = table.tBodies[0].rows.length;


  while (row_count < max_row_count) {
    var new_row = table.tBodies[0].insertRow(row_count);

    for (var cell_index = 0; cell_index < col_count; cell_index++) {
      var cell = new_row.insertCell(-1);
    }
    //new_row.style.height = TABLE_ROW_HEIGHT;
    row_count = table.tBodies[0].rows.length;
  }

  // set row height

  for (var row_index = 0; row_index < row_count; row_index++) {
    var rows = table.tBodies[0].rows;
    rows[row_index].style.height = TABLE_ROW_HEIGHT;
  }

  // bind checkbox function
  TableCheckBoxInit(table_id);
  // TODO: highlight row by row
}

// AJAX helper
// url: request url
// method: GET or POST
// callback: function()
function LoadXMLDoc(url, method, callback, post_params) {
  var xmlhttp;

  // always for modern browsers
  xmlhttp = new XMLHttpRequest();

  xmlhttp.onreadystatechange = callback.bind(null, xmlhttp);

  xmlhttp.open(method, url, true);
  if (method.toLowerCase() == 'post') {
    xmlhttp.setRequestHeader("Content-type", "application/json");
    post_params = post_params || {};
   // xmlhttp.send(JSON.stringify(post_params));
  }
  else  //GET
  {
    xmlhttp.send();
  }
}
