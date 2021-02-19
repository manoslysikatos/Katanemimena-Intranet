

        

                <div class="container-fluid">

                    

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Μέλη Γενικής Συνέλευσης</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>User ID</th>
                                            <th>Ονοματεπώνυμο</th>
                                            <th>Email</th>
                                           	<th>Τηλέφωνο</th>
                                            
                                            
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <c:forEach var="tmp" items="${board}">

                                        <tr>
                                                <td>${tmp.id}</td>
                                                <td><a href="${pageContext.request.contextPath}/board-members/info?id=${tmp.id}">${tmp.firstName}&nbsp;${tmp.lastName}</a></td>
                                                <td>${tmp.email}</td>
                                                <td>${tmp.phone}</td>
                                               
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

           