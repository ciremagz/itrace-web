
var tags = [];

function pushTag(tag){
	tags.push(tag);
	console.log(tags);
	document.getElementById("tags").value = tags;
}

function songQueue(queue){
	console.log("Running songQueue().")
	console.log("Songs enqueue:")
	for(i=0;i<queue.length;i++){
		console.log(queue[i].getSongTitle());
	}
}

function showLyrics(value){
	console.log("value: "+value);
	if(value == 'false'){
		document.getElementById('lyrics').hidden = "true";
		document.getElementById('showLyricsButton').value = "true";
	}else{
		document.getElementById('lyrics').hidden = "true";
		document.getElementById('showLyricsButton').value = "true";
	}
}
