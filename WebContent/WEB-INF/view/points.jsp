
    <!-- Begin Page Content -->
    <div class="container-fluid" style="display:flex;flex-direction: column; justify-content: center; align-items: center">
       <h1>Συνολικοί Πόντοι</h1>
       
              
      
        <!-- /.container-fluid -->
    <script>

            function approveTask(id){
                var tmp = new FormData();
                var xhrSearchFields = new XMLHttpRequest();
                        xhrSearchFields.onreadystatechange = function() {
                            if (xhrSearchFields.readyState == XMLHttpRequest.DONE) {
                                var data = JSON.parse(xhrSearchFields.responseText);
                                if(data["success"]){
                                    alert("success");
                                }else{
                                    alert("fail");
                                }
                               
                                    

                              
                            }
                            
                    }

                    xhrSearchFields.open('POST', '../api/tasks/approval', true);
                    xhrSearchFields.send(JSON.stringify({"id": document.getElementById("taskID").value, "role": "ROLE_ADMIN"}));
            }
    </script>
     
</div>
    <!-- End of Main Content -->
