<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

</body>
<script>

  var vegetables = ${vegetables};
  var categories = ${categories};
  var data = [];
  for(var i =0 ; i<categories.length; i++){
      var cid = categories[i].id;
      for (var j=0 ; j<vegetables.length;j++){
         var vegetable = vegetables[j];
          if(vegetable.vegetableCategoryId==cid){
            var objs = data[cid];
            if(objs==null){
              objs = new Array();
            }
            objs.push(vegetable);
            data[cid] = objs;
          }
      }
  }
console.log(data);
</script>
</html>
