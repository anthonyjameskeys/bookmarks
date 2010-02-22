<!--
  Copyright 2010 Anthony Chaves
  
  This file is part of Bookmarks.

  Bookmarks is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  Bookmarks is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with Bookmarks.  If not, see <http://www.gnu.org/licenses/>.
-->
<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="/bookmarks/jquery-1.4.1.js"></script>
<script type="text/javascript" src="/bookmarks/jquery-ui-1.7.2.custom.min.js"></script>

<script type="text/javascript">
  $(function() {
    
    delete_bookmark = function(elm) {
      var bId = elm.find('.delete_button')[0].id;
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
                                      
  });
</script>

<style type="text/css">
  .drophover {
    background-color: LightGreen;
  }
  
  .drophovertrash {
    background-color: Tomato;
  }
  
  .droppable {
    padding: 5px;
  }
</style>