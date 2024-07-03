<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ajax Call Example</title>
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
<button id="myButton">Click me</button>
<div id="resultContainer"></div>

<script>
  $(document).ready(function () {
    // Attach click event to the button
    $("#myButton").click(function () {
      // Get data or parameters
      var parameterValue = "someValue";  // Replace with your actual data

      // Make Ajax call
      $.ajax({
        type: "POST",
        url: "http://localhost:8081/ggggWywoluj",
        data: { parameterName: parameterValue },
        success: function (result) {
          // Handle the result
          $("#resultContainer").html(result);
        },
        error: function (error) {
          console.error("Ajax request failed: " + error);
        }
      });
    });
  });
</script>
</body>
</html>