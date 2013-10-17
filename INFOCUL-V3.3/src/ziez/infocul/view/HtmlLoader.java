
package ziez.infocul.view;

final class HtmlLoader {
	protected static final String LS = System.getProperty("line.separator");
	
	  public static String goDirection(String fromLatLong, String toPlace){
	  
	  
	  final String htmlContent =
			"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"														" +
			"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">																" +
			"<html xmlns=\"http://www.w3.org/1999/xhtml\"\r\n\txmlns:v=\"urn:schemas-microsoft-com:vml\">						" +
			"<head>																												" +
			"<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />											" +
			"<style type=\"text/css\">  																										"+
			"html, body {  																												"+								
			"	  height: 100%;  																									"+														
			"	  font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;																"+
			"	  font-size: small;																									"+
			"	  margin: 0;																												"+
			"	}																																		"+
			"</style>																											"+
			"<script type=\"text/javascript\"																					" +
			"	src=\"http://maps.google.com/maps?file=api&amp;v=2.129e&amp;													" +
			"	key=ABQIAAAAjU0EJWnWPMv7oQ-jjS7dYxSPW5CJgpdgO_s4yyMovOaVh_KvvhSfpvagV18eOyDWu7VytS6Bi1CWxw\">					" +
			"</script>																											" +
			"<script type=\"text/javascript\">																 					" +
			"  	var map;																										" +
			"	var directionsPanel;																							" +
			"	var directions;																									" +
			"	function initialize() {																							" +
			"      map = new GMap2(document.getElementById(\"map_canvas\"));													" +
			"      map.setCenter(new GLatLng(-7.283636,112.733434), 15);														" +
			"      directionsPanel = document.getElementById(\"route\");														" +
			"      directions = new GDirections(map, directionsPanel);															" +
			"      directions.load(\" from: "+fromLatLong+" to: "+toPlace+" \", {avoidHighways: true});							" +
			"    }																												" +
			"</script>																											" +
			"</head>																											" +
			"<body onload=\"initialize()\">																						" +
			"<div style=\"position:relative; width: 100%px; height: 100%;\">										" +
			"	<div id=\"map_canvas\" style=\"border-right:1px solid black; position:absolute; width:627px; height:632px;\"></div>	" +
			"	<div id=\"route\" style=\"position:absolute; left: 635px; width:255px; height:632px; overflow: auto\"></div>	" +
			"</div>																												" +
			"</body>																											" +
			"</html>";
	  return htmlContent;
  }
	  
 
	  public static String showAndLoadMap(String lat, String lang, String title) {
	        final String htmlContent =
	                "<html>" + LS
	                + " <head>" + LS
	                + " <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />" + LS
	                + " <style type=\"text/css\">" + LS
	                + "   html { height: 100% }" + LS
	                + "   body { height: 100%; margin: 0; padding: 0 }" + LS
	                + "   #map_canvas { height: 100% }" + LS
	                + " </style>" + LS
	                + " <script type=\"text/javascript\"" + LS
	                + "   src=\"http://maps.googleapis.com/maps/api/js?key=AIzaSyDDmWyAXfxovL2ZG8lrsQmAar4BzPedVKQ&sensor=true\">" + LS
	                + " </script>" + LS
	                + " <script type=\"text/javascript\">" + LS
	                + "   function initialize(lat,lag) {" + LS
	                + "    var myOptions = {" + LS
	                + "      center: new google.maps.LatLng(lat,lag)," + LS
	                + "     zoom: 15," + LS
	                + "     mapTypeId: google.maps.MapTypeId.ROADMAP" + LS
	                + "   };" + LS
	                + "   var map = new google.maps.Map(document.getElementById(\"map_canvas\")," + LS
	                + "       myOptions);" + LS
	                + " var marker = new google.maps.Marker({" + LS
	                + "  position: new google.maps.LatLng(lat,lag)," + LS
	                + "  title: \"" +  title + "\"," + LS
	                + "  map: map" + LS
	                
	                + "});" + LS
	                + " }" + LS
	                + " </script>" + LS
	                + " </head> " + LS
	                + "  <body onload=\"initialize(" + lat + "," + lang + ")\">" + LS
	                + "    <div id=\"map_canvas\" style=\"width:100%; height:100%\"></div>" + LS
	                + "  </body>" + LS
	                + "</html>";
	        
	        return htmlContent;
	        
	    }
}
