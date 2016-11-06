<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap 101 Template</title>
        <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
    
    <nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
        <a class="navbar-brand" href="#">Maggie's Center</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
          <li class="active"><a href="/visitor_recording.jsp">Visitor Recording</a></li>
          <li><a href="/list_all.do">Daily Records</a></li>
          <li><a href="/list_visits.do">Reports</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Daily Record</h2>
                    <hr/>
                    <form style="margin-top:50px;" action="patient_merge.do" method="post">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td>Day id</td>
                                <td>Seen By</td>
                                <td>Person</td>
                                <td>Visit Type</td>
                                <td>Gender</td>
                                <td>Cancer Site</td>
                                <td>Journey Stage</td>
                                <td>Nature of Visit</td>
                                <td>Activity</td>
                            </tr>
                            </thead>
                            <s:iterator value="merged_record" id="record">
                                <tr>
                                    <td><input type="text" name="day_id" value="<s:property value="day_id"/>" size="2"/></td>
                                    <td><s:property value="seen_by"/></td>
                                    <td><s:property value="person"/></td>
                                    <td><s:property value="visit_type"/></td>
                                    <td><s:property value="gender"/></td>
                                    <td><s:property value="cancer_site"/></td>
                                    <td><s:property value="journey_stage"/></td>
                                    <td><s:property value="nature_of_visit"/></td>
                                    <td><s:property value="activity"/></td>
                                </tr>
                            </s:iterator>
                        </table>
                        <button type="submit" class="btn btn-success center-block">Submit</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    </body>
</html>

