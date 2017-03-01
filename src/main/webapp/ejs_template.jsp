<!DOCTYPE html5>
<html lang="zh">
	<head>
	</head>
	<body>
		<script>
		<\% for(var i =0,size=users.length;i<size;i++){
			var user = users[i];

			if(i%6==0){

  %>
  			<div class="row">
  <\%
			}
  %>
  			<div class="col-xs-2">
                  <label class="normal"><input type="checkbox" value="<\%=user.id  %>"><\%= user.username %></label>
             </div>
  <\%
			if((i+1)%6==0){
	  %>
              </div>

	  <\%
			}
		}
		  %>
		</script>
	</body>
</html>