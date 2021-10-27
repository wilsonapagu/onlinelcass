<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="panel">
    <div class="panel-body">
        <h3 class="title-hero">
            Create Leave
        </h3>

        <div class="example-box-wrapper">
            <input type="hidden" id="csr-token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <form class="form-horizontal bordered-row" name="leave" id="leaveFormId" method="post">
                <div class="row">

                    <input type="hidden" name="id" id="leaveId" value="">
                    <input type="hidden" name="version" id="version" value="">

                </div>

                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Hr Year</label>
                            <div class="col-md-8">
                                <select name="hrYear:hrYearId" class="custom-select">
                                    <option value="">Please Select</option>
                                    <c:forEach var="hrYear" items="${hrYearList}">
                                        <option value="${hrYear.id}" > ${hrYear.name}</option>
                                    </c:forEach>

                                    <!-- 	<option>Please Select</option>
                                          <option>Male</option>
                                        <option>Female</option>
                                        <option>Others</option> -->
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Days</label>
                        <div class="col-md-8">
                            <input type="text" name="days" id="days" path="name" placeholder="days"
                                   required class="form-control">
                        </div>
                    </div>
                </div>
                </div>



                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">isForwardable</label>
                            <div class="col-md-8">
                                <%--<input type="hidden" name="isForwardable" id="" value="false" />--%>
                                <input type="checkbox" name="isForwardable" id="isForwardable" value="true" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Max Foward</label>
                            <div class="col-md-8">
                                <input type="text" name="maxFoward" id="maxFoward" path="name" placeholder="maxFoward"class="">

                            </div>
                        </div>
                    </div>
                </div>

                <div class="bg-default content-box text-center pad20A mrg10T">
                    <input id="saveButton" style="float: left; margin-left: 5px;" type="button" value="Save"
                           onclick="saveLeave()" class="btn btn-lg btn-primary"/>
                    <input id="deleteButton" style="float: left; margin-left: 5px; display: none;" type="button"
                           value="Delete" onclick="deleteLeave()" class="btn btn-lg btn-primary"/>
                    <input style="float: left; margin-left: 5px;" type="button" value="Cancel"
                           onclick="Server.resetForm('leaveFormId')" class="btn btn-lg btn-primary"/>
                    <div style="clear: both"></div>
                </div>

                <div class="bg-default content-box text-center pad20A mrg25T grid-resize">
                    <table id="jqGridLeave" ></table>
                    <div id="jqGridPager"></div>
                </div>
            </form>
        </div>
    </div>
</div>

<style type="text/css">
    .bg-red {
        background: #cf4436;
        color: #FFFFFF;
    }

    .bg-green {
        background: #449d44;
        color: #FFFFFF;
    }

    .bg-azure {
        background: #d67520;
        color: #FFFFFF;
    }
</style>

<script type="text/javascript" src="../../static/custom/commons.js"></script>
<script>

</script>

<script type="text/javascript">
    $(document).ready(function () {
        $(function() { "use strict";
            $('.bootstrap-datepicker').bsdatepicker({
                format: 'mm-dd-yyyy',
            });
        });

        var id= $('#leaveId').val();
        var gridId= "jqGridLeave";
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val(),
        };
        var url = "${pageContext.request.contextPath}/leave/list/?id="+id;
        var formId = 'leaveFormId';
        var caption = 'Leave Information'
        var urlmethod='POST'
        var colModel = [
            {label: 'id', name: 'id', key: true, hidden: true},
            {label: 'HrYearID', name: 'hr_year_id',width:150},
            {label: 'Days', name: 'days',width:150},
            // {label: 'Description', name: 'description',width:150},
        ]
        console.log();
        Server.list(header,url,colModel,formId,caption,urlmethod,gridId);
    });

    function edit(leaveId) {
        // console.log("Id : "+leaveId);
        var action = "${pageContext.request.contextPath}/leave/edit/";
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };

        Server.edit(header, action, leaveId,setDataToEdit);

    }

    function setDataToEdit(result) {
        Server.resetForm('leaveFormId');
        $('#leaveId').val(result.id);
        $('#days').val(result.days);
        $('#hrYear').val(result.hr_year_id);
        // $('#maxFoward').val(result.maxForward);
        $('#version').val(result.version);
        $('#isForwardable').prop('checked',result.isForwardable);
        $('#saveButton').val('Update');
        // console.log(result.id);
    }


    function deleteLeave() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var content = 'Are you sure you want to delete this Leave?';
        var formId=$('#deleteButton').closest('form').attr('id');
        var caption = "Leave Information"
        var url ="${pageContext.request.contextPath}/leave/delete/";
        var gridId="jqGridLeave"


        console.log(_form_values);
        Server.delete(header,url,_form_values,formId,content,caption,gridId);
        console.log(_form_values);
    }





    <%--var isRepeated = $('#isRepeated').val();--%>
    <%--if ($('#isRepeated').is(":checked")){--%>
    <%--isRepeated = true;--%>
    <%--} else {--%>
    <%--isRepeated = false;--%>
    <%--}--%>
    <%--console.log(isRepeated);--%>


    function saveLeave() {
        header = {
            'X-CSRF-TOKEN': $('#csr-token').val(),
            '${_csrf.parameterName}': $('#csr-token').val()
        };
        var caption = "Leave Information"

        var formId = $('#saveButton').closest('form').attr('id');
        var gridId= "jqGridLeave";

        console.log(caption);
        // var hrYearId =  $('#hrYearId').val(result.id);

        var _form_values =$('#leaveFormId').serializeJSON({
            // hr_year_id : $('#hrYear.id').val(),
            checkboxUncheckedValue: "false" ,
            customTypes: {

                // hrYear : $('#hrYear').val(),

                hrYearId : function(str) {
                  return  "120";
                }

                // strDate: function(str) { // value is always a string
                //     var strdate=new Date($('#holidayDate').val());
                //     return new Date(strdate);
                // },

            }
        });

        if ($('#leaveId').val() > 0) {
            var action ="${pageContext.request.contextPath}/leave/update";
        } else {
            var action ="${pageContext.request.contextPath}/leave/save";
        }
        console.log(action);
        console.log(formId);
        console.log(gridId);
        console.log(_form_values);
        Server.save(header, _form_values, action, formId, caption,gridId);
        Server.resetForm(formId);
    }

</script>