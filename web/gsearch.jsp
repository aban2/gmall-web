<html>
    <head>
        <title>gsearch</title>
    </head>
    <link rel="stylesheet" type="text/css" href="css/genyu.css">
    <jsp:include flush="true" page="head.jsp"></jsp:include>
    
    <div class="dleft">
    </div>
    
    <div class="dright">

        <div class="item">
            <img src="311images/5.png" style="width:100%;height:60%" />
            <p>men''s white regular fit shirt</p>
            <p>&72.99</p>
            
            <%
                String[] name = new String[5];
                String[] image = new String[5];
                String[] price = new String[5];
                String[] star = new String[5];
                
                for(int i = 0; i < 5; i++){
                    name[i] = (String)request.getAttribute("name"+i);
                    image[i] = (String)request.getAttribute("image"+i);
                    price[i] = (String)request.getAttribute("price"+i);
                    star[i] = (String)request.getAttribute("star"+i);
                }
              
            %>
            
            <img src=<%=image[0]%> style="width:100%;height:60%" />
            <p><%=name[0]%></p>
            <p><%=price[0]%></p>
            <p><%=star[0]%></p>
            <img src=<%=image[1]%> style="width:100%;height:60%" />
            <p><%=name[1]%></p>
            <p><%=price[1]%></p>
            <p><%=star[1]%></p>
            <img src=<%=image[2]%> style="width:100%;height:60%" />
            <p><%=name[2]%></p>
            <p><%=price[2]%></p>
            <p><%=star[2]%></p>
            <img src=<%=image[3]%> style="width:100%;height:60%" />
            <p><%=name[3]%></p>
            <p><%=price[3]%></p>
            <p><%=star[3]%></p>
            <img src=<%=image[4]%> style="width:100%;height:60%" />
            <p><%=name[4]%></p>
            <p><%=price[4]%></p>
            <p><%=star[4]%></p>
        </div> 
        
    </div>
    
</html>
