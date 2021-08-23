<!DOCTYPE html>
<html lang="en">
    <!-- BEGIN HEAD -->

    <head>
        <?php include_once 'includes/header_css.php'; ?>
    </head>
    <!-- END HEAD -->
    <body class="page-header-fixed sidemenu-closed-hidelogo page-content-white page-md header-blue">
        <div class="page-wrapper">
            <!-- start header -->
            <?php include_once 'includes/header_menu.php'; ?>
            <!-- end header -->
            <!-- start page container -->
            <div class="page-container">
                <!-- start sidebar menu -->
                <?php
                include_once 'includes/side_menu.php';
                ?>
                <!-- end sidebar menu -->
                <!-- start page content -->
                <div class="page-content-wrapper">
                    <div class="page-content">

                        <div class="row">

                            <div class="col-md-12 col-sm-12">
                                <div class="panel tab-border card-topline-green">
                                    <header class="panel-heading panel-heading-gray custom-tab ">
                                        <ul class="nav nav-tabs">
                                            <li class="active"><a href="#group" data-toggle="tab">Create a group</a>
                                            </li>
                                            <?php
                                            $sender_id = $_SESSION['staff_id'];
                                            $groupList = DB::getInstance()->querySample("SELECT groups.Group_id,groups.Group_name FROM groups,grouped_files WHERE groups.Group_id=grouped_files.Group_id and(grouped_files.Receiver_id='$sender_id' or grouped_files.Sender_id='$sender_id') group by groups.Group_id");
                                            foreach ($groupList AS $groups) {
                                                ?>
                                                <li class=""><a href="#group_<?php echo $groups->Group_id ?>" data-toggle="tab"><?php echo $groups->Group_name ?></a>
                                                </li>
                                            <?php }
                                            ?>

                                        </ul>
                                    </header>
                                    <div class="panel-body">
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="group">

                                                <?php
                                                if (Input::exists() && Input::get("internal_sharing") == "internal_sharing") {

                                                    $sender_id = $_SESSION['staff_id'];
                                                    $file = Input::get('file_id');
                                                    $receiver_id = Input::get('staff_id');

                                                    $submited = 0;
                                                    $duplicate = 0;
                                                    if (!empty($receiver_id)) {
                                                        for ($i = 0; $i < sizeof($receiver_id); $i++) {
                                                            $queryDup = DB::getInstance()->checkRows("select * from shared_files where  Receiver_id='$receiver_id[$i]' and File_id ='$file'");
                                                            if (!$queryDup) {
                                                                DB::getInstance()->insert("shared_files", array(
                                                                    "Receiver_id" => $receiver_id[$i],
                                                                    "Sender_id" => $sender_id,
                                                                    "File_id" => $file));
                                                                $submited++;
                                                            } else {
                                                                $duplicate++;
                                                            }
                                                        }
                                                        if ($submited > 0) {
                                                            echo '<div class="alert alert-success"> has been shared to ' . $submited . ' members</div>';
                                                        }
                                                        if ($duplicate > 0) {
                                                            echo '<div class="alert alert-warning">' . $duplicate . ' member(s) already have the this file</div>';
                                                        }
                                                    } else {
                                                        echo '<div class="alert alert-warning">Select members and try again</div>';
                                                    }
                                                    Redirect::go_to("index.php?page=group");
                                                }
                                                if (isset($_GET['action']) && $_GET['action'] == 'delete') {

                                                    $file_id = $_GET['file_id'];
                                                    $query = DB::getInstance()->query("UPDATE file SET status=0 WHERE file_id='$file_id'");
                                                    if ($query) {

                                                        echo $message = "<center><h4 style='color:red'>data has been deleted successfully</h4></center>";
                                                    } else {
                                                        echo $error = "<center><h4 style='color:red'>there is a server error</h4></center>";
                                                    }
                                                    Redirect::go_to("index.php?page=group");
                                                }

                                                if (isset($_GET['action']) && $_GET['action'] == 'remove_member') {

                                                    $group_id = $_GET['group_id'];
                                                    $query = DB::getInstance()->query("delete from grouped_files WHERE Id='$group_id'");
                                                    if ($query) {

                                                        echo $message = "<center><h4 style='color:red'>Member has been removed from the group</h4></center>";
                                                    } else {
                                                        echo $error = "<center><h4 style='color:red'>there is a server error</h4></center>";
                                                    }
                                                    Redirect::go_to("index.php?page=group");
                                                }
                                                if (Input::exists() && Input::get("edit_file") == "edit_file") {
                                                    $file_id = Input::get('file_id');
                                                    $shelve = Input::get('shelve');
                                                    $box_number = Input::get('box_number');
                                                    $file_name = Input::get('file_name');
                                                    $file = $_FILES['file']['name'];
                                                    $temp_file = $_FILES["file"]["tmp_name"];
                                                    $file_z = DB::getInstance()->displayTableColumnValue("select file from file where file_id='$file_id' ", "file");


                                                    if ($file != "") {
                                                        $starget_dir = "uploaded_files/";
                                                        $starget_file = $starget_dir . $file;
                                                        move_uploaded_file($temp_file, $starget_file);
                                                        unlink("uploaded_files/" . $file_z);
                                                        $editquestion = DB::getInstance()->update("file", $file_id, array(
                                                            "file_name" => $file_name,
                                                            "Shelve_number" => $shelve,
                                                            "Box_number" => $box_number,
                                                            "file" => $file), "file_id");
                                                    } else {

                                                        $editquestion = DB::getInstance()->update("file", $file_id, array(
                                                            "Shelve_number" => $shelve,
                                                            "Box_number" => $box_number,
                                                            "file_name" => $file_name
                                                                ), "file_id");
                                                    }

                                                    if ($editquestion) {

                                                        echo $message = "<center><h4 style='color:blue'>data has been edited successfully</h4></center>";
                                                    } else {
                                                        echo $error = "<center><h4 style='color:red'>there is a server error</h4></center>";
                                                    }
                                                    Redirect::go_to("index.php?page=group");
                                                }

                                                if (Input::exists() && Input::get("send_email") == "send_email") {
                                                    $file_id = Input::get('file_id');
                                                    $email = Input::get('email');
                                                    $code = generatePasswordz();
                                                    $editquestion = DB::getInstance()->query("UPDATE file SET key='$code' WHERE file_id='$file_id'");
                                                    if ($editquestion) {
                                                        sendEmail($email, $code);
                                                        $message = "File has been shared ";
                                                    }
                                                }




                                                if (Input::exists() && Input::get("submit_group_members") == "submit_group_members") {
                                                    $sender_id = $_SESSION['staff_id'];
                                                    $group_id = Input::get('group_id');
                                                    $receiver_id = Input::get('staff_id');
                                                    for ($i = 0; $i < sizeof($receiver_id); $i++) {
                                                        $queryDup = DB::getInstance()->checkRows("select * from grouped_files where  Receiver_id='$receiver_id[$i]' and Group_id ='$group_id'");
                                                        if (!$queryDup) {
                                                            DB::getInstance()->insert("grouped_files", array(
                                                                "Receiver_id" => $receiver_id[$i],
                                                                "Sender_id" => $sender_id,
                                                                "Group_id" => $group_id));
                                                            $submited++;
                                                        } else {
                                                            $duplicate++;
                                                        }
                                                    }
                                                    if ($submited > 0) {
                                                        echo '<div class="alert alert-success">' . $submited . ' members, has been added to this group</div>';
                                                    }
                                                    if ($duplicate > 0) {
                                                        echo '<div class="alert alert-warning">' . $duplicate . ' member(s) already have the this group</div>';
                                                    }
                                                    Redirect::go_to("index.php?page=group");
                                                }
                                                if (Input::exists() && Input::get("submit_group") == "submit_group") {
                                                    $sender_id = $_SESSION['staff_id'];
                                                    $group = Input::get('group');
                                                    $receiver_id = Input::get('staff_id');

                                                    $submited = 0;
                                                    $duplicate = 0;
                                                    $groupDup = DB::getInstance()->checkRows("select * from groups where  Group_name='$group'");

                                                    if (!$groupDup) {
                                                        DB::getInstance()->insert("groups", array(
                                                            "Group_name" => $group,
                                                            "Staff_id" => $sender_id));
                                                        $group_id = DB::getInstance()->displayTableColumnValue("select Group_id  from groups where Group_name='$group'", "Group_id");
                                                        if (!empty($receiver_id)) {
                                                            for ($i = 0; $i < sizeof($receiver_id); $i++) {
                                                                $queryDup = DB::getInstance()->checkRows("select * from grouped_files where  Receiver_id='$receiver_id[$i]' and Group_id ='$group_id'");
                                                                if (!$queryDup) {
                                                                    DB::getInstance()->insert("grouped_files", array(
                                                                        "Receiver_id" => $receiver_id[$i],
                                                                        "Sender_id" => $sender_id,
                                                                        "Group_id" => $group_id));
                                                                    $submited++;
                                                                } else {
                                                                    $duplicate++;
                                                                }
                                                            }
                                                            if ($submited > 0) {
                                                                echo '<div class="alert alert-success">' . $group . ' group with  ' . $submited . ' members, has been created</div>';
                                                            }
                                                            if ($duplicate > 0) {
                                                                echo '<div class="alert alert-warning">' . $duplicate . ' member(s) already have the this group</div>';
                                                            }
                                                        } else {
                                                            echo '<div class="alert alert-warning">Select members and try again</div>';
                                                        }
                                                    } else {

                                                        echo '<div class="alert alert-warning">' . $group . ' already exists</div>';
                                                        $group_id = DB::getInstance()->displayTableColumnValue("select Group_id  from groups where Group_name='$group'", "Group_id");
                                                        if (!empty($receiver_id)) {
                                                            for ($i = 0; $i < sizeof($receiver_id); $i++) {
                                                                $queryDup = DB::getInstance()->checkRows("select * from grouped_files where  Receiver_id='$receiver_id[$i]' and Group_id ='$group_id'");
                                                                if (!$queryDup) {
                                                                    DB::getInstance()->insert("grouped_files", array(
                                                                        "Receiver_id" => $receiver_id[$i],
                                                                        "Sender_id" => $sender_id,
                                                                        "Group_id" => $group_id));
                                                                    $submited++;
                                                                } else {
                                                                    $duplicate++;
                                                                }
                                                            }
                                                            if ($submited > 0) {
                                                                echo '<div class="alert alert-success">' . $group . ' group with  ' . $submited . ' members, has been created</div>';
                                                            }
                                                            if ($duplicate > 0) {
                                                                echo '<div class="alert alert-warning">' . $duplicate . ' member(s) already have the this group</div>';
                                                            }
                                                        } else {
                                                            echo '<div class="alert alert-warning">Select members and try again</div>';
                                                        }
                                                    }
                                                    Redirect::go_to("index.php?page=group");
                                                }
                                                ?>
                                                <?php
                                                $staff_id = $_SESSION['staff_id'];
                                                $department_id = $_SESSION['department'];
                                                if (Input::exists() && Input::get("submit_file") == "submit_file") {
                                                    $file_name = Input::get('file_name');
                                                    $file = $_FILES['file']['name'];
                                                    $temp_file = $_FILES["file"]["tmp_name"];
                                                    $shelve = Input::get('shelve');
                                                    $group_id = Input::get('group_id');
                                                    $box_number = Input::get('box_number');


                                                    $duplicate = 0;
                                                    $submited = 0;
                                                    for ($i = 0; $i < sizeof($file_name); $i++) {
                                                        $queryDup = DB::getInstance()->checkRows("select * from file where file_name='$file_name[$i]'");

                                                        if ($file[$i] != "") {
                                                             $filename_ = explode (".", $file[$i]); 
                                                             $filename1 = $filename_[0];
                                                             $fileextension1 = $filename_[1];
                                                             $newfilename=$filename1.''.(DB::getInstance()->displayTableColumnValue("SELECT file_id FROM file  ORDER BY file_id DESC LIMIT 1", "file_id")+1).'.'.$fileextension1;
                                                            $starget_dir = "uploaded_files/";
                                                            $starget_file = $starget_dir . $newfilename;
                                                            move_uploaded_file($temp_file[$i], $starget_file);
                                                        }
                                                        if (!$queryDup) {
                                                            DB::getInstance()->insert("file", array(
                                                                "file_name" => $file_name[$i],
                                                                "file" => $newfilename,
                                                                "staff_id" => $staff_id,
                                                                "Shelve_number" => $shelve[$i],
                                                                "Box_number" => $box_number[$i],
                                                                "Group_id" => $group_id,
                                                                "department_id" => $department_id));
                                                            $submited++;
                                                        } else {
                                                            $duplicate++;
                                                        }
                                                    }
                                                    if ($submited > 0) {
                                                        echo '<div class="alert alert-success">' . $submited . ' file(s) submitted successfully</div>';
                                                    }
                                                    if ($duplicate > 0) {
                                                        echo '<div class="alert alert-warning">' . $duplicate . ' file(s) already exisits</div>';
                                                    }
                                                    Redirect::go_to("index.php?page=group");
                                                }
                                                ?>
                                                <form class="col-md-5 col-sm-12" role="form" method="POST" enctype="multipart/form-data">

                                                    <div class="form-group" >
                                                        <label>Group Name</label>
                                                        <input type="text" name="group"  class="form-control" required>

                                                    </div>
                                                    <div class="form-group">
                                                        <label>Group Members:<label class="btn btn-primary btn-xs" id="selectControl<?php echo $file->file_id ?>" onclick="modulesSelected('<?php echo $file->file_id ?>', this)">select all</label></label>
                                                        <select id="modules_accessed<?php echo $file->file_id ?>" class="form-control select2" multiple name="staff_id[]" >
                                                            <?php
                                                            $staffList = DB::getInstance()->querySample("SELECT * FROM staff WHERE staff_id!='$recz_id' and Status=1");
                                                            foreach ($staffList AS $staffs) {
                                                                echo '<option value="' . $staffs->staff_id . '">' . $staffs->name . '</option>';
                                                            }
                                                            ?>
                                                        </select>
                                                    </div>
                                                    <div class="box-footer">
                                                        <button type="submit"  name="submit_group" value="submit_group" class="btn btn-primary pull-right">Submit</button>
                                                    </div>

                                                </form>
                                            </div>
                                            <?php
                                            $sender_id = $_SESSION['staff_id'];
                                            $groupList = DB::getInstance()->querySample("SELECT groups.Group_id,groups.Staff_id,groups.Group_name FROM groups,grouped_files WHERE groups.Group_id=grouped_files.Group_id and(grouped_files.Receiver_id='$sender_id' or grouped_files.Sender_id='$sender_id') group by groups.Group_id");
                                            foreach ($groupList AS $groups) {
                                                ?>
                                                <div class="tab-pane" id="group_<?php echo $groups->Group_id ?>">
                                                    <div class="col-md-12 col-sm-12">
                                                        <div class="card card-topline-red">
                                                            <div class="card-head">
                                                                <header><?php echo $groups->Group_name ?> by:<a> <?php echo DB::getInstance()->displayTableColumnValue("select name  from staff where staff_id='$groups->Staff_id'", "name"); ?></a><span id="email_result" class="center"></span></header>
                                                                <div class="tools">
                                                                    <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
                                                                    <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
                                                                    <a class="t-close btn-color fa fa-times" href="javascript:;"></a>
                                                                </div>
                                                            </div>
                                                            <div class="card-body " id="bar-parent">
                                                                <div class="row">
                                                                    <div class="col-md-2 col-sm-2 col-xs-2">
                                                                        <ul class="nav nav-tabs tabs-left">
                                                                            <li class="active">
                                                                                <a href="#group_files_<?php echo $groups->Group_id ?>" data-toggle="tab"> Group files </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="#add_members_<?php echo $groups->Group_id ?>" data-toggle="tab"> Group members </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="#upload_files_<?php echo $groups->Group_id ?>" data-toggle="tab"> Upload files </a>
                                                                            </li>

                                                                        </ul>
                                                                    </div>
                                                                    <div class="col-md-10 col-sm-10 col-xs-10">
                                                                        <div class="tab-content">
                                                                            <div class="tab-pane active" id="group_files_<?php echo $groups->Group_id ?>">
                                                                                <div class="card-head">
                                                                                    <header>Group files(s)</header>
                                                                                </div>
                                                                                <?php
                                                                                $queryfile = "SELECT * FROM file WHERE Group_id='$groups->Group_id' and Status='1'";
                                                                                if (DB::getInstance()->checkRows($queryfile)) {
                                                                                    ?>
                                                                                    <table id="example1" class="table table-bordered table-striped">
                                                                                        <thead>
                                                                                            <tr>
                                                                                                <th style="width: 1%;">#</th>
                                                                                                <th >File Name/Description</th>
                                                                                                <th >File</th>
                                                                                                <th >Box file No.</th>
                                                                                                <th >Shelve No.</th>

                                                                                                <th >Department</th>
                                                                                                <th style="width: 20%;"></th>
                                                                                            </tr>
                                                                                        </thead>
                                                                                        <tbody>
                                                                                            <?php
                                                                                            $data_got = DB::getInstance()->querySample($queryfile);
                                                                                            $no = 1;
                                                                                            foreach ($data_got as $file) {
                                                                                                $hide = (((isset($_SESSION['user_type']) && $_SESSION['user_type'] != "Admin") && !isset($_SESSION['immergencepassword'])) && $file->staff_id != $staff_id) ? "hidden" : "";
                                                                                                $staff_idd = $_SESSION['staff_id'];
                                                                                                $restricted_member = DB::getInstance()->checkRows("select * from restrict_member where  Staff_id='$staff_idd' and File_id ='$file->file_id'");
                                                                                                if ($restricted_member > 0) {
                                                                                                    
                                                                                                } else {
                                                                                                    ?>
                                                                                                    <tr> 
                                                                                                        <td style="width: 1%;"><?php echo $no; ?></td>
                                                                                                        <td><?php echo $file->file_name; ?></td>

                                                                                                        <td><a href="uploaded_files/<?php echo $file->file; ?>" target="_blank"><span class="fa fa-download"></span><?php echo $file->file; ?></a></td>
                                                                                                        <td><?php echo $file->Box_number; ?></td>
                                                                                                        <td><?php echo $file->Shelve_number; ?></td>
                                                                                                        <td><?php echo DB::getInstance()->displayTableColumnValue("select department_name from department where id='$file->department_id' ", "department_name"); ?> (By: <a><?php echo DB::getInstance()->displayTableColumnValue("select name  from staff where staff_id='$file->staff_id'", "name"); ?></a>)</td>
                                                                                                        <td style="width: 20%;">
                                                                                                            <div class="btn-group">
                                                                                                                <a  data-toggle="modal" class="btn btn-primary btn-xs <?php echo $hide; ?>" data-target="#modal-email_<?php echo $file->file_id; ?>">email</a>
                                                                                                                <a  data-toggle="modal" class="btn btn-info btn-xs <?php echo $hide; ?>" data-target="#modal-share_<?php echo $file->file_id; ?>">Share</a>
                                                                                                            </div>
                                                                                                            <div class="btn-group <?php echo $hide; ?>" >
                                                                                                                <a class="btn btn-warning btn-xs" data-toggle="modal" data-target="#modal-<?php echo $file->file_id; ?>">Edit</a>
                                                                                                                <a class="btn btn-danger btn-xs" href="index.php?page=<?php echo "group" . '&action=delete&file_id=' . $file->file_id; ?>" onclick="return confirm('Are you sure you want to delete <?php echo $file->file; ?>?');">Delete</a>

                                                                                                            </div>

                                                                                                        </td>

                                                                                                <div class="modal fade" id="modal-<?php echo $file->file_id; ?>">
                                                                                                    <div class="modal-dialog">
                                                                                                        <form role="form" action="index.php?page=<?php echo "group"; ?>" method="POST" enctype="multipart/form-data">
                                                                                                            <div class="modal-content">
                                                                                                                <div class="modal-header">
                                                                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                                                        <span aria-hidden="true">&times;</span></button>

                                                                                                                </div> <div class="modal-body">
                                                                                                                    <input type="hidden" name="file_id" value="<?php echo $file->file_id ?>">
                                                                                                                    <div class="form-group">
                                                                                                                        <label>File Name/description</label>
                                                                                                                        <input name="file_name" class="form-control" value="<?php echo $file->file_name; ?>" required/>
                                                                                                                    </div>
                                                                                                                    <div class="form-group">
                                                                                                                        <label>File</label>
                                                                                                                        <input type="file" class="form-control" name="file" accept=".xlsx,.xls,.doc, .docx,.ppt, .pptx,.txt,.pdf"  >

                                                                                                                    </div>
                                                                                                                    <div class="form-group" >
                                                                                                                        <label>Box file No.</label>
                                                                                                                        <input type="text" name="box_number" value="<?php echo $file->Box_number; ?>" class="form-control"/>
                                                                                                                    </div>
                                                                                                                    <div class="form-group" >
                                                                                                                        <label>Shelve No.</label>
                                                                                                                        <input type="text" name="shelve" value="<?php echo $file->Shelve_number; ?>" class="form-control"/>
                                                                                                                    </div>

                                                                                                                </div>
                                                                                                                <div class="modal-footer">
                                                                                                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                                                                                                                    <button type="submit" name="edit_file" value="edit_file" class="btn btn-primary">Save changes</button>
                                                                                                                </div>


                                                                                                            </div>
                                                                                                        </form>
                                                                                                        <!-- /.modal-content -->
                                                                                                    </div>
                                                                                                    <!-- /.modal-dialog -->
                                                                                                </div>

                                                                                                <div class="modal fade" id="modal-share_<?php echo $file->file_id; ?>">
                                                                                                    <div class="modal-dialog">
                                                                                                        <div class="modal-content">

                                                                                                            <form role="form"  method="POST" enctype="multipart/form-data">
                                                                                                                <div class="modal-header">
                                                                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                                                        <span aria-hidden="true">&times;</span></button>
                                                                                                                    <header>File Sharing</header>

                                                                                                                </div> 
                                                                                                                <div class="modal-body">

                                                                                                                    <input type="hidden" name="file_id" value="<?php echo $file->file_id ?>">

                                                                                                                    <div class="form-group">
                                                                                                                        <label>Staff Names:<label class="btn btn-primary btn-xs" id="selectControl<?php echo $file->file_id ?>" onclick="modulesSelected('<?php echo $file->file_id ?>', this)">select all</label></label>
                                                                                                                        <select id="modules_accessed<?php echo $file->file_id ?>" class="form-control select2" multiple name="staff_id[]" style="width: 80%;">
                                                                                                                            <?php
                                                                                                                            $staffList = DB::getInstance()->querySample("SELECT * FROM staff WHERE staff_id!='$recz_id' and Status=1");
                                                                                                                            foreach ($staffList AS $staffs) {
                                                                                                                                echo '<option value="' . $staffs->staff_id . '">' . $staffs->name . '</option>';
                                                                                                                            }
                                                                                                                            ?>
                                                                                                                        </select>
                                                                                                                    </div>



                                                                                                                </div>
                                                                                                                <div class="modal-footer">
                                                                                                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                                                                                                                    <button type="submit" name="internal_sharing"  value="internal_sharing" class="btn btn-primary">Share</button>
                                                                                                                </div>
                                                                                                            </form>

                                                                                                        </div>
                                                                                                        <!-- /.modal-content -->
                                                                                                    </div>
                                                                                                    <!-- /.modal-dialog -->
                                                                                                </div>
                                                                                                <div class="modal fade" id="modal-email_<?php echo $file->file_id; ?>">
                                                                                                    <div class="modal-dialog">
                                                                                                        <div class="modal-content">
                                                                                                            <div class="modal-header">
                                                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                                                    <span aria-hidden="true">&times;</span></button>

                                                                                                            </div> 
                                                                                                            <div class="modal-body">
                                                                                                                <div id="question"><button type="button" class="btn btn-success btn-xs pull-right" id="add_more" onclick="add_email('<?php echo $file->file_id; ?>');">Add more</button>
                                                                                                                    <div id="add_email<?php echo $file->file_id; ?>" > 
                                                                                                                        <div class="form-group">
                                                                                                                            <label>emails</label>
                                                                                                                            <input type="text" class="form-control" name="email_sent[]"  required />
                                                                                                                        </div>
                                                                                                                    </div>
                                                                                                                </div>


                                                                                                            </div>
                                                                                                            <div class="modal-footer">
                                                                                                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                                                                                                                <button type="submit" name="send_email" data-dismiss="modal" value="send_email" onclick="send_email('<?php echo $file->file_id ?>');" class="btn btn-primary">send</button>
                                                                                                            </div>


                                                                                                        </div>
                                                                                                        <!-- /.modal-content -->
                                                                                                    </div>
                                                                                                    <!-- /.modal-dialog -->
                                                                                                </div>
                                                                                                </tr>
                                                                                                <?php
                                                                                            }
                                                                                            $no++;
                                                                                        }
                                                                                        ?>
                                                                                        </tbody>
                                                                                        <tfoot>
                                                                                            <tr>
                                                                                                <th></th>
                                                                                                <th>File name/Description</th>
                                                                                                <th>file</th>
                                                                                                <th >Box file No.</th>
                                                                                                <th >Shelve No.</th>
                                                                                                <th>Department</th>
                                                                                                <th></th>
                                                                                            </tr>
                                                                                        </tfoot>

                                                                                    </table>
                                                                                    <?php
                                                                                } else {
                                                                                    echo '<div class="alert alert-danger">No file uploads</div>';
                                                                                }
                                                                                ?>  </div>
                                                                            <div class="tab-pane fade" id="add_members_<?php echo $groups->Group_id ?>">


                                                                                <div class="modal fade" id="modal__<?php echo $groups->Group_id ?>">
                                                                                    <div class="modal-dialog">
                                                                                        <form role="form" method="POST" enctype="multipart/form-data">
                                                                                            <div class="modal-content">
                                                                                                <div class="modal-header">
                                                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                                        <span aria-hidden="true">&times;</span></button>

                                                                                                </div>
                                                                                                <div class="modal-body">
                                                                                                    <div class="form-group">
                                                                                                        <input type="hidden" name="group_id"  class="form-control" value="<?php echo $groups->Group_id ?>">

                                                                                                        <label>Group Members:<label class="btn btn-primary btn-xs" id="selectControl<?php echo $file->file_id ?>_<?php echo $groups->Group_id ?>" onclick="modulesSelected('<?php echo $file->file_id ?>_<?php echo $groups->Group_id ?>', this)">select all</label></label>
                                                                                                        <select id="modules_accessed<?php echo $file->file_id ?>_<?php echo $groups->Group_id ?>" class="form-control select2" multiple name="staff_id[]" style="width:80%;" >
                                                                                                            <?php
                                                                                                            $staffList = DB::getInstance()->querySample("SELECT * FROM staff WHERE staff_id!='$recz_id' and Status=1");
                                                                                                            foreach ($staffList AS $staffs) {
                                                                                                                echo '<option value="' . $staffs->staff_id . '">' . $staffs->name . '</option>';
                                                                                                            }
                                                                                                            ?>
                                                                                                        </select>
                                                                                                    </div>

                                                                                                </div>
                                                                                                <div class="modal-footer">
                                                                                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                                                                                                    <button type="submit"  name="submit_group_members" value="submit_group_members" class="btn btn-primary">Save changes</button>
                                                                                                </div>


                                                                                            </div>
                                                                                        </form>
                                                                                        <!-- /.modal-content -->
                                                                                    </div>
                                                                                    <!-- /.modal-dialog -->
                                                                                </div>
                                                                                <div class="card card-topline-green col-md-12 col-sm-12">
                                                                                    <div class="card-head">
                                                                                        <header>Group Members</header>  <a  data-toggle="modal" class="btn btn-info btn-xs <?php echo ($groups->Staff_id == $sender_id || $_SESSION['user_type'] == "Admin") ? '' : 'hidden'; ?>" data-target="#modal__<?php echo $groups->Group_id ?>"><i class="fa fa-users"></i>Add members</a>

                                                                                    </div>
                                                                                    <?php
                                                                                    $querymembers = "SELECT grouped_files.Id,grouped_files.Receiver_id,staff.staff_id,staff.name,staff.department_id FROM grouped_files,staff WHERE grouped_files.Receiver_id=staff.staff_id and grouped_files.Group_id='$groups->Group_id'";
                                                                                    if (DB::getInstance()->checkRows($querymembers)) {
                                                                                        ?>
                                                                                        <table id="example1" class="table table-bordered table-striped">
                                                                                            <thead>
                                                                                                <tr>
                                                                                                    <th style="width: 1%;">#</th>
                                                                                                    <th >Member</th>
                                                                                                    <th >Department</th>

                                                                                                    <th style="width: 20%;"></th>
                                                                                                </tr>
                                                                                            </thead>
                                                                                            <tbody>
                                                                                                <?php
                                                                                                $member_got = DB::getInstance()->querySample($querymembers);
                                                                                                $no = 1;
                                                                                                foreach ($member_got as $file) {
                                                                                                    $hide = (((isset($_SESSION['user_type']) && $_SESSION['user_type'] != "Admin") && !isset($_SESSION['immergencepassword'])) && $file->staff_id != $staff_id) ? "hidden" : "";
                                                                                                    $staff_idd = $_SESSION['staff_id'];
                                                                                                    $restricted_member = DB::getInstance()->checkRows("select * from restrict_member where  Staff_id='$staff_idd' and File_id ='$file->file_id'");
                                                                                                    if ($restricted_member > 0) {
                                                                                                        
                                                                                                    } else {
                                                                                                        ?>
                                                                                                        <tr> 
                                                                                                            <td style="width: 1%;"><?php echo $no; ?></td>
                                                                                                            <td><?php echo $file->name; ?></td>

                                                                                                            <td><?php echo DB::getInstance()->displayTableColumnValue("select department_name from department where id='$file->department_id' ", "department_name"); ?> </td>
                                                                                                            <td style="width: 20%;">
                                                                                                                <a  href="index.php?page=<?php echo "group" . '&action=remove_member&group_id=' . $file->Id; ?>" onclick="return confirm('Are you sure you want to delete <?php echo $file->name; ?>?');" class="btn btn-danger btn-xs <?php echo ($groups->Staff_id == $sender_id || $_SESSION['user_type'] == "Admin") ? '' : 'hidden'; ?>"><i class="fa fa-times"></i>Remove</a>
                                                                                                            </td>

                                                                                                        </tr>
                                                                                                        <?php
                                                                                                    }
                                                                                                    $no++;
                                                                                                }
                                                                                                ?>
                                                                                            </tbody>
                                                                                            <tfoot>
                                                                                                <tr>
                                                                                                    <th style="width: 1%;">#</th>
                                                                                                    <th >Member</th>
                                                                                                    <th >Department</th>

                                                                                                    <th style="width: 20%;"></th>
                                                                                                </tr>
                                                                                            </tfoot>

                                                                                        </table>
                                                                                        <?php
                                                                                    } else {
                                                                                        echo '<div class="alert alert-danger">No file uploads</div>';
                                                                                    }
                                                                                    ?> 
                                                                                </div>
                                                                            </div>
                                                                            <div class="tab-pane fade" id="upload_files_<?php echo $groups->Group_id ?>">
                                                                                <form role="form" method="POST" enctype="multipart/form-data">

                                                                                    <div class="tab-pane" id="files">
                                                                                        <div class="card-head">
                                                                                            <header>Upload file(s)</header>
                                                                                        </div>
                                                                                        <div class="card-body " id="bar-parent">

                                                                                            <div id="question">
                                                                                                <div id="add_element_<?php echo $groups->Group_id ?>" > 
                                                                                                    <div class="form-group col-md-3" >
                                                                                                        <label>File Name/description</label>
                                                                                                        <input type="text" name="file_name[]" class="form-control"/>
                                                                                                        <input type="hidden" value="<?php echo $groups->Group_id ?>" name="group_id" class="form-control"/>
                                                                                                    </div>
                                                                                                    <div class="form-group col-md-2" >
                                                                                                        <label>File</label>
                                                                                                        <input type="file" class="form-control" name="file[]" accept=".xlsx,.xls,.doc, .docx,.ppt, .pptx,.txt,.pdf" required >
                                                                                                    </div>
                                                                                                    <div class="form-group col-md-3" >
                                                                                                        <label>Box file No.</label>
                                                                                                        <input type="text" name="box_number[]" class="form-control"/>
                                                                                                    </div>
                                                                                                    <div class="form-group col-md-3" >
                                                                                                        <label>Shelve No.</label>
                                                                                                        <input type="text" name="shelve[]" class="form-control"/>
                                                                                                    </div>

                                                                                                    <br/> 
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="box-footer">

                                                                                                <button type="submit"  name="submit_file" value="submit_file" class="btn btn-primary pull-right">Submit</button>
                                                                                                <button type="button" class="btn btn-success btn-xs pull-right" id="add_more" onclick="add_element('<?php echo $groups->Group_id ?>');">Add more</button>
                                                                                            </div>
                                                                                        </div>  </div>
                                                                                </form>

                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            <?php }
                                            ?>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- /.row -->
                        </div>
                    </div>
                </div>
                <!-- end page content -->
            </div>
            <!-- end page container -->
            <!-- start footer -->
            <?php include_once 'includes/footer.php'; ?>
            <!-- end footer -->
        </div>
        <!-- start js include path -->
        <script type="text/javascript">
            function modulesSelected(user_id, selectControlId) {
                var modulesAccessedElement = document.getElementById('modules_accessed' + user_id);
                var selectedValues = new Array();
                for (var i = 0; i < modulesAccessedElement.options.length; i++) {
                    selectedValues.push(modulesAccessedElement.options[i].value);
                    modulesAccessedElement.getElementsByTagName('option')[i].selected = true;
                }
                selectedValues = (selectControlId.innerHTML === "select all") ? selectedValues : null;
                $('#modules_accessed' + user_id).val(selectedValues).trigger('change');
                selectControlId.innerHTML = (selectControlId.innerHTML === "select all") ? "unselect all" : 'select all';
            }
        </script>
        <script type="text/javascript">
            function modulesSelected(user_id, selectControlId) {
                var modulesAccessedElement = document.getElementById('modules_accessed' + user_id);
                var selectedValues = new Array();
                for (var i = 0; i < modulesAccessedElement.options.length; i++) {
                    selectedValues.push(modulesAccessedElement.options[i].value);
                    modulesAccessedElement.getElementsByTagName('option')[i].selected = true;
                }
                selectedValues = (selectControlId.innerHTML === "select all") ? selectedValues : null;
                $('#modules_accessed' + user_id).val(selectedValues).trigger('change');
                selectControlId.innerHTML = (selectControlId.innerHTML === "select all") ? "unselect all" : 'select all';
            }
        </script>
        <script>

            function add_email(id) {

                var row_ids = Math.round(Math.random() * 300000000);
                document.getElementById('add_email' + id).insertAdjacentHTML('beforeend',
                        '<div id="' + row_ids + '"><div class="form-group">\n\
    <input type="email"  name="email_sent[]" class="form-control" required/>\n\
<button type="button" value="' + row_ids + '" class="btn btn-danger btn-xs pull-right" onclick="delete_email(this.value);">\n\
<i class ="fa fa-times"></i></button>\n\
    </div>');

            }
            function delete_email(element_id) {
                jQuery('#' + element_id).html('');
            }

            function add_element(value) {
                var row_ids = Math.round(Math.random() * 300000000);
                document.getElementById('add_element_'+value).insertAdjacentHTML('beforeend',
                        '<div id="' + row_ids + '"><div class="form-group col-md-3">\n\
            <input type="text" name="file_name[]"  class="form-control" required>\n\
        </div>\n\
        <div class="form-group col-md-2" >\n\
           <input type="file"  name="file[]" class="form-control" required/></div>\n\
        <div class="form-group col-md-3" >\n\
            <input type="text" name="box_number[]" class="form-control"/>\n\
        </div>\n\
        <div class="form-group col-md-3" >\n\
            <input type="text" name="shelve[]" class="form-control"/>\n\
<button type="button" value="' + row_ids + '" class="btn btn-danger btn-xs pull-right" onclick="delete_item(this.value);">\n\
<i class ="fa fa-times"></i></button>\n\
</div>\n\
    </div>');

            }
            function delete_item(element_id) {
                jQuery('#' + element_id).html('');
            }





            function send_email(file_id) {
                var email_sent = document.getElementsByName('email_sent[]');
                var email_sent_data = [];

                for (var i = 0; i < email_sent.length; i++) {
                    if (email_sent[i].value != '') {
                        email_sent_data[i] = email_sent[i].value;
                    }
                }
                jQuery('#email_result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
                jQuery('#email_result').html('Sending....');
                jQuery.ajax({
                    type: 'POST',
                    url: 'index.php?page=ajax_data',
                    data: {submit_text: "send_email", file_id: file_id, email: email_sent_data},
                    success: function (html) {

                        var word = 'Emails has been successfully sent';
                        var wordz = 'Email could not be sent';
                        var regex = new RegExp('\\b' + word + '\\b');
                        var regexz = new RegExp('\\b' + wordz + '\\b');
                        if (regex.test(html)) {
                            jQuery('#email_result').css({'color': 'blue', 'font-style': 'italic', 'font-size': '150%'});
                            jQuery('#email_result').html(word);
                        } else if (regexz.test(html)) {
                            jQuery('#email_result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
                            jQuery('#email_result').html(wordz);
                        } else {
                            jQuery('#email_result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
                            jQuery('#email_result').html('Connection erorr');
                        }
                    }
                });


            }

        </script>
        <?php include_once 'includes/footer_js.php'; ?>
        <!-- end js include path -->
    </body>

</html>