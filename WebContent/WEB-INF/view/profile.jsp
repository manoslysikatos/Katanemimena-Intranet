

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Προφίλ</h1>
                </div>
                <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <label for="firstname">Όνομα:</label>
                        <input type="text" class="form-control form-control-user" id="exampleFirstName" value="${selectedInfo.firstName}"
                            placeholder="Πληκτρολογήστε..." disabled>
                    </div>
                    <div class="col-sm-6">
                        <label for="lastname">Επώνυμο:</label>
                        <input type="text" class="form-control form-control-user" id="exampleLastName" value="${selectedInfo.lastName}"
                            placeholder="Πληκτρολογήστε..." disabled>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <label for="role">Ρόλος:</label>
                        <select id="role" name="role" class="form-control form-control-user" value="${selectedInfo.role}" disabled >
                            <option value="blank">Επιλέξτε...</option>
                            <option value="student">Υποψήφιος διδάκτορας</option>
                            <option value="professor">Καθηγητής</option>
                            <option value="staff">Γραμματεία</option>
                        </select>
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <label for="department">Τμήμα:</label>
                        <select id="department" name="department" class="form-control form-control-user" disabled>
                            <option value="blank">Επιλέξτε...</option>
                            <option value="informatics">Τμήμα Πληροφορικής</option>
                            <option value="geography">Τμήμα Γεωγραφίας και Οικονομίας</option>
                            <option value="health">Τμήμα Υγείας</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <label for="birthday">Ημερομηνία Γέννησης:</label>
                        <input type="date" id="birthday" name="birthday" class="form-control form-control-user" disabled>
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <label for="phone">Τηλέφωνο:</label>
                        <input type="tel" id="phone" name="phone" placeholder="123-45-678" value="${selectedInfo.phone}" class="form-control form-control-user"
                        pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" disabled>
                    </div>
                </div>
 <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                    <label for="email">Ηλεκτρονική διεύθυνση:</label>
                    <input type="email" class="form-control form-control-user" id="exampleInputEmail" value="${selectedInfo.email}"
                        placeholder="Email Address" disabled>
                </div>
</div>
<div style="display: flex; justify-content: end; align-items: end; flex-direction: column;">
                <div class="form-group col-sm-3">
                    <button onclick="enableBtn()" class="form-control form-control-user btn-secondary">Επεξεργασία πληροφοριών</button>
                </div>

                <div class="form-group col-sm-3">
                    <button onclick="disableBtn()" class="form-control form-control-user btn-primary">Αποθήκευση</button>
                </div>
</div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

   <!-- Scroll to Top Button-->
   <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Αποσύνδεση</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Πατήστε "Αποσύνδεση" αν επιθυμείτε να αποδυνδεθείτε.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Ακύρωση</button>
                    <a class="btn btn-primary" href="login.html">Αποσύνδεση</a>
                </div>
            </div>
        </div>
    </div>


   
    <script>
        function enableBtn() {
            document.getElementById("exampleFirstName").disabled = false;
            document.getElementById("exampleLastName").disabled = false;
            document.getElementById("role").disabled = false;
            document.getElementById("department").disabled = false;
            document.getElementById("birthday").disabled = false;
            document.getElementById("phone").disabled = false;
            document.getElementById("exampleInputEmail").disabled = false;
        } 
        function disableBtn() {
            document.getElementById("exampleFirstName").disabled = true;
            document.getElementById("exampleLastName").disabled = true;
            document.getElementById("role").disabled = true;
            document.getElementById("department").disabled = true;
            document.getElementById("birthday").disabled = true;
            document.getElementById("phone").disabled = true;
            document.getElementById("exampleInputEmail").disabled = true;
        }
    </script>