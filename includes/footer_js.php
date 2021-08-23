<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery.blockui.min.js" type="text/javascript"></script>
<!-- bootstrap -->
<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>

<script src="js/jquery.slimscroll.js"></script>
<script src="js/app.js" type="text/javascript"></script>
<script src="js/layout.js" type="text/javascript"></script>
<script src="js/theme-color.js" type="text/javascript"></script>
<!--select2-->
<script src="js/select2/js/select2.js" type="text/javascript"></script>
<script src="js/select2/js/select2-init.js" type="text/javascript"></script>
<!-- data tables -->
<script src="js/datatables/datatables.min.js" type="text/javascript"></script>
<script src="js/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<script src="js/table_data.js" type="text/javascript"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script> var logoutLink = '<?php echo "logout"; ?>';</script>
<script src="js/jquery-idle-timeout/jquery.idletimeout.js" type="text/javascript"></script>
<script src="js/jquery-idle-timeout/jquery.idletimer.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="js/jquery-idle-timeout/ui-idletimeout.js"></script>

<script src="js/jquery-tags-input/jquery-tags-input.js" type="text/javascript"></script>
<script src="js/jquery-tags-input/jquery-tags-input-init.js" type="text/javascript"></script>

<!-- bootstrap -->
<script src="js/summernote/summernote.js" type="text/javascript"></script>
<!--<script src="timing.js" type="text/javascript"></script>-->
<script type="text/javascript">
    function messaging(staff_id) {
        document.getElementById("staff_id").value = staff_id;
        $('#sendtextbutton').attr({
            "class": "form-inline"        // values (or variables) here
        });
        $.ajax({
            type: 'POST',
            url: 'index.php?page=<?php echo "ajax_data"; ?>',
            data: {action: "returnmessages", staff_id: staff_id, remove: "clear_activemessage"},
            success: function (html) {
                $("#header_inbox_bar").load(window.location.href + " #header_inbox_bar");
                $('#returned_message').html(html);
                $('#notification_' + staff_id).html("");
            }
        });
    }

    function sendmessage(sender, message_id) {
        var message = document.getElementById(message_id).value;
        var receiver = document.getElementById("staff_id").value;
        if (message === '') {
            alert("Message box is null")
        } else {
            $.ajax({
                type: 'POST',
                url: 'index.php?page=<?php echo "ajax_data"; ?>',
                data: {action: "returnmessages", sender: sender, receiver: receiver, message: message, button: "sendmessage"},
                success: function (html) {
                    $('#returned_message').html(html);
                    document.getElementById(message_id).value = "";
                }
            });
        }

    }

</script>
<script>
    jQuery(document).ready(function () {
        //App.init();
        // initialize session timeout settings
        //UIIdleTimeout.init();
    });</script>

