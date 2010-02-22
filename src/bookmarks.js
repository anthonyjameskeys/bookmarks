$(function() {

 $("#remember_me_button").click(function(msg) {
                                  $.ajax({type: "POST",
                                          url: "/bookmarks/b/tokens.json",
                                          data: "",
                                          dataType: "json",
                                          error:  function(request, settings) {
                                                    alert("Uh oh, something went wrong.  We've been notified of the problem and will fix it ASAP.  Please try again later.");
                                                  },
                                          success:  function(data, textStatus) { 
                                                      if (data["status"] != "success") {
                                                        alert("Uh oh, something went wrong.  We've been notified of the problem and will fix it ASAP.  Please try again later.");
                                                      }
                                                      
                                                    }
                                          });
                                        });
  
 $("#new_api_token_button").click(function(msg) {
                                    $.ajax({type: "POST",
                                        url: "/bookmarks/b/apiKey.json",
                                        data: "",
                                        dataType: "json",
                                        error:  function(request, settings) {
                                                  alert("Uh oh, something went wrong.  We've been notified of the problem and will fix it ASAP.  Please try again later.");
                                                },
                                        success:  function(data, textStatus) { 
                                                    if (data["status"] != "success" || data["newKey"] == "") {
                                                      alert("Uh oh, something went wrong.  We've been notified of the problem and will fix it ASAP.  Please try again later.");
                                                    } else {
                                                      $("#api_token")[0].innerHTML = data["newKey"];
                                                    }
                                                  }
                                        });
                                      });
  
  delete_bookmark = function(elm) {
    var bId = elm.find('.bookmark_content')[0].id;
    $.ajax({type: "POST",
           url: "/bookmarks/b/bookmarks.json",
           data: "_method=delete&bookmarkId=" + bId,
           success: function(msg) {
             elm.slideUp();
             var count_span = $('#bookmark_count')[0];
             var count = count_span.innerHTML;
             count_span.innerHTML = --count;
           },
           error: function(request, settings) {
             alert("you screwed up\n"+request.responseText);
           }});
  };
  
  $('.bookmark_container').draggable({revert: false, opacity: 0.7, helper: 'clone', containment: "document"});

  $('#droppable_trash').droppable({ drop: function(event, ui) {
                                            delete_bookmark(ui.draggable);
                                          },
                                    tolerance: 'pointer',
                                    hoverClass: 'drophovertrash'
                                  });


  $('#droppable_facebook').droppable({drop: function(event, ui) {
                                              var url = encodeURI($(ui.draggable).find(".bookmark_link")[0]);
                                              var title = encodeURI($(ui.draggable).find(".bookmark_link")[0].text);
                                              window.open("http://www.facebook.com/sharer.php?u=" + url + "&t=" + title, "share_window", "width=800, height=500");
                                            },
                                      tolerance: 'pointer', 
                                      hoverClass: 'drophover'
                                    });

  submit_tags = function(id, tags) {
    
  };

  $(".bookmark_container").click( function() {
                                    var elm = $(this).find(".tag_form")[0];
                                    if (elm == null) {
                                      elm = $("<div></div>").addClass("tag_form").hide();
                                      text_field = $("<input type=\"text\" id=\"tags\" name=\"tags\"/>");
                                      elm.appent(text_field);
                                      img = $("<img src=\"/bookmarks/tag_submit.png\"");
                                      img.click(submit_tags(ID, TAGS));
                                      
                                      $(this).append(elm);
                                    }
                                    $(elm).slideToggle("slow");
                                  });                                    
});
