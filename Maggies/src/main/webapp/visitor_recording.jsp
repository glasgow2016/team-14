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
                <div class="col-md-8 col-md-offset-2">
                    <h2>Visitor Recording</h2>
                    <hr/>
                    <form style="margin-top:50px;" action="insert_record.do" method="post">
                        <div class="row form-group">
                            <label class="col-sm-2 control-label">Seen By</label>
                            <div class="col-sm-10">
                                <select id="seen_by" name="seen_by" class="form-control">
                                    <option value="Centre Head">Centre Head</option>
                                    <option value="CSS">CSS</option>
                                    <option value="Psychologist">Psychologist</option>
                                    <option value="Benefits Advisor">Benefits Advisor</option>
                                    <option value="Fundraiser">Fundraiser</option>
                                    <option value="Sessional Staff">Sessional Staff</option>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label class="col-sm-2 control-label">Person</label>
                            <div class="col-sm-10">
                                <select id="person", name="person" class="form-control">
                                    <option value="PwC">PwC</option>
                                    <option value="Carer">Carer</option>
                                    <option value="Professional">Professional</option>
                                    <option value="Architectural">Architectural</option>
                                    <option value="International">International</option>
                                    <option value="Other Visitor">Other Visitor</option>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label class="col-sm-2 control-label">Visit Type</label>
                            <div class="col-sm-10">
                                <select id="visit_type" name="visit_type" class="form-control">
                                    <option value="New">New</option>
                                    <option value="Returning">Returning</option>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label class="col-sm-2 control-label">Gender</label>
                            <div class="col-sm-10">
                                <select id="gender" name="gender" class="form-control">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Under 18 Male">Under 18 Male</option>
                                    <option value="Under 18 Female">Under 18 Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label class="col-sm-2 control-label">Cancer Site</label>
                            <div class="col-sm-10">
                                <select id="cancer_site" name="cancer_site" class="form-control">
                                    <option value="Brain/CNS">Brain/CNS</option>
                                    <option value="Breast">Breast</option>
                                    <option value="Gynae">Gynae</option>
                                    <option value="Head/Neck">Head/Neck</option>
                                    <option value="Haemat">Haemat</option>
                                    <option value="Liver">Liver</option>
                                    <option value="Lower GI">Lower GI</option>
                                    <option value="Lung">Lung</option>
                                    <option value="Pancreatic">Pancreatic</option>
                                    <option value="Prostate">Prostate</option>
                                    <option value="Rare">Rare</option>
                                    <option value="Sarcoma">Sarcoma</option>
                                    <option value="Skin/Mel">Skin/Mel</option>
                                    <option value="Testicular">Testicular</option>
                                    <option value="Unknown Primary">Unknown Primary</option>
                                    <option value="Upper GI">Upper GI</option>
                                    <option value="Urolog">Urolog</option>
                                    <option value="Not Stated">Not Stated</option>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label class="col-sm-2 control-label">Journey Stage</label>
                            <div class="col-sm-10">
                                <select id="journey_stage" name="journey_stage" class="form-control">
                                    <option value="Pre-Diagnosis">Pre-Diagnosis</option>
                                    <option value="Curative Intent">Curative Intent</option>
                                    <option value="Post Treatment - Curative Intent">Post Treatment - Curative Intent</option>
                                    <option value="Non-Curative Intent">Non-Curative Intent</option>
                                    <option value="End of Life">End of Life</option>
                                    <option value="Bereaved">Bereaved</option>
                                    <option value="Not Stated">Not Stated</option>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label class="col-sm-2 control-label">Nature of Visit</label>
                            <div class="col-sm-10">
                                <select id="nature_of_visit" name="nature_of_visit" class="form-control">
                                    <option value="Booked">Booked</option>
                                    <option value="Drop-In">Drop-In</option>
                                    <option value="Programme">Programme</option>
                                    <option value="E-mail Support">E-mail Support</option>
                                    <option value="Fundraiser">Fundraiser</option>
                                    <option value="Outreatch">Outreach</option>
                                    <option value="Telephone Support">Telephone Support</option>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label class="col-sm-2 control-label">Activity</label>
                            <div class="col-sm-10">
                                <textarea name="activity" class="form-control" rows="3">
                                </textarea>
                            </div>
                        </div>
                        <div class="row form-group">
                            <label class="col-sm-2 control-label">Remark</label>
                            <div class="col-sm-10">
                                <textarea name="remark" class="form-control" rows="3">
                                </textarea>
                            </div>
                        </div>
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