<script>

    function readURL1(input) {
        var file = $('#i_file')[0].files[0];
        var fsize = file.size;
        var imgwidth = 0;
        var imgheight = 0;
        var maxwidth = 2.489;
        var maxheight = 3.34;
        var _URL = window.URL || window.webkitURL;
        img = new Image();
        img.src = _URL.createObjectURL(file);
        img.onload = function () {

            imgwidth = this.width * (2.54 / 96);
            imgheight = this.height * (2.54 / 96);
            if (imgwidth <= maxwidth && imgheight <= maxheight) {
                if (input.files && input.files[0]) {

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $('#blah')
                                .attr('src', e.target.result)
                                .width(120)
                                .height(130);
                    };
                    reader.readAsDataURL(input.files[0]);
                }

            } else {
                alert('The image width and height must be less than to  2.49 and 3.34 cm respectively \nPlease reformat your image and try again');
                $('#file_div').html('<div class="form-group"><label for="exampleInputFile">Staff Image</label>\n\
    <input type="file" id="i_file" name="stuff_photo" accept="image/*" onchange="readURL1(this);">\n\
<img id="blah" src="staff_image/person.JPG" class="user-image" alt="User Image">\n\
</div>');
            }

        }
    }

    function readURL2(input) {
        var fsize = $('#i_files')[0].files[0].size;
        if (input.files && input.files[0]) {

            var reader = new FileReader();
            reader.onload = function (e) {
                $('#blahs')
                        .attr('src', e.target.result)
                        .width(50)
                        .height(40);
            };
            reader.readAsDataURL(input.files[0]);
        }

    }

    function readURL(input, id) {
        var file = $('#i_file' + id)[0].files[0];
        var fsize = file.size;
        var imgwidth = 0;
        var imgheight = 0;
        var maxwidth = 2.489;
        var maxheight = 3.34;
        var _URL = window.URL || window.webkitURL;
        img = new Image();
        img.src = _URL.createObjectURL(file);
        img.onload = function () {
            imgwidth = this.width * (2.54 / 96);
            imgheight = this.height * (2.54 / 96);
            if (imgwidth <= maxwidth && imgheight <= maxheight) {
                if (input.files && input.files[0]) {

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $('#blah' + id)
                                .attr('src', e.target.result)
                                .width(90)
                                .height(119);
                    };
                    reader.readAsDataURL(input.files[0]);
                }
            } else {

                alert('The image width and height must be less or equal to 2.48 and 3.34 respectively \nPlease reformat your image and try again');
                $('#file_div' + id).html('<div class="form-group"><label for="exampleInputFile">Staff Image</label>\n\
    <input type="file" id="i_file' + id + '" name="stuff_photo" accept="image/*" onchange="readURL(this,' + id + ');">\n\
<img id="blah' + id + '" src="staff_image/person.JPG" class="user-image" alt="User Image">\n\
</div>');
//                alert('The image width and height must be 90 and 119 respectively \nPlease reformat your image and try again');
//                $('#file_div' + id).html('<img style="width:100%;"id="blah' + id + '" src="staff_image/person.jpg" alt="your image" align="left"/><br/><br/><p style="color:blue">Upload a clear and professional photo .</p><input type="file" class="form-control" id="i_file" name="std_photo" accept="image/*" onchange="readURL(this,"' + id + '");" />');

            }

        }


    }
    function readURL2(input, id) {
        var fsize = $('#i_files' + id)[0].files[0].size;
        if (fsize > 7168000) {
            alert('The image size is very big, must be less than 700 KBs\nPlease select another image');
            $('#file_divs').html('<img style="width:10%;"id="blah" src="logo/logo.png" alt="your image" align="left"/><br/><br/><p style="color:blue">Upload a clear and professional photo .</p><input type="file" class="form-control" id="i_file" name="std_photo" accept="image/*" onchange="readURL(this);" />');
        } else {
            if (input.files && input.files[0]) {

                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#blahs' + id)
                            .attr('src', e.target.result)
                            .width(50)
                            .height(40);
                };
                reader.readAsDataURL(input.files[0]);
            }
        }
    }
    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2()

        //Datemask dd/mm/yyyy
        $('#datemask').inputmask('dd/mm/yyyy', {'placeholder': 'dd/mm/yyyy'})
        //Datemask2 mm/dd/yyyy
        $('#datemask2').inputmask('mm/dd/yyyy', {'placeholder': 'mm/dd/yyyy'})
        //Money Euro
        $('[data-mask]').inputmask()

        //Date range picker
        $('#reservation').daterangepicker()
        //Date range picker with time picker
        $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, locale: {format: 'MM/DD/YYYY hh:mm A'}})
        //Date range as a button
        $('#daterange-btn').daterangepicker(
                {
                    ranges: {
                        'Today': [moment(), moment()],
                        'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                        'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                        'This Month': [moment().startOf('month'), moment().endOf('month')],
                        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                    },
                    startDate: moment().subtract(29, 'days'),
                    endDate: moment()
                },
                function (start, end) {
                    $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
                }
        )

        //Date picker
        $('#datepicker').datepicker({
            autoclose: true
        })

        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        })
        //Red color scheme for iCheck
        $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
            checkboxClass: 'icheckbox_minimal-red',
            radioClass: 'iradio_minimal-red'
        })
        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        })

        //Colorpicker
        $('.my-colorpicker1').colorpicker()
        //color picker with addon
        $('.my-colorpicker2').colorpicker()

        //Timepicker
        $('.timepicker').timepicker({
            showInputs: false
        })
    })


    function Check() {
        var checkBoxes = document.getElementsByName('staff[]');
        for (i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].checked = (selectControl.innerHTML == "Click to Select All") ? 'checked' : '';
            var staff_id = checkBoxes[i].id;
            var staff_name = checkBoxes[i].value;
            clicked(staff_id, staff_name);
        }
        selectControl.innerHTML = (selectControl.innerHTML == "Click to Select All") ? "Click to Unselect All" : 'Click to Select All';
    }
    $tr_id = 1;
    function send_survey(id) {
        var staff_id = document.getElementById('staff_id' + id).value;
        var staff_name = document.getElementById('staff_name' + id).value;
        var code = document.getElementById('code' + id).value;
        var email = document.getElementById('email' + id).value;
        jQuery('#result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
        if (email != "") {

            jQuery('#result').html('sending...');
            jQuery.ajax({
                type: 'POST',
                url: 'index.php?page=ajax_data',
                data: {submit_text: "survey", staff_id: staff_id, staff_name: staff_name, code: code, email: email},
                success: function (html) {

                    var word = 'Survey has been successfully sent';
                    var wordz = 'Survey could not be sent';
                    var regex = new RegExp('\\b' + word + '\\b');
                    var regexz = new RegExp('\\b' + wordz + '\\b');
                    if (regex.test(html)) {
                        jQuery('#result').css({'color': 'blue', 'font-style': 'italic', 'font-size': '150%'});
                        jQuery('#result').html(word);
                    } else if (regexz.test(html)) {
                        jQuery('#result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
                        jQuery('#result').html('connection error');
                    } else {
                        jQuery('#result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
                        jQuery('#result').html('communication error');
                    }
                }
            });
        } else {
            jQuery('#result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
            jQuery('#result').html('Please enter email and send again');
        }
    }
    function send_policy(id) {
        var staff_id = document.getElementById('survey_staff_id' + id).value;
        var staff_name = document.getElementById('survey_staff_name' + id).value;
        var code = document.getElementById('survey_code' + id).value;
        var email = document.getElementById('survey_email' + id).value;
        jQuery('#result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
        if (email != "") {

            jQuery('#result').html('sending...');
            jQuery.ajax({
                type: 'POST',
                url: 'index.php?page=ajax_data',
                data: {submit_text: "policy", staff_id: staff_id, staff_name: staff_name, code: code, email: email},
                success: function (html) {

                    var word = 'Policy has been successfully sent';
                    var wordz = 'Policy could not be sent';
                    var regex = new RegExp('\\b' + word + '\\b');
                    var regexz = new RegExp('\\b' + wordz + '\\b');
                    if (regex.test(html)) {
                        jQuery('#result').css({'color': 'blue', 'font-style': 'italic', 'font-size': '150%'});
                        jQuery('#result').html(word);
                    } else if (regexz.test(html)) {
                        jQuery('#result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
                        jQuery('#result').html('connection error');
                    } else {
                        jQuery('#result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
                        jQuery('#result').html('communication error');
                    }
                }
            });
        } else {
            jQuery('#result').css({'color': 'red', 'font-style': 'italic', 'font-size': '150%'});
            jQuery('#result').html('Please enter email and send again');
        }
    }

    function delete_item(element_id) {
        //            document.getElementById('add_button').style.display = 'block';
        $('#' + element_id).html('');
    }
    function delete_item1(element_id) {

        //            document.getElementById('add_button').style.display = 'block';
        $('#' + element_id).html('');
    }
</script>
<script type="text/javascript">
    var startButton = document.getElementById("start-button");
    var stopButton = document.getElementById("stop-button");
    var elapsedTimeText = document.getElementsByClassName("elapsed-time-text")[0];

//Listeners
// none

    /** Displays the start button */
    function displayStartButton() {
        // Display start button
        startButton.style.display = "block";
        // Hide stop button
        stopButton.style.display = "none";
    }

    /** Hide the start button */
    function hideStartButton() {
        // Hide start button
        startButton.style.display = "none";
        // Display stop button
        stopButton.style.display = "block";
    }

//Controller

    /** Stores the reference to the elapsed time interval*/
    var elapsedTimeIntervalRef;
    /** Stores the start time of timer */
    var startTime;
    /** Stores the details of elapsed time when paused */
    var elapsedTimeWhenPaused;
    /** Starts the stopwatch */
// Set start time based on whether it's stopped or resetted
    setStartTime();
// Every second
    var passeddata = document.getElementById("duration").value;
    var array = passeddata.split(',')
    var duration = array[0];
    if (duration === null || duration === '') {

    } else {
        elapsedTimeIntervalRef = setInterval(() => {
            // Compute the elapsed time & display
            elapsedTimeText.innerText = timeAndDateHandling.getElapsedTime(startTime); //pass the actual record start time
            var value = elapsedTimeText.innerText.split(':');
            var pageid = document.getElementById('page_id').value;
            var testing = value[2];

            if (testing % 2 === 0) {

                if (pageid === "dashboard") {
                    var id = "returned_message";
                    var div = document.getElementById(id);
                    $('#' + id).animate({
                        scrollTop: div.scrollHeight - div.clientHeight
                    }, 500);
                    var staff_id = document.getElementById("staff_id").value;
                    $("#divtoreload").load(window.location.href + " #divtoreload");
                    $("#header_notification_bar").load(window.location.href + " #header_notification_bar");
                    $("#header_inbox_bar").load(window.location.href + " #header_inbox_bar");
                    if (staff_id !== '') {

                        $.ajax({
                            type: 'POST',
                            url: 'index.php?page=ajax_data',
                            data: {action: "returnmessages", staff_id: staff_id},
                            success: function (html) {
                                $('#returned_message').html(html);
                                $('#notification_' + staff_id).html("");
                            }
                        });
                    }

                } else {
                    $("#header_notification_bar").load(window.location.href + " #header_notification_bar");
                    $("#header_inbox_bar").load(window.location.href + " #header_inbox_bar");
                }
                var notification_header = document.getElementById("notification_header");
                var message_header = document.getElementById("message_header");
                var notification_headervalue = parseInt(notification_header.innerText);
                var message_headervalue = parseInt(message_header.innerText);

                if (testing % 20 === 0) {
                    var displaymessage = '';


                    if (notification_headervalue > 0 || message_headervalue > 0) {
                        if (message_headervalue === 0) {
                            displaymessage = "You have "+notification_headervalue + " unread shared files ";
                        } else if (notification_headervalue === 0) {
                            displaymessage = "You have " + message_headervalue + " unread messages";
                        } else {
                            displaymessage = 'You have ' + notification_headervalue + ' unread shared files and ' + message_headervalue + ' unread messages';
                        }

                        if (!window.Notification) {
                            alert('Browser does not support notifications.');
                        } else {
                            // check if permission is already granted
                            if (Notification.permission === 'granted') {

                                // show notification here
                                var notify = new Notification(displaymessage, {
                                    body: '',
                                });
                            } else {
                                // request permission from user
                                Notification.requestPermission().then(function (p) {
                                    if (p === 'granted') {
                                        // show notification here
                                        var notify = new Notification(displaymessage, {
                                            body: '',
                                        });
                                    } else {
                                        alert('User blocked notifications.');
                                    }
                                }).catch(function (err) {
                                    console.error(err);
                                });
                            }
                        }
                    }
                }

            }
            // Improvement: Can Stop elapsed time and resert when a maximum elapsed time
            //              has been reached.
        }, 1000);
    }


    /** Sets the start time value */
    function setStartTime() {
        if (elapsedTimeWhenPaused) {
            startTime = new Date();
            // Subtract the elapsed hours, minutes and seconds from the current date
            // To get correct elapsed time to resume from it
            startTime.setHours(startTime.getHours() - elapsedTimeWhenPaused.hours);
            startTime.setMinutes(startTime.getMinutes() - elapsedTimeWhenPaused.minutes);
            startTime.setSeconds(startTime.getSeconds() - elapsedTimeWhenPaused.seconds);
        } else {
            startTime = new Date();
        }
    }

    /** Pauses stopwatch */
    function stopStopwatch() {
        // Clear interval
        if (typeof elapsedTimeIntervalRef !== "undefined") {
            clearInterval(elapsedTimeIntervalRef);
            elapsedTimeIntervalRef = undefined;
        }

        // Store the elapsed time on pause
        storeElapsedTimeOnPause();
        // display the start button
        displayStartButton();
    }

    /** Stores the elapsed time hours, minutes and seconds details
     * on pause*/
    function storeElapsedTimeOnPause() {
        // Break down elapsed time from display test
        const brokenDownElapsedTime = elapsedTimeText.innerText.split(":");
        // Convert list to numbers
        const brokenDownElapsedTimeAsNumbers = brokenDownElapsedTime.map(numberAsString => parseInt(numberAsString));
        // Store the hours minutes and seconds from that time
        elapsedTimeWhenPaused = {
            hours: brokenDownElapsedTimeAsNumbers.length === 3 ? brokenDownElapsedTimeAsNumbers[0] : 0,
            minutes: brokenDownElapsedTimeAsNumbers.length === 3 ? brokenDownElapsedTimeAsNumbers[1] : brokenDownElapsedTimeAsNumbers[0],
            seconds: brokenDownElapsedTimeAsNumbers.length === 3 ? brokenDownElapsedTimeAsNumbers[2] : brokenDownElapsedTimeAsNumbers[1]
        }
    }

    /** Resets stopwatch */
    function resetStopwatch() {
        // Clear interval
        if (typeof elapsedTimeIntervalRef !== "undefined") {
            clearInterval(elapsedTimeIntervalRef);
            elapsedTimeIntervalRef = undefined;
        }

        // Reset elapsed time when paused object
        elapsedTimeWhenPaused = undefined

        // display the start button
        displayStartButton();
        // Reset elapsed time text
        elapsedTimeText.innerText = "00:00:00";
    }

//API for time and date functions

    var timeAndDateHandling = {
        /** Computes the elapsed time since the moment the function is called in the format mm:ss or hh:mm:ss
         * @param {String} startTime - start time to compute the elapsed time since
         * @returns {String} elapsed time in mm:ss format or hh:mm:ss format if elapsed hours are 0.
         */
        getElapsedTime: function (startTime) {

            // Record end time
            let endTime = new Date();
            // Compute time difference in milliseconds
            let timeDiff = endTime.getTime() - startTime.getTime();
            // Convert time difference from milliseconds to seconds
            timeDiff = timeDiff / 1000;
            // Extract integer seconds that dont form a minute using %
            let seconds = Math.floor(timeDiff % 60); //ignoring uncomplete seconds (floor)

            // Pad seconds with a zero if neccessary
            let secondsAsString = seconds < 10 ? "0" + seconds : seconds + "";
            // Convert time difference from seconds to minutes using %
            timeDiff = Math.floor(timeDiff / 60);
            // Extract integer minutes that don't form an hour using %
            let minutes = timeDiff % 60; //no need to floor possible incomplete minutes, becase they've been handled as seconds

            // Pad minutes with a zero if neccessary
            let minutesAsString = minutes < 10 ? "0" + minutes : minutes + "";
            // Convert time difference from minutes to hours
            timeDiff = Math.floor(timeDiff / 60);
            // Extract integer hours that don't form a day using %
            let hours = timeDiff % 24; //no need to floor possible incomplete hours, becase they've been handled as seconds

            // Convert time difference from hours to days
            timeDiff = Math.floor(timeDiff / 24);
            // The rest of timeDiff is number of days
            let days = timeDiff;
            let totalHours = hours + (days * 24); // add days to hours
            let totalHoursAsString = totalHours < 10 ? "0" + totalHours : totalHours + "";
            if (totalHoursAsString === "00") {
                return totalHoursAsString + ":" + minutesAsString + ":" + secondsAsString;
            } else {
                return totalHoursAsString + ":" + minutesAsString + ":" + secondsAsString;
            }
        }
    }


</script>