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
                      $.ajax( {type: "POST",
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
                                }
                              });
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

  format_tags = function(tags) {
    s_tags = tags.split(",");
    for (var i=0; i < s_tags.length; i++) {
      s_tags[i] = "<a href=\"/bookmarks/b/tags?tag=" + s_tags[i] + "\">" + s_tags[i] + "</a>";
    }
    return s_tags.join("&nbsp;&nbsp");
  };

  submit_tags = function(id, elm, parent) {
    var tags = elm.find("#tags")[0].value;
    var bookmark_tags = parent.find(".bookmark_tags")[0];

    $.ajax( {type: "POST",
              url: "/bookmarks/b/bookmarks.json",
              data: "_method=put&id=" + id + "&tags=" + encodeURI(tags),
              success: function(data, textStatus) {
                if (data["bookmark"]["tags"] == "") {
                  bookmark_tags.innerHTML = "<span class=\"no_tags\">Click to tag</span>";
                } else {
                  bookmark_tags.innerHTML = format_tags(data["bookmark"]["tags"]);
                }
                elm.fadeOut("fast", function() {$(elm[0].parentNode).remove(elm); $(bookmark_tags).fadeIn("fast");});
              },
              error: function(request, settings) {
                alert("you screwed up\n"+request.responseText);
              }
            });
  };
  
  $(".bookmark_tags").click(function() {
                              var parent = $(this.parentNode);
                              var bId = parent.find('.bookmark_content')[0].id;
                              
                              $(".bookmark_tags_form").fadeOut("slow",  function() {
                                                                          $(this.parentNode).find(".bookmark_tags").fadeIn("slow");
                                                                        });

                              
                              elm = $("<div></div>").addClass("bookmark_tags_form").hide();
                              text_field = $("<input type=\"text\" id=\"tags\" name=\"tags\" size=\"35\"/>");
                              if (this.innerText != "Click to tag") {
                                text_field[0].value = this.innerText;
                              }
                              elm.append(text_field);

                              submit_button = $("<input type=\"submit\" name=\"submit\" value=\"update\"/>");
                              submit_button.click(function() {submit_tags(bId, elm, parent);});
                              elm.append(submit_button);

                              elm.appendTo(parent);
                              $(this).fadeOut("fast", function() {elm.fadeIn("fast");});
                            });                                    
});
