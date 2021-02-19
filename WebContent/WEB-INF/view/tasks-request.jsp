

        

                <div class="container-fluid">

                    

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Έργα</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Task ID</th>
                                            <th>Τίτλος</th>
                                            <th>Περιγραφή</th>
                                           	<th>Επιβλέπων</th>
                                            <th>Πόντοι</th>
                                        
                                            
                                        </tr>
                                    </thead>
                                    <tbody id="tableBody">   
                                        
                               

                                      
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
             <script>
             $(function () {
                        
                        var xhrSearchFields = new XMLHttpRequest();
                        xhrSearchFields.onreadystatechange = function() {
                            if (xhrSearchFields.readyState == XMLHttpRequest.DONE) {
                                var data = JSON.parse(xhrSearchFields.responseText);
                                var options="";
                            var type;
                            var status;
                                
                                for(var i=0;i<data.length;i++){
                                    console.log(data[i]);
                                    if(data[i]["type"]===1){
                                        type = "Διδασκαλία Εργαστηρίου";
                                    }else if(data[i]["type"]===2){
                                        type="Επιτήρηση";
                                    }else if(data[i]["type"]===3){
                                        type="Διόρθωση Γραπτών";
                                    }
                                    if(data[i]["status"]===0){
                                        status="<td style='color: orange'>Αναμονή Έγκρισης</td>"
                                    }else if(data[i]["status"]===1){
                                        status="<td style='color: green'>Έχει Εγκριθεί</td>"
                                    }
                                   
                                        options += "<tr><td>"+data[i]["id"]+"</td><td><a href='../../tasks/info?id="+data[i]["id"]+"'>"+type+"</a></td><td>"+data[i]["description"]+"</td><td>"+data[i]["firstName"]+" "+data[i]["lastName"]+"</td><td>"+data[i]["points"]+"</td></tr>";
                                    
                                    
                                        
                                    
                                }
                                
                                document.getElementById("tableBody").innerHTML = options;
                            }
                            
                    }
                    xhrSearchFields.open('GET',"../../api/tasks/list?id=0&role=ROLE_ADMIN&status=2",true);
                            xhrSearchFields.send(null);
                    });
            </script>

           