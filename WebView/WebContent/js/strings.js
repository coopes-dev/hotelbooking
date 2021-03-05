

var Strings=null;

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function translateText(content) {
	if (serverInfo.testMode) return(content);
	var ret="";
	var nextString="";
	var escape=false;
	var readingString=false;
	for (const letter of content) {
		  if (letter=='\\') {
			  escape=true;
			  continue;
		  }
		  if (!escape) {
		     if (letter=='{') {
			     reading=true;
			     nextString="";	// clear out string
			     continue;	// filter delimiters out
		     }
		     if (letter=='}') {
		    	 if (reading) {
		    		 if (Strings[nextString]) {
		                 ret=ret+Strings[nextString];// add new string to outpot
		              } else {
		            	 ret=ret+"{"+nextString+"}";
		              }
		              reading=false;
		              nextString="";
		    	 }
		    	 continue;	// filter delimiters out
		     }
		  }
		  esxape=false;	// escape used up now
		  if (reading) {
			  nextString=nextString+letter;
		  } else {
			  ret=ret+letter;		// outside { }  these are literal, no translate
		  }
		     
    }
	return(ret);
}

function translateText1(content) {
    if (content) {
        if ((content.substring(0, 1) == '{') && (content.substring(content.length - 1) == '}')) {
            var key = content.substring(1, content.length - 1);
            if (Strings[key]) {
               return (Strings[key]);
            }
            return(content);
        }
    }
    return(content);
}


function translateText2(content) {
    if (content) {
        if ((content.substring(0, 1) == '{') && (content.substring(content.length - 1) == '}')) {
            var key = content.substring(1, content.length - 1);
            if (Strings[key]) {
               return (Strings[key]);
            }
            return(content);
        }
    }
    return(content);
}


var doneTranslate=false;
function doTranslate() {
    doneTranslate=true;
    $(".translated").each(function () {
        var o=$(this)[0];
        var tn=o.tagName;
        var content="";
        if (tn=="INPUT") {
        	if (o.placeholder!=null) {
        		content=o.placeholder;
        		content=translateText(content);
        		o.placeholder=content;
        	}
            content=$(this).val();
        } else {
            content=$(this).html();
        }
        var content=translateText(content);
        //if (content) {
        	if (tn=="INPUT") {
                $(this).val(content)
            } else {
                $(this).html(content);
            }
            var t=jQuery.type($(this));

        //}
        $(this).css('visibility','visible');
    });


}

function translatePage() {
    var lang=getParameterByName("lang");
    setTimeout(function() {
        if (!doneTranslate) {
            alert("Translate failed, perhaps there is a problem with "+"js/language/strings."+lang);
            Strings=new Object();
            doTranslate();
        }

    },2000);
    if (lang==null) lang="en";  // default
    $.getJSON("js/language/strings."+lang, function(json,status) {
            Strings=json;
            doTranslate(Strings)

    });


}



