

        

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
                                            <th>Κατάσταση</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <c:forEach var="tmp" items="${tasks}">
                                      
										
										      
										      
                                        <tr>
                                                <td>${tmp.id}</td>
                                                <td><a href="${pageContext.request.contextPath}/tasks/info?id=${tmp.id}">
                                                <c:choose>
                                                	<c:when test = "${tmp.type == 1}">
											            Διδασκαλία Εργαστηρίου
											         </c:when>
											         <c:when  test = "${tmp.type == 2}">
											            Επιτήρηση
											         </c:when>
											         <c:when test = "${tmp.type == 3}">
											            Διόρθωση Γραπτών
											         </c:when>
                                                </c:choose>
                                                </a></td>
                                                <td>${tmp.description}</td>
                                                <td>${tmp.getSupervisor().firstName}&nbsp;${tmp.getSupervisor().lastName}</td>
                                                <td>${tmp.points}</td>
                                                <c:choose>
                                                	<c:when test = "${tmp.status == 1}">
											           <td style="color:green">Έχει Εγκριθεί</td>
											         </c:when>
											         <c:when test = "${tmp.status == 0}">
											           <td style="color:orange">Αναμονή Έγκρισης</td>
											         </c:when>
											         <c:when test = "${tmp.status == 2}">
											           <td style="color:orange">Αίτημα Ολοκλήρωσης</td>
											         </c:when>
											         <c:when test = "${tmp.status == 3}">
											           <td style="color:green">Ολοκλήρωση</td>
											         </c:when>
											         <c:when test = "${tmp.status == 4}">
											           <td style="color:red">Απορρίφθηκε</td>
											         </c:when>
                                                </c:choose>
                                                
                                                 
                                        </tr>
                                </c:forEach>

                                      
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

           