
var songs = [];

function addToPlaylist(id){
	songs.push(id);
	console.log(songs);
	document.getElementById("songIdList").value = songs;
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
