function(){
  url = encodeURI(window.location);
  targetUrl = 'http://localhost:8080/bookmarks/b/bookmarks/new?url=' + url; 
  window.open(targetUrl,'QuickBookmark','width=400,height=400,location=yes,links=no,scrollbars=no,toolbar=no');
}