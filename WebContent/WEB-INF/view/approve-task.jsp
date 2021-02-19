
    <!-- Begin Page Content -->
    <div class="container-fluid" style="display:flex;flex-direction: row;">
        <div class="col-xl-6">
        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 id="title" class="h3 mb-0 text-gray-800">
 <c:choose>
                                                	<c:when test = "${selectedInfoTask.type == 1}">
											            Διδασκαλία Εργαστηρίου
											         </c:when>
											         <c:when  test = "${selectedInfoTask.type == 2}">
											            Επιτήρηση
											         </c:when>
											         <c:when test = "${selectedInfoTask.type == 3}">
											            Διόρθωση Γραπτών
											         </c:when>
                                                </c:choose>
			</h1>
        </div>
        <div class="form-group row">
            <div class="col-sm-6">
                <label for="studentname">Ονοματεπώνυμο Υποψηφίου:</label>
                <input type="text" class="form-control form-control-user" id="studentName"
                    placeholder="[Ονοματεπώνυμο]" disabled value='${candidateInfo.get("firstName")}&nbsp;${candidateInfo.get("lastName")}'>
            </div>
            <div class="col-sm-6">
                <label for="profname">Ονοματεπώνυμο Καθηγητή:</label>
                <input type="text" class="form-control form-control-user" id="profName"
                    placeholder="[Ονοματεπώνυμο]" disabled value="${selectedInfoTask.getSupervisor().firstName}&nbsp;${selectedInfoTask.getSupervisor().lastName}">
            </div>
        </div>

        <div class="form-group row">
        <div class="col-sm-3">
                <label for="taskID">Αναγνωριστικό Έργου:</label>
                <input type="text" class="form-control form-control-user" id="taskID"
                    placeholder="Αναγνωριστικό Έργου" disabled value="${selectedInfoTask.id}">
            </div>
            <div class="col-sm-8">
                <label for="taskType">Τύπος Έργου:</label>
                <input type="text" class="form-control form-control-user" id="taskType"
                    placeholder="[Τύπος]" disabled 
                	value="<c:choose><c:when test="${selectedInfoTask.type==1}">Διδασκαλία Εργαστηρίου</c:when><c:when test="${selectedInfoTask.type==2}">Επιτήρηση</c:when><c:when test="${selectedInfoTask.type==3}">Διόρθωση Γραπτών</c:when></c:choose>"    
                >
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-12">
                <label for="profname">Σύντομη Περιγραφή:</label>
                <textarea id="info" name="info" class="form-control form-control-user" disabled >
${selectedInfoTask.description}
                </textarea>
            </div>
        </div>
    
            <c:choose>
            <c:when test="${selectedInfoTask.status==2}">
            <div class="col-sm-6">
                <a onclick="approveTask()" id="approveBtn" class="form-control form-control-user btn btn-primary">
                    Αποδοχή
                </a>
            </div>
            </c:when>
            </c:choose>
        </div>
        </div>
       
              
      
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
