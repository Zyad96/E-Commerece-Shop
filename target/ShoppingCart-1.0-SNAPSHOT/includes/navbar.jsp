<%-- 
    Document   : navbar
    Created on : Apr 10, 2023, 5:05:32 AM
    Author     : zyadm
--%>

 <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">Shopping-Cart</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto ">
        <li class="nav-item active">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="cart.jsp">Cart<span class="badge rounded-pill bg-danger px-1" style="background-color: red;">${cart_list.size()}</span></a>
        </li>
        
        <li class="nav-item">
            <a class="nav-link active" href="orders.jsp">Orders</a>
        </li>
      
    </div>
  </div>
</nav>
